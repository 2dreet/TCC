package utilitario;

public class ParametroCrud {

	
	private static int modoCrudNovo = 1;
	private static int modoCrudAlterar = 2;
	private static int modoCrudDeletar = 3;
	private static int modoCrudVisualizar = 4;
	private static int modoInfo = 5;
	private static int modoAlerta = 6;
	private static int modoErro = 7;
	
	public static int getModoCrudNovo() {
		return modoCrudNovo;
	}
	
	public static int getModoCrudAlterar() {
		return modoCrudAlterar;
	}
	
	public static int getModoCrudDeletar() {
		return modoCrudDeletar;
	}

	public static int getModoInfo() {
		return modoInfo;
	}

	public static int getModoAlerta() {
		return modoAlerta;
	}

	public static int getModoErro() {
		return modoErro;
	}
	
	public static int getModoVisualizar(){
		return modoCrudVisualizar;
	}
	
}
