package GS;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import org.apache.commons.lang3.SystemUtils;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaRootPaneUI;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Util {
	public static String MD5Image = "be53a0541a6d36f6ecb879fa2c584b08";
	//public static String MD5botones = "677ae1d06e3fcc562086e1089024a514";
	
/*----------------------------------------------------------------------------*/ 
	public static String VersionToString(double Version) {
		String vers = String.valueOf((int)(Version*100));
		String[] v = new String[2];
		v[0] = vers.substring(0, vers.length()-2);
		v[1] = vers.substring(vers.length()-2);
		vers = Completar(v[0], 3, "0", true) +"."+Completar(v[1], 2, "0", false);		
		vers = RandomString(10,false)+vers+RandomString(10,false) + "@;@";
		return vers;
	}

/*----------------------------------------------------------------------------*/ 
	@SuppressWarnings("unused")
	private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to "+newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

/*----------------------------------------------------------------------------*/ 
	public static String getExtensionArchivo(String NombreArchivo) {
		String ext = "";
		int i = NombreArchivo.lastIndexOf('.');
		if (i > 0) {
		    ext = NombreArchivo.substring(i+1);
		}
		return ext;
} 
/*----------------------------------------------------------------------------*/ 
	   public static String RandomString(int length, boolean SoloLetrasYNumeros) {
		   String output = null;
		   char[] chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ!#$%&/().;,{}[]¿?*".toCharArray();
		   if(SoloLetrasYNumeros){
			   chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		   }
		   StringBuilder sb = new StringBuilder();
		   Random random = new Random();
		   for (int i = 0; i < length; i++) {
			   char c = chars[random.nextInt(chars.length)];
			   sb.append(c);
		   }
		   output = sb.toString();
		   return output;
	   } 
	   
/*-------------------------------------------------------------------------*/
		public static String FormatoDosDecimales(Double d){
			DecimalFormat formatoDosDecimales = new DecimalFormat("#.00");
			String ret = formatoDosDecimales.format(d);
			ret = ret.replace(",", ".");
			if(ret.startsWith(".")) {
				ret = "0"+ret;
			}
			if(ret.endsWith("0")) {
				ret = ret.substring(0, ret.length()-1);
			}
			if(ret.endsWith(".0")) {
				ret = ret.substring(0, ret.length()-2);
			}
			return ret;
		}
		
/*----------------------------------------------------------------------------*/ 
	   public static boolean ContieneCaracteres(String Palabra, String Caracteres) {
		   boolean Contiene = false;
		   char[] chars = Caracteres.toCharArray();
		   for (int i = 0; i < chars.length; i++) {
			   if(Palabra.contains(String.valueOf(chars[i]))){
				   Contiene = true;
			   }
		   }
		   return Contiene;		  
	   } 
	   
/*----------------------------------------------------------------------------*/ 
	   public static boolean SoloNumerosYLetras(String Palabra, boolean incluirEnie) {
		   boolean SoloNumerosYLetras = true;
		   String Chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		   if(incluirEnie) {
			   Chars = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz";
		   } 
		   char[] charsPalabra = Palabra.toCharArray();
		   for (int i = 0; i < charsPalabra.length; i++) {
			  if(!Chars.contains(String.valueOf(charsPalabra[i]))) {
				  SoloNumerosYLetras = false;
			  }
			   
		   }
		   return SoloNumerosYLetras;
	   } 
/*----------------------------------------------------------------------------*/
	    public static String Encriptar(String s){
	    	String sEncript="";
	    	for(int i=0;i<s.length();i++){
	    		int encr = (int)s.charAt(i) + 5 ;
	    		sEncript = sEncript+(char)encr;
	    	}		
	    	return sEncript;
	    }
	    
/*----------------------------------------------------------------------------*/
	public static int getScreenWidth(){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension Dim = tk.getScreenSize();
	    return Dim.width;
	}
	
/*----------------------------------------------------------------------------*/
	public static int getScreenHeight(){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension Dim = tk.getScreenSize();
	    return Dim.height;
	}
	
/*----------------------------------------------------------------------------*/
	public static int MinInt(int[] vals){
		int min = vals[0];
		for(int i=1;i<vals.length;i++){
			min = Math.min(min, vals[i]);
		}
	    return min;
	}
	
/*----------------------------------------------------------------------------*/
	public static int MaxInt(int[] vals){
		int min = vals[0];
		for(int i=1;i<vals.length;i++){
			min = Math.max(min, vals[i]);
		}
	    return min;
	}
	
/****************************************************************
 * Se fija si en el JPanel jp esta agregado el c2
 ***************************************************************/
	public static boolean Contiene(JPanel jp, Component c2){
		Component c[] = jp.getComponents();
		boolean ret = false;
		for(int i=0;i<c.length;i++){
			if(c[i].equals(c2)){
				ret = true;
			}
		}
	    return ret;
	}
	
/*----------------------------------------------------------------------------*/
	public static String MayusculaPrimeraLetra(String cadena){
		if(!EsNulo(cadena)){
			cadena = cadena.toLowerCase();
			char[] caracteres = cadena.toCharArray();		
			caracteres[0] = Character.toUpperCase(caracteres[0]);
			for (int i = 0; i < cadena.length()- 2; i++) {
			    if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ','){
			      caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
			    }
			}
		    return new String(caracteres);
		}
		return "";
	}
	
