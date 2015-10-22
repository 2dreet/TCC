package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Periferico;

public class PerifericoDao {

	public static Periferico getPerifericoNome(String nomePeriferico){
		try {
			String sql = "SELECT * FROM periferico where descricao = '"+nomePeriferico+"' and ativo = true";
			return (Periferico) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Periferico.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Periferico> getListaPesquisa(String metodoPesquisa, String valorPesquisa){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " codigoPeriferico like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Descrição")){
				condicao = " descricao like '%"+valorPesquisa+"%'";
			} 
			
			String sql = "SELECT * FROM periferico "
					+ " where "+condicao+" AND ativo = true";
			
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Periferico.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Periferico> getListaPeriferico(){
		try {
			String sql = "SELECT * FROM periferico "
					+ " where ativo = true";
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Periferico.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Object[] getVetorPeriferico(){
		List<Periferico> listaPeriferico = getListaPeriferico();
		Object [] listaAux;
		if(listaPeriferico !=null && listaPeriferico.size() > 0){
			listaAux = new Object [listaPeriferico.size()];
			int i = 0;
			for(Periferico p : listaPeriferico){
				listaAux[i] = p.getDescricao();
				i++;
			}
			return  listaAux;
		}else{
			listaAux = new Object [0];
			return listaAux;
		}
	}
	
	public static Periferico getPeriferico(int codigoPeriferico){
		try {
			String sql = "SELECT * FROM periferico where codigoPeriferico = '"+codigoPeriferico+"' and ativo = true";
			return (Periferico) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Periferico.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static boolean verificaPeriferico(String nomePeriferico){
		Periferico periferico = getPerifericoNome(nomePeriferico);
		if(periferico != null){
			return true;
		}
		
		return false;
	}
}
