package main;

import javax.swing.UnsupportedLookAndFeelException;

import dao.FuncionarioDao;
import dao.ParametroDao;
import dao.PermissaoDao;
import entidade.Permissao;
import tela.Home;
import utilitario.Login;
import utilitario.ValidadorCrud;

public class Main {

	public static void main(String[] args) {
		try {
			Login.usuario = FuncionarioDao.getFuncionario(1);
			ParametroDao.atualizaBanco();
			Home home = new Home();
			home.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

}
