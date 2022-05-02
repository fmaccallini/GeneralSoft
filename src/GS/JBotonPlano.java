package GS;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.swing.JButton;

public class JBotonPlano extends JButton{
	private static final long serialVersionUID = 1L;
	public boolean redondeado = true, ConBorde = false;	
	public Color ColorBorde = Color.LIGHT_GRAY;
	public JBotonPlano(boolean Redondeado, boolean conBorde) throws IOException{
		super();		
		redondeado = Redondeado;
		ConBorde = conBorde;
	}
	
/*----------------------------------------------------------------------------*/ 
    public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(this.getBackground());
			if(redondeado){
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);	
				if(ConBorde){
					g2.setColor(ColorBorde);
					g2.drawRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
				}								
			}else{
				g2.fillRect(0, 0, getWidth(), getHeight());	
				if(ConBorde){
					g2.setColor(ColorBorde);
					g2.drawRect(0, 0, getWidth(), getHeight());
				}	
			}
    }
}
