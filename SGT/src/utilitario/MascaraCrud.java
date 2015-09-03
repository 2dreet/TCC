package utilitario;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MascaraCrud {
	
	private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	public static String mascaraTelefone(String telefone){
		if(telefone.length() == 2){
			telefone = "("+telefone+")";
		} else if(telefone.length() == 8){
			telefone += "-";
		} else if(telefone.length() == 13){
			telefone = "("+telefone.substring(1, 3)+")"+telefone.substring(4, 8)+"-"+telefone.substring(9, 13);
		}
		
		
		return telefone;
	}
	
	public static String macaraDataBanco(Date data){
		if(data==null){
			return "";
		}
		return format.format(data);
	}
	
	public static String mascaraTelefoneResult(String telefone){
		 if(telefone.length() == 10){
				telefone = "("+telefone.substring(0, 2)+")"+telefone.substring(2, 6)+"-"+telefone.substring(6, 10);
		}
		 return telefone;
	}
	
	public static String mascaraData(String data){
		
		if(data.length() == 2){
			if(validarDia(data)){
				data += "/";
			}else{
				data = "";
			}
		} else if(data.length() == 5){
			if(validarDia(data.substring(0, 2)) && validarMes(data.substring(3, 5))){
				data += "/";
			}else{
				if(validarDia(data.substring(0, 2))){
					data = data.substring(0, 2) + "/";
				}else{
					data = "";
				}
			}
		} else if(data.length() == 10){
			if(validarDia(data.substring(0, 2)) && validarMes(data.substring(3, 5)) && validarAno(data.substring(6, 10))){
				
			}else{
				if(validarDia(data.substring(0, 2)) && validarMes(data.substring(3, 5))){
					data = data.substring(0, 2) + "/" + data.substring(3, 5)+ "/";
				}else{
					if(validarDia(data.substring(0, 2))){
						data = data.substring(0, 2) + "/";
					}else{
						data = "";
					}
				}
			}
			
		}
		return data;
	}
	
	public static boolean validarDia(String dia){
		if(ValidadorCrud.isInt(dia) && Integer.parseInt(dia) > 0 && Integer.parseInt(dia) < 32){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean validarMes(String mes){
		if(ValidadorCrud.isInt(mes) && Integer.parseInt(mes) > 0 && Integer.parseInt(mes) < 13){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean validarAno(String ano){
		if(ValidadorCrud.isInt(ano) && Integer.parseInt(ano) > 1900){
			return true;
		}else{
			return false;
		}
	}
	
}
