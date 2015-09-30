package utilitario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import componente.Menssage;
import dao.CampeonatoTimeDao;
import dao.EntityManagerLocal;
import entidade.Campeonato;
import entidade.CampeonatoTime;
import entidade.Grupo;
import entidade.Partida;
import entidade.Time;
import entidade.TimePartida;

public class GerenciadorPartida {

	public static boolean adicionarPatidas(Campeonato campeonato) {
		try {
			List<Time> listaTime = CampeonatoTimeDao
					.getListaCampeonatoListaTime(campeonato
							.getCodigoCampeonato());
			if (listaTime != null && listaTime.size() > 0) {
				if (campeonato.getChave().getCodigoChave() == 1 || campeonato.getChave().getCodigoChave() == 2) {
					// Winer - Lower  MATA MATA
					gerarPartidasMataMata( listaTime, campeonato, false);
				} else if (campeonato.getChave().getCodigoChave() == 3) {
					// Grupo
					
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

	public static void gerarPartidasMataMata(List<Time> listaTime,
			Campeonato campeonato, boolean winerLower) {
		List<Time> listaTimeA = new ArrayList<Time>();
		List<Time> listaTimeB = new ArrayList<Time>();
		List<Partida> listaPartida = new ArrayList<Partida>();
		List<Partida> listaPartidaA = new ArrayList<Partida>();
		List<Partida> listaPartidaB = new ArrayList<Partida>();
		List<Partida> listaPartidaAFilho = new ArrayList<Partida>();
		List<Partida> listaPartidaBFilho = new ArrayList<Partida>();

		boolean timeA = true;
		for (int i = 0; i < listaTime.size(); i++) {
			Random gerador = new Random();
			int numero = gerador.nextInt(listaTime.size());
			if (timeA) {
				listaTimeA.add(listaTime.get(numero));
				timeA = false;
			} else {
				listaTimeB.add(listaTime.get(numero));
				timeA = true;
			}
			listaTime.remove(numero);
			i = 0;
		}

		if (listaTimeA.size() > listaTimeB.size()) {
			listaTimeB.add(new Time());
		} else if (listaTimeA.size() < listaTimeB.size()) {
			listaTimeA.add(new Time());
		}

		EntityManagerLocal.begin();
		for (int i = 0; i < listaTimeA.size(); i++) {

			Partida partida = new Partida();
			partida.setAtivo(true);
			partida.setCampeonato(campeonato);
			partida.setCancelada(false);
			partida.setWinerLower(winerLower);
			EntityManagerLocal.persist(partida);

			TimePartida tpA = new TimePartida();
			tpA.setPartida(partida);
			tpA.setTime(listaTimeA.get(i));
			EntityManagerLocal.persist(tpA);

			TimePartida tpB = new TimePartida();
			tpB.setPartida(partida);
			tpB.setTime(listaTimeB.get(i));
			EntityManagerLocal.persist(tpB);

			listaPartida.add(partida);
		}
		EntityManagerLocal.commit();
		EntityManagerLocal.clear();
		if (listaPartida.size() > 1) {
			boolean partidaA = true;
			if (listaPartida.size() % 2 == 0) {
				for (int i = 0; i < listaPartida.size(); i++) {
					if (partidaA) {
						listaPartidaA.add(listaPartida.get(i));
						partidaA = false;
					} else {
						listaPartidaB.add(listaPartida.get(i));
						partidaA = true;
					}
				}
			} else {
				for (int i = 0; i < listaPartida.size() - 1; i++) {
					if (partidaA) {
						listaPartidaA.add(listaPartida.get(i));
						partidaA = false;
					} else {
						listaPartidaB.add(listaPartida.get(i));
						partidaA = true;
					}
				}
				listaPartidaB.add(listaPartida.get(listaPartida.size() - 1));
			}
		}
		
		if (listaPartidaA.size() > 1) {
			listaPartidaA = criarPartidasMataMataFilho(listaPartidaA, campeonato, winerLower);
		}
		

		if (listaPartidaB.size() > 1) {
			listaPartidaB = criarPartidasMataMataFilho(listaPartidaB, campeonato, winerLower);
		}
		
		EntityManagerLocal.begin();
		Partida partida = new Partida();
		partida.setAtivo(true);
		partida.setCampeonato(campeonato);
		partida.setCancelada(false);
		EntityManagerLocal.persist(partida);
		
		TimePartida tpA = new TimePartida();
		tpA.setPartida(partida);
		EntityManagerLocal.persist(tpA);
		
		listaPartidaA.get(0).setPartidaFilho(partida);
		EntityManagerLocal.merge(listaPartidaA.get(0));
		listaPartidaB.get(0).setPartidaFilho(partida);
		EntityManagerLocal.merge(listaPartidaB.get(0));
		EntityManagerLocal.commit();
		EntityManagerLocal.clear();
	}

	public static List<Partida> criarPartidasMataMataFilho(List<Partida> lista, Campeonato campeonato, boolean winerLower) {
		List<Partida> novaLista = new ArrayList<Partida>();
		if (lista.size() > 1) {
			if (lista.size() % 2 == 0) {
				EntityManagerLocal.begin();
				for (int i = 0; i < lista.size(); i++) {
					EntityManagerLocal.begin();
					Partida partida = new Partida();
					partida.setAtivo(true);
					partida.setCampeonato(campeonato);
					partida.setCancelada(false);
					EntityManagerLocal.persist(partida);
					
					TimePartida tpA = new TimePartida();
					tpA.setPartida(partida);
					EntityManagerLocal.persist(tpA);

					lista.get(i).setPartidaFilho(partida);
					EntityManagerLocal.merge(lista.get(i));
					i++;
					lista.get(i).setPartidaFilho(partida);
					EntityManagerLocal.merge(lista.get(i));
					EntityManagerLocal.commit();
					EntityManagerLocal.clear();
					novaLista.add(partida);
				}
				return criarPartidasMataMataFilho(novaLista, campeonato, winerLower);
			} else {
				for (int i = 0; i < lista.size() - 1; i++) {
					EntityManagerLocal.begin();
					Partida partida = new Partida();
					partida.setAtivo(true);
					partida.setCampeonato(campeonato);
					partida.setCancelada(false);
					EntityManagerLocal.persist(partida);
					
					TimePartida tpA = new TimePartida();
					tpA.setPartida(partida);
					EntityManagerLocal.persist(tpA);

					lista.get(i).setPartidaFilho(partida);
					EntityManagerLocal.merge(lista.get(i));
					i++;
					lista.get(i).setPartidaFilho(partida);
					EntityManagerLocal.merge(lista.get(i));
					EntityManagerLocal.commit();
					EntityManagerLocal.clear();
					novaLista.add(partida);
				}
				EntityManagerLocal.begin();
				Partida partida = new Partida();
				EntityManagerLocal.persist(partida);
				
				TimePartida tpA = new TimePartida();
				tpA.setPartida(partida);
				EntityManagerLocal.persist(tpA);
				
				Partida filho = lista.get(lista.size()-2).getPartidaFilho();
				filho.setPartidaFilho(partida);
				EntityManagerLocal.merge(filho);
				
				lista.get(lista.size()-1).setPartidaFilho(partida);
				EntityManagerLocal.merge(lista.get(lista.size()-1));
				EntityManagerLocal.commit();
				EntityManagerLocal.clear();
				novaLista.add(partida);
				return criarPartidasMataMataFilho(novaLista, campeonato, winerLower);
			}
		}

		return lista;
	}

	public static void gerarPartidasWinerLower(List<CampeonatoTime> listaTime) {

	}

}
