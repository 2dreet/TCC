package utilitario;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import dao.JogadorDao;
import entidade.Jogador;
import entidade.Pc;

public class Computador {

	private static Thread t;
	public static Thread t2;

	public static void stopPCconectado() {
		try {
			t2.interrupt();
			t.interrupt();
		} catch (Exception e) {

		}
	}
	
	public static boolean verificaPc(Pc pc) {
		try {
			Socket s = new Socket(pc.getIp(), 2020);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			String retorno = input.readLine();
			if (retorno != null && retorno.equals("true")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public static Jogador verificaJogadorLogado(Pc pc) {
		try {
			if(pc.getIp() != null && !pc.getIp().trim().isEmpty()){
				Socket s = new Socket(pc.getIp(), 3030);
				BufferedReader input = new BufferedReader(new InputStreamReader(
						s.getInputStream()));
				String retorno = input.readLine();
				if (retorno != null) {
					return JogadorDao.getJogadorPorUsuario(Integer.parseInt(retorno));
				} else {
					return null;
				}
			}

		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public static void getPcConectado(Pc pc, Container parent) {
		t = new Thread() {
			@Override
			public void run() {
				try {
					while (!interrupted()) {
						parent.removeAll();
						JLabel lbPc;
						if (verificaPc(pc)) {
							lbPc = new JLabel(new ImageIcon(
									Computador.class
											.getResource("/imagem/pcon.png")));
						} else {
							lbPc = new JLabel(new ImageIcon(
									Computador.class
											.getResource("/imagem/pcoff.png")));
						}
						lbPc.setBounds(7, 7, 35, 35);
						parent.add(lbPc);
						parent.repaint();
						if(interrupted()) return;
						sleep(3000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}


}
