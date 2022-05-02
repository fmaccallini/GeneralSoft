package GS;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
/******************************************************************
 * Se debe sobreescribir el metodo 
 * Abrir_CodigoActivacion()
 *****************************************************************/
public class ActivarSoft extends JFrame implements WindowListener{
	private static final long serialVersionUID = 1L;
	private TextField tf1;
	private JButton JB_Aceptar, JB_CopiarSN;
	private Idioma_General IDM;
	private Image icon;
	private JPanel JP_N_Serie, JP_Cod_Prod;
	private Border etched = BorderFactory.createEtchedBorder();
	private TitledBorder BordeTitulo;
	protected VariablesSoft Vars;
        
    public ActivarSoft(VariablesSoft vars) {
        super(vars.Name_Soft);
		setSize(600,400);
		setLocationRelativeTo(null); 
		Vars = vars;
		try{
			icon = Toolkit.getDefaultToolkit().getImage("LocalData/"+Util.MD5Image+"/Icono"+Vars.Pizarra+".png");
		}catch(Exception e){}
        super.setIconImage(icon);
		IDM = new Idioma_General(0);

        JPanel JP_Box = new JPanel();//Creo el primer panel
		JP_Box.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		JP_Box.setLayout(new GridLayout(0,1));
		
		JP_N_Serie = new JPanel();
		Util.TamanioFijo(JP_N_Serie, 400, 70);
		JP_N_Serie.setLayout(new BoxLayout(JP_N_Serie, BoxLayout.X_AXIS));
		BordeTitulo = BorderFactory.createTitledBorder(etched, IDM.Labels[0]);
		JP_N_Serie.setBorder(BordeTitulo);
		JLabel Serie = new JLabel(String.valueOf(Vars.SN));
		JP_N_Serie.add(Serie);
		JB_CopiarSN= new JButton(IDM.Labels[5]);
		JB_CopiarSN.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent event){	
			CopiarSN();
	    }});
		JP_N_Serie.add(Box.createHorizontalGlue());
		JP_N_Serie.add(JB_CopiarSN);
		JP_Box.add(JP_N_Serie);
		
		JP_Box.add(new JLabel(IDM.Labels[15]));
		
		JP_Cod_Prod = new JPanel();
		JP_Cod_Prod.setLayout(new BoxLayout(JP_Cod_Prod, BoxLayout.X_AXIS));
		BordeTitulo = BorderFactory.createTitledBorder(etched, IDM.Labels[1]);
		JP_Cod_Prod.setBorder(BordeTitulo);
		tf1 = new TextField(50);
		JP_Cod_Prod.add( tf1 );		
		tf1.setForeground(Color.BLACK);
		if(Vars.DirServidorCodigo.equals("")) {
			JP_Box.add(JP_Cod_Prod);	
		}		
		
		JPanel JP_Aceptar = new JPanel();
		JB_Aceptar = new JButton(IDM.Labels[2]);
		JB_Aceptar.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent event){	
			Activar();
	    }});
		JP_Aceptar.add(JB_Aceptar);
		JP_Box.add(JP_Aceptar);

		getContentPane().add(JP_Box);
		setVisible(true);
		}
    

