package exemplos;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JRViewer;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import utilitario.ExportarXls;
import utilitario.MascaraCrud;
import dao.EntityManagerLocal;
import dao.RelatorioDao;
import dao.TimeDao;
import entidade.Time;

public class ExemploRelatorio {

	private FastReportBuilder drb;
	protected ResultSet rs = null;
	public JasperPrint print;
	private boolean imprimirDireto;
	private JRViewer jr;
	private JFrame fr = null;
	int totalPaginas = 0;

	public JFrame getRelatorio() throws ColumnBuilderException, ClassNotFoundException {

		rs = RelatorioDao.getJogadoresMediaModalidade();
		
		Style headerAlignRight = new Style();
		headerAlignRight.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerAlignRight.setHorizontalAlign(HorizontalAlign.RIGHT);
		headerAlignRight.setVerticalAlign(VerticalAlign.MIDDLE);
		headerAlignRight.setStretchWithOverflow(true);
		headerAlignRight.setTransparent(false);
		headerAlignRight.setBackgroundColor(Color.LIGHT_GRAY);
		headerAlignRight.setBorder(Border.THIN);

		Style headerAlignLeft = new Style();
		headerAlignLeft.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerAlignLeft.setHorizontalAlign(HorizontalAlign.LEFT);
		headerAlignLeft.setVerticalAlign(VerticalAlign.MIDDLE);
		headerAlignLeft.setStretchWithOverflow(true);
		headerAlignLeft.setTransparent(false);
		headerAlignLeft.setBackgroundColor(Color.LIGHT_GRAY);
		headerAlignLeft.setBorder(Border.THIN);

		Style headerAlignCenter = new Style();
		headerAlignCenter.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerAlignCenter.setHorizontalAlign(HorizontalAlign.CENTER);
		headerAlignCenter.setVerticalAlign(VerticalAlign.MIDDLE);
		headerAlignCenter.setStretchWithOverflow(true);
		headerAlignCenter.setTransparent(false);
		headerAlignCenter.setBackgroundColor(Color.LIGHT_GRAY);
		headerAlignCenter.setBorder(Border.THIN);

		Style headerAlignJustify = new Style();
		headerAlignJustify.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerAlignJustify.setHorizontalAlign(HorizontalAlign.JUSTIFY);
		headerAlignJustify.setVerticalAlign(VerticalAlign.MIDDLE);
		headerAlignJustify.setStretchWithOverflow(true);
		headerAlignJustify.setTransparent(false);
		headerAlignJustify.setBackgroundColor(Color.LIGHT_GRAY);
		headerAlignJustify.setBorder(Border.THIN);

		try {
			drb = new FastReportBuilder();
			
			drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y,
					AutoText.POSITION_HEADER, AutoText.ALIGMENT_RIGHT);

			drb.addAutoText("SGTG - Sistema Gerenciador de Torneio Gamer",
					AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 600);
			
			drb.addAutoText("Emitido em " + MascaraCrud.macaraDataBanco(new Date()),
					AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 250);

			drb.setTitle("Média de Participante Por Modalidade");

			drb.setPrintBackgroundOnOddRows(true);
			drb.setUseFullPageWidth(true);
			drb.setPageSizeAndOrientation(Page.Page_A4_Portrait());
			drb.setColumnsPerPage(new Integer(1));
			
			drb.addColumn("Modalidade", "descricao", String.class.getName(),
					50, false);
			drb.addColumn("Sexo", "sexo",
					String.class.getName(), 50, true);
			drb.addColumn("Idade", "idade",
					String.class.getName(), 50, true);
			drb.addColumn("Quantidade", "qtdSexo", String.class.getName(),
					50, false);
		
			drb.getColumn(0).setHeaderStyle(headerAlignCenter);
			drb.getColumn(1).setHeaderStyle(headerAlignCenter);
			drb.getColumn(2).setHeaderStyle(headerAlignCenter);
			drb.getColumn(3).setHeaderStyle(headerAlignCenter);
			
			drb.addGlobalFooterVariable(drb.getColumn(3), DJCalculation.SUM, headerAlignJustify);

            drb.setGrandTotalLegend("Total Geral :  ");
            drb.setGrandTotalLegendStyle(headerAlignJustify);

			
         	DynamicReport dr = drb.build();

			JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
			print = DynamicJasperHelper.generateJasperPrint(dr,
					new ClassicLayoutManager(), jrRS);
			totalPaginas = print.getPages().size();
			if (imprimirDireto) {
				JasperPrintManager.printReport(print, false);
			} else {
				if (totalPaginas > 0) {
					jr = new JRViewer(print);
					fr = new JFrame();
					fr.setTitle("Teste");
					fr.setSize(new Dimension(800, 600));
					fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
					fr.setContentPane(jr);
				}
			}
		} catch (JRException ex) {
			Logger.getLogger(ExemploRelatorio.class.getName()).log(Level.SEVERE,
					null, ex);
		} 
		return fr;
	}
	
}
