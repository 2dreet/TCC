package main;
import java.net.InetAddress;

import utilitario.RetornoCliente;
import Telas.Home;
import Telas.TelaLogin;
import dao.ParametroDao;



public class Main {

	public static void main(String[] args) {
		try {
//			Home home = new Home();
//			home.setVisible(true);
			RetornoCliente.aberto();
			RetornoCliente.logado();
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
