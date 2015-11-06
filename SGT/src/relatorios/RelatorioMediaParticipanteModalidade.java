package relatorios;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
import componente.ComboBox;
import componente.DadoComIcone;
import componente.Menssage;
import dao.BanimentoDao;
import dao.MarcaDao;
import dao.ModalidadeDao;
import dao.RelatorioDao;
import dao.TimeDao;
import dialog.DialogCrudBanimento;
import dialog.DialogCrudMarca;
import entidade.Banimento;
import entidade.Jogador;
import entidade.Marca;
import entidade.Modalidade;
import entidade.Time;
import exemplos.ExemploRelatorio;
import utilitario.BordaSombreada;
import utilitario.MascaraCrud;
import utilitario.ParametroCrud;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import utilitario.ValidadorCrud;
import menu.MenuConfiguracoes;
import menu.MenuJogador;

public class RelatorioMediaParticipanteModalidade extends JPanel {

	private JLabel lblHeader;
	private JPanel meio;
	private JTextField txDataInicial;
	private JTextField txDataFinal;
	private ComboBox cbModalidade;
	private FastReportBuilder drb;
	protected ResultSet rs = null;
	public JasperPrint print;
	private boolean imprimirDireto;
	private JRViewer jr;
	private JFrame fr = null;
	int totalPaginas = 0;

