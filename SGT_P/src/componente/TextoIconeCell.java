package componente;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TextoIconeCell extends DefaultTableCellRenderer {
	  protected void setValue(Object value) {
	    if (value instanceof DadoComIcone) {
	      if (value != null) {
	    	  DadoComIcone d = (DadoComIcone) value;
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