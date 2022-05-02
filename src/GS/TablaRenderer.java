package GS;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class TablaRenderer extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 1L;
	private JLabel lb = new JLabel();
	public boolean MosrarSeleccionColumna = false;
	public boolean MostrarSeleccionRow0 = true;
	public Color Color_Row0_Columna0 = Color.WHITE;
	public Color Color_Row0 = Color.WHITE;
	public Color Color_Columna0 = Color.WHITE;
	private Font f = null;
		
/*-------------------------------------------------------------------------*/	
	public TablaRenderer(){
		super();
	}
	
/*-------------------------------------------------------------------------*/	
	public TablaRenderer(boolean mosrarSeleccionColumna){
		super();
		MosrarSeleccionColumna = mosrarSeleccionColumna;
	}
	
/*-------------------------------------------------------------------------*/
	public TablaRenderer(Color colorRow0, Color colorColumna0, Color colorRow0Columna0, boolean mosrarSeleccionColumna){
		super();
		Color_Row0_Columna0 = colorRow0Columna0;
		Color_Row0 = colorRow0;
		if(!Color_Row0.equals(Color.WHITE)){
			MostrarSeleccionRow0 = false;
		}
		Color_Columna0 = colorColumna0;
		MosrarSeleccionColumna = mosrarSeleccionColumna;
	}

/*-------------------------------------------------------------------------*/
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		if(value instanceof Boolean){
			JPanel JP_JchBx = new JPanel();
			JP_JchBx.setLayout(new BoxLayout(JP_JchBx, BoxLayout.Y_AXIS));
			JCheckBox JchBx = new JCheckBox("",(Boolean)value);
			if (table.getSelectedRow()==row){ 
				JP_JchBx.setBackground(new Color(155,155,255));
			}else{
				JP_JchBx.setBackground (Color.WHITE);
			}
			JchBx.setBorder(null);
			JP_JchBx.setBorder(borde(table, row, column));
			JchBx.setAlignmentX(CENTER_ALIGNMENT);	
			JchBx.setHorizontalAlignment(SwingConstants.CENTER);
			JP_JchBx.add(Box.createVerticalGlue());
			JP_JchBx.add(JchBx);
			JP_JchBx.add(Box.createVerticalGlue());
			return JP_JchBx; 
		}
		if(value instanceof String){	
			lb.setBackground (Color.WHITE);		
			if(f != null){lb.setFont(f);}
			if (column>0 && table.getSelectedColumn()==column && MosrarSeleccionColumna){ 
				lb.setBackground(new Color(155,255,255));
			}
			if(row==0){ 
				lb.setBackground(Color_Row0);
			}
			if(column==0){ 
				lb.setBackground(Color_Columna0);//(new Color(232,221,201));
			}			
			if(row==0 && column==0){ 
				lb.setBackground(Color_Row0_Columna0);//(new Color(232,221,201));
			}
			if (table.getSelectedRow()==row){ 
				if (row!=0 || (row==0 && MostrarSeleccionRow0)){ 
					lb.setBackground(new Color(155,155,255));
				}
			}
			lb.setBorder(borde(table, row, column));
			lb.setForeground(Color.BLACK);
			lb.setText(String.valueOf(value));
			lb.setOpaque(true);
			lb.setAlignmentX(CENTER_ALIGNMENT);		
			lb.setHorizontalAlignment(SwingConstants.CENTER);
			lb.setHorizontalTextPosition(SwingConstants.CENTER);
			return lb; 
		}
		return lb;      
	}
	
/*-------------------------------------------------------------------------*/
	public Border borde(JTable table, int row, int column){
		int bordeArriba = 1;
		int bordeIzquierda = 1;
		int bordeAbajo = 0;
		int bordeDerecha = 0;
		int ultimaColumnaVisible = 0;
		for(int i=0;i<table.getColumnCount()-1;i++){
			if(table.getColumnModel().getColumn(i).getWidth()>0){
				ultimaColumnaVisible = i;	
			}			
		}		
		if(row==0 && MostrarSeleccionRow0){
			bordeArriba = 0;
		}
		if(column == ultimaColumnaVisible){
			bordeDerecha = 1;
		}
		if(row==table.getRowCount()-1){
			bordeAbajo = 1;
		}
		return new MatteBorder(bordeArriba,bordeIzquierda,bordeAbajo,bordeDerecha,Color.LIGHT_GRAY);
	}
	
/*-------------------------------------------------------------------------*/
	public void setFont(Font font){
		f = font;
	}
	
}