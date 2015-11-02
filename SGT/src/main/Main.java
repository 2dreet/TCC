package main;

import javax.swing.UnsupportedLookAndFeelException;

import dao.FuncionarioDao;
import dao.ParametroDao;
import dao.PermissaoDao;
import entidade.Permissao;
import tela.Home;
import tela.TelaLogin;
import utilitario.Login;
import utilitario.ValidadorCrud;

public class Main {

	public static void main(String[] args) {
		try {
			ParametroDao.atualizaBanco();
			TelaLogin tl = new TelaLogin();
			tl.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
