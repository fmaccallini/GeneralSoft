package GS;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;

public class JButtonNotificacion extends JButton{
	private static final long serialVersionUID = 1L;
	public Image Imagen = null;
	public String NroNotificaciones = "0";
	public int x=30,y=20, w=12;
	public JButtonNotificacion() {
		super();
	}
	
	public JButtonNotificacion(String nroNotificaciones){
		super();
		NroNotificaciones = nroNotificaciones;
	}
		
	    public void paint(Graphics g) {
	    	super.paint(g);
	    	if(Integer.parseInt(NroNotificaciones)>0) {
	    		g.setColor(Color.BLACK);
	    		g.fillOval(x+1, y+1, w, w);
	    		g.setColor(Color.RED);
	    		g.fillOval(x, y, w, w);
	    		Font f = new Font("Dialog",Font.BOLD,10);
	    		g.setFont(f);
	    		FontMetrics dim = getFontMetrics(f);
	    		int width = dim.stringWidth(NroNotificaciones);
	    		g.setColor(Color.WHITE);
	    		g.drawString(String.valueOf(NroNotificaciones), x + (w-width)/2+1, y+ w - (w-dim.getHeight())/2-2);
	    	}	       
	    }


}
