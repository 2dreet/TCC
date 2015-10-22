package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Banimento;
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
	
	public static List<Modalidade> getListaModalidade(){
		try {
			String sql = "SELECT * FROM modalidade "
					+ " where ativo = true order by descricao";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Modalidade.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Object[] getVetorModalidade(){
		List<Modalidade> listaModalidade = getListaModalidade();
		Object [] listaAux;
		if(listaModalidade !=null && listaModalidade.size() > 0){
			listaAux = new Object [listaModalidade.size()];
			int i = 0;
			for(Modalidade m : listaModalidade){
				listaAux[i] = m.getDescricao();
				i++;
			}
			return  listaAux;
		}else{
			listaAux = new Object [0];
			return listaAux;
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
