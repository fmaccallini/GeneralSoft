package GS;

import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

/******************************************************************
 * Se debe sobreescribir el metodo 
 * Abrir_Software()
 * InstalarActualizacion()
 *****************************************************************/
public class WelcomeWindow extends JWindow	implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	protected String strs[] = {"Cargando Herrramientas","Cargando Imagenes","Cargando Configuracion","Inicializando..."} ;
	protected JLabel lblCargando = null;
	public VariablesSoft Vars;
	protected double VersionServidor=1.0;
	protected int interval = 1000;
	public boolean Visible = true, ComprobarActualizacion = false;
	public int Progreso = 0;
	public JFrame Soft;

	public WelcomeWindow(Frame owner, final VariablesSoft vars) {
		super(owner);
		Vars = vars;
		Configuracion();
		if(Visible){
			initialize();
		}
		MiSwingWorkerInit winit =new MiSwingWorkerInit();
		winit.addPropertyChangeListener(this);
		winit.execute();	
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("progress")){
			Progreso =(Integer) evt.getNewValue();
		}
		if(Soft!=null){
			if(Soft.isVisible() && Progreso==100){
				OcultarSplash();	
			}			
		}
	}
	
/**********************************************************************/	
	public void Configuracion(){
		//Visible = false;		
		//ComprobarActualizacion = true;
	}	

/**********************************************************************/	
	public void Cargar_Componentes(){
		Thread t = new Thread(new Runnable() {	public void run() {
			Abrir_Software();
		}});  
		t.start();
	}
	
/**********************************************************************/	
public void Abrir_Software(){
	/* Sobrescribir este metodo de esta manera...
	try{ 
		SwingUtilities.invokeLater(new Runnable() { 
    		public void run() {
    			/*final RXFutbol3D RXF3D = new RXFutbol3D(Vars);
    			RXF3D.addWindowListener(new WindowAdapter() {
    				public void windowClosing(WindowEvent e) {
    					RXF3D.miPanel.File_SaveOnExit();
    				}});
    			RXF3D.pack();
    			RXF3D.setExtendedState(Frame.MAXIMIZED_BOTH);    		
    			RXF3D.setVisible(true);
    	}});		
		this.setVisible(false);        
	}catch(Exception e){
		e.printStackTrace();
	}*/
}
	
/**********************************************************************/	
public void OcultarSplash(){
	lblCargando.setText("");
	jLabel.setIcon(null);
	this.jLabel.setVisible(false); 
	this.lblCargando.setVisible(false); 
	this.jContentPane.setVisible(false); 
	this.setVisible(false); 
	this.removeAll();
	this.dispose();
	System.gc();
}

/**********************************************************************/	
	public void InstalarActualizacion(){
		System.out.println("Ejecutando Upgrade.jar");
		Util.EjecutarJAR("Upgrade.jar");
		System.exit(0);
	}
	
/**********************************************************************/
    public void ComprobarActualizacion(){		
		if(Util.Comprobar_Actulizaciones(Vars.RutaActualizaciones, Vars.JarSoftware)){ //Si el tamaño del Jar no es igual al del servidor actualizo
			InstalarActualizacion();
		}	    			
	}
	

/**********************************************************************/
	private void initialize() {
		this.setSize(450, 260);
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null); 
	}

/**********************************************************************/
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblCargando = new JLabel();
			lblCargando.setBounds(new Rectangle(7, 240, 393, 16));
			lblCargando.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			lblCargando.setFont(new Font("Arial", Font.PLAIN, 10));
			//lblCargando.setForeground(Color.BLACK);
			lblCargando.setText("");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(0, 0, 450, 237));
			File splash = new File("LocalData/be53a0541a6d36f6ecb879fa2c584b08/Splash.jpg");
			Image icon = Toolkit.getDefaultToolkit().getImage("LocalData/be53a0541a6d36f6ecb879fa2c584b08/Splash.jpg");
			if(!splash.exists()){
				icon = Toolkit.getDefaultToolkit().getImage("LocalData/be53a0541a6d36f6ecb879fa2c584b08/"+Vars.Name_Soft+".jpg");
			}			
			jLabel.setIcon(new ImageIcon(icon));
			jContentPane = new JPanel();
			//jContentPane.setBackground(Color.LIGHT_GRAY);
			//jContentPane.setForeground(Color.BLACK);
			jContentPane.setLayout(null);
			jContentPane.setBorder(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(lblCargando, null);
		}
		return jContentPane;
	}

	/**
	 * Clase SwingWorker para realizar todas las cargas necesarias antes de mostrar la pantalla principal
	 */
	class MiSwingWorkerInit extends SwingWorker<Boolean, Void>{

		private String[] strCargando;

		public MiSwingWorkerInit(){
			strCargando = strs;
		}

		protected Boolean doInBackground() throws Exception {
			if(Visible){
				for(int i=0; i<strCargando.length; i++){
					lblCargando.setText(strCargando[i]);
					Thread.sleep(interval);
					if(i==0 && ComprobarActualizacion){
						ComprobarActualizacion();							
					}
					if(i==strCargando.length-2){
						Cargar_Componentes();						
					}
				}
			}else{
				if(ComprobarActualizacion){
					ComprobarActualizacion();		
				}
				Cargar_Componentes();
			}			
			this.setProgress(100);
			return new Boolean(true);
		}

	}
}
