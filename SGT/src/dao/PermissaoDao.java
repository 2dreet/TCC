package dao;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Permissao;

public class PermissaoDao {

	public static Permissao getPermissao(int id) {
		try {
			String sql = "SELECT * FROM permissao where codigoPermissao = '" + id + "'";
			return (Permissao) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Permissao.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
