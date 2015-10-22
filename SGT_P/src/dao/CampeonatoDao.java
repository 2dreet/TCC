package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Campeonato;

public class CampeonatoDao {

	public static List<Campeonato> getListaPesquisa(String metodoPesquisa, String valorPesquisa){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " codigoCampeonato like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Nome")){
				condicao = " descricao like '%"+valorPesquisa+"%'";
			} 
			
			String sql = "SELECT * FROM campeonato where "+condicao+" AND ativo = true";
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Campeonato.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Campeonato getCampeonato(int codigo){
		try {
			String sql = "SELECT * FROM campeonato where codigoCampeonato = '"+codigo+"' AND ativo = true";
			return (Campeonato) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Campeonato.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
