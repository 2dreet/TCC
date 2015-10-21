package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Jogador;
import entidade.Partida;
import entidade.Pc;
import entidade.TimePartida;

public class PartidaDao {

	public static List<Partida> getPartidasIniciadas(int codigoCampeonato) {
		try {
			String sql = "SELECT * FROM partida WHERE ativo = true AND cancelada = false AND horaInicio is not null AND codigoCampeonato = '"
					+ codigoCampeonato + "'";
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static List<Partida> getPartidasNaoIniciadas(int codigoCampeonato) {
		try {
			String sql = "SELECT * FROM partida WHERE ativo = true AND cancelada = false AND horaInicio is null AND codigoCampeonato = '"
					+ codigoCampeonato + "'";
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	
	public static TimePartida getTimePartida(int codigoCampeonato,
			int codigoPartida, String ordem) {
		try {
			String sql = " SELECT * FROM time_partida tp INNER JOIN partida p ON p.codigoPartida = tp.codigoPartida"
					+ " WHERE p.ativo = true AND p.cancelada = false AND p.codigoCampeonato = '"
					+ codigoCampeonato
					+ "'"
					+ " AND p.codigoPartida = '"
					+ codigoPartida
					+ "'"
					+ " ORDER BY tp.codigoTimePartida "
					+ ordem + " LIMIT 1; ";
			return (TimePartida) EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, TimePartida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Partida getPartidaFinal(int codigoCampeonato, boolean winerLower){
		try {
			String sql = "SELECT * FROM partida "
					+ " where codigoCampeonato = '"+codigoCampeonato+"' AND winerLower = "+winerLower+" AND codigoPartidaFilho is null";
			return (Partida) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Partida getAllPartida(int codigo){
		try {
			String sql = "SELECT * FROM partida "
					+ " where codigoPartida = '"+codigo+"'";
			return (Partida) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static boolean isPartidaFilho(int codigo){
		try {
			String sql = "SELECT * FROM partida where codigoPartidaFilho = "+codigo+" LIMIT 1";
			Partida partida = (Partida) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
			if(partida != null){
				return true;
			}
		} catch (NoResultException ex) {
			
		}
		return false;
	}
	
	public static List<Partida> getPartidasPai(int codigoPatida) {
		try {
			String sql = "SELECT * FROM partida WHERE codigoPartidaFilho = "+codigoPatida;
			return EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql, Partida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
