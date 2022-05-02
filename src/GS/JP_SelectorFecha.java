package GS;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import datechooser.beans.DateChooserDialog;

public class JP_SelectorFecha extends JPanel{
	private static final long serialVersionUID = 1L;
	public JTextArea JTA_Fecha = new JTextArea();
	public JTextField JTF_Fecha = new JTextField();
	private boolean modificando = false;
	public int Idioma = 0;
	private Frame Owner;
	public JButton SeSelecciono = new JButton();
	
	public JP_SelectorFecha(int idioma, boolean BordeEnBoton) {
		Idioma = idioma;
		Owner = new Frame();
		Owner.setLocationRelativeTo(null); 
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));		
		JPanel JP_Borde = new JPanel();
		JP_Borde.setLayout(new BoxLayout(JP_Borde, BoxLayout.X_AXIS));
		JTA_Fecha.addKeyListener(new KeyAdapter() { public void keyTyped(KeyEvent e){
			Util.EsFecha(e);
		}});			
		JTA_Fecha.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void warn() {
				  if(!modificando) {
					  modificando = true;
					  JTF_Fecha.setText(JTA_Fecha.getText());
					  modificando = false;
				  }
			  }
			});
		JTF_Fecha.addKeyListener(new KeyAdapter() { public void keyTyped(KeyEvent e){
			Util.EsFecha(e);
		}});	
		JTF_Fecha.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void warn() {
				  if(!modificando) {
					  modificando = true;
					  JTA_Fecha.setText(JTF_Fecha.getText());
					  modificando = false;
				  }
			  }
			});
		JP_Borde.add(JTF_Fecha);
		
		JButton JB_Buscar = new JButton(new ImageIcon(Util.Image_Obtener("LocalData/be53a0541a6d36f6ecb879fa2c584b08/datepicker.png")));
		if(!BordeEnBoton) {
			Util.JButton_Configurar(JB_Buscar, null, 16, 15);
		}
		JB_Buscar.addActionListener(new ActionListener(){	public void actionPerformed(ActionEvent event){	                        
			DateChooserDialog dt = new DateChooserDialog(); 
			dt.setCalendarPreferredSize(new Dimension(450, 260));
			Point location = new Point((Util.getScreenWidth()-450)/2, (Util.getScreenHeight()-260)/2);
			dt.showDialog(Owner, true, location);
			Calendar cal=dt.getSelectedDate();
			if(cal!=null) {
				Date date=cal.getTime();
				String Fecha  = new SimpleDateFormat("dd/MM/yyyy").format(date);
				JTA_Fecha.setText(Fecha);
				JTF_Fecha.setText(Fecha);
				SeSelecciono.doClick();
			}			
    	}});  
		JP_Borde.add(JB_Buscar);
		add(JP_Borde);
	}
	
/********************************************************************************
 * 				Devuelve la fecha ingresada
 * *****************************************************************************/	
	public String getFecha() {
		return JTA_Fecha.getText();
	}
	
/********************************************************************************
* 				Setea la fecha
* *****************************************************************************/	
	public void setFecha(String Fecha) {
		JTA_Fecha.setText(Fecha);
	}
	
/********************************************************************************
* 				Devuelve la fecha ingresada
 *******************************************************************************/	
		public boolean CorroborarFecha() {
			int valida = Util.EsFechaValida(JTA_Fecha.getText(), true);
			if(valida != 0){
				JOptionPane.showMessageDialog(Owner,getLabels(1),getLabels(0),JOptionPane.ERROR_MESSAGE);
				return false;
			}			
			return true;
		}
	
/********************************************************************************
* 				Devuelve la fecha ingresada
*******************************************************************************/
		private String getLabels(int label) {
			String[] ret={};
	    	switch(label){
		    	case 0: ret = new String[]{"Error","Error","Erro"};break;
				case 1: ret = new String[]{"Tenes que ingresar una fecha de la forma dd/mm/yyyy","You have to enter a date of the form dd/mm/yyyy","Você deve inserir uma data do formulário dd/mm/yyyy"};break;
	    	}
	    	return Util.CaracteresEspeciales(ret[Idioma]);
		}
		
}
	