/*******************************************************************/
    public void Activar(){
		String CodigoAct = tf1.getText();
		if(!Util.EsNulo(CodigoAct)) {
			int Cod = Integer.parseInt(CodigoAct);
			if(Vars.Cod == Cod){
				PrintWriter salida = null;
				try{ 					
					String codigo = "";
					if(Vars.SoftConVencimiento){	
						if(Util.ComprobarConexion()){
							Date d = new Date();
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
							String hoy = formatter.format(d);
							SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy");
							int AnioPC = Integer.parseInt(formatter2.format(d));						 
							String fechaServ = GetFecha();
							if(hoy.equals(fechaServ)){
								if(AnioPC<Vars.AnioVencimiento){
									codigo = Cod+"@;"+Util.Encriptar(hoy+"@;"+hoy); //Codigo de activacion	
								}else{
									JOptionPane.showConfirmDialog((java.awt.Component) null, IDM.Labels[11], IDM.Labels[3],javax.swing.JOptionPane.DEFAULT_OPTION);
									return;//Si no hay conexion no activo el software con vencimiento
								}							
							}else{
								JOptionPane.showConfirmDialog((java.awt.Component) null, IDM.Labels[10], IDM.Labels[3],javax.swing.JOptionPane.DEFAULT_OPTION);
								return;//Si no hay conexion no activo el software con vencimiento
							}
						}else{
							JOptionPane.showConfirmDialog((java.awt.Component) null, IDM.Labels[9], IDM.Labels[3],javax.swing.JOptionPane.DEFAULT_OPTION);
							return;//Si no hay conexion no activo el software con vencimiento
						}					
					}else{
						codigo = String.valueOf(Cod);
					}
		        	File folder = new File("LocalData");
		        	if(!folder.exists()){folder.mkdirs();} 
					File file = new File("LocalData/Config.cnf");
					if(!file.exists()){file.createNewFile();}   
					salida = new PrintWriter( new BufferedWriter(new FileWriter(file)));
					salida.println(codigo); //Codigo de activacion	
					salida.println("0");//Idioma
					salida.close();
				}catch(Exception  e2){
					JOptionPane.showMessageDialog((java.awt.Component) null,"Excepcion en ActivarSoft ="+e2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); }
				finally{// En el finally cerramos el fichero, para asegurarnos que se cierra tanto si todo va bien como si salta una excepcion.
					try{ if( null != salida ){ salida.close();}                  
					}catch (Exception e3){ e3.printStackTrace();}
				}	
				this.dispose();
				Abrir_CodigoActivacion();		
			}else {
	        	javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null, IDM.Labels[4], IDM.Labels[3],javax.swing.JOptionPane.DEFAULT_OPTION);
			}  
		}else {
			if(!Vars.DirServidorCodigo.equals("")) {
				this.dispose();
			}
		}
    	
    }

/**********************************************************************/
	public String GetFecha(){
		String ret = "01/01/2010";
		try{
			try{
				JEditorPane ObtenerDatos = new JEditorPane();
				ObtenerDatos.setEditable( false );
	    		ObtenerDatos.setPage("http://www.rxfutbol.com/software/getFecha.php");
	    		int Segundos = 10;
	    		boolean seguir=true;
	    		while(seguir){
	    			String fecha = ObtenerDatos.getText();
	    			if(fecha.contains("<ad>")){
	    				String Datos[] = fecha.split("<ad>");
	    				String fechas[] = Datos[1].split(":");
	    				ret = fechas[1]+"/"+fechas[2]+"/"+fechas[3];
	    				seguir=false;
	    			}
	    			if(Segundos==0){
	    				seguir=false;
	    			}
	    			try {
	    				Thread.sleep(1000);
	    			} catch (InterruptedException e1) {}
	    			Segundos--;
	    		}
			}catch(Exception e){
				e.printStackTrace();
			} 		
		}catch(Exception e){
			e.printStackTrace();
		}   
		return ret;
	}
	
/*******************************************************************/
    public void CopiarSN(){
    	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	StringSelection data = new StringSelection(String.valueOf(Vars.SN));
    	clipboard.setContents(data, data);
    }

/*----------------------------------------------------------------------------*/
    public void Abrir_CodigoActivacion() {	
    	/* Sobrescribir este metodo de esta manera...
    		new CA(Vars);*/   					   	 
    }    
	
 /**************************************************************************************************/ 
  //Metodos para windowListener, para preguntar al cerrar
      public void windowClosed(WindowEvent evt){ } 				 
      public void windowClosing(WindowEvent evt){ System.exit(0); }		
      public void windowIconified(WindowEvent evt){ }
      public void windowDeiconified(WindowEvent evt){ }
      public void windowOpened(WindowEvent evt){ }
      public void windowActivated(WindowEvent evt){ }
      public void windowDeactivated(WindowEvent evt){ } 
}