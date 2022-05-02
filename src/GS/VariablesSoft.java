package GS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class VariablesSoft {
	
	public float Version = 1.0f;		//Version del software	
	public String Name_Soft="";			//Nombre del Software
	public int SN = -1;					//Numero de serie
	public byte ModoLecturaSN = 0;		//De que modo obtengo el SN
	public int Cod = 0;					//Codigo de activacion
	public Calcular_CA cmp;
	public String Extension = "";
	public String RutaActualizaciones = "";	//Ruta de las actualizaciones
	public String DirServidor = "";
	public String DirServidorCodigo = "";
	public String JarSoftware = "";
	public boolean NoEsApplet = true;
	public boolean SoftConVencimiento = false;
	public Date FechaActivacion= null;
	public String DefaultLook = "";
	public boolean ATFA = false; 
	public int AnioVencimiento = 2000;
	public boolean ModoEdicion = false;

/**********************************************************************************************************
 *  Para el software de 3D y Pizarra solamente!!!
 *********************************************************************************************************/
	public String Pizarra = "", RXDeportes = "";
	public boolean pizarra = false;
	
/**
* @param Idioma = 0: Castellano
* @param Idioma = 1: Ingles
* @param Idioma = 2: Portugues
* @param Idioma = 3: Italiano
*/ 
	public int Idioma = 0; 
	
//****************************************************************//
		public VariablesSoft(){	
			Inicializar();
		}
		
//****************************************************************//
	public VariablesSoft(boolean sinCodigo){
		if(sinCodigo){
			InicializarSinCodigo();
		}else{
			Inicializar();
		}	
	}

//****************************************************************//
		public VariablesSoft(String Nombre, float version, String extension){
			Name_Soft = Nombre;
			Version = version;
			Extension = extension;
			Inicializar();	
		}	
		
//****************************************************************//
		public VariablesSoft(float version, String extension, int anioVencimiento){
			Version = version;
			Extension = extension;
			AnioVencimiento = anioVencimiento;
			Inicializar();	
		}
	
//****************************************************************//
		public VariablesSoft(float version, String extension, boolean sinCodigo, boolean EsPizarra, int anioVencimiento){				
			Version = version;
			Extension = extension;
			AnioVencimiento = anioVencimiento;
			if(EsPizarra){
		    	pizarra = true;
				Extension = "r3p";
		    	Pizarra = "_Pizarra";
			}
			if(sinCodigo){
				InicializarSinCodigo();
			}else{
				Inicializar();
			}	
		}
		
//****************************************************************//
	public VariablesSoft(float version, String extension, boolean sinCodigo, boolean EsPizarra){		
		Version = version;
		Extension = extension;
		if(EsPizarra){
	    	pizarra = true;
			Extension = "r3p";
	    	Pizarra = "_Pizarra";
		}
		if(sinCodigo){
			InicializarSinCodigo();
		}else{
			Inicializar();
		}	
	}	
			
//****************************************************************//
	private void Inicializar(){
		InicializarSinCodigo();
		if(NoEsApplet){
			ObtenerCodigo();
		}else{
			System.out.println("Es un Applet y no ejecuto CPUID!!!");
		}
	}
	
//****************************************************************//
	public void InicializarSinCodigo(){	
		DefaultLook = UIManager.getSystemLookAndFeelClassName();
		BufferedReader br = null;
    	try{
    		File archivoDebug = new File("LocalData/ModoEdicion.dbg");
    		if(archivoDebug.exists()){
    			ModoEdicion = true;
    		}
    		String Datasoft = "LocalData/SoftwareData.sdt";
    		if(pizarra){
    			Datasoft = "LocalData/SoftwareData2D.sdt";
    		}
    		File archivo = new File (Datasoft);
    		if(archivo.exists()){
    			FileReader fr = new FileReader(archivo);    			
    			br = new BufferedReader(fr);
    			String s = br.readLine();
    			boolean encriptado = false;
    			if(s.equals("kfghlm")) {  
    				s = br.readLine();
    				encriptado = true;
    			}
    			Name_Soft = Util.Desencriptar(s, encriptado);
    			JarSoftware = Util.Desencriptar(br.readLine(), encriptado);  
    			RutaActualizaciones = Util.Desencriptar(br.readLine(), encriptado);  
    			String vencimiento = br.readLine();   
    			if(vencimiento!=null){    	
    				vencimiento = Util.Desencriptar(vencimiento, encriptado);
    				SoftConVencimiento = !(vencimiento.equals("XNS%[JSHNRNJSYT")); // XNS%[JSHNRNJSYT -> cualquier otro valor TIENE VENCIMIENTO
    			}  	    
    			s = br.readLine();   
    			if(s!=null){    				
    				DirServidorCodigo = Util.Desencriptar(s, encriptado);
    			}     	
    			if(ModoEdicion) {
	    			System.out.println("kfghlm");
	    			System.out.println(Name_Soft);
	    			System.out.println(JarSoftware);
	    			System.out.println(RutaActualizaciones);
	    			if(SoftConVencimiento) {
	    				System.out.println(vencimiento);
	    			}else {
	    				System.out.println("XNS%[JSHNRNJSYT");
	    				
	    			}	   
	    			System.out.println(DirServidorCodigo);
    			}
    		}
    		if(Name_Soft.contains("ATFA")){
    			ATFA = true;
    		}
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (Exception ex) { }
		}
	}
	
//****************************************************************//
    public void ObtenerCodigo() {
    	Obtener_SN OSN = new Obtener_SN(Name_Soft);
		if(OSN.SN != -1){
			Calcular_CA Cmp = new Calcular_CA();			
			SN = OSN.SN;
			ModoLecturaSN = (byte) OSN.Modo;
			Cod = Cmp.CA_Calcular(SN, Name_Soft);
			cmp = Cmp;	
		}else{ //Si el numero de serie es -1
			JOptionPane.showMessageDialog((java.awt.Component) null,"Error al tratar de leer numero de serie en VarSoft","Error",JOptionPane.ERROR_MESSAGE); 			
		}
    }        
    
}
