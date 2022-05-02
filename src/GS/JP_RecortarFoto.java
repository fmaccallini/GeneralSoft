package GS;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JP_RecortarFoto extends JPanel implements MouseListener,MouseMotionListener{    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x1=10, y1=10, tamanio=300, width=100, heigth=100;
	private String ArchivoBase = "";
	private Double Scale = 1.0;
	private BufferedImage Fondo = null;
	public boolean escalarImagen = true;

/***************************************************************/
    public JP_RecortarFoto(String archivoBase) {
    	setFoto(archivoBase);
    	this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

/***************************************************************/
    @Override
    public void paint(Graphics g) {
    	super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D)g;   
    	if(Fondo!=null){
            g2.drawImage(Fondo, 0, 0, width, heigth, this);  
            int tipo = AlphaComposite.SRC_OVER;
        	g2.setComposite(AlphaComposite.getInstance(tipo,(float)0.6));
        	g2.setColor(Color.BLACK);
        	g2.fillRect(0, 0, width, y1);
        	g2.fillRect(0, y1+tamanio, width, heigth-tamanio);
        	g2.fillRect(0, y1, x1, tamanio);
        	g2.fillRect(x1+tamanio, y1, width-tamanio-x1, tamanio);   
    	}                  
		g2.dispose();  
        g.dispose();
    }
    
    
/***************************************************************/
    public void setFoto(String archivoBase){ 
    	if(!Util.EsNulo(archivoBase)){
    		 ArchivoBase = archivoBase;
        	 File f = new File(ArchivoBase);    	 
        	 try {
        		 if(f.exists()){
        			 BufferedImage FondoOriginal = ImageIO.read(f);	
        			 width = FondoOriginal.getWidth();
        			 heigth = FondoOriginal.getHeight();
        			 if(escalarImagen) {
        				 if(width<heigth){
            				 if(heigth>600){
            					 Scale = ((double)600/(double)heigth);
            					 heigth = 600;
            					 width = (int) (width*Scale);
            				 }
            				 tamanio = width - 20;       			 
            			 }else{
            				 if(width>600){
            					 Scale = ((double)600/(double)width);
            					 width = 600;
            					 heigth = (int) (heigth*Scale);
            				 }        				
            				 tamanio = heigth - 20;
            			 }  
        			 }        			   
        			 Fondo = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);
            		 Graphics2D g2 = Fondo.createGraphics(); 
            		 g2.drawImage(FondoOriginal, 0, 0, width, heigth, 0, 0, FondoOriginal.getWidth(), FondoOriginal.getHeight(), this);            			
            		 g2.dispose();  
        		 }          		 
        		 this.setSize(width, heigth);  
        		 Util.TamanioFijo(this, width,heigth);
        	  } catch (IOException ex) { }     
        	 repaint();
    	}    	 
    }
    
/***************************************************************/
    public void Foto_Guardar(String RutaFoto, int ancho, int alto){     
    	/*if((ancho>tamanio || alto>tamanio)){
    		ancho = alto = tamanio;
    	}*/
 		BufferedImage bufferedImage = new BufferedImage(tamanio, tamanio, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferedImage.createGraphics(); 

		g2.drawImage(((BufferedImage)Fondo).getSubimage(x1, y1, tamanio, tamanio), 0, 0, null);  
		
		g2.dispose();    		
		
		File file_Foto = new File(RutaFoto);
		RutaFoto = RutaFoto.replace("\\", "/");
		File directorio = new File(RutaFoto.substring(0,RutaFoto.lastIndexOf("/")));
		if(!directorio.exists()){
			directorio.mkdirs();
		}		

		BufferedImage bufferedImageEscalada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = bufferedImageEscalada.createGraphics(); 
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.drawImage(bufferedImage, 0, 0, ancho, alto, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
		g2D.dispose();  
		RenderedImage rendImage = bufferedImageEscalada;
		try {
			ImageIO.write(rendImage, "jpg", file_Foto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }

	@Override
	public void mouseClicked(MouseEvent e) { }
	
	@Override
	public void mousePressed(MouseEvent e) { 
		x1 = e.getX();
		if(x1<0){
			x1 = 0;
		}
		y1 = e.getY();
		if(y1<0){
			y1 = 0;
		}
		repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) { }
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) { }	

	@Override
	public void mouseDragged(MouseEvent e) {
		if((e.getX()-x1)>(e.getY()-y1)){
			tamanio = e.getX()-x1;
		}else{
			tamanio = e.getY()-y1;
		}
		if((x1+tamanio)>=width){
			tamanio = width - x1 - 1;
		}
		if((y1+tamanio)>=heigth){
			tamanio = heigth - y1 - 1;
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
}