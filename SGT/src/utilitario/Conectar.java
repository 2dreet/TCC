package utilitario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ToolTipManager;

import componente.Menssage;

public class Conectar {

	public static String host;
	public static String usuario;
	public static String senha;

	public static void conectar() {
		try {
			FileReader arq = new FileReader("config.txt");
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = null;
			boolean continua = true;
			while (continua) {
				linha = lerArq.readLine();
				if (linha == null) {
					continua = false;
				} else {
					System.out.printf("%s\n", linha);

					if (linha.contains("host=")) {
						host = linha.replace("host=", "").replace(";", "");
					}

					if (linha.contains("usuario=")) {
						usuario = linha.replace("usuario=", "").replace(";", "");
					}

					if (linha.contains("senha=")) {
						senha = linha.replace("senha=", "").replace(";", "");
					}
					
				}

			}
			arq.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		String msgErro = "";
		if (host == null || host.trim().isEmpty()) {
			erro("Deve informar o Host do Servidor\n");
		}

		if (usuario == null || usuario.trim().isEmpty()) {
			erro("Deve informar o Usuario do Servidor\n");
		}

		if (senha == null || senha.trim().isEmpty()) {
			erro("Deve informar a Senha do Servidor\n");
		}
	
	}

	public static void erro(String msgErro) {
		msgErro += "\nDeve arrumar o parametro para que o sistema inicie!";
		Menssage.setMenssage("Erro", msgErro,
				ParametroCrud.getModoCrudDeletar(), null);
		System.exit(0);
	}
}
