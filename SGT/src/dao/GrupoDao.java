package dao;

import java.util.List;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Grupo;

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
	
}
