package dao;

import java.util.List;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Grupo;
import entidade.Time;
import entidade.TimeGrupo;

public class GrupoDao {

	public static List<Grupo> getListaGrupo(int codigoCampeonato){
		try{
			String sql = "SELECT * FROM grupo where codigoCampeonato = '"+codigoCampeonato+"'";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Grupo.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<TimeGrupo> getListaTimeGrupo(int codigoCampeonato, int codigoGrupo){
		try{
			String sql = "SELECT * FROM time_grupo tg INNER JOIN grupo g"
					+ "	ON tg.codigoGrupo = g.codigoGrupo "
					+ " where g.codigoCampeonato = '"+codigoCampeonato+"' AND g.codigoGrupo = '"+codigoGrupo+"' ORDER BY pontuacao DESC;";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, TimeGrupo.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
