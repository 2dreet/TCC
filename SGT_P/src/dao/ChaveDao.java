package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Chave;

public class ChaveDao {

	public static List<Chave> getListaChave(){
		try {
			String sql = "SELECT * FROM chave "
					+ " where ativo = true";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Chave.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Chave getChaveNome(String nomeChave){
		try {
			String sql = "SELECT * FROM chave where descricao = '"+nomeChave+"' and ativo = true";
			return (Chave) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Chave.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Object[] getVetorChave(){
		List<Chave> listaChave = getListaChave();
		Object [] listaAux;
		if(listaChave !=null && listaChave.size() > 0){
			listaAux = new Object [listaChave.size()];
			int i = 0;
			for(Chave c : listaChave){
				listaAux[i] = c.getDescricao();
				i++;
			}
			return  listaAux;
		}else{
			listaAux = new Object [0];
			return listaAux;
		}
	}
	
}
