package gui.almacen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Producto;
import gui.mantenimiento.GuiMantenimientoClientes;
import gui.seleccionar.GuiSeleccionarProducto;
import libreria.TablaGeneral;
import libreria.Utilidades;
import operaciones.ArregloProductos;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class GuiAlmacen extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private TablaGeneral tblAlmacen;
	private JScrollPane scrollPane;
	private JTextField txtCodigo;
	private JLabel lblNewLabel_1;
	private JButton btnBuscar;
	private JTextField txtDescripcion;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JButton btnAumentar;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiAlmacen dialog = new GuiAlmacen(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiAlmacen(JFrame gcc) {
		super(gcc, true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiAlmacen.class.getResource("/image/logo.png")));
		setTitle("Almacen");
		setBounds(100, 100, 725, 527);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Utilidades.aplicarFlatLaft();
		
		lblNewLabel = new JLabel("Almacen Productos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(267, 26, 174, 22);
		contentPanel.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 184, 689, 293);
		contentPanel.add(scrollPane);
		
		tblAlmacen = new TablaGeneral(
				new Object[][] {},
				new String[] {"CODIGO", "DESCRIPCION", "STOCK ACTUAL"},
				new boolean[] {false, false, false}
				);
		scrollPane.setViewportView(tblAlmacen);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(77, 89, 90, 31);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Codigo:");
		lblNewLabel_1.setBounds(10, 97, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setIconTextGap(10);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/buscar-blanco.png")));
		btnBuscar.setBackground(Color.decode("#0396DA"));
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(177, 87, 133, 35);
		contentPanel.add(btnBuscar);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setEditable(false);
		txtDescripcion.setBounds(388, 89, 311, 31);
		contentPanel.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(10, 139, 68, 14);
		contentPanel.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(76, 131, 90, 31);
		contentPanel.add(txtCantidad);
		
		btnAumentar = new JButton("Aumentar");
		btnAumentar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAumentar.setIconTextGap(10);
		btnAumentar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAumentar.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/nuevo.png")));
		btnAumentar.setBackground(Color.decode("#2ecc71"));
		btnAumentar.setForeground(Color.WHITE);
		btnAumentar.addActionListener(this);
		btnAumentar.setBounds(177, 129, 133, 35);
		contentPanel.add(btnAumentar);
		
		lblNewLabel_2 = new JLabel("Producto:");
		lblNewLabel_2.setBounds(320, 97, 58, 14);
		contentPanel.add(lblNewLabel_2);
		
		listar();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAumentar) {
			actionPerformedBtnAumentar(e);
		}
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		buscar();
	}
	
	private void buscar() {
		try {
			GuiSeleccionarProducto gcc = new GuiSeleccionarProducto(this);
			gcc.setLocationRelativeTo(this);
			gcc.setVisible(true);
			
			Producto p = gcc.getProducto();
			txtCodigo.setText(""+p.getCodigoProducto());
			txtDescripcion.setText(p.getDescripcion());
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	private void listar() {
		tblAlmacen.getModeloTabla().setRowCount(0);
		for(Producto p: ArregloProductos.getListaProductos()) {
			tblAlmacen.getModeloTabla().addRow(new Object[] {
					p.getCodigoProducto(),
					p.getDescripcion(),
					p.getStockActual()
			});
		}
	}
	protected void actionPerformedBtnAumentar(ActionEvent e) {
		aumentar();
	}
	
	private void aumentar() {
		try {
			int codigo = leerCodigo();
			int cantidad = leerCantidad();
			
			Producto p = ArregloProductos.buscarProducto(codigo);
			if(p.getStockActual()+cantidad<=p.getStockMaximo()) {
				ArregloProductos.aumentarStockProducto(codigo, cantidad);				
				Utilidades.informationMensaje(this, "STOCK ACTUALIZADO");
			}else
				Utilidades.errorMensaje(this, "LA CANTIDAD SUPERA AL STOCK MAXIMO");
				
			
			listar();
		} catch (Exception e) {
			Utilidades.errorMensaje(this, "CANTIDAD NO VALIDA O PRODUCTO NO ENCONTRADO");
		}
	}
	
	private int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}
	
	private int leerCantidad() {
		return Integer.parseInt(txtCantidad.getText());
	}
}
