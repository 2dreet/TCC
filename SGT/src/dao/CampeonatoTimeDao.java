package dao;

import java.util.List;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.CampeonatoTime;
import entidade.JogadorBanimento;
import entidade.Time;

public class CampeonatoTimeDao {

	public static CampeonatoTime getCampeonatoTime(int codigoTime, int codigoCampeonato){
		try{
			String sql = "SELECT * FROM campeonato_time where codigoTime = '"+codigoTime+"' AND codigoCampeonato = '"+codigoCampeonato+"'";
			return (CampeonatoTime) EntityManagerLocal.getEntityManager().createNativeQuery(sql, CampeonatoTime.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Time> getListaCampeonatoListaTime(int codigoCampeonato){
		try{
			String sql = "SELECT t.* FROM campeonato_time ct INNER JOIN time t"
					+ "	ON ct.codigoTime = t.codigoTime "
					+ "  where codigoCampeonato = '"+codigoCampeonato+"' AND ativo = true";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Time.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Time> getListaCampeonatoListaTimeSemGrupo(int codigoCampeonato){
		try{
			String sql = "SELECT t.* FROM campeonato_time ct INNER JOIN time t"
					+ "	ON ct.codigoTime = t.codigoTime "
					+ "  where codigoCampeonato = '"+codigoCampeonato+"' AND ativo = true AND codigoTime not in(SELECT codigoTime FROM time_grupo);";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Time.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<CampeonatoTime> getListaCampeonatoTimeCamp(int codigoCampeonato){
		try{
			String sql = "SELECT * FROM campeonato_time where codigoCampeonato = '"+codigoCampeonato+"'";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, CampeonatoTime.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<CampeonatoTime> getListaCampeonatoTimeCampSemGrupo(int codigoCampeonato){
		try{
			String sql = "SELECT * FROM campeonato_time where codigoCampeonato = '"+codigoCampeonato+"' AND codigoTime not in(SELECT codigoTime FROM time_grupo);";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, CampeonatoTime.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static CampeonatoTime getCampeonatoTime(int codigoCampeonatoTime){
		try{
			String sql = "SELECT * FROM campeonato_time where codigoCampTime = '"+codigoCampeonatoTime+"'";
			return (CampeonatoTime) EntityManagerLocal.getEntityManager().createNativeQuery(sql, CampeonatoTime.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
