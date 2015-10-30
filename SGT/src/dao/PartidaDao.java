package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Campeonato;
import entidade.Jogador;
import entidade.Partida;
import entidade.Pc;
import entidade.Time;
import entidade.TimePartida;

public class PartidaDao {

	public static List<Partida> getPartidasIniciadas(int codigoCampeonato) {
		try {
			String sql = "SELECT * FROM partida WHERE ativo = true AND horaInicio is not null AND codigoCampeonato = '"
					+ codigoCampeonato + "'";
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static List<Partida> getPartidasApenas1Time(int codigoCampeonato) {
		try {
			String sql = " SELECT * FROM partida p INNER JOIN time_partida tp ON p.codigoPartida = tp.codigoPartida "
					+ " WHERE p.ativo = true AND p.codigoCampeonato = '"
					+ codigoCampeonato
					+ "' AND tp.codigoTime1 is not null AND tp.codigoTime2 is null";
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static List<Time> getTimesParaLower(int codigoCampeonato) {
		try {
			String sql = " SELECT * FROM partida p INNER JOIN time_partida tp ON p.codigoPartida = tp.codigoPartida"
					+ " INNER JOIN time t ON t.codigoTime = tp.codigoTime"
					+ " where p.codigoPartida not in (SELECT codigoPartidaFilho FROM partida where codigoPartidaFilho is not null AND codigoCampeonato = '"
					+ codigoCampeonato
					+ "')"
					+ " AND codigoCampeonato = '"
					+ codigoCampeonato
					+ "' AND tp.codigoTime not in (SELECT timeVencedor FROM partida where timeVencedor is not null AND codigoCampeonato = '1')"
					+ " AND horaFim is not null;";
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Time.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static List<Partida> getPartidasNaoIniciadas(int codigoCampeonato) {
		try {
			String sql = "SELECT * FROM partida WHERE ativo = true AND horaInicio is null AND codigoCampeonato = '"
					+ codigoCampeonato + "'";
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static Integer[] getIndicesPartidaParaGerarLowers(
			int codigoCampeonato) {
		try {
			String sql = " SELECT * FROM partida p INNER join campeonato c on p.codigoCampeonato = c.codigoCampeonato"
					+ " where p.codigoCampeonato = '"
					+ codigoCampeonato
					+ "' AND c.codigoChave = 2 AND p.winerLower = false"
					+ " GROUP BY indice ORDER BY indice";
			List<Partida> lista = EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
			Integer[] indices = new Integer[lista.size()];
			int i = 0;
			for (Partida partida : lista) {
				indices[i] = partida.getIndice();
				i++;
			}
			return indices;

		} catch (NoResultException ex) {
			return null;
		}
	}

	public static List<TimePartida> getPartidasPorIndiceParaGerarLowers(
			int codigoCampeonato, int indice) {
		try {
			String sql = " SELECT * FROM time_partida tp INNER JOIN partida p ON p.codigoPartida = tp.codigoPartida"
					+ " INNER JOIN campeonato c on p.codigoCampeonato = c.codigoCampeonato"
					+ " WHERE p.ativo = false AND p.codigoCampeonato = '"
					+ codigoCampeonato
					+ "'"
					+ " AND p.indice = '"
					+ indice
					+ "'"
					+ " AND tp.timePerdedor is not null"
					+ " AND c.codigoChave = 2" + " AND p.winerLower = false;";
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, TimePartida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static TimePartida getTimeCampeao(
			int codigoCampeonato) {
		try {
			String sql = " SELECT * FROM time_partida tp INNER JOIN partida p ON p.codigoPartida = tp.codigoPartida"
					+ " INNER JOIN campeonato c on p.codigoCampeonato = c.codigoCampeonato"
					+ " WHERE p.ativo = false AND codigoPartidaFilho is null "
					+ " AND codigoGrupo is null AND p.codigoCampeonato = '" + codigoCampeonato + "'";
					
			return (TimePartida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, TimePartida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static List<TimePartida> getPartidasParaTabelaMataMata(Campeonato campeonato,
			int indice, boolean winerLower) {
		try {
			String sql = " SELECT * FROM time_partida tp INNER JOIN partida p ON p.codigoPartida = tp.codigoPartida"
					+ " INNER JOIN campeonato c on p.codigoCampeonato = c.codigoCampeonato"
					+ " WHERE p.codigoCampeonato = '"
					+ campeonato.getCodigoCampeonato()
					+ "'"
					+ " AND p.indice = '"
					+ indice
					+ "'" + " AND p.codigoGrupo is null AND p.winerLower = "
						+ winerLower + " ORDER BY p.indice, p.codigoPartidaFilho, p.codigoPartida;";
			
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, TimePartida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<TimePartida> getPartidasPorIndice(Campeonato campeonato,
			int indice, boolean winerLower) {
		try {
			String sql = " SELECT * FROM time_partida tp INNER JOIN partida p ON p.codigoPartida = tp.codigoPartida"
					+ " INNER JOIN campeonato c on p.codigoCampeonato = c.codigoCampeonato"
					+ " WHERE p.ativo = false AND p.codigoCampeonato = '"
					+ campeonato.getCodigoCampeonato()
					+ "'"
					+ " AND p.indice = '"
					+ indice
					+ "'" + " AND tp.timePerdedor is not null AND c.codigoChave = "+campeonato.getChave().getCodigoChave()+" AND p.winerLower = '"
						+ winerLower + "'ORDER BY p.indice, p.codigoPartidaFilho, p.codigoPartida;";
			
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, TimePartida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static List<Partida> getPartidasNaoFinalizadasIniciadasMataMata(
			int codigoCampeonato) {
		try {
			String sql = " SELECT * FROM partida p inner join campeonato c on p.codigoCampeonato = c.codigoCampeonato"
					+ " where c.codigoChave = 1"
					+ " AND c.codigoCampeonato = '"
					+ codigoCampeonato
					+ "'"
					+ " AND p.horaFim is null"
					+ " AND p.winerLower = false;";
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static List<Partida> getPartidasNaoFinalizadasIniciadasWinnerLower(
			int codigoCampeonato) {
		try {
			String sql = " SELECT * FROM partida p inner join campeonato c on p.codigoCampeonato = c.codigoCampeonato"
					+ " where c.codigoChave = 2"
					+ " AND c.codigoCampeonato = '"
					+ codigoCampeonato
					+ "'"
					+ " AND p.horaFim is null"
					+ " AND p.winerLower = false;";
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Partida> getPartidasWinnerLower(
			int codigoCampeonato) {
		try {
			String sql = " SELECT * FROM partida p inner join campeonato c on p.codigoCampeonato = c.codigoCampeonato"
					+ " where c.codigoChave = 2"
					+ " AND c.codigoCampeonato = '"
					+ codigoCampeonato
					+ "'"
					+ " AND p.winerLower = true;";
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Partida> getPartidasFinalizadas(
			int codigoCampeonato) {
		try {
			String sql = " SELECT * FROM partida WHERE codigoCampeonato = '"
					+ codigoCampeonato
					+ "'"
					+ " AND ativo = false AND horaInicio is not null ";
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static List<Partida> getPartidasNaoFinalizadasIniciadasGrupo(
			int codigoCampeonato) {
		try {
			String sql = " SELECT * FROM partida p inner join campeonato c on p.codigoCampeonato = c.codigoCampeonato"
					+ " where c.codigoChave = 3"
					+ " AND c.codigoCampeonato = '"
					+ codigoCampeonato
					+ "'"
					+ " AND p.horaFim is null;";
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static TimePartida getTimePartida(int codigoCampeonato,
			int codigoPartida) {
		try {
			String sql = " SELECT * FROM time_partida tp INNER JOIN partida p ON p.codigoPartida = tp.codigoPartida"
					+ " WHERE p.codigoCampeonato = '"
					+ codigoCampeonato
					+ "'"
					+ " AND p.codigoPartida = '"
					+ codigoPartida + "'";
			return (TimePartida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, TimePartida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static TimePartida getTimePartidaTime1Null(int codigoCampeonato,
			int codigoPartida) {
		try {
			String sql = " SELECT * FROM time_partida tp INNER JOIN partida p ON p.codigoPartida = tp.codigoPartida"
					+ " WHERE p.ativo = true AND p.codigoCampeonato = '"
					+ codigoCampeonato
					+ "'"
					+ " AND p.codigoPartida = '"
					+ codigoPartida
					+ "'"
					+ " AND tp.codigoTime1 is null LIMIT 1; ";
			return (TimePartida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, TimePartida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static TimePartida getTimePartidaTime2Null(int codigoCampeonato,
			int codigoPartida) {
		try {
			String sql = " SELECT * FROM time_partida tp INNER JOIN partida p ON p.codigoPartida = tp.codigoPartida"
					+ " WHERE p.ativo = true AND p.codigoCampeonato = '"
					+ codigoCampeonato
					+ "'"
					+ " AND p.codigoPartida = '"
					+ codigoPartida
					+ "'"
					+ " AND tp.codigoTime2 is null LIMIT 1; ";
			return (TimePartida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, TimePartida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static Partida getPartidaFinal(int codigoCampeonato,
			boolean winerLower) {
		try {
			String sql = "SELECT * FROM partida "
					+ " where codigoCampeonato = '" + codigoCampeonato
					+ "' AND winerLower = " + winerLower
					+ " AND codigoPartidaFilho is null";
			return (Partida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static Partida getPartida(int codigoPartida) {
		try {
			String sql = "SELECT * FROM partida " + " where codigoPartida = '"
					+ codigoPartida + "' ";
			return (Partida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static Partida getAllPartida(int codigo) {
		try {
			String sql = "SELECT * FROM partida " + " where codigoPartida = '"
					+ codigo + "'";
			return (Partida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static Partida getPartidaPai(int codigo) {
		try {
			String sql = "SELECT * FROM partida " + " where codigoPartidaFilho = '"
					+ codigo + "'";
			return (Partida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static boolean isCampeonatoChaveGrupo(int codigo) {
		try {
			String sql = "SELECT * FROM partida where codigoCampeonato = "
					+ codigo + " AND codigoGrupo is not null LIMIT 1";
			Partida partida = (Partida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
			if (partida != null) {
				return true;
			}
		} catch (NoResultException ex) {

		}
		return false;
	}
	
	public static boolean isCampeonatoChaveMataMata(int codigo) {
		try {
			String sql = "SELECT * FROM partida where codigoCampeonato = "
					+ codigo + " AND codigoGrupo is null and winerLower = false LIMIT 1";
			Partida partida = (Partida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
			if (partida != null) {
				return true;
			}
		} catch (NoResultException ex) {

		}
		return false;
	}
	
	public static boolean isCampeonatoChaveWinnerLower(int codigo) {
		try {
			String sql = "SELECT * FROM partida where codigoCampeonato = "
					+ codigo + " AND codigoGrupo is null and winerLower = true LIMIT 1";
			Partida partida = (Partida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
			if (partida != null) {
				return true;
			}
		} catch (NoResultException ex) {

		}
		return false;
	}
	
	public static boolean isPartidaFilho(int codigo) {
		try {
			String sql = "SELECT * FROM partida where codigoPartidaFilho = "
					+ codigo + " LIMIT 1";
			Partida partida = (Partida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
			if (partida != null) {
				return true;
			}
		} catch (NoResultException ex) {

		}
		return false;
	}

	public static boolean isPartidaFinal(int codigo) {
		try {
			String sql = "SELECT * FROM sgtg.partida where codigoPartidaFilho is null and codigoPartida = "+codigo+" AND ativo = false;";
			Partida partida = (Partida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
			if (partida != null) {
				return true;
			}
		} catch (NoResultException ex) {

		}
		return false;
	}
	
	public static List<Partida> getPartidasPai(int codigoPatida) {
		try {
			String sql = "SELECT * FROM partida WHERE ((horaInicio is null and horaFim is null) OR  (horaInicio is not null and horaFim is null) OR  (horaInicio is not null and horaFim is not null)) AND codigoPartidaFilho = "
					+ codigoPatida;
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<TimePartida> getTimeBaia(int codigoTime) {
		try {
			String sql = "SELECT * FROM time_partida tp INNER JOIN partida p ON p.codigoPartida = tp.codigoPartida"+
						 " WHERE tp.codigoTime1 = "+codigoTime+" and p.horaInicio is null and p.horaFim is not null AND p.codigoGrupo is null;";
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, TimePartida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static Partida getProximaPartidaPartida(Campeonato campeonato) {
		try {
			String sql = " SELECT * FROM time_partida tp INNER JOIN partida p ON p.codigoPartida = tp.codigoPartida"
					+ " INNER JOIN campeonato c on p.codigoCampeonato = c.codigoCampeonato"
					+ " WHERE p.ativo = true AND p.codigoCampeonato = '"
					+ campeonato.getCodigoCampeonato()
					+ "'"
					+ " AND p.horaFim is null"
					+ " AND c.codigoChave = '"
					+ campeonato.getChave().getCodigoChave()
					+ "'"
					+ "  ORDER BY p.indice, p.codigoPartidaFilho, p.codigoPartida";
			return (Partida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