/*----------------------------------------------------------------------------*/
	public static String MinusculaSinAcentos(String cadena){
		if(!EsNulo(cadena)){
			cadena = cadena.toLowerCase();
			char[] caracteres = cadena.toCharArray();	
			char[] conAcentos = new char[]{'á','é','í','ó','ú'};
			char[] sinAcentos = new char[]{'a','e','i','o','u'};
			for (int i = 0; i < cadena.length(); i++) {
				for (int j = 0; j < conAcentos.length; j++) {
				    if (caracteres[i] == conAcentos[j]){
				      caracteres[i] = sinAcentos[j];
				    }
				}
			}
		    return new String(caracteres);
		}
		return "";
	}
/*----------------------------------------------------------------------------*/
	public static String getUserDir(){
		String UserDir = System.getProperty("user.dir");
		UserDir = UserDir.replace("\\", "/");			
	    return UserDir;
	}
	
/*************************************************************************************
* Devuelve la cantidad de Bits de la PC 32 o 64
*************************************************************************************/
	public static int BitSize(){
		int BitSize = 32;
		if(System.getProperty("sun.arch.data.model").contains("64")){
			BitSize = 64;
		}		
	    return BitSize;
	}
	
/*----------------------------------------------------------------------------*/
	public static boolean ComprobarConexion(){
		Conexion cnx = new Conexion();
	    return cnx.ProbarConexion();
	}
	
/****************************************************************/
	  public static String CaracteresEspeciales(String cadena){
		  String ret=cadena;
		  ret = ret.replace("á", "\u00E1");
		  ret = ret.replace("é", "\u00E9");
		  ret = ret.replace("í", "\u00ED");
		  ret = ret.replace("ó", "\u00F3");
		  ret = ret.replace("ú", "\u00FA");
		  ret = ret.replace("Á", "\u00C1");
		  ret = ret.replace("É", "\u00C9");
		  ret = ret.replace("Í", "\u00CD");
		  ret = ret.replace("Ó", "\u00D3");
		  ret = ret.replace("Ú", "\u00DA");
		  ret = ret.replace("ñ", "\u00f1");
		  ret = ret.replace("Ñ", "\u00d1");
		  return ret;
	  }	   
	  
/****************************************************************/
	public boolean ProbarConexion() {
		Conexion conexion = new Conexion();
		return conexion.ProbarConexion();
	}
	
/*----------------------------------------------------------------------------*/
	    public static String Completar(String s, int longitud, String caracter, boolean adelante){
	    	String ret=s;
	    	while(ret.length()<longitud){
	    		if(adelante){
	    			ret = caracter + ret;
	    		}else{
	    			ret = ret + caracter;
	    		}
	    	}		
	    	return ret;
	    }
/*----------------------------------------------------------------------------*/
	    public static void TamanioFijo(Component c, int w, int h){
	    	c.setMaximumSize(new Dimension(w,h));
	    	c.setMinimumSize(new Dimension(w,h));
	    	c.setPreferredSize(new Dimension(w,h));
	    }
	    
/*----------------------------------------------------------------------------*/
	    public static void TamanioFijoW(Component c, int w, int preferredh){
	    	c.setMaximumSize(new Dimension(w,3000));
	    	c.setMinimumSize(new Dimension(w,10));
	    	c.setPreferredSize(new Dimension(w,preferredh));
	    }
	    
/*----------------------------------------------------------------------------*/
	    public static void TamanioFijoH(Component c, int h, int preferredw){
	    	c.setMaximumSize(new Dimension(3000,h));
	    	c.setMinimumSize(new Dimension(10,h));
	    	c.setPreferredSize(new Dimension(preferredw,h));
	    }
	    
/*----------------------------------------------------------------------------*/
	    public static String Desencriptar(String s, boolean Desenc){
	    	String sDesencript = "";
	    	if(Desenc){
	    		for(int i=0;i<s.length();i++){
	    			int desencr = (int)s.charAt(i) - 5 ;
	    			sDesencript = sDesencript+(char)desencr;
	    		}   
	    	}else{
	    		sDesencript = s;
	    	}    
	    	//System.out.println(sDesencript);
	    	return sDesencript;
	    }
	    
/*----------------------------------------------------------------------------*/
	    public static void EjecutarJAR(String Jar){
	    	try{
				if (isWindows()) {
					System.out.println("cmd.exe /K Java -jar " + Jar);
					Runtime.getRuntime().exec("cmd.exe /K Java -jar " + Jar);
					//Runtime.getRuntime().exec("cmd.exe /K "+Jar);
				} 
				if (isMac()) {
					Runtime.getRuntime().exec("Java -jar " +Jar);
				}else{
					if(isUnix()){
						//Linux o Unix	
						System.out.println("Es Unix");
						String UserDir = getUserDir();
						if(UserDir.contains(" ")){
							System.out.println("La ruta del archivo contiene un espacio en blanco, por favor cambialo por _");
						}else{
							String command = "java -jar " +getUserDir()+"/"+Jar;					
							Runtime.getRuntime().exec(command);	
						}
					}
				}
				Thread.sleep(3000); 
			}catch (Exception e){ }
	    }
	    
/*************************************************************************************
 * Devuelve un arreglo con el contenido de los dos + Item0 ordenados en forma alfabetica
 * @param array1: primer arreglo
 * @param array2: segundo arreglo
 * @param Item0: va a ser el primer valor del arreglo, salvo que sea ""
 * @param ordenarAlfab: si true ordena alfabeticamente el arreglo.
 * @return
 *************************************************************************************/
		public static String[] Array_Sumar(String[] array1, String[] array2, String Item0, boolean ordenarAlfab){
			String a[] = {};
			if(!Item0.equals("")){
				a = new String[array1.length+array2.length+1];
				a[0] = Item0;
				System.arraycopy(array1, 0, a, 1, array1.length);
				System.arraycopy(array2, 0, a, array1.length+1, array2.length);
				if(ordenarAlfab){
					Arrays.sort(a, 1, a.length);
				}
			}else{
				a = new String[array1.length+array2.length];
				System.arraycopy(array1, 0, a, 0, array1.length);
				System.arraycopy(array2, 0, a, array1.length, array2.length);
				if(ordenarAlfab){
					Arrays.sort(a);
				}
			}		 
			return a;
		}
		
