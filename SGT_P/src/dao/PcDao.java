package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Jogador;
import entidade.Pc;
import entidade.PcPartida;

public class PcDao {

	public static Pc getPcIp(String ip){
		try {
			String sql = "SELECT * FROM pc where ip = '"+ip+"' AND ativo = true";
			return (Pc) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Pc.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Pc getPc(int codigoPc){
		try {
			String sql = "SELECT * FROM pc where codigoPc = '"+codigoPc+"' AND ativo = true";
			return (Pc) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Pc.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}
	
	public static PcPartida getPcPartida(int codigoPc, int codigoPartida){
		try {
			String sql = "SELECT * FROM pc_partida where codigoPc = '"+codigoPc+"' AND codigoPartida = '"+codigoPartida+"'";
			return (PcPartida) EntityManagerLocal.getEntityManager().createNativeQuery(sql, PcPartida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}
	
	public static List<Pc> getListaPesquisa(String metodoPesquisa, String valorPesquisa){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("C�digo")){
				condicao = " codigoPc like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Descri��o")){
				condicao = " descricao like '%"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("IP")){
				condicao = " ip like '%"+valorPesquisa+"%'";
			} 
			String sql = "SELECT * FROM pc where "+condicao+" AND ativo = true";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Pc.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<PcPartida> getListaPcPartida(int codigoPartida){
		try {
			String condicao = "";
			String sql = "SELECT * FROM pc_partida where codigoPartida = '"+codigoPartida+"'";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, PcPartida.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return new ArrayList<PcPartida>();
		}
	}
	

	public static List<Pc> getListaPcNaoPartida(String metodoPesquisa, String valorPesquisa, int codigoPartida){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("C�digo")){
				condicao = " codigoPc like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Descri��o")){
				condicao = " descricao like '%"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("IP")){
				condicao = " ip like '%"+valorPesquisa+"%'";
			} 
			String sql = "SELECT * FROM pc where "+condicao+" AND ativo = true AND codigoPc not in (SELECT codigoPc from pc_partida where codigoPartida = '"+codigoPartida+"')";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Pc.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Pc> getListaPc(){
		try {
			String condicao = "";
			String sql = "SELECT * FROM pc where ativo = true";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, PcPartida.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
