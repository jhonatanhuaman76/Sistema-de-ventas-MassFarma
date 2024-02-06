package libreria;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class MiHeaderRender extends DefaultTableCellRenderer {

	  @Override
	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    component.setFont(new Font("Segoe UI", Font.BOLD, 12));
	    setBackground(UIManager.getColor("TableHeader.background")); 
	    setForeground(UIManager.getColor("TableHeader.foreground"));
	    setBorder(new MatteBorder(0, 10, 0, 0, (Color) null));
		return component;
	}
}
