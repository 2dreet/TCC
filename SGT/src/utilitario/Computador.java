package utilitario;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.JogadorDao;
import dao.PcDao;
import entidade.Jogador;
import entidade.Partida;
import entidade.Pc;
import entidade.PcPartida;
import entidade.Time;

public class Computador {

	private static Thread t;
	
	public static Thread t2;

	public static void stopPCconectado() {
		try {
			t.stop();
			t2.stop();
		} catch (Exception e) {

		}
	}
	
	public static void stopT2() {
		try {
			t2.stop();
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

	public static boolean verificaPcLogadoJogador(Pc pc, Jogador jogador) {
		try {
			Socket s = new Socket(pc.getIp(), 3030);
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println(jogador.getUsuario().getCodigoUsuario());
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

	public static void getPcConectado(Pc pc, Container parent) {
		t = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
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
						sleep(3000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	public static void getJogadorConectado(Partida partida, Time time, List<Container> listParent) {
		try {
			List<Jogador> listaJogador = JogadorDao
					.getListaJogadorTitularTime(time
							.getCodigoTime());
			List<PcPartida> listaPc = PcDao
					.getListaPcPartida(partida.getCodigoPartida());
			int i = 0;
			boolean logados = false;
			for (Container parent : listParent) {
				if(listaPc != null && listaPc.size() > 0){
					parent.removeAll();
					JLabel lbPc = null;
					for (PcPartida pcPartida : listaPc) {
						if (verificaPcLogadoJogador(pcPartida.getPc(), listaJogador.get(i))) {
							lbPc = new JLabel(new ImageIcon(Computador.class.getResource("/imagem/pcon.png")));
						} else {
							lbPc = new JLabel(new ImageIcon(Computador.class.getResource("/imagem/pcoff.png")));
						}
					}
					lbPc.setBounds(10, 0, 35, 35);
					
					JLabel lbcJogador;
					lbcJogador = new JLabel("Jogador: "+listaJogador.get(i).getUsuario().getCodigoUsuario()+" - "+listaJogador.get(i).getUsuario().getNome());
					lbcJogador.setFont(UtilitarioTela.getFont(12));
					lbcJogador.setForeground(UtilitarioTela.getFontColorPadrao());
					lbcJogador.setBounds(100, 0, 300, 15);
					
					JLabel lbcIp;
					lbcIp = new JLabel("Pc IP: ");
					lbcIp.setFont(UtilitarioTela.getFont(12));
					lbcIp.setForeground(UtilitarioTela.getFontColorPadrao());
					lbcIp.setBounds(100, 16, 200, 15);
					
					parent.add(lbPc);
					parent.add(lbcJogador);
					parent.add(lbcIp);
				}else{
					parent.removeAll();
					JLabel lbPc;
					lbPc = new JLabel(new ImageIcon(Computador.class.getResource("/imagem/pcoff.png")));
					lbPc.setBounds(10, 0, 35, 35);
					
					JLabel lbcJogador;
					lbcJogador = new JLabel("Jogador: "+listaJogador.get(i).getUsuario().getCodigoUsuario()+" - "+listaJogador.get(i).getUsuario().getNome());
					lbcJogador.setFont(UtilitarioTela.getFont(12));
					lbcJogador.setForeground(UtilitarioTela.getFontColorPadrao());
					lbcJogador.setBounds(100, 0, 300, 15);
					
					JLabel lbcIp;
					lbcIp = new JLabel("Pc IP: ");
					lbcIp.setFont(UtilitarioTela.getFont(12));
					lbcIp.setForeground(UtilitarioTela.getFontColorPadrao());
					lbcIp.setBounds(100, 16, 200, 15);
					
					parent.add(lbPc);
					parent.add(lbcJogador);
					parent.add(lbcIp);
				}
				i++;
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
}
