package main;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RetornoCliente {

	private static Thread tLogado;
	private static Thread tAberto;

	public static void deslogar() {
		try {
			tLogado.interrupt();
		} catch (Exception e) {

		}
	}

	public static void aberto() {
		tAberto = new Thread() {
			@Override
			public void run() {
				try {
					while (!interrupted()) {
						try {
							ServerSocket listener = new ServerSocket(2020);
							try {
								while (!interrupted()) {
									Socket socket = listener.accept();
									try {
										PrintWriter out = new PrintWriter(
												socket.getOutputStream(), true);
										out.println(true);
										System.out.print(socket
												.getOutputStream());
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
				} catch (Exception e) {
					System.out.print(e);
				}
			}
		};
		tAberto.start();
	}

	public static void logado() {
		tLogado = new Thread() {
			@Override
			public void run() {
				try {
					while (!interrupted()) {
						ServerSocket listener = new ServerSocket(3030);
						try {
							while (!interrupted()) {
								Socket socket = listener.accept();
								try {
									PrintWriter out = new PrintWriter(
											socket.getOutputStream(), true);
									if (Login.usuario != null) {
										out.println(Login.usuario
												.getCodigoUsuario());
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
					}
				} catch (Exception e) {
					System.out.print(e);
				}
			}
		};
		tLogado.start();
	}
}
