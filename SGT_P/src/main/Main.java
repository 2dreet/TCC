package main;
import java.net.InetAddress;

import utilitario.Conectar;
import utilitario.Login;
import utilitario.RetornoCliente;
import Telas.Home;
import Telas.TelaLogin;
import dao.JogadorDao;
import dao.ParametroDao;
import dao.PartidaDao;



public class Main {

	public static void main(String[] args) {
		try {
			Conectar.conectar();
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
