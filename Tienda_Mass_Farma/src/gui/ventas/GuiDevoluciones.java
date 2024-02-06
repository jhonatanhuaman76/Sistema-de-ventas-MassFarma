package gui.ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;

import entidades.Cliente;
import entidades.Factura;
import entidades.ItemDetalleVenta;
import entidades.Producto;
import gui.mantenimiento.GuiMantenimientoClientes;
import gui.seleccionar.GuiSeleccionarCliente;
import libreria.TablaComboBoxDevoluciones;
import libreria.ModeloTabla;
import libreria.CellRenderComponents;
import libreria.Utilidades;
import operaciones.ArregloClientes;
import operaciones.ArregloDetalles;
import operaciones.ArregloFacturas;
import operaciones.ArregloProductos;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.awt.event.ItemEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class GuiDevoluciones extends JDialog implements ActionListener, KeyListener {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField txtCodigoCliente;
	private JButton btnBuscar;
	private JTextField txtNombreCliente;
	private JLabel lblNombreCliente;
	private TablaComboBoxDevoluciones tblVentas;
	private JScrollPane scrollPane;
	private JButton btnVerDetalle;
	private JLabel lblNumeroVentas;
	private JTextField txtNroVentas;
	private JButton btnGuardar;
	private int codigoCliente;
	
	public static final int editCombo = 6;
	public static final int editObservacion = 7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiDevoluciones dialog = new GuiDevoluciones(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiDevoluciones(JFrame gcc) {
		super(gcc, true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiDevoluciones.class.getResource("/image/logo.png")));
		setResizable(false);
		setTitle("Devoluciones");
		setBounds(100, 100, 902, 565);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Utilidades.aplicarFlatLaft();
		
		lblNewLabel = new JLabel("Codigo Cliente:");
		lblNewLabel.setBounds(10, 31, 86, 14);
		contentPanel.add(lblNewLabel);
		
		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setEditable(false);
		txtCodigoCliente.addKeyListener(this);
		txtCodigoCliente.setBounds(98, 23, 99, 31);
		contentPanel.add(txtCodigoCliente);
		txtCodigoCliente.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setIconTextGap(10);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/buscar-blanco.png")));
		btnBuscar.setBackground(Color.decode("#0396DA"));
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(208, 21, 121, 35);
		contentPanel.add(btnBuscar);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setEditable(false);
		txtNombreCliente.setBounds(471, 21, 405, 31);
		contentPanel.add(txtNombreCliente);
		txtNombreCliente.setColumns(10);
		
		lblNombreCliente = new JLabel("Nombre Cliente:");
		lblNombreCliente.setBounds(369, 31, 93, 14);
		contentPanel.add(lblNombreCliente);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 866, 389);
		contentPanel.add(scrollPane);
		
		tblVentas = new TablaComboBoxDevoluciones(
			new Object[][] {},
			new String[] {"<html>Codigo<br>Factura<html>", "<html>Codigo<br>Vendedor<html>", "<html>Unidades<br>vendidas<html>", "<html>Importe<br>Total<html>", "Fecha", "Detalle", "ESTADO", "OBSERVACION"},
			new boolean[] {false, false, false, false, false, false, true, false},
			this, editCombo
		);
		scrollPane.setViewportView(tblVentas);
		
		//Ancho de columnas
		TableColumnModel modeloColumna = tblVentas.getColumnModel();
		modeloColumna.getColumn(5).setPreferredWidth(100);
		
		lblNumeroVentas = new JLabel("Numero Ventas");
		lblNumeroVentas.setBounds(10, 494, 93, 14);
		contentPanel.add(lblNumeroVentas);
		
		txtNroVentas = new JTextField();
		txtNroVentas.setEditable(false);
		txtNroVentas.setBounds(116, 486, 99, 31);
		contentPanel.add(txtNroVentas);
		txtNroVentas.setColumns(10);
		
		btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.setIconTextGap(10);
		btnGuardar.setBackground(Color.decode("#2ecc71"));
		btnGuardar.setIcon(new ImageIcon(GuiDevoluciones.class.getResource("/image/guardar.png")));
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(716, 479, 160, 35);
		contentPanel.add(btnGuardar);
		
		btnVerDetalle = new JButton("Ver Detalle");
		btnVerDetalle.setIcon(new ImageIcon(GuiDevoluciones.class.getResource("/image/ver.png")));
		btnVerDetalle.setIconTextGap(10);
		btnVerDetalle.setBackground(Color.decode("#ff9914"));
		btnVerDetalle.setForeground(Color.WHITE);
		btnVerDetalle.setName("d");
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtCodigoCliente) {
			keyTypedTxtCodigoCliente(e);
		}
	}
	protected void keyTypedTxtCodigoCliente(KeyEvent e) {
		char c = e.getKeyChar();
	    if(c<'0' || c>'9' ) e.consume();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(e);
		}
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		buscar();
	}
	
	protected void actionPerformedBtnGuardar(ActionEvent e) {
		guardarCambios();
	}
	
	private void buscar() {
		try {
			
			GuiSeleccionarCliente gcc = new GuiSeleccionarCliente(this);
			gcc.setLocationRelativeTo(this);
			gcc.setVisible(true);
			
			Cliente c = gcc.getCliente();
			codigoCliente = c.getCodigoCliente();
			
			txtCodigoCliente.setText(""+c.getCodigoCliente());
			txtNombreCliente.setText(c.getNombres() + " " + c.getApellidos());
			
			TableCellEditor editor = tblVentas.getCellEditor();
            if (editor != null) {
                editor.stopCellEditing();
            }
            
			llenarTabla(codigoCliente);
		} catch (Exception e) {
			
		}
	}
	
	private void guardarCambios() {
		try {
			int ok = Utilidades.confirmMensaje(this, "¿Confirmar Cambios?");
			if(ok == 0) {
				TableCellEditor editor = tblVentas.getCellEditor();
	            if (editor != null) {
	                editor.stopCellEditing();
	            }
	            
				for(int i=0; i<tblVentas.getRowCount();i++) {
					int codigoFactura = Integer.parseInt(tblVentas.getValueAt(i, 0).toString());
					String estadoTexto = tblVentas.getValueAt(i, 6).toString();
					int estado = ArregloFacturas.getIndiceEstado(estadoTexto);
					String observacion = tblVentas.getValueAt(i, 7).toString();
					
					ArregloFacturas.actualizarEstadoFacturas(codigoFactura, estado, observacion);
				}
				
				
				Utilidades.informationMensaje(this, "CAMBIOS GUARDADOS");
			}
		}catch (Exception e) {
			Utilidades.errorMensaje(this, "ERROR AL GUARDAR CAMBIOS"); 
		}
	}
	
	private void llenarTabla(int codigo) {
		Utilidades.limpiarTabla(tblVentas.getModeloTabla());
		ArrayList<Factura> facturas = ArregloFacturas.buscarFacturasCliente(codigo);
		for(Factura f: facturas) {
			int codigofactura = f.getCodigoFactura();
			int codigoVendedor = f.getCodigoVendedor();
			int unidadesVendias = f.getTotalUnidades();
			double impoteTotal = f.getImporteTotal();
			String fecha = f.getFecha();
			String observacion = f.getObservacion();
			
			tblVentas.getModeloTabla().addRow(new Object[] {codigofactura, codigoVendedor, unidadesVendias, impoteTotal, fecha, btnVerDetalle, ArregloFacturas.ESTADO[f.getEstado()], observacion});
		}
		txtNroVentas.setText(""+facturas.size());
	}
	
	private int leerCodigoCliente() {
		return Integer.parseInt(txtCodigoCliente.getText());
	}
}
