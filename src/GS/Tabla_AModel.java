package GS;
import javax.swing.table.AbstractTableModel;
import java.util.*;

/*****************************************Clase Mi Tabla********************************************/
public class Tabla_AModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
			public Vector<String> Columnas = new Vector<String>();     //final String[] columnNames = {}
			public Vector<Vector<String>> Filas = new Vector<Vector<String>>();
			public int[] ColNoEditables={0};
			public boolean Fila0NoEditable = true;
			public boolean TablaNoEditable = false;
			public boolean AgregarFilaVaciaAutomaticamente = false;

		
			public Tabla_AModel(){	}
			
//*****************Agregar una Columna a la Tabla*****************************\\
  			public void AgregarColumna(String s) {
  				Columnas.addElement(s);
  			}	  			
//*****************Agregar un Objeto a la Tabla*****************************\\
  			public void AgregarObjeto(Vector<String> v) {
  				Filas.addElement(v);
  			}	
//*****************Agregar un Fila vacia a la Tabla*****************************\\
  			public void AgregarFila() {
  				Vector<String> VecTemp = new Vector<String>();
  		        for(int i=0;i<Columnas.size();i++){VecTemp.addElement("");}
  				Filas.addElement(VecTemp);
  			}	
//************************Obtengo la cantidad de columnas*******************\\
			public int getColumnCount() {
				return Columnas.size();
			}
//************************Obtengo la cantidad de Filas***********************\\			
			public int getRowCount() {
				return Filas.size();
			}
//*********************Obtengo el nombre de la columna**********************\\			
			public String getColumnName(int col) {
				return (String)Columnas.get(col);
			}		
//********************Obtengo el contenido de una celda**********************\\				
			public Object getValueAt(int row, int col) {
				Object o = new Object();
				Vector<String> v=new Vector<String>();
				if(Filas.size()>row) {
					v=(Vector<String>)Filas.get(row);
					if(v.size()>col) {
						o = v.get(col);
					}
				}
				return o;
			}	
//**************************Obtengo las filas*******************************\\					
			public Vector<Vector<String>> getFilas(){
				return Filas;
			}
//**************************Obtengo las columnas*****************************\\				
			public Class<? extends Object> getColumnClass(int c) {
			   return getValueAt(0, c).getClass();		   
			}
//*************************Pongo en una celda*******************************\\					
			public void setValueAt(String value, int row, int col) {
				Vector<String> v=(Vector<String>)Filas.get(row);
				v.set(col,value);
				Filas.set(row,v);
				if(row==getRowCount()-1 && AgregarFilaVaciaAutomaticamente){AgregarFila();}
				fireTableDataChanged(); 
		   }
			
//*************************Cargo la tabla con el String*******************************\\	
			public void CargarTabla(String TabTemp, boolean agregar) {
	    		if(!agregar){Filas.removeAllElements();}
	    		String [] FilasTemp = TabTemp.split("&&"); 
	    		for(int i=0;i<FilasTemp.length;i++){
		    		String [] CeldasTemp = FilasTemp[i].split("%%"); 
		    		Vector<String> VecTemp = new Vector<String>();
		    		for(int j=0;j<CeldasTemp.length;j++){
		    			VecTemp.addElement(CeldasTemp[j]);	}
		  			Filas.addElement(VecTemp);
	    		}
				fireTableDataChanged(); 
			}

//*************************Cargo la tabla con el String*******************************\\	
			public void CargarTabla(Vector<Vector<String>> filas) {
	    		Filas.removeAllElements();
	    		Filas = filas;
				fireTableDataChanged(); 
			}
			
//************* Obtengo un String con los elementos de la tabla ************************\\	
			public String TablaToString() {
				String str="";				
    			for(int k=0;k<getRowCount();k++){
    				for(int j=0;j<getColumnCount();j++){
    					if(!getValueAt(k, j).equals("")){
    						str = str + getValueAt(k, j) + "%%";// %% diferencia celdas
    					}else{
    						str = str + " %%";// %% diferencia celdas
    					}
    				}
    				str = str + "&&";// $$ diferencia filas
    			}
    			//str = str + "##";// ## diferencia Tablas
				return str;
			}
//***************************Borro una fila**********************************\\	
			public void RemoveElementAt(int row) {
				Filas.removeElementAt(row);
			}	
//***************************Borro Toda la tabla**********************************\\	
			public void RemoveAllElements() {
				Filas.removeAllElements();
				if(AgregarFilaVaciaAutomaticamente){AgregarFila();}
			}
			
//************************¿La celda es editable?******************************\\
			public boolean isCellEditable(int row, int col) {
				if(row==0 && Fila0NoEditable){return false;}
				for(int i=0; i<ColNoEditables.length;i++){
					if(col==ColNoEditables[i]){ return false;}
				}	
				if(TablaNoEditable){return false;}
				return true;
			}
/****************************************************************
 Se fija si el String es igual a algun campo en la columna pedida
*****************************************************************/	
			public int Tiene(String str,int columna) {
				int n=-1;
				 for(int i=0;i<getRowCount();i++){
        			 String Nombre = (String)getValueAt(i, columna);
        			 if(str.equals(Nombre)){
        				 n=i;
        				 }
        			 }
				return n;
			}
			
/**************************************************************
 * Se fija si el campo contiene al String en la columna pedida
*****************************************************************/	
			public int Contiene(String str,int columna) {
				int n=-1;
				for(int i=0;i<getRowCount();i++){
					String Nombre = (String)getValueAt(i, columna);
					if(str.contains(Nombre)){
						n=i;
					}
				}
				return n;
			}
//***************Se fija si tiene el String en la columna pedida**********************\\	
			public boolean Existe(String str,int columna) {
				boolean ret = false;
				if(Tiene(str,columna)>=0){ret=true;}
				return ret;
			}
			
//***************************Copiar Tabla**********************************\\	
			public void Copy(Tabla_AModel MT) {
				this.Filas = MT.Filas;	
				this.Columnas = MT.Columnas;
				this.ColNoEditables = MT.ColNoEditables;
				this.Fila0NoEditable = MT.Fila0NoEditable;
				this.TablaNoEditable = MT.TablaNoEditable;
				this.AgregarFilaVaciaAutomaticamente = MT.AgregarFilaVaciaAutomaticamente;
			}	

}//Termina la Clase MiTabla

