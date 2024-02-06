package libreria;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import entidades.Factura;
import entidades.ItemDetalleVenta;
import gui.reportes.GuiDetalleFactura;
import gui.ventas.GuiEfectuarVenta;
import operaciones.ArregloDetalles;
import operaciones.ArregloFacturas;

public class TablaButtonDetalle extends TablaGeneral implements MouseListener{
	
	JDialog gcc;
	
	public TablaButtonDetalle(Object[][]data, Object[]column, boolean[]editables) {
		super(data, column, editables);
	}

	public TablaButtonDetalle(Object[][]data, Object[] column, boolean[]editables, JDialog gcc) {
		super(data, column, editables);
		this.gcc = gcc;
		addMouseListener(this);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(gcc != null)
			clickTable(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	private void clickTable(MouseEvent e) {
		int fila = rowAtPoint(e.getPoint());
		int columna = columnAtPoint(e.getPoint());

		Object value = getValueAt(fila, columna);

		if(value instanceof JButton) {
			((JButton) value).doClick();
			JButton boton = (JButton) value;

			//Accion del boton VerDetalles
			if(boton.getName().equals("d")) {
				ArrayList<ItemDetalleVenta> items = obtenerDetalle(fila);
				GuiDetalleFactura gdt = new GuiDetalleFactura(gcc, items);
				gdt.setLocationRelativeTo(gcc);
				gdt.setVisible(true);
			} 
		}
	}
	
	private ArrayList<ItemDetalleVenta> obtenerDetalle(int fila) {
		int codigoFactura = Integer.parseInt(getValueAt(fila, 0).toString());
		Factura f = ArregloFacturas.buscarFactura(codigoFactura);
		
		return f.getItemsDetalles();
	}
}
