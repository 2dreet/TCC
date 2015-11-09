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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
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
import dialog.DialogLocalizarCampeonato;
import entidade.Banimento;
import entidade.Campeonato;
import entidade.Jogador;
import entidade.Marca;
import entidade.Modalidade;
import entidade.Time;
import utilitario.BordaSombreada;
import utilitario.MascaraCrud;
import utilitario.ParametroCrud;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import utilitario.ValidadorCrud;
import menu.MenuConfiguracoes;
import menu.MenuJogador;

import javax.swing.JCheckBox;

public class RelatorioCampeonato extends JPanel {

	private JLabel lblHeader;
	private JPanel meio;
	private JTextField txDataInicial;
	private JTextField txDataFinal;
	private JTextField txCampeonato;
	private ComboBox cbModalidade;
	private ComboBox cbModelo;
	private ComboBox cbFaixaEtaria;
	private FastReportBuilder drb;
	private Campeonato campeonato;
	protected ResultSet rs = null;
	public JasperPrint print;
	private boolean imprimirDireto;
	private JRViewer jr;
	private JFrame fr = null;
	int totalPaginas = 0;
	private final JCheckBox agrFaixaEteria = new JCheckBox("Faixa Etaria");

	public RelatorioCampeonato(Dimension tamanho) {

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

		JLabel lbModalidade = new JLabel("Modalidade :");
		lbModalidade.setBounds(20, 10, 100, 20);
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

		cbModalidade.setLocation(130, 10);
		meio.add(cbModalidade);

		JLabel lbDataInicial = new JLabel("Data Inicial :");
		lbDataInicial.setBounds(20, 40, 100, 20);
		lbDataInicial.setFont(UtilitarioTela.getFont(14));
		lbDataInicial.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbDataInicial);

