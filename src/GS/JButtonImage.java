package GS;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;

public class JButtonImage extends JButton{
	private static final long serialVersionUID = 1L;
	public Image Imagen = null;
	public String titulo = "";
	public JButtonImage() {
		super();
	}
	
	public JButtonImage(String s, String imagen){
		super();
		Imagen = Util.Image_Obtener(imagen);
		titulo = s;
	}
		
	    public void paint(Graphics g) {
	    	if(Imagen != null){
	    		 g.drawImage(Imagen, 0, 0, getWidth(), getHeight(), 0, 0, Imagen.getWidth(null), Imagen.getHeight(null), null);
	    		 Font f = new Font("Dialog",Font.PLAIN,10);
	    		 g.setFont(f);
	    		 FontMetrics dim = getFontMetrics(f);
	    		 int w_text = dim.stringWidth(titulo);
	    		 g.drawString(titulo, (getWidth()-w_text)/2, ((getHeight()-dim.getHeight())/2)+(dim.getHeight()/2)+4);
	    	}	       
	    }


}
