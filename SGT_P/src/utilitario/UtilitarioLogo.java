package utilitario;

public class UtilitarioLogo {

	public static boolean validarLogo(String logo){
		try{
			logo =  logo.substring( logo.length()-3, logo.length());
			logo = logo.toLowerCase();
			if(logo.equals("png")){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}
	
}
