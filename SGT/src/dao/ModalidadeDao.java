package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Marca;
import entidade.Modalidade;


public class ModalidadeDao {

	
	public static Modalidade getModalidade(int codigoModalidade){
		try {
			String sql = "SELECT * FROM modalidade where codigoModalidade = '"+codigoModalidade+"' and ativo = true";
			return (Modalidade) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Modalidade.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Modalidade getModalidadeNome(String nomeModalidade){
		try {
			String sql = "SELECT * FROM modalidade where descricao = '"+nomeModalidade+"' and ativo = true";
			return (Modalidade) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Modalidade.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Modalidade> getListaPesquisa(String metodoPesquisa, String valorPesquisa){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " codigoModalidade like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Descrição")){
				condicao = " descricao like '%"+valorPesquisa+"%'";
			} 
			String sql = "SELECT * FROM modalidade "
					+ " where "+condicao+" AND ativo = true";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Modalidade.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static boolean verificaModalidade(String nomeModalidade){
		Modalidade modalidade = getModalidadeNome(nomeModalidade);
		if(modalidade != null){
			return true;
		}
		
		return false;
	}
	
}
