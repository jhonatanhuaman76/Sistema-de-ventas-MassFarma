package libreria;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.metal.MetalBorders.PopupMenuBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class CellRenderComponents  extends DefaultTableCellRenderer{
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		setBorder(new MatteBorder(0, 10, 0, 0, (Color) null));

		//Para que la tabla acepte botones
		if(value instanceof JButton) {
			JButton btn = (JButton) value;
			return btn;
		}
		//Para que la tabla acepte JComboBox
		if (value instanceof JComboBox) {
			return (JComboBox) value;
		}
		
		if(value instanceof JLabel) {
			return (JLabel) value;
		}
		
	return component;
	}
}
