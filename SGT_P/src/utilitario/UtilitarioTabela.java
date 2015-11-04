package utilitario;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import componente.StripedCell;
import componente.TabelaCell;


public class UtilitarioTabela {

	
	public static void pintarColona(Color fundo, Color font, TableColumnModel tcm, Object[] colunas) {
		TabelaCell headerRenderer = new TabelaCell(SwingConstants.CENTER,
				SwingConstants.BOTTOM);
		headerRenderer.setBackground(fundo);
		headerRenderer.setForeground(font);
		headerRenderer.setBorder(new BordaTabela());
		headerRenderer.setFont(UtilitarioTela.getFont(12));
		int columns = tcm.getColumnCount();
		for (int i = 0; i < columns; i++) {
			tcm.getColumn(i).setHeaderRenderer(headerRenderer);
			tcm.getColumn(i).setHeaderValue(colunas[i]);
		}
	}
	
	public static void pintarLinha(Color impar, Color par, JTable tabela){
		StripedCell.installInTable(tabela, impar,
		        par, null, null);
	}

	public static DefaultTableModel getModelo(Object[] colunas) {
		DefaultTableModel tableModel = new DefaultTableModel(colunas, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		return tableModel;
	}
	
	public static Color getFundoHeaderPadrao(){
		return new Color(46, 49, 56);
	}
	
	public static Color getFontColotHeaderPadrao(){
		return new Color(252, 79, 63);
	}
	
}
