package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import dao.JogadorDao;

public class Main {

	public static void main(String[] args) {
		
		Login.usuario = JogadorDao.getJogador(41).getUsuario();
//		
//		try {
//			ServerSocket listener = new ServerSocket(2020);
//			try {
//				while (true) {
//					Socket socket = listener.accept();
//					try {
//						PrintWriter out = new PrintWriter(
//								socket.getOutputStream(), true);
//						out.println(true);
//						System.out.print(socket.getOutputStream());
//					} finally {
//						socket.close();
//					}
//				}
//			} finally {
//				listener.close();
//			}
//		} catch (Exception e) {
//			System.out.print(e);
//		}
		
		try {
			ServerSocket listener = new ServerSocket(3030);
			try {
				while (true) {
					Socket socket = listener.accept();
					try {
						BufferedReader input = new BufferedReader(new InputStreamReader(
								socket.getInputStream()));
						String retorno = input.readLine();
						System.out.println(retorno);
						PrintWriter out = new PrintWriter(
								socket.getOutputStream(), true);
						if(Login.usuario != null && Integer.parseInt(retorno) == Login.usuario.getCodigoUsuario()){
							out.println(true);
						} else {
							out.println(false);
						}
						System.out.print(socket.getOutputStream());
					} finally {
						socket.close();
					}
				}
			} finally {
				listener.close();
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
