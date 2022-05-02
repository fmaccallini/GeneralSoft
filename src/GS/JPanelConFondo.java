package GS;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class JPanelConFondo extends JPanel {
	private static final long serialVersionUID = 1L;
	public Image imagen;

	public JPanelConFondo(Image img){
		imagen = img;
	}
    public void paint(Graphics g) {
    	try{
    		if(imagen != null){
    			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    		}    		
    		setOpaque(false);
    		super.paint(g);
    	}catch(Exception e){
    		e.printStackTrace();    		
    	}
    }
}
