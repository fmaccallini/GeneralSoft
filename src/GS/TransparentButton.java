package GS;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

public class TransparentButton extends JButton {
	private static final long serialVersionUID = 1L;
	private BufferedImage Imagen = null;
	private String Text ="";
	private Color ColorText = Color.WHITE;
	public TransparentButton(BufferedImage img) {
		setOpaque(false);
		Imagen = img;
	}

	public void setImagen(BufferedImage img) {
		Imagen = img;
	}
	
	public void setText(String text) {
		Text = text;
	}
	
	public void setcolorText(Color c) {
		ColorText = c;
	}
	
	public void paint(Graphics g) {
		int x = getWidth();		
		int y = getHeight();
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));
		super.paint(g2);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		if(Imagen != null){
			int anchoImagen = Imagen.getWidth();
			int altoImagen = Imagen.getHeight();
			if(x>anchoImagen && y>altoImagen){
				g.drawImage (Imagen, (x-anchoImagen)/2, (y-altoImagen), null);	
			}else{
				double escala = 1.0;
				if(x/anchoImagen < y/altoImagen){
					escala = (double)x/(double)anchoImagen;
				}else{
					escala = (double)y/(double)altoImagen;
				}			
				g.drawImage (Imagen,0,0, (int)(anchoImagen*escala), (int)(altoImagen*escala), null);	
			}	
		}		
		g.setColor(ColorText);
		g.drawString(Text,48,13);
		g2.dispose();
	}
}