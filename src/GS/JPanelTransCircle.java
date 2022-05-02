package GS;
import java.awt.AlphaComposite; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.RenderingHints; 
import javax.swing.JPanel; 

public class JPanelTransCircle extends JPanel{ 
	private static final long serialVersionUID = 1L;
	private float transp= 0.5f;//el nivel de transparencia 

	public JPanelTransCircle(){ } 


	public void paintBorder(Graphics g){ 
		//si quieres dibujarle un borde puedes llamar a la funcion drawOval(x,y,ancho,alto).. 
		//g.drawOval(0,0,getSize().width,getSize().height); //a modo de ejemplo 
	} 

	public void paintComponent(Graphics g){ 
		Graphics2D g2 = (Graphics2D) g;//transformar a 2d para permitir transparencia 
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
		AlphaComposite old = (AlphaComposite) g2.getComposite(); 
		g2.setComposite(AlphaComposite.SrcOver.derive(getTranspValor())); 
		g2.setColor(this.getBackground()); //eliges el color de fondo..sigue siendo transparente. 
		g2.fillRoundRect(0, 0, getSize().width-1, getSize().height-1, 15, 15);
		//g2.fillOval(0, 0,getSize().width-1,getSize().height-1); 
		g2.setComposite(old); 

	} 

	public float getTranspValor() { 
		return transp; 
	} 

	public void setTranspValor(float tran) { 
		this.transp = tran; 
	} 

} 

