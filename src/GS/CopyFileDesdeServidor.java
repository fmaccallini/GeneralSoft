package GS;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JProgressBar;

public class CopyFileDesdeServidor{
	private boolean b=true;
	public JProgressBar Progress = new JProgressBar(0, 100);
	public Rectangle progressRect;
	public int Maximo=0, progreso=0;
	public boolean MostrarInfo = true;
	
/******************************************/
    public CopyFileDesdeServidor(boolean mostrarInfo) {  
    	MostrarInfo = mostrarInfo;
    }

/******************************************/
    public CopyFileDesdeServidor() {  
    }
    
/******************************************/
    public void copyFile(String ArchFnt, String RutaDst, String ArchDst){
		try {
	
			URL url = new URL(ArchFnt);

			// establecemos conexion
			URLConnection urlCon = url.openConnection();

			// Sacamos por pantalla el tipo de fichero
			if(MostrarInfo) {
				System.out.println(urlCon.getContentType()+", "+ArchFnt);	
				System.out.println("RutaDst: "+RutaDst);	
				System.out.println("ArchDst: "+ArchDst);
			}
				

			// Se obtiene el inputStream del archivo y se abre el fichero local.
			InputStream is = urlCon.getInputStream();
			File dstDir = new File(RutaDst);
			if (!dstDir.exists()) {
                if(!dstDir.mkdir()){
                	if(!dstDir.mkdirs()){
                    	b=false;
                    	System.out.println("Error al crear el directorio en Copy");
                    }
                }                
            }
			if(b){
				int length = urlCon.getContentLength();
				FileOutputStream fos = new FileOutputStream(RutaDst + ArchDst);
			
				// Lectura de la foto de la web y escritura en fichero local
				byte[] array = new byte[1000]; // buffer temporal de lectura.
				int leido = is.read(array);
				double leidoTotal = leido;
				double prog_temp = 0;
				double porcentage_byte = ((double)Maximo - (double)progreso)/length;
			/*	System.out.println("Maximo: "+Maximo);
				System.out.println("progreso: "+progreso);
				System.out.println("length: "+length);				
				System.out.println("porcentage_byte: "+porcentage_byte);	*/
				
				while (leido > 0) {
					fos.write(array, 0, leido);
					leido = is.read(array);
					leidoTotal = leidoTotal + leido;
					prog_temp = leidoTotal*porcentage_byte;
					if(progreso+prog_temp<Maximo){						
						ProgressBar((int)(progreso+prog_temp));
					}else{
						ProgressBar(Maximo);
					}
				}							

				// cierre de conexion y fichero.
				is.close();
				fos.close();
			}
			File f = new File(RutaDst + ArchDst);
			if(!f.exists()){
				System.out.println("Error al copiar el archivo en Copy");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
/**************************************************************/
    public void ProgressBar(int i){	
    	Progress.setValue(i); 
    	Progress.setStringPainted(true); 
    	progressRect = Progress.getBounds(); 
    	progressRect.x = 0; 
    	progressRect.y = 0; 
    	Progress.paintImmediately(progressRect); 
    }
}