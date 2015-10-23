package exemplos;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import utilitario.ExportarXls;
import utilitario.MascaraCrud;
import dao.EntityManagerLocal;
import dao.TimeDao;
import entidade.Time;

public class ExemploRelatorio {

	 public static boolean exportarXls() {
	        boolean exportado = false;
	        String dataAtual = MascaraCrud.macaraDataBanco(new Date()).replace("/", "-");
	        String nome = "relatorioTimes"+dataAtual;
	        File arquivo = new File("c:/"+nome+".xls");
	        FileOutputStream out = null;
	        String linhaAtual = null;
	        try {
	            ResultSet rs = EntityManagerLocal.getResultSet("SELECT * FROM time ORDER BY descricao");
	            if (rs.next()) {
	                out = new FileOutputStream(arquivo);
	                ExportarXls exportar = new ExportarXls(arquivo);
	                exportar.addLinha(new Object[]{"Codigo", "Descricao", "Data Cadastro", "Ativo"});
	                exportar.setTamanhoColunas(new int[]{6000, 6000, 6000, 0, 6000});
	                rs.beforeFirst();
	                int i = 0;
	                while(rs.next()) {
	                	String ativo = "Nao";
	                	if(rs.getBoolean("ativo")){
	                		ativo = "Sim";
	                	}
	                    exportar.addLinha(new Object[]{rs.getInt("codigoTime"), rs.getString("descricao"), MascaraCrud.macaraDataBanco(rs.getDate("dataCadastro")), ativo});
	                    i++;
	                }
	                exportar.exportarArquivo(nome);
	                if (exportar != null && exportar.getArquivo().exists()) {
	                    exportado = true;
	                }
	                out.close();
	            } else {
	                JOptionPane.showMessageDialog(null, "Sem Time!");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return exportado;
	    }
	public static void main(String[] args){
		if(exportarXls()){
			
		}
		
	}
}
