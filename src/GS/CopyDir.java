package GS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyDir {
	
	/**********************************************
	 * 
	 * @param d1: Directorio Fuente
	 * @param d2: Directorio Destino
	 */
		public CopyDir(File d1, File d2){
			copiarDirectorios(d1, d2);
		}
        
        public static void copiarFicheros(File f1, File f2){                
        	try {
        		if(!f2.exists()){
        			InputStream in = new FileInputStream(f1);
        			OutputStream out = new FileOutputStream(f2);
        			
        			byte[] buf = new byte[1024];
        			int len;
        			
        			while ((len = in.read(buf)) > 0) {
        				out.write(buf, 0, len);
        			}
        			
        			in.close();
        			out.close();
                        
        			System.out.println("Copiando fichero " + f1.toString()+ " to "+ f2.toString());
        		}
                        
        	} catch (IOException ioe){
        		ioe.printStackTrace();
        	}
        }
        

        public static void copiarDirectorios(File d1, File d2){                
                // Comprobamos que es un directorio
                if(d1.isDirectory()){
                        //Comprobamos si existe el directorio destino, si no lo creamos
                        if (!d2.exists()){                              
                                d2.mkdir();
                                System.out.println("Creando directorio " + d2.toString());
                        }
                        // Sacamos todos los ficheros del directorio
                        String[] ficheros = d1.list();
                        for(int x=0;x<ficheros.length;x++) {                        	
                                // Por cada fichero volvemos a llamar recursivamente a la copa de directorios
                                copiarDirectorios(new File(d1,ficheros[x]),new File(d2,ficheros[x]));                           
                        }                                               
                }else {
                        copiarFicheros(d1,d2);
                }
                
        }
}