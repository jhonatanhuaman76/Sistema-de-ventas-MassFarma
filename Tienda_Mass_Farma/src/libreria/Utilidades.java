package libreria;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;

public class Utilidades {
	
	private static final double IGV = 0.18;

	private ArrayList<Component> components = new ArrayList<>();
	
	public Utilidades(Component...components) {
		for(Component component : components) {
			this.components.add(component);			
		}
		
	}
	
	public static double getIGV() {
		return IGV;
	}
	
	public static void informationMensaje(Component parent, String s) {
		JOptionPane.showMessageDialog(parent, s, "Informacion" ,JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void errorMensaje(Component parent, String s) {
		JOptionPane.showMessageDialog(parent, s, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static int confirmMensaje(Component parent, String s) {
		return JOptionPane.showConfirmDialog(parent, s, "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	}
	
	public static void limpiarTabla(DefaultTableModel modelo) {
		modelo.setRowCount(0);
	}
	
	public void limpiarCajas() {
		for(Component component: components) {
			if(component instanceof JTextField)
				((JTextField)component).setText("");
			if(component instanceof JComboBox)
				((JComboBox)component).setSelectedIndex(0);
			if(component instanceof JLabel)
				((JLabel)component).setText("");
		}
	}
	
	public static void aplicarFlatLaft() {
		/*APLICAR LIBRERIA FLATLAFT*/
		FlatLightFlatIJTheme.setup();
		System.setProperty("flatlaf.menuBarEmbedded", "false");
		UIManager.put("TableHeader.hoverBackground", false);
		UIManager.put("Table.showHorizontalLines", true);
		UIManager.put("Table.showVerticalLines", true);
	}
}