/********************************************************************
* @return true Actualizar
* @return false No actualizar y abrir el software
******************************************************************/
		public static boolean Comprobar_Actulizaciones(String RutaActualizaciones, String JarSoftware){
			boolean Descargar = false;
			try{ 
				File archivo = new File (JarSoftware);
				String DirectorioServidor = RutaActualizaciones.substring(0,RutaActualizaciones.lastIndexOf("/")+1);	
				//System.out.println("DirectorioServidor: "+DirectorioServidor);
				if (archivo.exists()){    //Si el archivo existe me fijo si son iguales	 						
					int TamanoArchivoServidor = (Integer) Http_Obtener(DirectorioServidor+JarSoftware,1);
					//System.out.println("Version en PC: "+archivo.length());
					//System.out.println("Version en Servidor: "+TamanoArchivoServidor);
					if(archivo.length()!= TamanoArchivoServidor){    						
						Descargar = true;
					}
				}else{
					Descargar = true;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return Descargar;	    			
	}
	
/********************************************************************
* @param ret = 0 -> devuelve BufferedReader
* @param ret = 1 -> devuelve Tamaño del Archivo
******************************************************************/ 
		public static Object Http_Obtener(String Rutahttp, int ret){
			BufferedReader br = null;
			int TamanioArchivo = 0;
			try{ 
				URL url = new URL(Rutahttp);	
				if(Rutahttp.startsWith("https:")){
					TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {					
		                        public java.security.cert.X509Certificate[] getAcceptedIssuers(){
		                            return null;
		                        }
		                        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType){
		                            //No need to implement.
		                        }
		                        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType){
		                            //No need to implement.
		                        }
		                    }
		            };
		            // Install the all-trusting trust manager
		            SSLContext sc = SSLContext.getInstance("SSL");
		            sc.init(null, trustAllCerts, new java.security.SecureRandom());
		            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
		            con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		            br = new BufferedReader(new InputStreamReader(con.getInputStream()));	
		            TamanioArchivo = con.getContentLength();
				}else{			    	
					HttpURLConnection con = (HttpURLConnection)url.openConnection();
					con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));	
					TamanioArchivo = con.getContentLength();
				}
            }catch (Exception e){
                System.out.println(e);
            }
			switch(ret){
				case 0: 
					return br;
				case 1: 
					return TamanioArchivo;
			}
			return null;
		}
/*----------------------------------------------------------------------------*/ 
		public static String[] Array_Eliminar(String[] array,int index){
			String a[] = new String[array.length-1];			
			for(int i=0;i<array.length;i++){
				if(i<index){
					a[i] = array[i];
				}
				if(i>index){
					a[i-1] = array[i];
				}
				
			}
			return a;
		}
	
/*************************************************************************************
* Devuelve el primer indice del valor buscado o -1 si no lo encuentra
*************************************************************************************/
		public static int Array_getIndex(String[] array,String Buscar){
			int ret=-1;			
			for(int i=0;i<array.length;i++){
				if(array[i].equals(Buscar)){
					ret = i;
				}				
			}
			return ret;
		}
