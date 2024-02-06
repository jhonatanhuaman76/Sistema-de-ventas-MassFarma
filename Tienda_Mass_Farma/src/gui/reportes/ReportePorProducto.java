package gui.reportes;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import entidades.Factura;
import entidades.ItemDetalleVenta;
import entidades.Producto;
import entidades.Vendedor;
import gui.mantenimiento.GuiMantenimientoClientes;
import gui.seleccionar.GuiSeleccionarProducto;
import gui.ventas.GuiDevoluciones;
import libreria.TablaButtonDetalle;
import libreria.ModeloTabla;
import libreria.CellRenderComponents;
import libreria.Utilidades;
import operaciones.ArregloDetalles;
import operaciones.ArregloFacturas;
import operaciones.ArregloProductos;
import operaciones.ArregloVendedores;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ReportePorProducto extends JPanel implements ActionListener, KeyListener {
	private TablaButtonDetalle table;
	private JScrollPane scrollPane;
	private JTextField txtCodigo;
	private JLabel lblCodigo;
	private JButton btnBuscar;
	private JTextField txtVendedor;
	private JLabel lblVendedor;
	private JButton btnVerDetalle;
	private JLabel lblNroventas;
	private JTextField txtNroVentas;
	private JLabel lblVentasCanceladas;
	private JTextField txtVentasCanceladas;
	private JDialog reporte;

	/**
	 * Create the panel.
	 */
	public ReportePorProducto(JDialog reporte) {
		setBounds(0, 0, 787, 421);
		setLayout(null);
		
		this.reporte = reporte;
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 767, 296);
		add(scrollPane);
		
		table = new TablaButtonDetalle(
			new Object[][] {},
			new String[] {"<html>Codigo<br>Factura<html>", "<html>Codigo<br>Cliente<html>", "<html>Codigo<br>Vendedor<html>", "<html>Unidades<br>vendidas<html>", "<html>Importe<br>Total<html>", "Fecha", "Detalle", "Estado", "Observacion"},
			new boolean[] {false, false, false, false, false, false, false, false, false},
			reporte
		);
		scrollPane.setViewportView(table);
		
		//Ancho de columnas
		TableColumnModel modeloColumna = table.getColumnModel();
		modeloColumna.getColumn(6).setPreferredWidth(120);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.addKeyListener(this);
		txtCodigo.setBounds(63, 26, 86, 31);
		add(txtCodigo);
		txtCodigo.setColumns(10);
		
		lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 34, 64, 14);
		add(lblCodigo);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setIconTextGap(10);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/buscar-blanco.png")));
		btnBuscar.setBackground(Color.decode("#0396DA"));
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(159, 24, 122, 35);
		add(btnBuscar);
		
		txtVendedor = new JTextField();
		txtVendedor.setEditable(false);
		txtVendedor.setBounds(369, 26, 408, 31);
		add(txtVendedor);
		txtVendedor.setColumns(10);
		
		lblVendedor = new JLabel("Producto");
		lblVendedor.setBounds(304, 34, 64, 14);
		add(lblVendedor);
		
		lblNroventas = new JLabel("Numero de Ventas");
		lblNroventas.setBounds(10, 383, 104, 14);
		add(lblNroventas);
		
		txtNroVentas = new JTextField();
		txtNroVentas.setEditable(false);
		txtNroVentas.setBounds(112, 375, 86, 31);
		add(txtNroVentas);
		txtNroVentas.setColumns(10);
		
		lblVentasCanceladas = new JLabel("Ventas Canceladas");
		lblVentasCanceladas.setBounds(278, 385, 126, 14);
		add(lblVentasCanceladas);
		
		txtVentasCanceladas = new JTextField();
		txtVentasCanceladas.setEditable(false);
		txtVentasCanceladas.setColumns(10);
		txtVentasCanceladas.setBounds(383, 375, 86, 31);
		add(txtVentasCanceladas);
		
		btnVerDetalle = new JButton("Ver Detalle");
		btnVerDetalle.setName("d");
		btnVerDetalle.setIcon(new ImageIcon(GuiDevoluciones.class.getResource("/image/ver.png")));
		btnVerDetalle.setIconTextGap(10);
		btnVerDetalle.setBackground(Color.decode("#ff9914"));
		btnVerDetalle.setForeground(Color.WHITE);
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtCodigo) {
			keyTypedTxtCodigo(e);
		}
	}
	protected void keyTypedTxtCodigo(KeyEvent e) {
		char c = e.getKeyChar();
	    if(c<'0' || c>'9' ) e.consume();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		buscar();
	}
	private void buscar() {
		try {
			GuiSeleccionarProducto gcc = new GuiSeleccionarProducto(reporte);
			gcc.setVisible(true);
			
			Producto p = gcc.getProducto();
			txtCodigo.setText(""+p.getCodigoProducto());
			txtVendedor.setText(p.getDescripcion());
			llenarTabla(p.getCodigoProducto());
			
		} catch (Exception e) {
//			Utilidades.errorMensaje(this, "CODIGO DE PRODUCTO NO ENCONTRADO");
		}
	}
	
	private void llenarTabla(int codigo) {
		Utilidades.limpiarTabla(table.getModeloTabla());
		ArrayList<Factura> facturas = ArregloFacturas.buscarFacturasProducto(codigo);
		for(Factura f: facturas) {
			int codigofactura = f.getCodigoFactura();
			int codigoCliente = f.getCodigoCliente();
			int codigoVendedor = f.getCodigoVendedor();
			int unidadesVendias = f.getTotalUnidades();
			double impoteTotal = f.getImporteTotal();
			String fecha = f.getFecha();
			String estado = ArregloFacturas.ESTADO[f.getEstado()];
			String observacion = f.getObservacion();
			table.getModeloTabla().addRow(new Object[] {codigofactura, codigoCliente, codigoVendedor, unidadesVendias, impoteTotal, fecha, btnVerDetalle, estado, observacion});
		}
		
		txtNroVentas.setText(""+ArregloFacturas.buscarFacturasProcesadasProducto(codigo).size());
		txtVentasCanceladas.setText(""+ArregloFacturas.buscarFacturasCanceladasProducto(codigo).size());
	}
	
	private int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}
}

