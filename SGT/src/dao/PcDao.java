package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Jogador;
import entidade.Pc;

public class PcDao {

	public static Pc getPcIp(String ip){
		try {
			String sql = "SELECT * FROM pc where ip = '"+ip+"' AND ativo = true";
			return (Pc) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Pc.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Pc getPc(int codigoPc){
		try {
			String sql = "SELECT * FROM pc where codigoPc = '"+codigoPc+"' AND ativo = true";
			return (Pc) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Pc.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}
	
	public static List<Pc> getListaPesquisa(String metodoPesquisa, String valorPesquisa){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " codigoPc like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Descrição")){
				condicao = " descricao like '%"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("IP")){
				condicao = " ip like '%"+valorPesquisa+"%'";
			} 
			String sql = "SELECT * FROM pc where "+condicao+" AND ativo = true";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Pc.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
