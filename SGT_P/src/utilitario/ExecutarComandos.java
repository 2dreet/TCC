package utilitario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import componente.Menssage;

public class ExecutarComandos {

	public synchronized static String execCommand(final String commandLine) throws IOException {  
		  
	    boolean success = false;  
	    String result;  
	  
	    Process p;  
	    BufferedReader input;  
	    StringBuffer cmdOut = new StringBuffer();  
	    String lineOut = null;  
	    int numberOfOutline = 0;  
	  
	    try {  
	  
	        p = Runtime.getRuntime().exec(commandLine);  
	  
	        input = new BufferedReader(new InputStreamReader(p.getInputStream()));  
	  
	        while ((lineOut = input.readLine()) != null) {  
	            if (numberOfOutline > 0) {  
	                cmdOut.append("\n");  
	            }  
	            cmdOut.append(lineOut);  
	            numberOfOutline++;  
	        }  
	  
	        result = cmdOut.toString();  
	  
	        success = true;  
	  
	        input.close();  
	          
	    } catch (IOException e) {  
	        String msgErro = ("Falha ao executar comando\nSoftware Não encontrado ou não instalado\nConferir Arquivo config.txt");
	        Menssage.setMenssage("Erro", msgErro,
					ParametroCrud.getModoCrudDeletar(), null);
	        return null;
	    }  
	  
	    // Se não executou com sucesso, lança a falha  
	    if (!success) {  
	       return null;
	    }  
	  
	    return result;  
	  
	}
	
}
