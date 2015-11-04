package componente;

import javax.swing.Icon;

public class DadoComIcone {
	  public DadoComIcone(Object data, Icon icon) {
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
