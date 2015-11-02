package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.Jogador;
import entidade.JogadorBanimento;


public class JogadorBanimentoDao {
	
	public static boolean jogadorJaBanido(int codigoJogador){
		try{
			String sql = "SELECT * FROM jogador_banimento where codigoJogador = '" + codigoJogador + "'";
			List<JogadorBanimento> lista = EntityManagerLocal.getEntityManager().createNativeQuery(sql, JogadorBanimento.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
			if(lista == null || lista.size() == 0){
				return false;
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean jogadorBanido(int codigoJogador){
		try{
			String sql = "SELECT * FROM jogador_banimento where codigoJogador = '" + codigoJogador + "' AND ativo = true";
			List<JogadorBanimento> lista = EntityManagerLocal.getEntityManager().createNativeQuery(sql, JogadorBanimento.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
			if(lista == null || lista.size() == 0){
				return false;
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static JogadorBanimento getJogadorBanimento(int codigoJogadorBanimento){
		try {
			String sql = "SELECT * FROM jogador_banimento where codigoJogBan = '"+codigoJogadorBanimento+"' AND ativo = true";
			return (JogadorBanimento) EntityManagerLocal.getEntityManager().createNativeQuery(sql, JogadorBanimento.class)
					.setHint(QueryHints.REFRESH, HintValues.TRUE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<JogadorBanimento> getListaPesquisa(String metodoPesquisa, String valorPesquisa, int codigoJogador){
		try {
			String condicao = "";
			if(metodoPesquisa.equals("Código")){
				condicao = " codigoJogBan like '"+valorPesquisa+"%'";
			} else if (metodoPesquisa.equals("Descrição")){
				condicao = " descricao like '%"+valorPesquisa+"%'";
			} 
			
			String sql = "SELECT * FROM jogador_banimento where codigoJogador = '"+codigoJogador+"' AND "+condicao+" AND ativo = true";
			
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, JogadorBanimento.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static List<JogadorBanimento> getHistorico(int codigoJogador){
		try {
			
			String sql = "SELECT * FROM jogador_banimento where codigoJogador = '"+codigoJogador+"' ";
			
			
			return EntityManagerLocal.getEntityManager().createNativeQuery(sql, JogadorBanimento.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