/*----------------------------------------------------------------------------*/ 
	    public static void Cargar_UIManager(Color ColorFondo, Color ColorFondo2, Color ColorLetra, Color ColorLetra2, Color ColorLetra3) {
	    	 if(isWindows()) { //Menus		 
    			 UIManager.put("MenuBar.background", ColorFondo);
    			 UIManager.put("Menu.background", ColorFondo);
    			 UIManager.put("Menu.foreground", ColorLetra);        
    			 UIManager.put("Menu.selectionBackground", ColorFondo2);   
    			 UIManager.put("Menu.selectionForeground", ColorLetra2);
    			 UIManager.put("Menu.borderPainted", new Boolean(false)); 
    			 UIManager.put("PopupMenu.background", ColorFondo);
    			 UIManager.put("PopupMenu.foreground", ColorLetra);        
    			 UIManager.put("PopupMenu.selectionBackground", ColorFondo2);   
    			 UIManager.put("PopupMenu.selectionForeground", ColorLetra2);
    			 UIManager.put("PopupMenu.borderPainted", new Boolean(false));
    			 UIManager.put("MenuItem.background", ColorFondo);
    			 UIManager.put("MenuItem.foreground", ColorLetra2);        
    			 UIManager.put("MenuItem.selectionBackground", ColorFondo2);   
    			 UIManager.put("MenuItem.selectionForeground", ColorLetra2);
    			 UIManager.put("MenuItem.acceleratorForeground", ColorLetra3);		
    			 UIManager.put("MenuItem.borderPainted", new Boolean(false));  
    			 UIManager.put("RadioButtonMenuItem.background", ColorFondo);
    			 UIManager.put("RadioButtonMenuItem.foreground", ColorLetra);        
    			 UIManager.put("RadioButtonMenuItem.selectionBackground", ColorFondo2);   
    			 UIManager.put("RadioButtonMenuItem.selectionForeground", ColorLetra2);
    			 UIManager.put("RadioButtonMenuItem.borderPainted", new Boolean(false));  
    			 UIManager.put("CheckBoxMenuItem.background", ColorFondo);
    			 UIManager.put("CheckBoxMenuItem.foreground", ColorLetra);        
    			 UIManager.put("CheckBoxMenuItem.selectionBackground", ColorFondo2);   
    			 UIManager.put("CheckBoxMenuItem.selectionForeground", ColorLetra2);
    			 UIManager.put("CheckBoxMenuItem.borderPainted", new Boolean(false));      
    		} 	    	 
	    	 
    		//Button
    		UIManager.put("Button.background", ColorFondo2);
    		UIManager.put("Button.foreground", ColorLetra2);	
    		UIManager.put("Button.selectBackground", ColorFondo2);
    		UIManager.put("Button.selectForeground", Color.WHITE);
    		
    		//ComboBox
    		UIManager.put("ComboBox.background", ColorFondo);
    		UIManager.put("ComboBox.foreground", ColorLetra);
    		UIManager.put("ComboBox.selectionBackground", ColorFondo2);
    		UIManager.put("ComboBox.selectionForeground", Color.BLACK);
    		UIManager.put("ComboBox.disabledBackground", ColorFondo2);
    		UIManager.put("ComboBox.disabledForeground", ColorLetra);
    		
    		//TabbedPane
    		UIManager.put("TabbedPane.background", ColorFondo);
    		UIManager.put("TabbedPane.foreground", ColorLetra3);
    		UIManager.put("TabbedPane.selected", Color.LIGHT_GRAY);
    		
    		//Slider
    		UIManager.put("Slider.background", ColorFondo);
    		UIManager.put("Slider.foreground", ColorLetra3);
    		UIManager.put("Slider.selectBackground", ColorFondo2);
    		UIManager.put("Slider.selectForeground", Color.WHITE);
    		
    		//Label
    		UIManager.put("Label.background", ColorFondo);
    		UIManager.put("Label.foreground", ColorLetra);
    		
    		//ToolTips
    		UIManager.put("ToolTip.background", ColorFondo2); 
    		UIManager.put("ToolTip.foreground", ColorLetra2);
    		
    		//TextField
    		UIManager.put("TextField.background", Color.LIGHT_GRAY); 
    		UIManager.put("TextField.foreground", Color.BLACK);
    		
    		//TextArea
    		UIManager.put("TextArea.background", Color.LIGHT_GRAY); 
    		UIManager.put("TextArea.foreground", Color.BLACK);
    		
    		//FormattedTextField
    		UIManager.put("FormattedTextField.background", Color.LIGHT_GRAY); 
    		UIManager.put("FormattedTextField.foreground", Color.BLACK);
    		
    		//List
    		UIManager.put("List.background", Color.LIGHT_GRAY); 
    		UIManager.put("List.foreground", Color.BLACK);
    		UIManager.put("List.selectionBackground", ColorFondo2); 
    		UIManager.put("List.selectionForeground", Color.BLACK);	
	    }
	    
/*----------------------------------------------------------------------------*/ 
	    public final static void Cargar_UIManagerSynteticaBlackEye(){
	    	try {
	    		UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());  
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }  
	    
/*----------------------------------------------------------------------------*/ 
	    public final static void JButton_Configurar(JButton JB, Border b){
	    	JB.setFocusable(false);
	    	JB.setBorder(b);
	    	JB.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    } 
	    
/******************************************************************************
 * @param JB = setFocusable(false);
 * @param JB = setBorder(b);
 * @param JB = setCursor(new Cursor(Cursor.HAND_CURSOR));
 * @param JB = setFont(new Font("Dialog",Font.PLAIN,10));
 * @param JB = Util.TamanioFijo(JB, w, h);
 *******************************************************************************/ 
	    public final static void JButton_Configurar(JButton JB, Border b, int w, int h){
	    	JB.setFocusable(false);
	    	JB.setBorder(b);
	    	JB.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	JB.setFont(new Font("Dialog",Font.PLAIN,10));
	    	Util.TamanioFijo(JB, w, h);
	    } 
	    
/******************************************************************************
 * @param JB = setFocusable(false);
 * @param JB = setCursor(new Cursor(Cursor.HAND_CURSOR));
 *******************************************************************************/ 
	    public final static void JButton_Configurar(JButton JB){
	    	JB.setFocusable(false);
	    	JB.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    } 
	    
