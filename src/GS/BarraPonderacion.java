package GS;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class BarraPonderacion extends JLabel{
	private static final long serialVersionUID = 1L;
	private int Porcentaje=0;
	private static Color ColorFondo;
	private boolean Vertical = false;
	private Image img = null;
	public String[] StringValores = {"","","","",""};
	public int[] Valores = {-1,-1,-1,-1,-1};
	public Color[] colores = {Color.RED, Color.CYAN, Color.GREEN, Color.YELLOW, Color.MAGENTA};
	
	public BarraPonderacion(Color colorFondo, int porcentaje, boolean vertical){
		super();
		Vertical = vertical;
		ColorFondo = colorFondo;		
		setPorcentaje(porcentaje);
	}
	
	 public void paint(Graphics g) {
		 int width = getWidth();
		 int height = getHeight();
		 g.setColor(ColorFondo);
		 g.fillRect(0, 0, width, height);
		 if(Vertical){			
			 int alto = (height*Porcentaje)/100;
			 int anchoMaximo = 30;
			 if(width<anchoMaximo){
				 g.drawImage(img, 0, height-alto, width, height, this);
			 }else{
				 int anchoInicio = (width - anchoMaximo)/2;
				 g.drawImage(img, anchoInicio, height-alto, anchoMaximo, alto, this);
			 }
			 Graphics2D g2 = (Graphics2D)g;
			// AffineTransform origXform = g2.getTransform();	
			 for(int i=0;i<Valores.length;i++){
				 if(Valores[i]>=0){
					 g2.setColor(colores[i]);
					 int altura = height-((height*Valores[i])/100);
					 g2.drawLine(0, altura, width, altura);	
					// AffineTransform newXform = (AffineTransform)(origXform.clone());
					 //newXform.rotate(Math.toRadians(270), 10, altura);
					 //g2.setTransform(newXform);
					 g2.drawString(StringValores[i],2,altura-2);  
					 //g2.setTransform(origXform);
				 }			 
			 }
			 g2.setColor(Color.WHITE);
			 g2.drawLine(0, height, width, height);
			 g2.drawLine(0, height-1, width, height-1);
		 }else{
			 if(height<20){
				 g.drawImage(img, 0, 0, (width*Porcentaje)/100, height, this);
			 }else{
				 int altoInicio = (height - 20)/2;
				 g.drawImage(img, 0, altoInicio, (width*Porcentaje)/100, 20, this);
			 }
		 }
		 super.paint(g);
	    }
	 
	 public void setPorcentaje(int porcentaje) {
		 Porcentaje = porcentaje;
		 try {
			 if(Porcentaje<50) {
				 if(Vertical){
					 img = ImageIO.read(new File("LocalData/be53a0541a6d36f6ecb879fa2c584b08/BarraPuntuacionRojoVertical.png"));
				 }else{
					 img = ImageIO.read(new File("LocalData/be53a0541a6d36f6ecb879fa2c584b08/BarraPuntuacionRojo.png"));
				 }
			 }
			 if(Porcentaje>=50 && Porcentaje<70) {
				 if(Vertical){
					 img = ImageIO.read(new File("LocalData/be53a0541a6d36f6ecb879fa2c584b08/BarraPuntuacionVertical.png"));
				 }else{
					 img = ImageIO.read(new File("LocalData/be53a0541a6d36f6ecb879fa2c584b08/BarraPuntuacion.png"));
				 }
			 }
			 if(Porcentaje>=70) {
				 if(Vertical){
					 img = ImageIO.read(new File("LocalData/be53a0541a6d36f6ecb879fa2c584b08/BarraPuntuacionVerdeVertical.png"));
				 }else{
					 img = ImageIO.read(new File("LocalData/be53a0541a6d36f6ecb879fa2c584b08/BarraPuntuacionVerde.png"));
				 }
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 repaint();
	 }
}
