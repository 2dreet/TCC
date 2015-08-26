package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Jogador;
import entidade.Usuario;

public class JogadorDao {

	public static List<Jogador> getListaPesquisa(String metodoPesquisa, String valorPesquisa){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " j.codigoJogador like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Nome")){
				condicao = " CONCAT(u.nome, u.sobrenome) like '%"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Usuário")){
				condicao = "u.usuario like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("RG")){
				condicao = "u.rg like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Telefone")){
				condicao = "u.telefone like '%"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Email")){
				condicao = "u.email like '%"+valorPesquisa+"%'";
			}
			
			
			String sql = "SELECT * FROM jogador j INNER JOIN usuario u"
					+ "	ON j.codigoUsuario = u.codigoUsuario"
					+ " where "+condicao+" AND u.ativo = true";
			
			System.out.println(sql);
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Jogador.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Jogador> getListaJogadorTime(int codigoTime){
		try {
			String sql = "SELECT * FROM jogador j INNER JOIN usuario u"
					+ "	ON j.codigoUsuario = u.codigoUsuario"
					+ " where codigoTime = ' " + codigoTime + " ' AND u.ativo = true";
			
			System.out.println(sql);
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Jogador.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Jogador getJogador(int codigo){
		try {
			String sql = "SELECT * FROM jogador j INNER JOIN usuario u"
					+ "	ON j.codigoUsuario = u.codigoUsuario"
					+ " where codigoJogador = '"+codigo+"' AND u.ativo = true";
			return (Jogador) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Jogador.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
