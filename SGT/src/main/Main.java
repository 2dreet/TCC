package main;

import javax.swing.UnsupportedLookAndFeelException;

import dao.PermissaoDao;
import entidade.Permissao;
import tela.Home;
import utilitario.ValidadorCrud;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		Home home = new Home();
		home.setVisible(true);
	}

}
