package main;

public class PcPartidaLogado {

	public static boolean isPcLogado(){
		if(Login.usuario != null){
			return true;
		}
		return false;
	}
	
}