/*----------------------------------------------------------------------------*/
        public static void Cargar_UIManager(String Nombre) {
            try {
                UIManager.setLookAndFeel(Nombre);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
/*-------------------------------------------------------------------------*/
	public static void Tabla_AnchoColumna(JTable tbl, int columna[], int ancho){
		for (int i = 0; i < columna.length; i++) {
			if(columna[i]>=0) {
			    tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(ancho);
			    tbl.getColumnModel().getColumn(columna[i]).setMinWidth(ancho);
			    tbl.getColumnModel().getColumn(columna[i]).setPreferredWidth(ancho);
			    tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(ancho);
			    tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(ancho);
			    tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setPreferredWidth(ancho);
			}
		}	 
	}
		
/*-------------------------------------------------------------------------*/
	public static int Tabla_NroColumna(JTable tbl, String Nombre){
		for (int i = 0; i <tbl.getColumnCount(); i++) {
			String ident =(String) tbl.getColumnModel().getColumn(i).getIdentifier();
			ident = ident.replace("<br>", "");
			Nombre= Nombre.replace("<br>", "");
			if(ident.equals(Nombre) || ident.equals("<html><div align='center' style='vertical-align:top'>"+Nombre+"</div></html>")){
				return i;
			}
		}
		return -1;
	}
	
/*-------------------------------------------------------------------------*/
	public static Object Tabla_getValor(JTable tbl, int row, String NombreColumna){
		try{
			return tbl.getValueAt(row, Tabla_NroColumna(tbl, NombreColumna));
		}catch(Exception e) {}		
		return null;
	}
	
/*-------------------------------------------------------------------------*/
	public static void Tabla_setValor(JTable tbl, Object valor, int row, String NombreColumna){
		try{	
			tbl.setValueAt(valor, row, Tabla_NroColumna(tbl, NombreColumna));
		}catch(Exception e) {}		
	}
	
/*-------------------------------------------------------------------------*/
	public static void Tabla_AjustarAltoFilas(JTable table){
	    try{	    	
	        for (int row = 0; row < table.getRowCount(); row++){
	            int rowHeight = table.getRowHeight();
	            for (int column = 0; column < table.getColumnCount(); column++){
	                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
	                if(comp instanceof JTextArea){	
	                	int Ancho = table.getColumnModel().getColumn(column).getWidth();
                		String text = ((JTextArea)comp).getText();
	                	if(Ancho>0 && !Util.EsNulo(text)){
	                		Font f = ((JTextArea)comp).getFont();
	                		FontMetrics dim = comp.getFontMetrics(f);
	                		String[] rows = text.split("\n");
	                		int alto = 0;
	                		for(int i=0;i<rows.length;i++){
		                		int w_text = dim.stringWidth(rows[i]);	            		
		                		if(w_text>Ancho){
		                			alto = alto + ((w_text/Ancho)+1)*(dim.getHeight()+2);		                			
		                		}else{
		                			alto = alto + (dim.getHeight()+2);
		                		}		                		
	                		}
	                		rowHeight = Math.max(rowHeight, alto);
	                	}
	            		
	        		}	               
	            }
	            table.setRowHeight(row, rowHeight);
	            table.repaint();
	        }
	        JTableHeader th_Resumen = table.getTableHeader(); //Para modificar el tipo de letra de la cabecera
	        FontMetrics FM =  th_Resumen.getFontMetrics(th_Resumen.getFont());
	        int heigth = FM.getHeight()+10;
	        for (int column = 0; column < table.getColumnCount(); column++){
	        	int Ancho = table.getColumnModel().getColumn(column).getWidth();
        		String ident =(String)table.getColumnModel().getColumn(column).getIdentifier();        		
        		ident = ident.replace("<br>", "");
        		ident = ident.replace("<html><div align='center' style='vertical-align:top'>", "");
        		ident = ident.replace("</div></html>", "");
        		int alto = FM.getHeight();
            	if(Ancho>0 && !Util.EsNulo(ident)){
            		String[] words = ident.split(" ");    
            		int w_text = 0;
            		for(int i=0;i<words.length;i++){            		
                		if(w_text+FM.stringWidth(words[i])>Ancho){
                			alto = alto + (FM.getHeight()+2);	
                			w_text = FM.stringWidth(words[i]);
                		}else{
                			w_text = w_text + FM.stringWidth(words[i]);
                		}		                		
            		}
            		heigth = Math.max(heigth, alto);
            	}
	        }
	        if(heigth>50) {
	        	heigth = 50;
	        }
	        th_Resumen.setMinimumSize(new Dimension(10, heigth));
			th_Resumen.setPreferredSize(new Dimension(1000, heigth));
			th_Resumen.setMaximumSize(new Dimension(100000, heigth));
			th_Resumen.repaint();
			table.repaint();
	    }
	    catch(ClassCastException e) {}
	}
/*----------------------------------------------------------------------------*/ 
    public static int Pestanias_GetByTitle(JTabbedPane jtb, String title){
    	int ret = -1;
    	for(int i=0;i<jtb.getTabCount();i++){
    		if((jtb.getTitleAt(i)).equals(title)){
    			ret = i;
    			i = jtb.getTabCount();
    		}
    	}	
    	return ret;
	}
	
/*-------------------------------------------------------------------------*/
	@SuppressWarnings({"unchecked", "rawtypes" })
	public static void JComboBox_Set(JComboBox jcb, Object[] array){
		try{			
			for(int i=0;i<array.length;i++){
				jcb.addItem(array[i]);
			}	
		}catch(Exception e){
			e.printStackTrace();
		}       
	}
	
/*-------------------------------------------------------------------------*/
	@SuppressWarnings({"unchecked", "rawtypes" })
	public static void JComboBox_ReplaceItem(JComboBox jcb, Object o, int index){
		try{			
			JComboBox jcb_temp = new JComboBox();
			for(int i=0;i<jcb.getItemCount();i++){
				if(i!=index){
					jcb_temp.addItem(jcb.getItemAt(i));
				}else{
					jcb_temp.addItem(o);
				}				
			}
			jcb = jcb_temp;
		}catch(Exception e){
			e.printStackTrace();
		}       
	}
/*----------------------------------------------------------------------------*/ 
	    public static void AbrirNavegador(String url){
	    	try{
	    		System.out.println("abriendo "+url);
	    		if(isWindows()) {
	    			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
	    		}else{
	    			if(isMac()){
	    				Runtime.getRuntime().exec("open " + url);
	    			}else{
		    			if(isUnix()){
		    				java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		    			}
	    			}
	    		}
	    	}catch(IOException e){
	    			e.printStackTrace();
	    	}
		}
	    
/*----------------------------------------------------------------------------*/ 
	    public static boolean isWindows() {
	    	String osName = System.getProperty("os.name");
	    	osName = osName.toLowerCase();
	    	boolean is = false;
	    	if(osName.indexOf("win") >= 0){
	    		is = true;
	    	}
	    	if(SystemUtils.IS_OS_WINDOWS){
	    		is = true;
	    	}
	        return is;
	    }

/*----------------------------------------------------------------------------*/ 
	    public static boolean isMac() {
	    	String osName = System.getProperty("os.name");
	    	osName = osName.toLowerCase();
	    	boolean is = false;
	    	if(osName.indexOf("mac") >= 0){
	    		is = true;
	    	}
	    	if(SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX){
	    		is = true;
	    	}
	        return is;
	    }

/*----------------------------------------------------------------------------*/ 
	    public static boolean isUnix() {
	    	String osName = System.getProperty("os.name");
	    	osName = osName.toLowerCase();
	    	boolean is = false;
	    	if(osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0){
	    		is = true;
	    	}
	    	if(SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_UNIX){
	    		is = true;
	    	}
	        return is;
	    }
/*-------------------------------------------------------------------------*/
		public static void EsDigito(KeyEvent e){
			char caracter = e.getKeyChar(); // Verificar si la tecla pulsada no es un digito    
			if (e.getKeyCode() == KeyEvent.VK_TAB){e.consume();}
			if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != KeyEvent.VK_ENTER)) {
				e.consume();  // ignorar el evento de teclado
			}
		}
		
