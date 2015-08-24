package utilitario;

import javax.swing.JFrame;

public class Parametros {

	private static int sexoMasculino = 1;
	private static int sexoFeminino = 2;
	private static int permissaoJogador =1;
	private static JFrame pai = null;
	
	public static String getSexo(int sexo){
		if(sexo == sexoMasculino){
			return "Masculino";
		}
		else if(sexo == sexoFeminino){
			return "Feminino";	
		}
		return "";
	}
	
	public static int getSexoMasculino() {
		return sexoMasculino;
	}
	public static int getSexoFeminino() {
		return sexoFeminino;
	}
	
	public static int getPermissaoJogador() {
		return permissaoJogador;
	}
	public static JFrame getPai() {
		return pai;
	}
	public static void setPai(JFrame pai) {
		Parametros.pai = pai;
	}
	
	
}
