package GS;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class JLabelTitulo extends JLabel {
	private static final long serialVersionUID = 1L;

	public JLabelTitulo(String s){
		super(s);
	}
	
    public void paint(Graphics g) {
    	g.setColor(Color.WHITE);
        g.drawString(getText(), 1, 1);
    	g.setColor(getForeground());
        g.drawString(getText(), 0, 0);
    }
}