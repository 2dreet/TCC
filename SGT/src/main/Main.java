package main;

import javax.swing.UnsupportedLookAndFeelException;

import dao.ParametroDao;
import dao.PermissaoDao;
import entidade.Permissao;
import tela.Home;
import utilitario.ValidadorCrud;

public class Main {

	public static void main(String[] args) {
		try {
			ParametroDao.atualizaBanco();
			Home home = new Home();
			home.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

}
