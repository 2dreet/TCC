package utilitario;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class UtilitarioRelatorio {

	public static PdfPTable formatarRelatorio(int[] tamanhoCelulas, String[] cabecalho) throws Throwable {
		PdfPTable tabela = new PdfPTable(cabecalho.length);
		tabela.setWidthPercentage(288 / 5.23f);
        tabela.setWidths(tamanhoCelulas);
		for (int i = 0; i < cabecalho.length; i++) {

		}

		return tabela;
	}

	public static PdfPCell getTotal(int total, int qtdColunas) {
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Total : " + total));
		cell.setColspan(qtdColunas);
		return cell;
	}

}
