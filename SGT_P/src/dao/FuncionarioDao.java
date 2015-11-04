package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Funcionario;
import entidade.Jogador;
import entidade.Usuario;

public class FuncionarioDao {

	public static List<Funcionario> getListaPesquisa(String metodoPesquisa, String valorPesquisa){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " f.codigoFuncionario like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Nome")){
				condicao = " CONCAT(u.nome, u.sobrenome) like '%"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Usuário")){
				condicao = "u.usuario like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Cpf")){
				condicao = "u.cpf like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Telefone")){
				condicao = "u.telefone like '%"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Email")){
				condicao = "u.email like '%"+valorPesquisa+"%'";
			}
			
			
			String sql = "SELECT * FROM funcionario f INNER JOIN usuario u"
					+ "	ON f.codigoUsuario = u.codigoUsuario"
					+ " where "+condicao+" AND u.ativo = true";
			if (metodoPesquisa.equals("Cpf")){
				sql += " OR u.ativo = false";
			}
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Funcionario.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Funcionario getFuncionario(int codigo){
		try {
			String sql = "SELECT * FROM funcionario f INNER JOIN usuario u"
					+ "	ON f.codigoUsuario = u.codigoUsuario"
					+ " where codigoFuncionario = '"+codigo+"' AND u.ativo = true";
			return (Funcionario) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Funcionario.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Funcionario getFuncionarioUsurio(int codigo){
		try {
			String sql = "SELECT * FROM funcionario f INNER JOIN usuario u"
					+ "	ON f.codigoUsuario = u.codigoUsuario"
					+ " where f.codigoUsuario = '"+codigo+"' AND u.ativo = true";
			return (Funcionario) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Funcionario.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Funcionario getAllFuncionario(int codigo){
		try {
			String sql = "SELECT * FROM funcionario f INNER JOIN usuario u"
					+ "	ON f.codigoUsuario = u.codigoUsuario"
					+ " where codigoFuncionario = '"+codigo+"' ";
			return (Funcionario) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Funcionario.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
