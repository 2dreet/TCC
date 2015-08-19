package utilitario;
import javax.swing.ImageIcon;

public class UtilitarioCrud {
	
	public static ImageIcon getIconeCrud(int modoCrud){
		if(modoCrud == ParametroCrud.getModoCrudDeletar()){
			return new ImageIcon(UtilitarioCrud.class.getResource("/imagem/delete.png"));
		}else{
			return new ImageIcon(UtilitarioCrud.class.getResource("/imagem/salvar.png"));
		}
		
	}

}
