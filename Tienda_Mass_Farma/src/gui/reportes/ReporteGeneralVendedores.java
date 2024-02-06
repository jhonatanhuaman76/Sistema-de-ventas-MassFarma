package gui.reportes;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import entidades.Producto;
import entidades.Vendedor;
import libreria.TablaButtonDetalle;
import libreria.TablaGeneral;
import libreria.ModeloTabla;
import libreria.Utilidades;
import operaciones.ArregloProductos;
import operaciones.ArregloVendedores;

import javax.swing.JScrollPane;

public class ReporteGeneralVendedores extends JPanel {
	private TablaGeneral table;
	private JScrollPane scrollPane;
	private JLabel lblNroventas;
	private JTextField txtNroVentas;

	/**
	 * Create the panel.
	 */
	public ReporteGeneralVendedores() {
		setBounds(0, 0, 787, 421);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 767, 354);
		add(scrollPane);
		
		table = new TablaGeneral(
			new Object[][] {},
			new String[] {"Codigo", "Nombres", "Apellidos", "<html>Numero de<br>Ventas<html>", "<html>Unidades vendidas<br>acumuladas<html>", "<html>Importe total<br>acumulado<html>", "<html>Ventas<br>Canceladas<html>"},
			new boolean[] {false, false, false, false, false, false, false}
		);
		scrollPane.setViewportView(table);
		
		//Ancho de columnas
		TableColumnModel modeloColumna = table.getColumnModel();
		modeloColumna.getColumn(1).setPreferredWidth(150);
		modeloColumna.getColumn(2).setPreferredWidth(150);
		
		lblNroventas = new JLabel("Numero de Vendedores");
		lblNroventas.setBounds(10, 385, 126, 14);
		add(lblNroventas);
		
		txtNroVentas = new JTextField();
		txtNroVentas.setEditable(false);
		txtNroVentas.setColumns(10);
		txtNroVentas.setBounds(136, 382, 86, 20);
		add(txtNroVentas);
		
		llenarTabla();
	}
	
	private void llenarTabla() {
		Utilidades.limpiarTabla(table.getModeloTabla());
		for(Vendedor p: ArregloVendedores.getListaVendedores()) {
			int codigo = p.getCodigoVendedor();
			String nombres = p.getNombres();
			String apellidos = p.getApellidos();
			int nroVentas = p.getNumeroVentas();
			int unidadesVendidas = p.getUnidadesVendidas();
			double importeTotal = p.getImporteTotal();
			int ventasCanceladas = p.getVentasCanceladas();
			table.getModeloTabla().addRow(new Object[] {codigo, nombres, apellidos, nroVentas, unidadesVendidas, importeTotal, ventasCanceladas});
		}
		
		txtNroVentas.setText(""+ArregloVendedores.getListaVendedores().size());
	}
}
