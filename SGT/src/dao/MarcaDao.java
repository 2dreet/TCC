package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Marca;

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
			
			System.out.println(sql);
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Marca.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
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
	
	public static void desativarPerifericos(int codigoMarca){
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
