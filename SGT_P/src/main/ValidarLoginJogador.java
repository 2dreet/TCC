package main;

import dao.JogadorBanimentoDao;
import dao.JogadorDao;
import entidade.Jogador;
import entidade.Usuario;

public class ValidarLoginJogador {

	public static boolean validarLogin(Usuario usuario) {
		try {
			Jogador jogador = JogadorDao.getJogadorPorUsuario(Login.usuario
					.getCodigoUsuario());
			if (JogadorDao.getJogadorPartida(Login.partida.getCodigoPartida(),
					jogador.getCodigoJogador()) != null) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean jogadorBanido(int codigoJogador) {
		if (JogadorBanimentoDao.getLista(codigoJogador) != null
				&& JogadorBanimentoDao.getLista(codigoJogador).size() > 0) {
			return true;
		}
		return false;
	}
}
