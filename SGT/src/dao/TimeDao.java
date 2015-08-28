package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Time;



public class TimeDao {

	public static List<Time> getListaPesquisa(String metodoPesquisa, String valorPesquisa){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " codigoTime like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Descrição")){
				condicao = " descricao like '%"+valorPesquisa+"%'";
			} 
			
			String sql = "SELECT * FROM time "
					+ " where "+condicao+" AND ativo = true";
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Time.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Time getTime(int codigo){
		try {
			String sql = "SELECT * FROM time "
					+ " where codigoTime = '"+codigo+"' AND ativo = true";
			return (Time) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Time.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Time getTime(String nome){
		try {
			String sql = "SELECT * FROM time "
					+ " where descricao = '"+nome+"' AND ativo = true";
			return (Time) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Time.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static boolean timeCadastrado(String nome){
		Time time = getTime(nome);
		if(time != null){
			return true;
		}
		return false;
	}
	
}
