package GS;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

public class Tree_CellRenderer implements TreeCellRenderer {
        private JLabel label = new JLabel();
        private ImageIcon Icono;
        private Color Color1 = Color.DARK_GRAY;
        private Color Color2 = Color.BLACK;

        public Tree_CellRenderer(ImageIcon icon, Color c1, Color c2) {
            Icono = icon;
        	Color1 = c1;
            Color2 = c2;
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        	label.setIcon(Icono); //new ImageIcon(ELibrary.getImagen("Logo Estantes.png"))
        	label.setText("" + value);                 
            if(selected){
            	label.setForeground(Color1);
            }else{
            	label.setForeground(Color2);
            }
            return label;
        }
    }