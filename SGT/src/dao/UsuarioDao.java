package dao;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Usuario;


public class UsuarioDao {

	public static Usuario validarUsuario(String login) {
		try {
			String sql = "SELECT * FROM usuario where usuario = '" + login + "' AND ativo = true";
			return (Usuario) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Usuario.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
