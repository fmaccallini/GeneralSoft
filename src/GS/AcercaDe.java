package GS;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.Border;

public class AcercaDe extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private Border etched = BorderFactory.createEtchedBorder();
	private String MD5Image = "be53a0541a6d36f6ecb879fa2c584b08";
	private VariablesSoft Vars;
	private Idioma_General IDM;
	
/*----------------------------------------------------------------------------*/
	public AcercaDe(VariablesSoft vars, boolean LetrasBlancas){
		try{
			Vars = vars;
			IDM = new Idioma_General(Vars);
			setUndecorated(true);
	    	
	    	JPanel JP_Borde = new JPanel();
	    	JP_Borde.setBorder(etched);
	    	
	    	JPanel JP_Box = new JPanel();
	    	JP_Box.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	    	JP_Box.setLayout(new BoxLayout(JP_Box, BoxLayout.Y_AXIS));
	    	JPanel Logo = new JPanel();
	    	Logo.setBorder(etched);
	    	File f = new File("LocalData/"+MD5Image+"/"+Vars.Name_Soft+".jpg");
	    	if(f.exists()){
	    		Logo.add(new JLabel(new ImageIcon("LocalData/"+MD5Image+"/"+Vars.Name_Soft+".jpg")));
	    	}else{
	    		Logo.add(new JLabel(new ImageIcon("LocalData/"+MD5Image+"/Splash.jpg")));
	    	}
	    	
	    	JP_Box.add(Logo);
	    	JPanel JPVersion = new JPanel();
	    	JPVersion.setBorder(etched);
	    	JLabel JLVersion = new JLabel(IDM.Labels[6]+Vars.Version);
	    	Color ColorLetras = Color.BLACK;
	    	if(LetrasBlancas){
	    		ColorLetras = Color.WHITE;
	    	}
	    	JLVersion.setForeground(ColorLetras);
	    	JPVersion.add(JLVersion);
	    	JP_Box.add(JPVersion);        
	    	JPanel box2 = new JPanel();
	    	box2.setBorder(etched);
	    	box2.setLayout(new BoxLayout(box2, BoxLayout.X_AXIS));
	    	box2.add(Box.createHorizontalGlue());
	    	JPanel box3 = new JPanel();
	    	box3.setLayout(new BoxLayout(box3, BoxLayout.Y_AXIS));
	    	box3.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	    	JLabel JLSerie = new JLabel(IDM.Labels[0] + String.valueOf(Vars.SN));
	    	JLSerie.setForeground(ColorLetras);
	    	JLSerie.setAlignmentX(Component.LEFT_ALIGNMENT);
	    	box3.add(JLSerie);
	    	JLabel JLModo = new JLabel(IDM.Labels[7] + String.valueOf(Vars.ModoLecturaSN));
	    	JLModo.setForeground(ColorLetras);
	 		box3.add(JLModo);
	    	JLabel JLCodigo = new JLabel(IDM.Labels[1] + String.valueOf(Vars.Cod));
	    	JLCodigo.setForeground(ColorLetras);
	 		box3.add(JLCodigo);
	 		box2.add(box3);	
	 		box2.add(Box.createHorizontalGlue());
	 		JP_Box.add(box2);
	 		JPanel Acept = new JPanel();
	 		Acept.setLayout(new FlowLayout(FlowLayout.CENTER));
	 		JButton JB_Acept=new JButton(IDM.Labels[8]);
	 		JB_Acept.setFocusable(false);
	 		JB_Acept.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent event){
	            setVisible(false); 
	            dispose();
	 		}});
	 		Acept.add(JB_Acept);
	 		JP_Box.add(Acept);
	 		JP_Borde.add(JP_Box);
	 		add(JP_Borde); 	
	 		pack();	
	 		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	 		setLocation((Util.getScreenWidth()-getWidth())/2,(Util.getScreenHeight()-getHeight())/2);
	 		setVisible(true);
		}catch(Exception ex){	
			ex.printStackTrace();
			//mostrarExcepcion.Mostrar(IDM.Excepcion[159],ex);
		}
	}//fin del Metodo Acercade
}
