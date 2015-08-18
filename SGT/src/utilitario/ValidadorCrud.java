package utilitario;

import java.awt.event.KeyEvent;
import java.util.Date;

public class ValidadorCrud {

	
	public static boolean isInt(String valor){
		try{
			int x = Integer.parseInt(valor);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean validarEmail(String email){
		try{
			if(email.contains("@")){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public static void  campoData(KeyEvent evt, String texto){
		try{
			String caracteres="0987654321";
			if(!caracteres.contains(evt.getKeyChar()+"")){
				evt.consume();
			}
			if((texto.length() > 9)){
				evt.consume();
			}
		}catch(Exception e){
			
		}
	}
	
	public static boolean validarData(String data){
		try{
			if(data.length() == 10){
				if(MascaraCrud.validarDia(data.substring(0, 2)) && MascaraCrud.validarMes(data.substring(3, 5)) && MascaraCrud.validarAno(data.substring(6, 10))){
					return true;
				}else{
					return false;
				}
			} else {
				return false;
			}
			
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean validarTelefone(String telefone){
		try{
			if(telefone.length() == 13){
				return true;
			} else {
				return false;
			}
			
		}catch(Exception e){
			return false;
		}
	}
	public static boolean validarRg(String rg){
		try{
			if(rg.length() > 0){
				return true;
			} else {
				return false;
			}
			
		}catch(Exception e){
			return false;
		}
	}
	
	
	public static void  campoInt(KeyEvent evt, String texto){
		try{
			String caracteres="0987654321";
			if(!caracteres.contains(evt.getKeyChar()+"")){
				evt.consume();
			}
		}catch(Exception e){
			evt.consume();
		}
	}
	
	public static void  campoTelefone(KeyEvent evt, String texto){
		try{
			String caracteres="0987654321";
			if(!caracteres.contains(evt.getKeyChar()+"")){
				evt.consume();
			}
			if((texto.length() > 12)){
				evt.consume();
			}
		}catch(Exception e){
			evt.consume();
		}
	}
	
	public static boolean isCpf(String cpf) {
		try {
			if (cpf.equals("00000000000") || cpf.equals("11111111111")
					|| cpf.equals("22222222222") || cpf.equals("33333333333")
					|| cpf.equals("44444444444") || cpf.equals("55555555555")
					|| cpf.equals("66666666666") || cpf.equals("77777777777")
					|| cpf.equals("88888888888") || cpf.equals("99999999999")
					|| (cpf.length() != 11)) {
				return (false);
			}
			char dig10, dig11;
			int sm, i, r, num, peso;
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			} else {
				dig10 = (char) (r + 48);
			}
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig11 = '0';
			} else {
				dig11 = (char) (r + 48);
			}
			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
				return (true);
			} else {
				return (false);
			}
		} catch (Exception e) {
			return false;
		}
	}

}
