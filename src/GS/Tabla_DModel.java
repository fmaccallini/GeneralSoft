package GS;

import javax.swing.table.DefaultTableModel;

public class Tabla_DModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	public Object[] obj;
	public int[] ColBoleanas={};
	public int[] ColNoEditables={};
	public boolean AgregarFilaVaciaAutomaticamente = false;
	
/**********************************************
* Para crear la tabla hacer:
* String[] tit = {"id", "etc.", "etc."};
* Object[] obj = new Object[]{0, " ", " ", " ", false,0,90,0,0,0,0,-1,-1,-1,-1};
* Tabla = new Tabla_DModel(tit, obj); 
*/
	public Tabla_DModel(String[] tit, Object[] OBJ){
		super(new Object[][]{OBJ},setTitulosHTML(tit));
		CambiarObj(OBJ);
	}
	
	
	private static String[] setTitulosHTML(String[] tit) {
		for(int i=0;i<tit.length;i++) {
			tit[i] ="<html><div align='center' style='vertical-align:top'>"+tit[i]+"</div></html>";				
		}
		return tit;
	}
/*********************************************************************/	
	public Class<?> getColumnClass(int columnIndex) {
    	return obj[columnIndex].getClass();
    }
	
/*********************************************************************/	
	public void removeAllColumns(){
		this.setColumnCount(0);
		this.fireTableStructureChanged();
	}
	
/*********************************************************************/
	public void CambiarObj(Object[] OBJ) {		
		obj = OBJ;
	}	
	
/*********************************************************************/
	public Object[] getFila(int fila) {		
		Object[] Fila = new Object[obj.length];
		if(fila<this.getRowCount()) {
			for(int i=0;i<obj.length;i++) {
				Fila[i] = this.getValueAt(fila, i);				
			}
		}else {
			Fila = obj;
		}		
		return Fila;
	}
	
/*********************************************************************/
	public void RemoveAllElements() {
		while(this.getRowCount()>0){
			this.removeRow(0);
			fireTableDataChanged();
		}
	}	
	
/*********************************************************************/
	public void AgregarFilaVacia() {
		this.addRow(obj);
		fireTableDataChanged();
	}
		
/*********************************************************************/
	public void AgregarFila(Object[] o) {
		this.addRow(o);
		fireTableDataChanged();
	}
	
/*********************************************************************/
	public void AgregarFilaAt(Object[] o, int row) {
		this.insertRow(row, o);
		fireTableDataChanged();
	}
	
//************************¿La celda es editable?******************************\\
	public boolean isCellEditable(int row, int col) {		
		for(int i=0; i<ColNoEditables.length;i++){
			if(col==ColNoEditables[i]){ return false;}
		}	
		return true;
	}
	
/*********************************************************************/
	public void CargarTabla(String TabTemp) {
		RemoveAllElements();
		String [] FilasTemp = TabTemp.split("&&"); 
		for(int i=0;i<FilasTemp.length;i++){
    		String[] CeldasTemp = FilasTemp[i].split("%%"); 
    		Object[] o = new Object[this.getColumnCount()];
    		for(int j=0;j<CeldasTemp.length;j++){
    			o[j] = CeldasTemp[j]; //Lo trato como objeto
    			if(obj[j] instanceof Boolean){
    				o[j] = Boolean.parseBoolean(CeldasTemp[j]);    				
    			}
    			if(obj[j] instanceof Integer){
    				o[j] = Integer.parseInt(CeldasTemp[j]);
    			}    		
    		}
    		if(CeldasTemp.length<obj.length){
    			for(int j=CeldasTemp.length;j<obj.length;j++){
        			o[j] = obj[j];        		
        		}
			}
  			this.addRow(o);
		}
		fireTableDataChanged(); 
	}
	
//*************************Pongo en una celda*******************************\\					
	public void setValueAt(String value, int row, int col) {
		super.setValueAt(value, row, col);
		if(row==getRowCount()-1 && AgregarFilaVaciaAutomaticamente){AgregarFilaVacia();}
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
}