	public RelatorioMediaParticipanteModalidade(Dimension tamanho) {

		setSize(tamanho);
		setLayout(null);
		setBackground(null);

		meio = new JPanel();
		meio.setSize(500, getHeight() - 50);
		meio.setLocation((getWidth() / 2) - 250, 0);
		meio.setLayout(null);
		meio.setBackground(UtilitarioTela.getFundoCrud());
		meio.setBorder(null);
		add(meio);

		lblHeader = new JLabel("Média de Participante Por Modalidade");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 10, meio.getWidth(), 30);
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lblHeader);

		JLabel lbModalidade = new JLabel("Modalidade :");
		lbModalidade.setBounds(20, 60, 100, 20);
		lbModalidade.setFont(UtilitarioTela.getFont(14));
		lbModalidade.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbModalidade);

		cbModalidade = new ComboBox(new Dimension(200, 25));
		DefaultComboBoxModel comboModel = (DefaultComboBoxModel) cbModalidade
				.getModel();
		comboModel.removeAllElements();
		List<Modalidade> list = ModalidadeDao.getListaModalidade();
		comboModel.addElement("< Todos >");
		for (Modalidade m : list) {
			comboModel.addElement(m);
		}

		cbModalidade.setLocation(130, 60);
		meio.add(cbModalidade);

		JLabel lbDataInicial = new JLabel("Data Inicial :");
		lbDataInicial.setBounds(20, 90, 100, 20);
		lbDataInicial.setFont(UtilitarioTela.getFont(14));
		lbDataInicial.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbDataInicial);

		txDataInicial = new JTextField();
		txDataInicial.setColumns(50);
		txDataInicial.setBounds(130, 90, 100, 25);
		txDataInicial.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txDataInicial.setBorder(UtilitarioTela.jTextFieldComFocus());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				txDataInicial.setBorder(UtilitarioTela.jTextFieldNormal());
			}
		});
		txDataInicial.setLayout(null);
		txDataInicial.setBorder(UtilitarioTela.jTextFieldNormal());
		txDataInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				ValidadorCrud.campoData(arg0, txDataInicial.getText());
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				txDataInicial.setText(MascaraCrud.mascaraData(txDataInicial
						.getText()));
			}
		});
		meio.add(txDataInicial);

		JLabel lbDataFinal = new JLabel("Data Final :");
		lbDataFinal.setBounds(20, 120, 100, 20);
		lbDataFinal.setFont(UtilitarioTela.getFont(14));
		lbDataFinal.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbDataFinal);

		txDataFinal = new JTextField();
		txDataFinal.setColumns(50);
		txDataFinal.setBounds(130, 120, 100, 25);
		txDataFinal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txDataFinal.setBorder(UtilitarioTela.jTextFieldComFocus());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				txDataFinal.setBorder(UtilitarioTela.jTextFieldNormal());
			}
		});
		txDataFinal.setLayout(null);
		txDataFinal.setBorder(UtilitarioTela.jTextFieldNormal());
		txDataFinal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				ValidadorCrud.campoData(arg0, txDataFinal.getText());
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				txDataFinal.setText(MascaraCrud.mascaraData(txDataFinal
						.getText()));
			}
		});
		meio.add(txDataFinal);

		JButton btGerar = new JButton("Gerar Relatório");
		btGerar.setBounds(20, 150, 200, 30);
		btGerar.setBorderPainted(false);
		btGerar.setBackground(UtilitarioTela.getFontColorCrud());
		btGerar.setForeground(UtilitarioTela.getFontColorPadrao());
		btGerar.setLayout(null);
		btGerar.setHorizontalAlignment(SwingConstants.LEFT);
		btGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					gerarRelatorio().setVisible(true);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		meio.add(btGerar);

	}

	private JFrame gerarRelatorio() throws Throwable {
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

		Style styleCenter = new Style();
        styleCenter.setFont(new Font(9, "Arial", false));
        styleCenter.setHorizontalAlign(HorizontalAlign.CENTER);
        styleCenter.setVerticalAlign(VerticalAlign.MIDDLE);
        styleCenter.setStretchWithOverflow(true);
        styleCenter.setTransparent(true);
        styleCenter.setBackgroundColor(Color.LIGHT_GRAY);
        styleCenter.setBorder(Border.THIN);

        Style styleDireita = new Style();
        styleDireita.setFont(new Font(9, "Arial", false));
        styleDireita.setHorizontalAlign(HorizontalAlign.RIGHT);
        styleDireita.setVerticalAlign(VerticalAlign.MIDDLE);
        styleDireita.setStretchWithOverflow(true);
        styleDireita.setTransparent(true);
        styleDireita.setBackgroundColor(Color.LIGHT_GRAY);
        styleDireita.setBorder(Border.THIN);
        styleDireita.setPaddingRight(5);
        styleDireita.setPattern("###,##0.00");

        Style styleEsquerda = new Style();
        styleEsquerda.setFont(new Font(9, "Arial", false));
        styleEsquerda.setHorizontalAlign(HorizontalAlign.LEFT);
        styleEsquerda.setVerticalAlign(VerticalAlign.MIDDLE);
        styleEsquerda.setStretchWithOverflow(true);
        styleEsquerda.setTransparent(true);
        styleEsquerda.setBackgroundColor(Color.LIGHT_GRAY);
        styleEsquerda.setBorder(Border.THIN);
        styleEsquerda.setPaddingRight(5);

        Style legendTotalStyle = new Style();
        legendTotalStyle.setFont(new Font(9, "Arial", true));
        legendTotalStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        legendTotalStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        legendTotalStyle.setStretchWithOverflow(true);
        legendTotalStyle.setTransparent(true);
        legendTotalStyle.setBackgroundColor(Color.LIGHT_GRAY);
        legendTotalStyle.setBorder(Border.THIN);
        legendTotalStyle.setPaddingLeft(5);

        Style totalValorStyle = new Style();
        totalValorStyle.setFont(new Font(9, "Arial", true));
        totalValorStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        totalValorStyle.setVerticalAlign(VerticalAlign.JUSTIFIED);
        totalValorStyle.setStretchWithOverflow(true);
        totalValorStyle.setTransparent(true);
        totalValorStyle.setBackgroundColor(Color.LIGHT_GRAY);
        totalValorStyle.setBorder(Border.THIN);
        totalValorStyle.setPaddingRight(5);
        totalValorStyle.setPattern("###,##0.00");
		

		try {
			drb = new FastReportBuilder();

			drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y,
					AutoText.POSITION_HEADER, AutoText.ALIGMENT_RIGHT);

			drb.addAutoText("SGTG - Sistema Gerenciador de Torneio Gamer",
					AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 600);

			drb.addAutoText(
					"Emitido em " + MascaraCrud.macaraDataBanco(new Date()),
					AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 250);

			drb.setTitle("Média de Participante Por Modalidade");

			drb.setPrintBackgroundOnOddRows(true);
			drb.setUseFullPageWidth(true);
			drb.setPageSizeAndOrientation(Page.Page_A4_Portrait());
			drb.setColumnsPerPage(new Integer(1));

			drb.addColumn("Modalidade", "descricao", String.class.getName(), 200, false);
			drb.addColumn("Sexo", "sexo", String.class.getName(), 100, true);
			drb.addColumn("Idade", "idade", String.class.getName(), 50, true);
			drb.addColumn("Quantidade", "qtdSexo", Integer.class.getName(), 70, true);

			drb.getColumn(0).setHeaderStyle(headerAlignCenter);
			drb.getColumn(1).setHeaderStyle(headerAlignCenter);
			drb.getColumn(2).setHeaderStyle(headerAlignCenter);
			drb.getColumn(3).setHeaderStyle(headerAlignCenter);

			drb.addGlobalFooterVariable(drb.getColumn(3), DJCalculation.SUM, headerAlignCenter);

			drb.setGrandTotalLegend("Total");
			drb.setGrandTotalLegendStyle(headerAlignCenter);

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
			Logger.getLogger(ExemploRelatorio.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return fr;
	}

}
