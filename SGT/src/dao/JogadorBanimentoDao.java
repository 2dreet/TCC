package dao;

import java.util.List;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import entidade.JogadorBanimento;


public class JogadorBanimentoDao {
	
	public static boolean jogadorBanido(int codigoJogador){
		try{
			String sql = "SELECT * FROM jogadorbanimento where codigoJogador = '" + codigoJogador + "' AND ativo = true";
			List<JogadorBanimento> lista = EntityManagerLocal.getEntityManager().createNativeQuery(sql, JogadorBanimento.class).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
			return false;
		}catch(Exception e){
			return true;
		}
		
	}

}
