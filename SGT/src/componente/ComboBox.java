package componente;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

import utilitario.UtilitarioTela;

public class ComboBox extends JComboBox{
 
    public ComboBox(Dimension dimension)
    {
        setPreferredSize(dimension);
        setSize(dimension);      
        setForeground(new Color(46,49,56));
        setFont(UtilitarioTela.getFont(12));
        setBorder(BorderFactory.createLineBorder(new Color(176, 177, 184), 1));
        setBackground(Color.WHITE);
        setUI(CustomUI.createUI(this));                
        setVisible(true);
    }

}