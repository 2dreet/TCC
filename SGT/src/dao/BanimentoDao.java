package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Banimento;
import entidade.Marca;
import entidade.Modalidade;


public class BanimentoDao {

	public static List<Banimento> getListaPesquisa(String metodoPesquisa, String valorPesquisa){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " codigoBanimento like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Descrição")){
				condicao = " descricao like '%"+valorPesquisa+"%'";
			} 
			String sql = "SELECT * FROM banimento "
					+ " where "+condicao+" AND ativo = true";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Banimento.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Banimento getBanimentoNome(String nomeBanimento){
		try {
			String sql = "SELECT * FROM banimento where descricao = '"+nomeBanimento+"' and ativo = true";
			return (Banimento) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Banimento.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Banimento getBanimento(int codigoBanimento){
		try {
			String sql = "SELECT * FROM banimento where codigoBanimento = '"+codigoBanimento+"' and ativo = true";
			return (Banimento) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Banimento.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static boolean verificaBanimento(String nomeBanimento){
		Banimento banimento = getBanimentoNome(nomeBanimento);
		if(banimento != null){
			return true;
		}
		
		return false;
	}
	
	public static List<Banimento> getListaBanimento(){
		try {
			String sql = "SELECT * FROM banimento "
					+ " where ativo = true order by descricao";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Banimento.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static boolean banimentosAtivos(){
		try {
			List<Banimento> lista = getListaBanimento();
			if(lista != null && lista.size() > 0){
				return true;
			}
			return false;
		} catch (NoResultException ex) {
			return false;
		}
	}
	
	public static Object[] getVetorBanimento(){
		List<Banimento> listaBanimento = getListaBanimento();
		Object [] listaAux;
		if(listaBanimento !=null && listaBanimento.size() > 0){
			listaAux = new Object [listaBanimento.size()];
			int i = 0;
			for(Banimento b : listaBanimento){
				listaAux[i] = b.getDescricao();
				i++;
			}
			return  listaAux;
		}else{
			listaAux = new Object [0];
			return listaAux;
		}
	}
}