		txDataInicial = new JTextField();
		txDataInicial.setColumns(50);
		txDataInicial.setBounds(130, 40, 100, 25);
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
		lbDataFinal.setBounds(20, 70, 100, 20);
		lbDataFinal.setFont(UtilitarioTela.getFont(14));
		lbDataFinal.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbDataFinal);

		txDataFinal = new JTextField();
		txDataFinal.setColumns(50);
		txDataFinal.setBounds(130, 70, 100, 25);
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

		JLabel lbCampeonato = new JLabel("Campeonato :");
		lbCampeonato.setBounds(20, 105, 200, 20);
		lbCampeonato.setFont(UtilitarioTela.getFont(14));
		lbCampeonato.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCampeonato);

		txCampeonato = new JTextField();
		txCampeonato.setColumns(50);
		txCampeonato.setBounds(130, 105, 200, 25);
		txCampeonato.setLayout(null);
		txCampeonato.setBorder(UtilitarioTela.jTextFieldNormal());
		txCampeonato.setEditable(false);
		meio.add(txCampeonato);

		JButton btLocalizar = new JButton("Localizar");
		btLocalizar.setBounds(340, 105, 100, 25);
		btLocalizar.setBorderPainted(false);
		btLocalizar.setBackground(UtilitarioTela.getFontColorCrud());
		btLocalizar.setForeground(UtilitarioTela.getFontColorPadrao());
		btLocalizar.setLayout(null);
		btLocalizar.setHorizontalAlignment(SwingConstants.LEFT);
		btLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (localizarCampeonato()) {
					txCampeonato.setText(campeonato.getDescricao());
				} else {

				}
			}
		});
		meio.add(btLocalizar);

		JButton btLimpar = new JButton("Limpar");
		btLimpar.setBounds(450, 105, 80, 25);
		btLimpar.setBorderPainted(false);
		btLimpar.setBackground(UtilitarioTela.getFontColorCrud());
		btLimpar.setForeground(UtilitarioTela.getFontColorPadrao());
		btLimpar.setLayout(null);
		btLimpar.setHorizontalAlignment(SwingConstants.LEFT);
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				campeonato = null;
				txCampeonato.setText("");

			}
		});
		meio.add(btLimpar);

		JLabel lbModelo = new JLabel("Modelo :");
		lbModelo.setBounds(20, 140, 100, 20);
		lbModelo.setFont(UtilitarioTela.getFont(14));
		lbModelo.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbModelo);

		Map<Integer, String> modelos = new HashMap<Integer, String>();
		modelos.put(0, new String(
				"Quantos jogadores participam em média de campeonato"));
		modelos.put(1, new String("Quais jogadores participaram de campeonato"));
		modelos.put(2, new String(
				"Quais computadores participaram de campeonato"));
		modelos.put(3, new String("Quais as modalidades mais jogas"));
		modelos.put(4, new String("Tempo médio de campeonato"));

		cbModelo = new ComboBox(new Dimension(350, 25));
		DefaultComboBoxModel comboModelTipo = (DefaultComboBoxModel) cbModelo
				.getModel();
		comboModelTipo.removeAllElements();
		for (int i = 0; i < modelos.size(); i++) {
			comboModelTipo.addElement(modelos.get(i));
		}

		cbModelo.setLocation(130, 140);
		meio.add(cbModelo);

		JLabel lbIdade = new JLabel("Faixa Etaria :");
		lbIdade.setBounds(20, 180, 100, 20);
		lbIdade.setFont(UtilitarioTela.getFont(14));
		lbIdade.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbIdade);

		Map<Integer, String> faixa = new HashMap<Integer, String>();
		faixa.put(0, new String("< Todos >"));
		faixa.put(1, new String("Menor de 18 Anos"));
		faixa.put(2, new String("Maior de 18 Anos"));

		cbFaixaEtaria = new ComboBox(new Dimension(350, 25));
		DefaultComboBoxModel faixaE = (DefaultComboBoxModel) cbFaixaEtaria
				.getModel();
		faixaE.removeAllElements();
		for (int i = 0; i < faixa.size(); i++) {
			faixaE.addElement(faixa.get(i));
		}

		cbFaixaEtaria.setLocation(140, 180);
		meio.add(cbFaixaEtaria);

		JLabel lbAgrupar = new JLabel("Agrupar Por :");
		lbAgrupar.setBounds(20, 205, 100, 20);
		lbAgrupar.setFont(UtilitarioTela.getFont(14));
		lbAgrupar.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbAgrupar);

		agrFaixaEteria.setBounds(20, 240, 100, 20);
		meio.add(agrFaixaEteria);

		JButton btGerar = new JButton("Gerar Relatório");
		btGerar.setBounds(20, 300, 200, 30);
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

	private boolean localizarCampeonato() {
		DialogLocalizarCampeonato dl = new DialogLocalizarCampeonato();
		dl.localizarCampeonato(meio);
		if (dl.getCampeonatoSelecionado() != null) {
			campeonato = dl.getCampeonatoSelecionado();
		}
		return false;
	}

	private JFrame gerarRelatorio() throws Throwable {
		int modelo = cbModelo.getSelectedIndex();

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

			drb.setPrintBackgroundOnOddRows(true);
			drb.setUseFullPageWidth(true);
			drb.setPageSizeAndOrientation(Page.Page_A4_Portrait());
			drb.setColumnsPerPage(new Integer(1));

			if (modelo == 0) {

				Modalidade modalidade = null;
				if (cbModalidade.getSelectedIndex() > 0) {
					modalidade = (Modalidade) cbModalidade.getSelectedItem();
				}
				rs = RelatorioDao.getJogadoresQuantidadeCampeonato(campeonato,
						agrFaixaEteria.isSelected(), txDataInicial.getText(),
						txDataFinal.getText(), modalidade);

				drb.setTitle("Média de Participante Por Modalidade");

				drb.addColumn("Campeonato", "campeonato",
						String.class.getName(), 200, false);
				drb.addColumn("Modalidade", "modalidade",
						String.class.getName(), 200, false);
				drb.addColumn("Sexo", "sexo", String.class.getName(), 100, true);
				if (agrFaixaEteria.isSelected()) {
					drb.addColumn("Idade", "idade", String.class.getName(), 50,
							true);
				}
				drb.addColumn("Quantidade", "qtdSexo", Integer.class.getName(),
						70, true);

				drb.getColumn(0).setHeaderStyle(headerAlignCenter);
				drb.getColumn(1).setHeaderStyle(headerAlignCenter);
				drb.getColumn(2).setHeaderStyle(headerAlignCenter);
				if (agrFaixaEteria.isSelected()) {
					drb.getColumn(3).setHeaderStyle(headerAlignCenter);
					drb.getColumn(4).setHeaderStyle(headerAlignCenter);
					drb.addGlobalFooterVariable(drb.getColumn(4),
							DJCalculation.SUM, headerAlignCenter);
				} else {
					drb.getColumn(3).setHeaderStyle(headerAlignCenter);
					drb.addGlobalFooterVariable(drb.getColumn(3),
							DJCalculation.SUM, headerAlignCenter);
				}

				drb.setGrandTotalLegend("Total");
				drb.setGrandTotalLegendStyle(headerAlignCenter);

			} else if (modelo == 1) {

				Modalidade modalidade = null;
				String faixaEtaria = null;
				if (cbModalidade.getSelectedIndex() > 0) {
					modalidade = (Modalidade) cbModalidade.getSelectedItem();
				}
				if (cbFaixaEtaria.getSelectedIndex() == 1) {
					faixaEtaria = " < 18";
				} else if (cbFaixaEtaria.getSelectedIndex() == 2) {
					faixaEtaria = " >= 18";
				}
				rs = RelatorioDao.getJogadoresCampeonato(campeonato,
						faixaEtaria, txDataInicial.getText(),
						txDataFinal.getText(), modalidade);

				drb.setTitle("Jogadores Campeonato");

				drb.addColumn("Campeonato", "campeonato",
						String.class.getName(), 200, false);
				drb.addColumn("Modalidade", "modalidade",
						String.class.getName(), 200, false);
				drb.addColumn("Jogador", "jogador", String.class.getName(),
						200, false);
				drb.addColumn("Sexo", "sexo", String.class.getName(), 100, true);
				if (agrFaixaEteria.isSelected()) {
					drb.addColumn("Idade", "idade", String.class.getName(), 50,
							true);
				}
				drb.addColumn("Quantidade", "qtdSexo", Integer.class.getName(),
						70, true);

				drb.getColumn(0).setHeaderStyle(headerAlignCenter);
				drb.getColumn(1).setHeaderStyle(headerAlignCenter);
				drb.getColumn(2).setHeaderStyle(headerAlignCenter);
				if (agrFaixaEteria.isSelected()) {
					drb.getColumn(3).setHeaderStyle(headerAlignCenter);
					drb.getColumn(4).setHeaderStyle(headerAlignCenter);
					drb.addGlobalFooterVariable(drb.getColumn(4),
							DJCalculation.SUM, headerAlignCenter);
				} else {
					drb.getColumn(3).setHeaderStyle(headerAlignCenter);
					drb.addGlobalFooterVariable(drb.getColumn(3),
							DJCalculation.SUM, headerAlignCenter);
				}

				drb.setGrandTotalLegend("Total");
				drb.setGrandTotalLegendStyle(headerAlignCenter);

			} else if (modelo == 2) {

				Modalidade modalidade = null;
				if (cbModalidade.getSelectedIndex() > 0) {
					modalidade = (Modalidade) cbModalidade.getSelectedItem();
				}
				rs = RelatorioDao.getPcCampeonato(campeonato,
						txDataInicial.getText(), txDataFinal.getText(),
						modalidade);

				drb.setTitle("Computadores Campeonato");

				drb.addColumn("Campeonato", "campeonato",
						String.class.getName(), 200, false);
				drb.addColumn("Modalidade", "modalidade",
						String.class.getName(), 200, false);
				drb.addColumn("Computador", "descricao",
						String.class.getName(), 200, false);
				drb.addColumn("Ip", "ip", String.class.getName(), 100, true);

				drb.getColumn(0).setHeaderStyle(headerAlignCenter);
				drb.getColumn(1).setHeaderStyle(headerAlignCenter);
				drb.getColumn(2).setHeaderStyle(headerAlignCenter);
				drb.getColumn(3).setHeaderStyle(headerAlignCenter);

				drb.addGlobalFooterVariable(drb.getColumn(3),
						DJCalculation.COUNT, headerAlignCenter);

				drb.setGrandTotalLegend("Total");
				drb.setGrandTotalLegendStyle(headerAlignCenter);

			} else if (modelo == 3) {

				Modalidade modalidade = null;
				if (cbModalidade.getSelectedIndex() > 0) {
					modalidade = (Modalidade) cbModalidade.getSelectedItem();
				}
				rs = RelatorioDao.getPcCampeonato(campeonato,
						txDataInicial.getText(), txDataFinal.getText(),
						modalidade);

				drb.setTitle("Modalidade Jogada");

				drb.addColumn("Modalidade", "modalidade",
						String.class.getName(), 200, false);
				drb.addColumn("Sexo", "sexo", String.class.getName(), 100, true);
				drb.addColumn("Idade", "idade", String.class.getName(), 50,
						true);
				drb.addColumn("Quantidade", "qtdSexo", Integer.class.getName(),
						70, true);

				drb.getColumn(0).setHeaderStyle(headerAlignCenter);
				drb.getColumn(1).setHeaderStyle(headerAlignCenter);
				drb.getColumn(2).setHeaderStyle(headerAlignCenter);
				drb.getColumn(3).setHeaderStyle(headerAlignCenter);

				drb.addGlobalFooterVariable(drb.getColumn(3),
						DJCalculation.SUM, headerAlignCenter);

				drb.setGrandTotalLegend("Total");
				drb.setGrandTotalLegendStyle(headerAlignCenter);

			} else if (modelo == 4) {
				Modalidade modalidade = null;
				if (cbModalidade.getSelectedIndex() > 0) {
					modalidade = (Modalidade) cbModalidade.getSelectedItem();
				}
				rs = RelatorioDao.getTempoMedio(campeonato,
						txDataInicial.getText(), txDataFinal.getText(),
						modalidade);

				drb.setTitle("Tempo Médio Modalidade");

				drb.addColumn("Modalidade", "modalidade",
						String.class.getName(), 200, false);
				drb.addColumn("Chave", "chave", String.class.getName(), 100,
						true);
				drb.addColumn("Tempo Médio", "tempoMedio",
						String.class.getName(), 100, true);

				drb.getColumn(0).setHeaderStyle(headerAlignCenter);
				drb.getColumn(1).setHeaderStyle(headerAlignCenter);
				drb.getColumn(2).setHeaderStyle(headerAlignCenter);
			}
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
					fr.setTitle("Relatório");
					fr.setSize(new Dimension(800, 600));
					fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
					fr.setContentPane(jr);
				}
			}
		} catch (JRException ex) {
			Logger.getLogger(RelatorioCampeonato.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return fr;
	}

}
