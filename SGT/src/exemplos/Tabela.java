package exemplos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import componente.TabelaCell;

import utilitario.BordaSombreada;
import utilitario.UtilitarioCrud;

public class Tabela {
  
  public static void main(String[] args) {
    try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (Exception evt) {}
  
    JFrame f = new JFrame("Multi-line Header Table");
    JTable tbl = new JTable(new CurrencyTableModel());
    tbl.setDefaultRenderer(java.lang.Number.class,
        new FractionCellRenderer(10, 3, SwingConstants.RIGHT));

    TableColumnModel tcm = tbl.getColumnModel();
    tcm.getColumn(0).setPreferredWidth(150);
    tcm.getColumn(0).setMinWidth(150);
    TextWithIconCellRenderer renderer = new TextWithIconCellRenderer();
    tcm.getColumn(0).setCellRenderer(renderer);
    tcm.getColumn(1).setPreferredWidth(100);
    tcm.getColumn(1).setMinWidth(100);

    // Add the stripe renderer.
    StripedTableCellRenderer.installInTable(tbl, Color.lightGray,
        Color.white, null, null);

    // Add the custom header renderer
    TabelaCell headerRenderer = new TabelaCell(
        SwingConstants.CENTER, SwingConstants.BOTTOM);
    headerRenderer.setBackground(Color.gray);
    headerRenderer.setForeground(Color.white);
    headerRenderer.setFont(new Font("Dialog", Font.BOLD, 12));
    int columns = tableHeaders.length;
    for (int i = 0; i < columns; i++) {
      tcm.getColumn(i).setHeaderRenderer(headerRenderer);
      tcm.getColumn(i).setHeaderValue(tableHeaders[i]);
    }

    tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    tbl.setPreferredScrollableViewportSize(tbl.getPreferredSize());

    JScrollPane sp = new JScrollPane(tbl);
    f.getContentPane().add(sp, "Center");
    f.pack();
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        System.exit(0);
      }
    });
    f.setVisible(true);
  }

  // Header values. Note the table model provides
  // string column names that are the default header
  // values.
  public static Object[][] tableHeaders = new Object[][] {
      new String[] { "Código" },
      new String[] { "Jogador" },
      new String[] { "Time" },
      new String[] { "Sexo" } };
}

class CurrencyTableModel extends AbstractTableModel {
  protected String[] columnNames = { "Currency", "Yesterday", "Today",
      "Change" };

  // Constructor: calculate currency change to create the last column
  public CurrencyTableModel() {
    for (int i = 0; i < data.length; i++) {
      data[i][DIFF_COLUMN] = new Double(
          ((Double) data[i][NEW_RATE_COLUMN]).doubleValue()
              - ((Double) data[i][OLD_RATE_COLUMN]).doubleValue());
    }
  }

  // Implementation of TableModel interface
  public int getRowCount() {
    return data.length;
  }

  public int getColumnCount() {
    return COLUMN_COUNT;
  }

  public Object getValueAt(int row, int column) {
    return data[row][column];
  }

  public Class getColumnClass(int column) {
    return (data[0][column]).getClass();
  }

  public String getColumnName(int column) {
    return columnNames[column];
  }

  protected static final int OLD_RATE_COLUMN = 1;

  protected static final int NEW_RATE_COLUMN = 2;

  protected static final int DIFF_COLUMN = 3;

  protected static final int COLUMN_COUNT = 4;

  protected static final Class thisClass = CurrencyTableModel.class;

  
  protected Object[][] data = new Object[][] {
      {
          new DataWithIcon("Belgian Franc", new ImageIcon(thisClass
              .getResource("/imagem/delete.png"))),
          new Double(37.6460110), new Double(37.6508921), null },
      {
          new DataWithIcon("British Pound", new ImageIcon(thisClass
              .getResource("/imagem/delete.png"))), new Double(0.6213051),
          new Double(0.6104102), null },
      {
          new DataWithIcon("Canadian Dollar", new ImageIcon(thisClass
              .getResource("/imagem/delete.png"))),
          new Double(1.4651209), new Double(1.5011104), null },
      {
          new DataWithIcon("French Franc", new ImageIcon(thisClass
              .getResource("/imagem/delete.png"))),
          new Double(6.1060001), new Double(6.0100101), null },
      {
          new DataWithIcon("Italian Lire", new ImageIcon(thisClass
              .getResource("/imagem/delete.png"))),
          new Double(1181.3668977), new Double(1182.104), null },
      {
          new DataWithIcon("German Mark", new ImageIcon(thisClass
              .getResource("/imagem/delete.png"))),
          new Double(1.8191804), new Double(1.8223421), null },
      {
          new DataWithIcon("Japanese Yen", new ImageIcon(thisClass
              .getResource("/imagem/delete.png"))),
          new Double(141.0815412), new Double(121.0040432), null } };
}

