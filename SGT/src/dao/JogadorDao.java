package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Jogador;
import entidade.JogadorPartida;
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
			} else if (metodoPesquisa.equals("Cpf")){
				condicao = "u.cpf like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Telefone")){
				condicao = "u.telefone like '%"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Email")){
				condicao = "u.email like '%"+valorPesquisa+"%'";
			}
			
			
			String sql = "SELECT * FROM jogador j INNER JOIN usuario u"
					+ "	ON j.codigoUsuario = u.codigoUsuario"
					+ " where "+condicao+" AND u.ativo = true";
			if (metodoPesquisa.equals("Cpf")){
				sql += " OR u.ativo = false";
			}
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Jogador.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<JogadorPartida> getListaJogadorPartidaTime(int codigoPartida, int codigoTime){
		try {
			String sql = "SELECT * FROM jogador_partida where codigoTime = "+codigoTime+" AND codigoPartida = "+codigoPartida;
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, JogadorPartida.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static JogadorPartida getJogadorPartidaTime(int codigoPartida, int codigoTime, int codigoJogador){
		try {
			String sql = "SELECT * FROM jogador_partida where codigoJogador = "+codigoJogador+" AND codigoTime = "+codigoTime+" AND codigoPartida = "+codigoPartida;
			return (JogadorPartida) EntityManagerLocal.getEntityManager().createNativeQuery(sql, JogadorPartida.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Jogador> getListaJogadorSemTime(String metodoPesquisa, String valorPesquisa){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " j.codigoJogador like '"+valorPesquisa+"%'";
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
			
			
			String sql = "SELECT * FROM jogador j INNER JOIN usuario u"
					+ "	ON j.codigoUsuario = u.codigoUsuario"
					+ " where "+condicao+" AND u.ativo = true AND j.codigoTime is null";
			
			System.out.println(sql);
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Jogador.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Jogador> getListaJogadorDoTime(String metodoPesquisa, String valorPesquisa, int codigoTime){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " j.codigoJogador like '"+valorPesquisa+"%'";
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
			
			
			String sql = "SELECT * FROM jogador j INNER JOIN usuario u"
					+ "	ON j.codigoUsuario = u.codigoUsuario"
					+ " where "+condicao+" AND u.ativo = true "
							+ " AND j.codigoJogador not in (SELECT codigoJogador FROM jogador_banimento where ativo = true)"
							+ " AND j.codigoTime ="+codigoTime;
			
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
					+ " where codigoTime = ' " + codigoTime + " ' AND u.ativo = true AND j.titular = false";
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Jogador.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<Jogador> getListaJogadorTitularTime(int codigoTime){
		try {
			String sql = "SELECT * FROM jogador j INNER JOIN usuario u"
					+ "	ON j.codigoUsuario = u.codigoUsuario"
					+ " where codigoTime = ' " + codigoTime + " ' AND u.ativo = true AND j.titular = true";
			
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, Jogador.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Long getQtdeJogadorTitular(int codigoTime){
		try {
			String sql = "SELECT count(*) as cont FROM jogador j INNER JOIN usuario u"
					+ "	ON j.codigoUsuario = u.codigoUsuario"
					+ " where codigoTime = ' " + codigoTime + " ' AND u.ativo = true AND j.titular = true";
			Query q = EntityManagerLocal.getEntityManager().createNativeQuery( sql );
			return ( (Long) q.getSingleResult() ).longValue();
					
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
	
	public static Jogador getJogadorPorUsuario(int codigo){
		try {
			String sql = "SELECT * FROM jogador j INNER JOIN usuario u"
					+ "	ON j.codigoUsuario = u.codigoUsuario"
					+ " where u.codigoUsuario = '"+codigo+"' AND u.ativo = true";
			return (Jogador) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Jogador.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Jogador getAllJogador(int codigo){
		try {
			String sql = "SELECT * FROM jogador j INNER JOIN usuario u"
					+ "	ON j.codigoUsuario = u.codigoUsuario"
					+ " where codigoJogador = '"+codigo+"'";
			return (Jogador) EntityManagerLocal.getEntityManager().createNativeQuery(sql, Jogador.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	
	
	public static void desvincularJogadorTime(int codigoTime){
		try {
			String sql = "UPDATE jogador SET codigoTime = null WHERE codigoTime = '"+codigoTime+"'";
			EntityManagerLocal.getEntityManager().createNativeQuery(sql).executeUpdate();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
	}
	
}
