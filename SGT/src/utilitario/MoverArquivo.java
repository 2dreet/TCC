package utilitario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MoverArquivo {

	public static void copyFile(File source, File destination) throws IOException {  
	     if (destination.exists())  
	         destination.delete();  
	  
	     FileChannel sourceChannel = null;  
	     FileChannel destinationChannel = null;  
	  
	     try {  
	         sourceChannel = new FileInputStream(source).getChannel();  
	         destinationChannel = new FileOutputStream(destination).getChannel();  
	         sourceChannel.transferTo(0, sourceChannel.size(),  
	                 destinationChannel);  
	     } finally {  
	         if (sourceChannel != null && sourceChannel.isOpen())  
	             sourceChannel.close();  
	         if (destinationChannel != null && destinationChannel.isOpen())  
	             destinationChannel.close();  
	    }  
	}
	
	
	public static File getLocalLogo(File arquivo){
		try{
			Files.createDirectories(Paths.get("logo/"));
		}catch(Exception e){
			
		}
		return new File("logo/"+arquivo.getName());
	}
	
}
