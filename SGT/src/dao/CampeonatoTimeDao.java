package dao;

import java.util.List;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.CampeonatoTime;
import entidade.Grupo;
import entidade.JogadorBanimento;
import entidade.Time;
import entidade.TimeGrupo;

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
	
	public static boolean timeDesqualificado(int codigoTime, int codigoCampeonato){
		try{
			String sql = "SELECT * FROM campeonato_time where codigoTime = '"+codigoTime+"' AND codigoCampeonato = '"+codigoCampeonato+"' AND desqualificado = true";
			CampeonatoTime ct = (CampeonatoTime) EntityManagerLocal.getEntityManager().createNativeQuery(sql, CampeonatoTime.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
			if(ct != null){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
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
	
	public static List<Time> getListaCampeonatoListaTimeLowers(int codigoCampeonato){
		try{
			String sql = "SELECT t.* FROM campeonato_time ct INNER JOIN time t"
					+ "	ON ct.codigoTime = t.codigoTime "
					+ "  where codigoCampeonato = '"+codigoCampeonato+"' AND ativo = true AND ct.codigoTime not in(SELECT timeVencedor FROM partida where codigoCampeonato = '"+codigoCampeonato+"')";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Time.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Time> getListaCampeonatoListaTimeSemGrupo(int codigoCampeonato){
		try{
			String sql = "SELECT * FROM campeonato_time ct INNER JOIN time t"
					+ "	ON ct.codigoTime = t.codigoTime "
					+ "  where ct.codigoCampeonato = '"+codigoCampeonato+"' AND t.ativo = true AND ct.codigoTime not in("
							+ "SELECT tg.codigoTime FROM time_grupo tg INNER JOIN grupo g ON g.codigoGrupo = tg.codigoGrupo where codigoCampeonato = "+codigoCampeonato+");";
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
			String sql = "SELECT * FROM campeonato_time where codigoCampeonato = '"+codigoCampeonato+"' AND codigoTime not in("
		+ "SELECT tg.codigoTime FROM time_grupo tg INNER JOIN grupo g ON g.codigoGrupo = tg.codigoGrupo where codigoCampeonato = "+codigoCampeonato+");";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, CampeonatoTime.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<TimeGrupo> getListaTimeGrupo(TimeGrupo time, Grupo grupo){
		try{
			String sql = "SELECT * FROM time_grupo where codigoGrupo = '"+grupo.getCodigoGrupo()+"' AND pontuacao > 0";
			if(time != null){
				sql += " AND codigoTime <> "+time.getTime().getCodigoTime();
			}
			sql +=" ORDER BY pontuacao";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, TimeGrupo.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
}
