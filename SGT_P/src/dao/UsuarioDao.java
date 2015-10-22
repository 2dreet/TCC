package dao;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Jogador;
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
	
	public static Usuario getCpf(String cpf){
		try {
			String sql = "SELECT * FROM usuario u"
					+ " where u.cpf = '"+cpf+"'";
			return (Usuario) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Usuario.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
