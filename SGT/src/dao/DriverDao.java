package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Driver;
import entidade.Periferico;

public class DriverDao {

	public static Driver getDriverNome(String nomeDriver){
		try {
			String sql = "SELECT * FROM driver where descricao = '"+nomeDriver+"' and ativo = true";
			return (Driver) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Driver.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Driver> getListaPesquisa(String metodoPesquisa, String valorPesquisa){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " codigoDriver like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Descrição")){
				condicao = " descricao like '%"+valorPesquisa+"%'";
			} 
			
			String sql = "SELECT * FROM driver "
					+ " where "+condicao+" AND ativo = true";
			
			System.out.println(sql);
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Driver.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Driver getDriver(int codigoDriver){
		try {
			String sql = "SELECT * FROM driver where codigoDriver = '"+codigoDriver+"' and ativo = true";
			return (Driver) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Driver.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static boolean verificaDriver(String nomeDriver){
		Driver driver = getDriverNome(nomeDriver);
		if(driver != null){
			return true;
		}
		
		return false;
	}
}
