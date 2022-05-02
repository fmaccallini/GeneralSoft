package GS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.swing.JOptionPane;

public class MostrarExcepcion {
	private static String NombreSoft = "";
	
	public MostrarExcepcion(String nombreSoft){
		NombreSoft = nombreSoft;
	}
	
/*----------------------------------------------------------------------------*/
	public void Mostrar(String metodo, Exception ex){
		System.out.println("Excepcion en metodo "+metodo+" de "+NombreSoft);
		ex.printStackTrace();
		BufferedWriter out = null; 
		try{
			File log =  new File("Log.txt");
			if(log.exists()){
				long tamano = log.length ( );
				System.out.println("Tamaño del archivo: "+tamano);
				if(tamano>1000000){log.delete();}
			}
			out = new BufferedWriter(new FileWriter("Log.txt", true));
			out.write("\r\n");
			out.write("----------------------------------------------------------------------------------------\r\n");
			out.write("----------------------------------------------------------------------------------------\r\n");
			java.util.Date fecha = new Date();
			out.write(fecha.toString()+"\r\n");
			out.write("Excepcion en metodo "+metodo+" de "+NombreSoft+"\r\n");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			out.write(sw.getBuffer().toString());
			File f =  new File("Debug.dbg");
			if(f.exists()){
				JOptionPane.showMessageDialog((java.awt.Component) null,"Exception","Ver excepcion",JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception  ex2){
		}finally{// En el finally cerramos el fichero, para asegurarnos que se cierra tanto si todo va bien como si salta una excepcion.
			try{ if( null != out ){ out.close();}                  
			}catch (Exception ex3){}
		}
	}
}