/*-------------------------------------------------------------------------*/
		public static void EsDigitoDecimal(KeyEvent e){
			char caracter = e.getKeyChar(); // Verificar si la tecla pulsada no es un digito             
			if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != '.')) {
				e.consume();  // ignorar el evento de teclado
			}				
		}
		
/*-------------------------------------------------------------------------*/
		public static void EsDigitoDecimalNegativo(KeyEvent e){
			char caracter = e.getKeyChar(); // Verificar si la tecla pulsada no es un digito             
			if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != '.') && (caracter != '-')) {
				e.consume();  // ignorar el evento de teclado
			}				
		}
/*-------------------------------------------------------------------------*/
		public static void EsCUIL(KeyEvent e){
			char caracter = e.getKeyChar(); // Verificar si la tecla pulsada no es un digito             
			if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != '-')) {
				e.consume();  // ignorar el evento de teclado
			}				
		}
		
/*-------------------------------------------------------------------------*/
		public static void EsCoordenada(KeyEvent e){
			char caracter = e.getKeyChar(); // Verificar si la tecla pulsada no es un digito             
			if(((caracter < '0') || (caracter > '9')) && (caracter != '-') && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != KeyEvent.VK_ENTER)) {
				e.consume();  // ignorar el evento de teclado
			}
		}
		
/*************************************************************************
 *  Division donde veo la posibilidad de que el divisor sea 0
 ************************************************************************/
		public static double Div(double d1, double d2){
			double ret = 0.0;
			if(d2!=0){
				ret = d1/d2;
			}else{
				if(d1>0){
					ret = Double.MAX_VALUE;
				}else{
					ret = -Double.MAX_VALUE;
				}
			}
			return ret;
		}
		
/*************************************************************************
*  Multiplicacion donde veo la posibilidad de que alguno sea MAX_VALUE
************************************************************************/
		public static double Mult(double d1, double d2){
			double ret = 0.0;
			if(Math.abs(d1) == Double.MAX_VALUE || Math.abs(d2) == Double.MAX_VALUE){
				if((d1>0 && d2>0) || (d1<0 && d2<0)){
					ret = Double.MAX_VALUE;
				}
				if(d1==0 || d2==0){
					ret = 0;
				}
				if((d1>0 && d2<0) || (d1<0 && d2>0) ){
					ret = - Double.MAX_VALUE;
				}
			}else{
				ret = d1*d2;
			}
			return ret;
		}
		
/*************************************************************************
*  Multiplicacion donde veo la posibilidad de que alguno sea MAX_VALUE
************************************************************************/
		public static double Suma(double d1, double d2){
			double ret = 0.0;
			if(Math.abs(d1) == Double.MAX_VALUE || Math.abs(d2) == Double.MAX_VALUE){
				if(d1 == Double.MAX_VALUE  || d2 == Double.MAX_VALUE ){
					ret = Double.MAX_VALUE;
				}
				if(d1 == - Double.MAX_VALUE  || d2 == - Double.MAX_VALUE ){
					ret = - Double.MAX_VALUE;
				}
				if((d1==-Double.MAX_VALUE  && d2==Double.MAX_VALUE) || (d1==Double.MAX_VALUE  && d2==-Double.MAX_VALUE) ){
					ret = 0;
				}
			}else{
				ret = d1*d2;
			}
			return ret;
		}
		
/*************************************************************************
*  Paso de Double a Int fijadome en Integer.MAX_VALUE
************************************************************************/
		public static Integer DobleToInt(double d1){
			Integer ret = 0;
			if(d1>Integer.MAX_VALUE){
				ret = Integer.MAX_VALUE;
			}else{
				if(d1 < -Integer.MAX_VALUE){
					ret = -Integer.MAX_VALUE;
				}else{
					ret = (int)d1;
				}
			}					
			return ret;
		}
		
/*************************************************************************
*  Paso de Double a Int fijadome en Integer.MAX_VALUE
************************************************************************/
		public static Short DobleToShort(double d1){
			Short ret = 0;
			if(d1>Short.MAX_VALUE){
				ret = Short.MAX_VALUE;
			}else{
				if(d1 < -Short.MAX_VALUE){
					ret = -Short.MAX_VALUE;
				}else{
					ret = (short)d1;
				}
			}					
			return ret;
		}
		
