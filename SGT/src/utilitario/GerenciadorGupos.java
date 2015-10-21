package utilitario;

import java.util.ArrayList;
import java.util.List;

import componente.Menssage;
import dao.CampeonatoTimeDao;
import dao.EntityManagerLocal;
import dialog.DialogGrupoSelectTime;
import entidade.Campeonato;
import entidade.Grupo;
import entidade.Time;
import entidade.TimeGrupo;

public class GerenciadorGupos {

	public static boolean gerarGrupos(Campeonato campeonato) {
		try {
			List<Time> listaTime = CampeonatoTimeDao
					.getListaCampeonatoListaTime(campeonato
							.getCodigoCampeonato());
			if (listaTime != null && listaTime.size() >= 12) {

				int quantidadeGrupo = getQuantidadeGrupo(listaTime);
				List<Grupo> listaGrupo = new ArrayList<Grupo>();

				for (int i = 0; i < quantidadeGrupo; i++) {
					EntityManagerLocal.begin();
					Grupo grupo = new Grupo();
					grupo.setCampeonato(campeonato);
					grupo.setAtivo(true);
					EntityManagerLocal.persist(grupo);
					EntityManagerLocal.commit();
					EntityManagerLocal.clear();
					listaGrupo.add(grupo);

					DialogGrupoSelectTime dialogTime = new DialogGrupoSelectTime();
					dialogTime.getGrupoSelectTime(grupo, campeonato,
							Parametros.getPai(), i + 1, quantidadeGrupo);

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
				}

				listaTime = CampeonatoTimeDao
						.getListaCampeonatoListaTimeSemGrupo(campeonato
								.getCodigoCampeonato());
				
				boolean continua = true;
				while (continua) {
					if (listaTime.size() > 0) {
						for (Grupo grupo : listaGrupo) {
							try {
								EntityManagerLocal.begin();
								TimeGrupo timeGrupo = new TimeGrupo();
								timeGrupo.setTime(listaTime.get(0));
								timeGrupo.setGrupo(grupo);
								timeGrupo.setClasse(3);
								timeGrupo.setAtivo(true);
								EntityManagerLocal.persist(timeGrupo);
								EntityManagerLocal.commit();
								EntityManagerLocal.clear();
								listaTime.remove(0);
							} catch (Exception e) {
								EntityManagerLocal.clear();
							}
						}
					} else {
						continua = false;
					}
				}

				return true;
			}
		} catch (Exception e) {
			Menssage.setMenssage("Erro",
					"Erro ao criar partidas\nEntrar em contato com o suporte!",
					ParametroCrud.getModoErro(), Parametros.getPai());
			return false;
		}
		return false;
	}

	private static int getQuantidadeGrupo(List<Time> listaTime) {
		int quantidade = 0;
		quantidade = listaTime.size() / 3;
		return quantidade;
	}

}