class DataWithIcon {
  public DataWithIcon(Object data, Icon icon) {
    this.data = data;
    this.icon = icon;
  }

  public Icon getIcon() {
    return icon;
  }

  public Object getData() {
    return data;
  }

  public String toString() {
    return data.toString();
  }

  protected Icon icon;

  protected Object data;
}

class StripedTableCellRenderer implements TableCellRenderer {
  public StripedTableCellRenderer(TableCellRenderer targetRenderer,
      Color evenBack, Color evenFore, Color oddBack, Color oddFore) {
    this.targetRenderer = targetRenderer;
    this.evenBack = evenBack;
    this.evenFore = evenFore;
    this.oddBack = oddBack;
    this.oddFore = oddFore;
  }

  // Implementation of TableCellRenderer interface
  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    TableCellRenderer renderer = targetRenderer;
    if (renderer == null) {
      // Get default renderer from the table
      renderer = table.getDefaultRenderer(table.getColumnClass(column));
    }

    // Let the real renderer create the component
    Component comp = renderer.getTableCellRendererComponent(table, value,
        isSelected, hasFocus, row, column);

    // Now apply the stripe effect
    if (isSelected == false && hasFocus == false) {
      if ((row & 1) == 0) {
        comp.setBackground(evenBack != null ? evenBack : table
            .getBackground());
        comp.setForeground(evenFore != null ? evenFore : table
            .getForeground());
      } else {
        comp.setBackground(oddBack != null ? oddBack : table
            .getBackground());
        comp.setForeground(oddFore != null ? oddFore : table
            .getForeground());
      }
    }

    return comp;
  }

  // Convenience method to apply this renderer to single column
  public static void installInColumn(JTable table, int columnIndex,
      Color evenBack, Color evenFore, Color oddBack, Color oddFore) {
    TableColumn tc = table.getColumnModel().getColumn(columnIndex);

    // Get the cell renderer for this column, if any
    TableCellRenderer targetRenderer = tc.getCellRenderer();

    // Create a new StripedTableCellRenderer and install it
    tc.setCellRenderer(new StripedTableCellRenderer(targetRenderer,
        evenBack, evenFore, oddBack, oddFore));
  }

  // Convenience method to apply this renderer to an entire table
  public static void installInTable(JTable table, Color evenBack,
      Color evenFore, Color oddBack, Color oddFore) {
    StripedTableCellRenderer sharedInstance = null;
    int columns = table.getColumnCount();
    for (int i = 0; i < columns; i++) {
      TableColumn tc = table.getColumnModel().getColumn(i);
      TableCellRenderer targetRenderer = tc.getCellRenderer();
      if (targetRenderer != null) {
        // This column has a specific renderer
        tc.setCellRenderer(new StripedTableCellRenderer(targetRenderer,
            evenBack, evenFore, oddBack, oddFore));
      } else {
        // This column uses a class renderer - use a shared renderer
        if (sharedInstance == null) {
          sharedInstance = new StripedTableCellRenderer(null,
              evenBack, evenFore, oddBack, oddFore);
        }
        tc.setCellRenderer(sharedInstance);
      }
    }
  }

  protected TableCellRenderer targetRenderer;

  protected Color evenBack;

  protected Color evenFore;

  protected Color oddBack;

  protected Color oddFore;
}

class FractionCellRenderer extends DefaultTableCellRenderer {
  public FractionCellRenderer(int integer, int fraction, int align) {
    this.integer = integer; // maximum integer digits
    this.fraction = fraction; // exact number of fraction digits
    this.align = align; // alignment (LEFT, CENTER, RIGHT)
  }

  protected void setValue(Object value) {
    if (value != null && value instanceof Number) {
      formatter.setMaximumIntegerDigits(integer);
      formatter.setMaximumFractionDigits(fraction);
      formatter.setMinimumFractionDigits(fraction);
      setText(formatter.format(((Number) value).doubleValue()));
    } else {
      super.setValue(value);
    }
    setHorizontalAlignment(align);
  }

  protected int integer;

  protected int fraction;

  protected int align;

  protected static NumberFormat formatter = NumberFormat.getInstance();
}

class TextWithIconCellRenderer extends DefaultTableCellRenderer {
  protected void setValue(Object value) {
    if (value instanceof DataWithIcon) {
      if (value != null) {
        DataWithIcon d = (DataWithIcon) value;
        Object dataValue = d.getData();

        setText(dataValue == null ? "" : dataValue.toString());
        setIcon(d.getIcon());
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.LEFT);
        setVerticalAlignment(SwingConstants.CENTER);
      } else {
        setText("");
        setIcon(null);
      }
    } else {
      super.setValue(value);
    }
  }
}
