package gui.reportes;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import entidades.Producto;
import libreria.TablaButtonDetalle;
import libreria.TablaGeneral;
import libreria.ModeloTabla;
import libreria.Utilidades;
import operaciones.ArregloProductos;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ReporteGeneralProductos extends JPanel {
	private TablaGeneral table;
	private JScrollPane scrollPane;
	private JLabel lblNroventas;
	private JTextField txtNroVentas;

	/**
	 * Create the panel.
	 */
	public ReporteGeneralProductos() {
		setBounds(0, 0, 787, 421);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 767, 354);
		add(scrollPane);
		
		table = new TablaGeneral(
			new Object[][] {},
			new String[] {"Codigo", "Descripcion", "<html>Numero de<br>Ventas<html>", "<html>Unidades vendidas<br>acumuladas<html>", "<html>Importe total<br>acumulado<html>", "<html>Ventas<br>canceladas<html>"},
			new boolean[] {false, false, false, false, false, false}
		);
		scrollPane.setViewportView(table);
		
		//Ancho de columnas
		TableColumnModel modeloColumna = table.getColumnModel();
		modeloColumna.getColumn(1).setPreferredWidth(300);
		
		lblNroventas = new JLabel("Numero de Productos");
		lblNroventas.setBounds(10, 386, 126, 14);
		add(lblNroventas);
		
		txtNroVentas = new JTextField();
		txtNroVentas.setEditable(false);
		txtNroVentas.setColumns(10);
		txtNroVentas.setBounds(133, 376, 86, 35);
		add(txtNroVentas);
		
		llenarTabla();
	}
	
	private void llenarTabla() {
		Utilidades.limpiarTabla(table.getModeloTabla());
		for(Producto p: ArregloProductos.getListaProductos()) {
			int codigo = p.getCodigoProducto();
			String descripcion = p.getDescripcion();
			int nroVentas = p.getNumeroVentas();
			int unidadesVendidas = p.getUnidadesVendidas();
			double importeTotal = p.getImporteTotal();
			int ventasCanceladas = p.getVentasCanceladas();
			table.getModeloTabla().addRow(new Object[] {codigo, descripcion, nroVentas, unidadesVendidas, importeTotal, ventasCanceladas});
		}
		
		txtNroVentas.setText(""+ArregloProductos.getListaProductos().size());
	}
}
