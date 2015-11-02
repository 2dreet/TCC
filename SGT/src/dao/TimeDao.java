package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.CampeonatoTime;
import entidade.Time;
import entidade.TimeGrupo;



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
	
	public static List<CampeonatoTime> getListaTimeBanidos(){
		try {
			String sql = "SELECT * FROM campeonato_time "
					+ " WHERE desqualificado = true";
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, CampeonatoTime.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Time> getListaTime(){
		try {
			String sql = "SELECT * FROM time "
					+ " order by descricao";
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Time.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Time> getListaPesquisaTime(String metodoPesquisa, String valorPesquisa, int codigoCampeonato){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " t.codigoTime like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Descrição")){
				condicao = " t.descricao like '%"+valorPesquisa+"%'";
			} 
			
			String sql = "SELECT * FROM time t "
					+ " where "+condicao+" AND t.ativo = true AND "
					+ " t.codigoTime NOT IN (SELECT codigoTime from campeonato_time ct where ct.codigoTime = t.codigoTime AND ct.codigoCampeonato = '"+codigoCampeonato+"')";
			
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
	
	public static TimeGrupo getTimeGrupo(int codigoTime, int codigoCampeonato){
		try {
			String sql = "SELECT * FROM time_grupo tg INNER JOIN grupo g ON tg.codigoGrupo = g.codigoGrupo"
					+ " where tg.codigoTime = '"+codigoTime+"' AND g.codigoCampeonato = '"+codigoCampeonato+"' AND tg.ativo = true";
			return (TimeGrupo) EntityManagerLocal.getEntityManager().createNativeQuery(sql, TimeGrupo.class)
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