/*-------------------------------------------------------------------------*/
	public static void SetExtendedStateSynthetica(JFrame jf){
		if(jf.getRootPane().getUI() instanceof SyntheticaRootPaneUI) {
			((SyntheticaRootPaneUI) jf.getRootPane().getUI()).setMaximizedBounds(jf);
		}
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
/*-------------------------------------------------------------------------*/
	public static void SetComportamientoVentana(final JFrame jf){			
		jf.addWindowStateListener(
			new WindowStateListener() {
			   public void windowStateChanged(WindowEvent e) {		
				   if (e.getNewState() == 0){ //Si 
					   jf.setSize(Util.getScreenWidth()-100, Util.getScreenHeight()-100);
				   }	 
				   boolean minimizado = ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED);
				   boolean Maximizado = ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH);
				   if (!minimizado && !Maximizado && e.getNewState() != 0){	// minimized	
					   Util.SetExtendedStateSynthetica(jf);
				   }
			   }
			});
	}
	
/*-------------------------------------------------------------------------*/
		public static void EsEntero(KeyEvent e){
			char caracter = e.getKeyChar(); // Verificar si la tecla pulsada no es un digito             
			if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)) {
				e.consume();  // ignorar el evento de teclado
			}		
		}
		
/*-------------------------------------------------------------------------*/
		public static boolean EsEntero(String s){
			boolean EsEntero=true;
			if(Util.EsNulo(s)) {
				EsEntero=false; 
			}
			char caracter[] = s.toCharArray();
			for(int i=0;i<caracter.length;i++) {
				if(((caracter[i] < '0') || (caracter[i] > '9'))) {
					EsEntero=false; 
				}	
			}
			return EsEntero;
		}
		
/*-------------------------------------------------------------------------*/
		public static boolean EsDecimal(String s){
			boolean EsDecimal=true;
			if(Util.EsNulo(s)) {
				EsDecimal=false; 
			}
			char caracter[] = s.toCharArray();
			for(int i=0;i<caracter.length;i++) {
				if(((caracter[i] < '0') || (caracter[i] > '9')) &&  (caracter[i] != '.')) {
					EsDecimal=false; 
				}	
			}
			if(s.endsWith(".")) {
				EsDecimal=false; 
			}
			int contador=0;
			String Decimal = s;
			while(Decimal.indexOf(".") > -1) {
				Decimal = Decimal.substring(Decimal.indexOf(".")+1,Decimal.length());
			    contador++; 
			}
			if(contador>1) {
				EsDecimal=false; 
			}
			return EsDecimal;
		}
/*-------------------------------------------------------------------------*/
		public static void EsFecha(KeyEvent e){
			char caracter = e.getKeyChar(); // Verificar si la tecla pulsada no es un digito             
			if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != '/')) {
				e.consume();  // ignorar el evento de teclado
			}
		}	
		
/***************************************************************
 * @Parameter PuedeSerCualquiera = false es una fecha de nacimiento (Mayor de 15 años)
 * @return 0: Es válida
 * @return 1: NO es válida
 * @return 2: no es de la forma dd/mm/aaaa
 **************************************************************/
	public static int EsFechaValida(String FechaIngresada, boolean PuedeSerCualquiera){
		String Fecha[] = FechaIngresada.split("/");
		if(Fecha.length!=3){
			return 1;
		}
		if(Fecha[0].length()!=2 || Fecha[1].length()!=2 || Fecha[2].length()!=4){
			return 2;
		}		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		Date date = new Date();			
		int anio_hoy = Integer.parseInt(sdf.format(date))-15;
		int anio0 = 1900;
		if(PuedeSerCualquiera){
			anio_hoy = 9999;
			anio0 = 0;
		}
		int dia = Integer.parseInt(Fecha[0]);
		int mes = Integer.parseInt(Fecha[1]);
		int anio = Integer.parseInt(Fecha[2]);
		try{
			LocalDate.of(anio, mes, dia);
		}catch(Exception e){
			return 4;
		}
		if(dia<0 || dia>31 || mes<0  || mes>12 || anio<anio0 || anio>anio_hoy){
			return 3;		
		}
		return 0;
	}
	
/***************************************************************
* @Parameter
 **************************************************************/
		public static boolean EsHoraValida(String HoraIngresada){
			if(HoraIngresada.contains(":")) {
				if(HoraIngresada.indexOf(":") != HoraIngresada.lastIndexOf(":")) {
					return false;
				}
				String Hora[] = HoraIngresada.split(":");
				if(Hora.length!=2){					
					return false;
				}
				if(!Util.EsEntero(Hora[0]) || !Util.EsEntero(Hora[1]) ) {
					return false;
				}
				int horas  = Integer.parseInt(Hora[0]);
				int minutos = Integer.parseInt(Hora[1]);
				if(horas<0 || horas>23 || minutos<0 || minutos>59) {
					return false;
				}
			}else {
				if(!Util.EsEntero(HoraIngresada)) {
					return false;
				}
				int horas  = Integer.parseInt(HoraIngresada);
				if(horas<0 || horas>23) {
					return false;
				}
			}			
			return true;
		}
		
