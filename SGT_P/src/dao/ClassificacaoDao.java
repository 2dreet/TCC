package dao;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Campeonato;
import entidade.Classificacao;

public class ClassificacaoDao {

	public static Classificacao getClassificacaoPorCampeonato(Campeonato campeonato){
		try {
			String sql = "SELECT * FROM classificacao where codigoCampeonato = '"+campeonato.getCodigoCampeonato()+"'";
			return (Classificacao) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Classificacao.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
