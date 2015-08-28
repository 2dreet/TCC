package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Marca;
import entidade.Periferico;

public class MarcaDao {

	public static Marca getMarcaNome(String nomeMarca){
		try {
			String sql = "SELECT * FROM marca where descricao = '"+nomeMarca+"' and ativo = true";
			return (Marca) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Marca.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Marca> getListaPesquisa(String metodoPesquisa, String valorPesquisa){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " codigoMarca like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Descrição")){
				condicao = " descricao like '%"+valorPesquisa+"%'";
			} 
			String sql = "SELECT * FROM marca "
					+ " where "+condicao+" AND ativo = true";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Marca.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Marca> getListaMarca(){
		try {
			String sql = "SELECT * FROM marca "
					+ " where ativo = true";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Marca.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Object[] getVetorMarca(){
		List<Marca> listaMarca = getListaMarca();
		Object [] listaAux;
		if(listaMarca !=null && listaMarca.size() > 0){
			listaAux = new Object [listaMarca.size()];
			int i = 0;
			for(Marca m : listaMarca){
				listaAux[i] = m.getDescricao();
				i++;
			}
			return  listaAux;
		}else{
			listaAux = new Object [0];
			return listaAux;
		}
	}
	
	public static Marca getMarca(int codigoMarca){
		try {
			String sql = "SELECT * FROM marca where codigoMarca = '"+codigoMarca+"' and ativo = true";
			return (Marca) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Marca.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static void desativarDrivers(int codigoMarca){
		try {
			String sql = "UPDATE driver SET ativo = false WHERE codigoMarca = '"+codigoMarca+"'";
			EntityManagerLocal.getEntityManager().createNativeQuery(sql).executeUpdate();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
	}
	
	public static boolean verificaMarca(String nomeMarca){
		Marca marca = getMarcaNome(nomeMarca);
		if(marca != null){
			return true;
		}
		
		return false;
	}
}
