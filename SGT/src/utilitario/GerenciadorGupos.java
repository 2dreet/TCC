package utilitario;

import java.util.ArrayList;
import java.util.List;

import componente.Menssage;
import dao.CampeonatoDao;
import dao.CampeonatoTimeDao;
import dao.EntityManagerLocal;
import dao.GrupoDao;
import dialog.DialogGrupoSelectTime;
import entidade.Campeonato;
import entidade.Grupo;
import entidade.Partida;
import entidade.Time;
import entidade.TimeGrupo;
import entidade.TimePartida;

public class GerenciadorGupos {

	public static boolean gerarGrupos(Campeonato campeonato) {
		try {
			List<Time> listaTime = CampeonatoTimeDao
					.getListaCampeonatoListaTimeSemGrupo(campeonato
							.getCodigoCampeonato());
			if (listaTime != null && listaTime.size() >= 12) {

				List<Time> listaTimeSobrando = new ArrayList<Time>();
				int resto = (listaTime.size() % 4);
				int quantidadeGrupo = (listaTime.size() / 4);

				List<Grupo> listaGrupo = new ArrayList<Grupo>();
				for (int i = 0; i < quantidadeGrupo; i++) {
					listaGrupo.add(obterGrupo(campeonato));
				}

				if (resto > 0) {
					listaGrupo.add(obterGrupo(campeonato));
				}

				obterSeeds(listaGrupo, campeonato);

				listaTime = CampeonatoTimeDao
						.getListaCampeonatoListaTimeSemGrupo(campeonato
								.getCodigoCampeonato());
				boolean continua = true;
				while (continua) {
					if (listaTime.size() > 0) {
						while (listaTime.size() != 0) {
							for (Grupo grupo : listaGrupo) {
								if (listaTime.size() > 0) {
									criarTimeGrupo(listaTime.get(0), grupo);
									listaTime.remove(0);
								}
							}
						}
					} else {
						continua = false;
					}
				}
				
				for (Grupo grupo : listaGrupo) {
					int indice = 1;
					List<TimeGrupo> listaGrupoTime = GrupoDao.getListaTimeGrupo(grupo.getCampeonato().getCodigoCampeonato(), grupo.getCodigoGrupo());
					if(listaGrupoTime.size() == 3){
						gerarPartidaGrupo(listaGrupoTime.get(0).getTime(), listaGrupoTime.get(1).getTime(), grupo, indice);
						indice++;
						gerarPartidaGrupo(listaGrupoTime.get(0).getTime(), listaGrupoTime.get(2).getTime(), grupo, indice);
						indice++;
						gerarPartidaGrupo(listaGrupoTime.get(1).getTime(), listaGrupoTime.get(2).getTime(), grupo, indice);
					} else if(listaGrupoTime.size() == 4){
						gerarPartidaGrupo(listaGrupoTime.get(0).getTime(), listaGrupoTime.get(1).getTime(), grupo, indice);
						indice++;
						gerarPartidaGrupo(listaGrupoTime.get(0).getTime(), listaGrupoTime.get(2).getTime(), grupo, indice);
						indice++;
						gerarPartidaGrupo(listaGrupoTime.get(0).getTime(), listaGrupoTime.get(3).getTime(), grupo, indice);
						indice++;
						gerarPartidaGrupo(listaGrupoTime.get(1).getTime(), listaGrupoTime.get(2).getTime(), grupo, indice);
						indice++;
						gerarPartidaGrupo(listaGrupoTime.get(1).getTime(), listaGrupoTime.get(3).getTime(), grupo, indice);
						indice++;
						gerarPartidaGrupo(listaGrupoTime.get(2).getTime(), listaGrupoTime.get(3).getTime(), grupo, indice);
					}
				}
				
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Menssage.setMenssage("Erro",
					"Erro ao criar partidas\nEntrar em contato com o suporte!",
					ParametroCrud.getModoErro(), Parametros.getPai());
			return false;
		}
		return false;
	}

	public static void gerarPartidaGrupo(Time time1, Time time2, Grupo grupo, int indice){
		EntityManagerLocal.begin();
		Partida partida = new Partida();
		partida.setAtivo(true);
		partida.setCampeonato(grupo.getCampeonato());
		partida.setCancelada(false);
		partida.setIndice(indice);
		partida.setGrupo(grupo);
		EntityManagerLocal.persist(partida);

		TimePartida tp = new TimePartida();
		tp.setPartida(partida);
		tp.setTime1(time1);
		tp.setTime2(time2);
		
		EntityManagerLocal.persist(tp);
		EntityManagerLocal.commit();
		EntityManagerLocal.clear();
	}
	
	public static void criarTimeGrupo(Time time, Grupo grupo) {
		EntityManagerLocal.begin();
		TimeGrupo timeGrupo = new TimeGrupo();
		timeGrupo.setTime(time);
		timeGrupo.setGrupo(grupo);
		timeGrupo.setClasse(3);
		timeGrupo.setAtivo(true);
		EntityManagerLocal.persist(timeGrupo);
		EntityManagerLocal.commit();
		EntityManagerLocal.clear();
	}

	public static Grupo obterGrupo(Campeonato campeonato) {
		EntityManagerLocal.begin();
		Grupo grupo = new Grupo();
		grupo.setCampeonato(campeonato);
		grupo.setAtivo(true);
		EntityManagerLocal.persist(grupo);
		EntityManagerLocal.commit();
		EntityManagerLocal.clear();
		return grupo;
	}

	public static void obterSeeds(List<Grupo> listaGrupo, Campeonato campeonato) {
		int i = 1;
		for (Grupo grupo : listaGrupo) {
			DialogGrupoSelectTime dialogTime = new DialogGrupoSelectTime();
			dialogTime.getGrupoSelectTime(grupo, campeonato,
					Parametros.getPai(), i, listaGrupo.size());
			if (dialogTime.getSeedA() == null || dialogTime.getSeedB() == null) {
				obterSeeds(listaGrupo, campeonato);
			}
			EntityManagerLocal.begin();
			TimeGrupo timeGrupo = new TimeGrupo();
			timeGrupo.setTime(dialogTime.getSeedA());
			timeGrupo.setGrupo(grupo);
			timeGrupo.setAtivo(true);
			timeGrupo.setClasse(1);
			EntityManagerLocal.persist(timeGrupo);

			timeGrupo = new TimeGrupo();
			timeGrupo.setTime(dialogTime.getSeedB());
			timeGrupo.setGrupo(grupo);
			timeGrupo.setAtivo(true);
			timeGrupo.setClasse(2);
			EntityManagerLocal.persist(timeGrupo);
			EntityManagerLocal.commit();
			EntityManagerLocal.clear();
			i++;
		}
	}

	public static void main(String[] args) {
		gerarGrupos(CampeonatoDao.getCampeonato(1));
	}
}
