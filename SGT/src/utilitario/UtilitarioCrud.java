package utilitario;

import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;

public class UtilitarioCrud {

	public static ImageIcon getIconeCrud(int modoCrud) {
		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			return new ImageIcon(
					UtilitarioCrud.class.getResource("/imagem/delete.png"));
		} else {
			return new ImageIcon(
					UtilitarioCrud.class.getResource("/imagem/salvar.png"));
		}

	}

	public static Date getData(String data) {
		Calendar c = Calendar.getInstance();
		if (ValidadorCrud.validarData(data)) {
			c.set(Integer.parseInt(data.substring(6, 10)),
					Integer.parseInt(data.substring(3, 5)) - 1,
					Integer.parseInt(data.substring(0, 2)));
		}
		return c.getTime();
	}

	
	
}
