package gui.reportes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidades.Producto;
import libreria.TablaButtonDetalle;
import libreria.TablaGeneral;
import operaciones.ArregloProductos;

import javax.swing.JScrollPane;

public class ReportePrecios extends JPanel {
	private TablaGeneral table;
	private JScrollPane scrollPane;
	private final int alturaTable = 320; 
	private final int alturaCabeceras = 23;
	private DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * Create the panel.
	 */
	public ReportePrecios() {
		setBounds(0, 0, 787, 421);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 27, 767, 320);
		add(scrollPane);
		
		table = new TablaGeneral(
			new Object[][] {},
			new String[] {"Precio promedio", "Precio Mayor", "Precio menor"},
			new boolean[] {false, false, false}
		);
		scrollPane.setViewportView(table);
		
		llenarTabla();
		alturaFila();
	}
	
	private void alturaFila() {
		table.setRowHeight(alturaTable-alturaCabeceras);
	}
	
	private void llenarTabla() {
		double promedio = ArregloProductos.precioPromedioProductos();
		double mayor = ArregloProductos.precioMayor();
		double menor = ArregloProductos.precioMenor();
		String productoMayor = ArregloProductos.productoPrecioMayor().getDescripcion();
		String productoMenor = ArregloProductos.productoPrecioMenor().getDescripcion();
		
		JLabel pMayor = new JLabel("<html><center>"+productoMayor+":<br>"+mayor+"<html>");
		pMayor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		JLabel pMenor = new JLabel("<html><center>"+productoMenor+":<br>"+menor+"<html>");
		pMenor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		table.getModeloTabla().addRow(new Object[] {df.format(promedio), pMayor, pMenor});
	}

}
