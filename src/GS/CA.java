package GS;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/******************************************************************
 * Se debe sobreescribir el metodo 
 * AbrirSoftware()
 * AbrirActivarSoft()
 *****************************************************************/
public class CA implements WindowListener{
   	public int CodigoLeido = -11111111;
   	public File archivo = null;
   	public FileReader fr = null;
   	public BufferedReader br = null; 
   	public VariablesSoft Vars;
   	
	public CA(VariablesSoft vars){
		Vars = vars;
		String strSoftConVencimiento="";
		try{					
			if(Vars.DirServidorCodigo.equals("")) {
				File folder = new File("LocalData");
	        	if(!folder.exists()){folder.mkdirs();} 
				File archivo = new File ("LocalData/Config.cnf");
			    if(archivo.exists()){
			    	fr = new FileReader (archivo);
			    	br = new BufferedReader(fr);
			    	String linea = br.readLine(); //Leo el codigo de activacion
			    	if(Vars.SoftConVencimiento){
			    		String strs[] = linea.split("@;");
			    		if(strs.length>1){
			    			CodigoLeido = Integer.valueOf(strs[0]);
			    			strSoftConVencimiento = Util.Desencriptar(strs[1], true);
			    		}else{
			    			archivo.delete();
			    		}
			    	}else{
			    		CodigoLeido = Integer.valueOf(linea);
			    	}
			    	br.close(); 
			    }   
			}else {								
				 if(Util.ComprobarConexion()) {
					 if(Vars.DirServidorCodigo.startsWith("https://drive.google.com")) {
						 String ruta = "";
						 switch(Vars.SN) {						 	
							 case 921018815:
								 if(Vars.Cod == 124195993) { //RXFutbol3D 2020
									 ruta = "https://drive.google.com/file/d/1dTfQ9AQHrmupi4CrN5N6Y5E47ElgqisD/view";
								 }								 
								 break;
							 case 938393949:
								  if(Vars.Cod == 462000) { //RXFutbol3D 2020
									 ruta = "https://drive.google.com/file/d/1JGt9xMVjvQxud2ucCDZGNXSDqE5KTRYG/view";
								  }
								 break;							
						 }
						 if(Util.FileExistsOnServer(ruta)) {							
							 CodigoLeido = Vars.Cod;
						 }else {
							 System.out.println("No existe el codigo en el servidor: "+ruta);
						 }
					 }else {
						 if(Util.FileExistsOnServer(Vars.DirServidorCodigo+Vars.Cod+".act")) {
							 CodigoLeido = Vars.Cod;
							 if(Vars.ModoEdicion) {
								 System.out.println("Existe el codigo en el servidor: "+Vars.DirServidorCodigo+Vars.Cod+".act");
							 }
							
						 }else {
							 System.out.println("No existe el codigo en el servidor: "+Vars.DirServidorCodigo+Vars.Cod+".act");
						 }
					 }					 
				 }else {
					JOptionPane.showMessageDialog((java.awt.Component) null,"Debe estar conectado a internet.","Error",JOptionPane.ERROR_MESSAGE);
					return;
				 }
			}        	
		}catch(IOException  e){
			JOptionPane.showMessageDialog((java.awt.Component) null,"Excepcion en LCA_RXF3D="+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); }
		finally{// En el finally cerramos el fichero, para asegurarnos que se cierra tanto si todo va bien como si salta una excepcion.
			try{ if( null != fr ){ fr.close();}                  
			}catch (Exception e2){ e2.printStackTrace();}
		}
		if(Vars.Cod == CodigoLeido){
			if(Vars.SoftConVencimiento){
				Idioma_General IDM = new Idioma_General(0);
				int vencimiento = ObtenerVencimiento(strSoftConVencimiento);
				switch(vencimiento){
					case 0: 
						AbrirSoftware();
						System.out.println("Abriendo el software...");
						break;
					case 1:
						JOptionPane.showMessageDialog(null,IDM.Labels[11],IDM.Labels[13],JOptionPane.ERROR_MESSAGE);
						break;
					case 2:
					case 3:
						JOptionPane.showMessageDialog(null,IDM.Labels[12],IDM.Labels[13],JOptionPane.ERROR_MESSAGE);
						break;
					case 4:
						JOptionPane.showMessageDialog(null,IDM.Labels[14],IDM.Labels[13],JOptionPane.ERROR_MESSAGE);
						break;
				}				
			}else{
				AbrirSoftware();
				System.out.println("Abriendo el software...");
			}
		}else {
			AbrirActivarSoft();
			System.out.println("Abriendo el activador...");
		}
	}
	
/*----------------------------------------------------------------------------*/
    public void AbrirSoftware() {	 
    /*	Sobrescribir este metodo de esta manera...
     * SwingUtilities.invokeLater(new Runnable() { 
    		public void run() {
    			WelcomeWindow thisClass = new WelcomeWindow(null,Vars);
    			thisClass.setVisible(true);
    	}});*/
    }
    
/*----------------------------------------------------------------------------*/
    public void AbrirActivarSoft() {	 
    /*	Sobrescribir este metodo de esta manera...
     *  ActivarSoft VC = new ActivarSoft(Vars);
		VC.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { System.exit(0);}
		});	
		VC.pack();
		VC.setVisible(true);*/
    }
    
//****************************************************************//
    public int ObtenerVencimiento(String strSoftConVencimiento) {
    	int ret = 4;
    	try {
    		String s[] = strSoftConVencimiento.split("@;");
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    		Vars.FechaActivacion = (Date) formatter.parse(s[0]);
    		Date FechaUltimaUtilizacion = (Date) formatter.parse(s[1]);
    		Date FechaActual = new Date();
    		Calendar cal = Calendar.getInstance(); 
    		SimpleDateFormat df = new SimpleDateFormat("yyyy");
    		int AnioActivacion = Integer.parseInt(df.format(Vars.FechaActivacion));
            cal.set(AnioActivacion+1, 0, 1); //Se vence en enero del año siguiente
            Date FechaVencimiento = cal.getTime();
            int AnioPC =  Integer.parseInt(df.format(FechaActual));
    		if((FechaActual.compareTo(FechaVencimiento)) >= 0 ){ //FechaActual > FechaVencimiento ->VECIDO!!
    			return 1;
    		}
    		if(AnioPC>= Vars.AnioVencimiento){ //AnioActual > AnioVencimiento ->VECIDO!!
    			return 1;
    		}
    		if((Vars.FechaActivacion.compareTo(FechaActual)) >= 0 ){ //FechaActivacion > FechaActual ->SE ACTIVO EN EL FUTURO!!
    			return 2;
    		}
    		if((FechaUltimaUtilizacion.compareTo(FechaActual)) >= 0 ){ //FechaUltimaUtilizacion > FechaActual ->SE ATRASO LA FECHA DE LA PC!!
    			return 2;
    		}    		
    		ret = 0;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ret;
    }    
/**************************************************************************************************/ 
	  //Metodos para windowListener, para preguntar al cerrar
	      public void windowClosed(WindowEvent evt) {	} 				 
	      public void windowClosing(WindowEvent evt) {	System.exit(0);	}		
	      public void windowIconified(WindowEvent evt) {}
	      public void windowDeiconified(WindowEvent evt) {	}
	      public void windowOpened(WindowEvent evt) {}
	      public void windowActivated(WindowEvent evt) {}
	      public void windowDeactivated(WindowEvent evt) {}
/**************************************************************************************************/ 	
}
