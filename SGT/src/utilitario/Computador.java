package utilitario;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import entidade.Pc;

public class Computador {

	private static Thread t;

	public static void stopPCconectado(){
		try{
			t.stop();
		}catch(Exception e){
			
		}
	}
	
	public static boolean verificaPc(Pc pc){
		try{
			Socket s = new Socket(pc.getIp(),2020);
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String retorno = input.readLine();
			if(retorno != null && retorno.equals("true")){
				return true;
			} else{
				return false;
			}
			
		}catch(Exception e ){
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
						if(verificaPc(pc)){
							lbPc = new JLabel(new ImageIcon(Computador.class.getResource("/imagem/pcon.png")));
						}else{
							lbPc = new JLabel(new ImageIcon(Computador.class.getResource("/imagem/pcoff.png")));
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
}