/***************************************************************
* Se fija si el cuil es de la forma xx-xxxxxxxxxx-x  (2digitos)-(7 u 8digitos)-(1digito)
* @return true: Es válido
* @return false: NO es válido
**************************************************************/
		public static boolean EsCUILValido(String CUILIngresado){			
			String Fecha[] = CUILIngresado.split("-");
			if(Fecha.length!=3){
				return false;
			}
			if(Fecha[0].length()!=2 || !(Fecha[1].length()>=7 && Fecha[1].length()<=8) || Fecha[2].length()!=1){
				return false;
			}					
			return true;
		}
		
/*-------------------------------------------------------------------------*/
		public static void EsHora(KeyEvent e){
			char caracter = e.getKeyChar(); // Verificar si la tecla pulsada no es un digito             
			if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != ':')) {
				e.consume();  // ignorar el evento de teclado
			}		
		}
		
/*-------------------------------------------------------------------------*/
		public static void EsTelefono(KeyEvent e){
			char caracter = e.getKeyChar(); // Verificar si la tecla pulsada no es un digito             
			if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != '-')) {
				e.consume();  // ignorar el evento de teclado
			}	
		}	
		
/*----------------------------------------------------------------------------*/
		public static boolean EsNulo(String s) {
	    	boolean ret=false;
	    	if(s!=null){
	    		if(s.equals(" ") || s.equals("") || s.equals("null") || s=="" || s.length()==0){
	    			ret=true;
	    		}
	    	}else{
	    		ret=true;
	    	}
	    	return ret;
	    }
	    
/******************************************************************************************************
 * Busca el texto textoAbuscar en TexteDondeBuscar pasando los dos a minusculas y sacando los acentos
 * Si el texto a buscar contiene espacios se fija que TexteDondeBuscar contenga todas las palabras
 *********************************************************************************************************/
		public static boolean BuscarTexto(String textoAbuscar, String TextoDondeBuscar) {
	    	textoAbuscar = textoAbuscar.toLowerCase();
	    	TextoDondeBuscar = TextoDondeBuscar.toLowerCase();
			String CaracteresAcento[] 		= {"á", "é", "í", "ó", "ú", "Á", "É", "Í", "Ó", "Ú"}; 
			String CaracteresSinAcento[] 	= {"a", "e", "i", "o", "u", "A", "E", "I", "O", "U" };					
			for(int i=0;i<CaracteresAcento.length;i++){
				textoAbuscar = textoAbuscar.replace(CaracteresAcento[i], CaracteresSinAcento[i]);
				TextoDondeBuscar = TextoDondeBuscar.replace(CaracteresAcento[i], CaracteresSinAcento[i]);						
			}			
			boolean ret = true;
			if(textoAbuscar.contains(" ")) {
				String text[] = textoAbuscar.split(" ");
				for(int i=0;i<text.length;i++) {
					if(!TextoDondeBuscar.contains(text[i])) {
						ret = false;
					}
				}
			}else {
				ret = TextoDondeBuscar.contains(textoAbuscar);
			}
	    	return ret;
	    }
		
/*----------------------------------------------------------------------------*/
		public static boolean FileExistsOnServer(String URLName){
		    try {
		      HttpURLConnection.setFollowRedirects(false);
		      // note : you may also need
		      //        HttpURLConnection.setInstanceFollowRedirects(false)
		      HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
		      con.setRequestMethod("HEAD");
		      return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		    }
		    catch (Exception e) {
		     //  e.printStackTrace();
		       return false;
		    }
		  }
		
/*---------------------------------------------------------------------*/
		public static ImageIcon Imagen_getIcon(ImageIcon iconoOriginal, int AltoMax, int AnchoMax){
			ImageIcon iconoEscala = null; 		
			int ancho = iconoOriginal.getIconWidth(); 
    		int alto = iconoOriginal.getIconHeight(); 
    		if(alto > AltoMax || ancho > AnchoMax){
    			double alt = (double)alto/(double)AltoMax;
    			double anch = (double)ancho/(double)AnchoMax;
    			if(alt > anch){
    				alto = AltoMax; // alto en pixeles que tendra el icono escalado
    				ancho = -1;// ancho (para que conserve la proporcion pasamos -1)
    			}else{
    				ancho = AnchoMax;// ancho en pixeles que tendra el icono escalado
    				alto = -1; // alto (para que conserve la proporcion pasamos -1)
    			}
    		}
    		// 	Obtiene un icono en escala con las dimensiones especificadas
    		iconoEscala = new ImageIcon(iconoOriginal.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_AREA_AVERAGING));    		    		
	    	return iconoEscala;			
		}
		
/*----------------------------------------------------------------------------*/	
		public static Image Image_Obtener(String file){
	    	return Toolkit.getDefaultToolkit().getImage(file);    	
	    }
		
/*-----------------------Clase para cargar los jpg -----------------------*/
		public static BufferedImage ImagenGetInJar(URL imageURL) throws IOException{   
			// Utilizar imageURL = I.class.getResource(file) para llamar a la funcion
			BufferedImage imagen = ImageIO.read(imageURL);  			
			return imagen; 
		}  
		
/*----------------------------------------------------------------------------*/ 
	    public static void Directorio_Borrar(String Ruta) { 
        	File directorio = new File(Ruta);	
        	String[] listaDirectorio = directorio.list();
        	if(listaDirectorio != null){
        		for(int x=0;x<listaDirectorio.length;x++){  
        			File f = new File(Ruta+listaDirectorio[x]);
        			if(f.isDirectory()){
        				Directorio_Borrar(Ruta+listaDirectorio[x]+"\\");
        			}else{
        				System.out.println("borrando: "+Ruta+listaDirectorio[x]);
        				f.delete();
        			}
        		}
        	}
        	directorio.delete();
	    }
}
