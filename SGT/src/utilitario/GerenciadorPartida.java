package utilitario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import componente.Menssage;
import dao.CampeonatoTimeDao;
import dao.EntityManagerLocal;
import dao.PartidaDao;
import dao.TimeDao;
import dialog.DialogLocalizarNovaChave;
import entidade.Campeonato;
import entidade.CampeonatoTime;
import entidade.Grupo;
import entidade.Partida;
import entidade.Time;
import entidade.TimePartida;

public class GerenciadorPartida {

	private static Campeonato camp;

	public static boolean adicionarPatidas(Campeonato campeonato) {
		camp = campeonato;
		try {
			if (campeonato.getChave().getCodigoChave() == 1
					|| campeonato.getChave().getCodigoChave() == 2) {
				List<Time> listaTime = CampeonatoTimeDao
						.getListaCampeonatoListaTime(campeonato
								.getCodigoCampeonato());
				if (listaTime != null && listaTime.size() > 0) {
					// Mata - Mata
					gerarPartidasMataMata(listaTime);
					if(campeonato.getChave().getCodigoChave() == 2){
						gerarLowers(campeonato);
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Menssage.setMenssage("Erro",
					"Erro ao criar partidas\nEntrar em contato com o suporte!",
					ParametroCrud.getModoErro(), Parametros.getPai());
			return false;
		}
	}

	public static boolean adicionarPatidasDeGrupo(Campeonato campeonato,
			JPanel pai) {

		DialogLocalizarNovaChave dialog = new DialogLocalizarNovaChave();
		dialog.localizarChave(pai, campeonato);
		if (dialog.isSelecionado() == null) {
			return adicionarPatidasDeGrupo(campeonato, pai);
		}
		camp = campeonato;
		try {

			// falta ordenar as lista dos times para pegar os colocados
			// E adicionar os pontos se caso for do tipo chave
			List<Time> listaTimePrimeiro = CampeonatoTimeDao
					.getListaCampeonatoListaTime(campeonato
							.getCodigoCampeonato());

			List<Time> listaTimeSegundo = CampeonatoTimeDao
					.getListaCampeonatoListaTime(campeonato
							.getCodigoCampeonato());
			if (listaTimePrimeiro != null && listaTimePrimeiro.size() > 0
					&& listaTimeSegundo != null && listaTimeSegundo.size() > 0) {
				List<Partida> listaPartida = new ArrayList<Partida>();
				List<Partida> listaPartidaFilho = new ArrayList<Partida>();

				EntityManagerLocal.begin();
				int tamanhoTimes = listaTimePrimeiro.size();
				int indice = 1;
				for (int i = 0; i < tamanhoTimes; i++) {
					Partida partida = new Partida();
					partida.setAtivo(true);
					partida.setCampeonato(camp);
					if(campeonato.getChave().getCodigoChave() == 1){
						partida.setWinerLower(false);
					}else if(campeonato.getChave().getCodigoChave() == 2){
						partida.setWinerLower(true);
					}
					partida.setIndice(indice);
					EntityManagerLocal.persist(partida);

					TimePartida tp = new TimePartida();
					tp.setPartida(partida);
					tp.setTime1(listaTimePrimeiro.get(i));
					tamanhoTimes--;

					tp.setTime2(listaTimeSegundo.get(i));

					EntityManagerLocal.persist(tp);
					listaPartida.add(partida);
				}
				EntityManagerLocal.commit();
				EntityManagerLocal.clear();
				indice++;
				if (listaPartida.size() > 0) {
					criarPartidasMataMataFilho(listaPartida, indice);
				}
				
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Menssage.setMenssage("Erro",
					"Erro ao criar partidas\nEntrar em contato com o suporte!",
					ParametroCrud.getModoErro(), Parametros.getPai());
			return false;
		}
	}

	public static boolean gerarLowers(Campeonato campeonato) {
		try {
			camp = campeonato;
			List<Partida> listaPartida = new ArrayList<Partida>();
			Integer[] indices = PartidaDao
					.getIndicesPartidaParaGerarLowers(campeonato
							.getCodigoCampeonato());
			int i = 0;
			List<TimePartida> listaTime = PartidaDao
					.getPartidasPorIndiceParaGerarLowers(
							campeonato.getCodigoCampeonato(), 1);
			int tamanhoTimes = listaTime.size();
			TimePartida timeSobrando = null;
			if (tamanhoTimes % 2 != 0) {
				tamanhoTimes--;
				timeSobrando = listaTime.get(tamanhoTimes);
			}
			EntityManagerLocal.begin();
			for (int x = 0; x < tamanhoTimes; x++) {
				
				Partida partida1 = listaTime.get(0).getPartida();
				listaTime.remove(0);
				tamanhoTimes--;
				Partida partida2 = listaTime.get(0).getPartida();
				listaTime.remove(0);
				
				Partida partida = new Partida();
				partida.setAtivo(true);
				partida.setCampeonato(camp);
				partida.setWinerLower(true);
				partida.setIndice(indices[i]);
				
				TimePartida tp = new TimePartida();
				tp.setPartida(partida);
				
				partida1.setPartidaLower(partida);
				partida2.setPartidaLower(partida);
				
				String descricao = "L: ("+partida1.getDescricao()+")";
				descricao += " x P: ("+partida2.getDescricao()+")";
				
				partida.setDescricao(descricao);
				EntityManagerLocal.persist(partida);
				EntityManagerLocal.persist(tp);
				EntityManagerLocal.merge(partida1);
				EntityManagerLocal.merge(partida2);
				listaPartida.add(partida);
			}
			if (timeSobrando != null) {
				
				Partida partida1 = timeSobrando.getPartida();
				
				Partida partida = new Partida();
				partida.setAtivo(true);
				partida.setCampeonato(camp);
				partida.setWinerLower(true);
				partida.setIndice(indices[i]);
				
				String descricao = "P: "+partida1.getDescricao();
				
				TimePartida tp = new TimePartida();
				tp.setPartida(partida);
				
				partida.setDescricao(descricao);
				EntityManagerLocal.persist(partida);
				EntityManagerLocal.persist(tp);

				listaPartida.add(partida);
			}
			EntityManagerLocal.commit();
			EntityManagerLocal.clear();
			i++;
			if (listaPartida.size() > 0) {
				listaPartida = criarPartidasMataMataFilhoLower(listaPartida,
						indices[i], campeonato, i, indices);
				if (listaPartida.size() == 1) {
					List<TimePartida> listaTimeWinner = PartidaDao
							.getPartidasPorIndice(campeonato,
									indices[indices.length - 1], false);

					String descricao = "";
					
					Time timeVencedor = listaTimeWinner.get(0)
							.getTimeVencedor();
					Partida partidaFinalWinner = listaTimeWinner.get(0)
							.getPartida();
					descricao = "GW: ("+partidaFinalWinner.getDescricao()+")";
					
					Partida partidaFinalLower = listaPartida.get(0);
					descricao = " x GL: ("+partidaFinalLower.getDescricao()+")";
					
					
					EntityManagerLocal.begin();
					Partida partida = new Partida();
					partida.setAtivo(true);
					partida.setCampeonato(camp);
					partida.setDescricao(descricao);
					partida.setWinerLower(false);
					partida.setIndice(partidaFinalLower.getIndice() + 1);
					EntityManagerLocal.persist(partida);

					TimePartida tp = new TimePartida();
					tp.setPartida(partida);
					tp.setTime1(timeVencedor);
					EntityManagerLocal.persist(tp);

					partidaFinalWinner.setPartidaFilho(partida);
					EntityManagerLocal.merge(partidaFinalWinner);

					partidaFinalLower.setPartidaFilho(partida);
					EntityManagerLocal.merge(partidaFinalLower);

					EntityManagerLocal.commit();
					EntityManagerLocal.clear();
				}
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			Menssage.setMenssage("Erro",
					"Erro ao criar partidas\nEntrar em contato com o suporte!",
					ParametroCrud.getModoErro(), Parametros.getPai());
			return false;
		}
	}

	public static void gerarPartidasMataMata(List<Time> listaTime) {
		List<Partida> listaPartida = new ArrayList<Partida>();
		List<Partida> listaPartidaFilho = new ArrayList<Partida>();

		EntityManagerLocal.begin();
		int tamanhoTimes = listaTime.size();
		Time timeSobrando = null;
		if (tamanhoTimes % 2 != 0) {
			tamanhoTimes--;
			timeSobrando = listaTime.get(tamanhoTimes);
			listaTime.remove(tamanhoTimes);
		}
		int indice = 1;
		int x = 0;
		for (int i = 0; i < tamanhoTimes; i++) {
			Partida partida = new Partida();
			partida.setAtivo(true);
			partida.setCampeonato(camp);
			partida.setDescricao("P"+i);
			partida.setWinerLower(false);
			partida.setIndice(indice);
			EntityManagerLocal.persist(partida);

			int valor = getValorRandom(listaTime.size());
			TimePartida tp = new TimePartida();
			tp.setPartida(partida);
			tp.setTime1(listaTime.get(valor));
			listaTime.remove(valor);
			tamanhoTimes--;

			valor = getValorRandom(listaTime.size());
			tp.setTime2(listaTime.get(valor));
			listaTime.remove(valor);

			EntityManagerLocal.persist(tp);
			listaPartida.add(partida);
			x++;
		}

		if (timeSobrando != null) {
			Partida partida = new Partida();
			partida.setAtivo(true);
			partida.setCampeonato(camp);
			partida.setDescricao("P"+x);
			partida.setWinerLower(false);
			partida.setIndice(indice);
			EntityManagerLocal.persist(partida);

			TimePartida tp = new TimePartida();
			tp.setPartida(partida);
			tp.setTime1(timeSobrando);
			EntityManagerLocal.persist(tp);

			listaPartida.add(partida);
		}
		EntityManagerLocal.commit();
		EntityManagerLocal.clear();
		indice++;
		if (listaPartida.size() > 0) {
			criarPartidasMataMataFilho(listaPartida, indice);
		}
		List<Partida> p = PartidaDao.getPartidasApenas1Time(camp
				.getCodigoCampeonato());
		for (Partida partida : p) {
			EntityManagerLocal.begin();
			TimePartida tp = PartidaDao.getTimePartidaTime2Null(
					camp.getCodigoCampeonato(),
					partida.getCodigoPartida());
			partida.setHoraFim(new Date());
			partida.setAtivo(false);
			if(partida.getPartidaFilho() != null){
				TimePartida tpNew = PartidaDao.getTimePartidaTime2Null(
						camp.getCodigoCampeonato(),
						partida.getPartidaFilho().getCodigoPartida());
				tpNew.setTime1(tp.getTime1());
				EntityManagerLocal.merge(tpNew);
			}
			EntityManagerLocal.merge(partida);
			EntityManagerLocal.commit();
			EntityManagerLocal.clear();
		}
	}

	public static int getValorRandom(int valorMaximo) {
		int i = 0;
		Random gerador = new Random();
		i = gerador.nextInt(valorMaximo);
		return i;
	}

	public static List<Partida> criarPartidasMataMataFilho(List<Partida> lista,
			int indice) {
		List<Partida> novaLista = new ArrayList<Partida>();
		if (lista.size() > 1) {
			if (lista.size() % 2 == 0) {
				for (int i = 0; i < lista.size(); i++) {
					
					String descricao = "";
					Partida partida = new Partida();
					partida.setAtivo(true);
					partida.setCampeonato(camp);
					partida.setIndice(indice);
					
					if(camp.getChave().getCodigoChave() == 1){
						partida.setWinerLower(false);
					}else if(camp.getChave().getCodigoChave() == 2){
						partida.setWinerLower(true);
					}
					
					TimePartida tp = new TimePartida();
					tp.setPartida(partida);
					
					Partida partida1 = lista.get(i); 
					partida1.setPartidaFilho(partida);
					descricao = "W: ("+lista.get(i).getDescricao()+")";
					i++;
					
					Partida partida2 = lista.get(i); 
					partida2.setPartidaFilho(partida);
					descricao += " x W: ("+lista.get(i).getDescricao()+")";
					
					partida.setDescricao(descricao);
					
					EntityManagerLocal.begin();
					EntityManagerLocal.persist(partida);
					EntityManagerLocal.persist(tp);
					EntityManagerLocal.merge(partida1);
					EntityManagerLocal.merge(partida2);
					EntityManagerLocal.commit();
					EntityManagerLocal.clear();
					novaLista.add(partida);
				}
				indice++;
				return criarPartidasMataMataFilho(novaLista, indice);
			} else {
				for (int i = 0; i < lista.size() - 1; i++) {
					String descricao = "";
					Partida partida = new Partida();
					partida.setAtivo(true);
					partida.setCampeonato(camp);
					partida.setIndice(indice);
					if(camp.getChave().getCodigoChave() == 1){
						partida.setWinerLower(false);
					}else if(camp.getChave().getCodigoChave() == 2){
						partida.setWinerLower(true);
					}
					
					TimePartida tp = new TimePartida();
					tp.setPartida(partida);
					
					Partida partida1 = lista.get(i); 
					partida1.setPartidaFilho(partida);
					descricao = "W: ("+lista.get(i).getDescricao()+")";
					i++;
					
					Partida partida2 = lista.get(i); 
					partida2.setPartidaFilho(partida);
					descricao += " x W: ("+lista.get(i).getDescricao()+")";

					partida.setDescricao(descricao);
					
					EntityManagerLocal.begin();
					EntityManagerLocal.persist(partida);
					EntityManagerLocal.persist(tp);
					EntityManagerLocal.merge(partida1);
					EntityManagerLocal.merge(partida2);
					EntityManagerLocal.commit();
					EntityManagerLocal.clear();
					novaLista.add(partida);
				}
				Partida partida = lista.get(lista.size() - 1);
				partida.setIndice(indice);
				novaLista.add(partida);
				indice++;
				return criarPartidasMataMataFilho(novaLista, indice);
			}
		}
		return lista;
	}

	public static List<Partida> criarPartidasMataMataFilhoLower(
			List<Partida> lista, int indice, Campeonato campeonato,
			int indiceWinner, Integer[] indicesWinner) {
		List<Partida> novaLista = new ArrayList<Partida>();
		if (indiceWinner < indicesWinner.length) {
			List<TimePartida> listaTime = PartidaDao
					.getPartidasPorIndiceParaGerarLowers(
							campeonato.getCodigoCampeonato(),
							indicesWinner[indiceWinner]);
			Time timeSobrando = null;
			Partida partidaTimeSobrando = null;
			if (lista.size() > 1) {
				if (listaTime.size() < lista.size()) {
					// Gera partidas Intermediarias
					int contagemPartida = lista.size() - 1;
					String descricao = "";
					
					EntityManagerLocal.begin();
					Partida partida = new Partida();
					partida.setAtivo(true);
					partida.setCampeonato(camp);

					partida.setIndice(indice);
					partida.setWinerLower(true);
					
					TimePartida tp = new TimePartida();
					tp.setPartida(partida);
					
					Partida partida1 = lista.get(contagemPartida);
					partida1.setPartidaFilho(partida);
					descricao = "W: ("+partida1.getDescricao()+")";
					lista.remove(lista.get(contagemPartida));
					
					contagemPartida--;
					
					Partida partida2 = lista.get(contagemPartida);
					partida2.setPartidaFilho(partida);
					descricao += " x W: ("+partida2.getDescricao()+")";
					lista.remove(lista.get(contagemPartida));

					EntityManagerLocal.persist(partida);
					EntityManagerLocal.persist(tp);
					EntityManagerLocal.merge(partida1);
					EntityManagerLocal.merge(partida2);
					
					EntityManagerLocal.commit();
					EntityManagerLocal.clear();
					lista.add(partida);
					indice++;
				} else if (listaTime.size() > lista.size()) {
					// Arruma time que Sobrou
					int contagemTime = listaTime.size() - 1;
					timeSobrando = listaTime.get(contagemTime)
							.getTimePerdedor();
					partidaTimeSobrando = listaTime.get(contagemTime).getPartida();
					listaTime.remove(contagemTime);
				}

				for (int i = 0; i < lista.size(); i++) {
					String descricao = "";
					EntityManagerLocal.begin();
					Partida partida = new Partida();
					partida.setAtivo(true);
					partida.setCampeonato(camp);
					partida.setIndice(indice);
					partida.setWinerLower(true);

					Partida partidaWinner = listaTime.get(i).getPartida();
					partidaWinner.setPartidaLower(partida);
					descricao = "L: ("+partidaWinner.getDescricao()+")";
					
					TimePartida tp = new TimePartida();
					tp.setPartida(partida);
					tp.setTime1(listaTime.get(i).getTimePerdedor());
					if (timeSobrando != null && i == lista.size() - 1) {
						tp.setTime2(timeSobrando);
						partidaTimeSobrando.setPartidaLower(partida);
						EntityManagerLocal.merge(partidaTimeSobrando);
						
					}
					
					Partida partidaPai = lista.get(i);
					partidaPai.setPartidaFilho(partida);
					descricao +=" x W: ("+partidaPai.getDescricao()+")";
					
					partida.setDescricao(descricao);
					EntityManagerLocal.persist(partida);
					EntityManagerLocal.persist(tp);
					EntityManagerLocal.merge(partidaPai);
					EntityManagerLocal.merge(partidaWinner);
					EntityManagerLocal.commit();
					EntityManagerLocal.clear();
					novaLista.add(partida);
				}
				indiceWinner++;
				indice++;
				return criarPartidasMataMataFilhoLower(novaLista, indice,
						campeonato, indiceWinner, indicesWinner);
			}
		}
		return lista;
	}

}
