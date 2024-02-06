package gui.mantenimiento.agregar;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Producto;
import gui.mantenimiento.GuiMantenimientoClientes;
import libreria.Utilidades;
import operaciones.ArregloProductos;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class GuiMantenimientoAgregarProducto extends JDialog implements ActionListener, KeyListener {

	private final JPanel contentPanel = new JPanel();
	private JPanel panelProductos;
	private JLabel lblNewLabel;
	private JTextField txtCodigo;
	private JLabel lblNewLabel_1;
	private JTextField txtDescripcion;
	private JLabel lblNewLabel_2;
	private JTextField txtPrecio;
	private JLabel lblCategoria;
	private JComboBox cboCategoria;
	private JLabel lblStockMaximo;
	private JTextField txtStockMax;
	private JLabel lblMarca;
	private JTextField txtMarca;
	private JButton btnAgregar;
	private JLabel lblNuevoProducto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiMantenimientoAgregarProducto dialog = new GuiMantenimientoAgregarProducto(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiMantenimientoAgregarProducto(JDialog gcc) {
		super(gcc, true);
		setResizable(false);
		setTitle("Nuevo");
		setBounds(100, 100, 711, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Utilidades.aplicarFlatLaft();
		
		panelProductos = new JPanel();
		panelProductos.setBackground(Color.WHITE);
		panelProductos.setBounds(10, 11, 675, 401);
		contentPanel.add(panelProductos);
		panelProductos.setLayout(null);
		
		lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 49, 126, 14);
		panelProductos.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(this);
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(10, 67, 312, 31);
		panelProductos.add(txtCodigo);
		
		lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 109, 127, 14);
		panelProductos.add(lblNewLabel_1);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(11, 130, 654, 31);
		panelProductos.add(txtDescripcion);
		
		lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 172, 126, 14);
		panelProductos.add(lblNewLabel_2);
		
		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(this);
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(10, 189, 312, 31);
		panelProductos.add(txtPrecio);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCategoria.setBounds(353, 172, 126, 14);
		panelProductos.add(lblCategoria);
		
		cboCategoria = new JComboBox();
		cboCategoria.setModel(new DefaultComboBoxModel(ArregloProductos.getCategorias()));
		cboCategoria.setBounds(353, 189, 312, 31);
		panelProductos.add(cboCategoria);
		
		lblStockMaximo = new JLabel("Stock Maximo");
		lblStockMaximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStockMaximo.setBounds(10, 231, 126, 14);
		panelProductos.add(lblStockMaximo);
		
		txtStockMax = new JTextField();
		txtStockMax.addKeyListener(this);
		txtStockMax.setColumns(10);
		txtStockMax.setBounds(10, 249, 312, 31);
		panelProductos.add(txtStockMax);
		
		lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMarca.setBounds(353, 231, 126, 14);
		panelProductos.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(353, 250, 312, 31);
		panelProductos.add(txtMarca);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAgregar.setIconTextGap(10);
		btnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAgregar.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/nuevo.png")));
		btnAgregar.setBackground(Color.decode("#2ecc71"));
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(277, 351, 121, 35);
		panelProductos.add(btnAgregar);
		
		lblNuevoProducto = new JLabel("Nuevo Producto");
		lblNuevoProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoProducto.setForeground(new Color(24, 24, 24));
		lblNuevoProducto.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNuevoProducto.setBounds(217, 11, 241, 42);
		panelProductos.add(lblNuevoProducto);
		
		insertarCodigo();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		agregar();
	}
	
	private void agregar() {
		try {
			if(validarCamposVacios()) {
				/*OBTENGO LOS DATOS*/
				int codigo = leerCodigo();
				String descripcion = leerDescripcion();
				double precio = leerPrecio();
				int categoria = leerCategoria();
				int stockMax = leerStocMax();
				String marca = leerMarca();
				
				/*AGREGO EL PRODUCTO A MI ARREGLO DE PRODUCTOS*/
				ArregloProductos.ingresarProductos(
						new Producto(codigo, descripcion, precio, categoria, 0, stockMax, marca)
				);
				
				Utilidades.informationMensaje(this, "PRODUCTO AGREGADO");
				dispose();
			}	
		} catch (Exception e) {
			Utilidades.errorMensaje(this, "DATOS INVALIDOS");
		}
	}
	
	private boolean validarCamposVacios() {
		if(leerDescripcion().isEmpty()) {
			Utilidades.errorMensaje(this, "CAMPO DESCRIPCION ESTA VACIO");
			return false;
		}else if(txtPrecio.getText().isEmpty()) {
			Utilidades.errorMensaje(this, "CAMPO PRECIO ESTA VACIO");
			return false;
		}else if(txtStockMax.getText().isEmpty()) {
			Utilidades.errorMensaje(this, "CAMPO STOCK MAXIMO ESTA VACIO");
			return false;
		}else if(leerMarca().isEmpty()) {
			Utilidades.errorMensaje(this, "CAMPO MARCA ESTA VACIO");
			return false;
		}else {
			return true;
		}
	}
	
	private void insertarCodigo() {
		txtCodigo.setText(""+ArregloProductos.codigoCorrelativo());
	}	
	private int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}
	private String leerDescripcion() {
		return txtDescripcion.getText();
	}
	private double leerPrecio() {
		return Double.parseDouble(txtPrecio.getText());
	}
	private int leerCategoria() {
		return cboCategoria.getSelectedIndex();
	}
	private int leerStocMax() {
		return Integer.parseInt(txtStockMax.getText());
	}
	
	private String leerMarca() {
		return txtMarca.getText();
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtStockMax) {
			keyTypedTxtStockMax(e);
		}
		if (e.getSource() == txtPrecio) {
			keyTypedTxtPrecio(e);
		}
		if (e.getSource() == txtCodigo) {
			keyTypedTxtCodigo(e);
		}
	}
	protected void keyTypedTxtCodigo(KeyEvent e) {
		char c = e.getKeyChar();
		if(c<'0' || c>'9' ) e.consume();
	}
	protected void keyTypedTxtPrecio(KeyEvent e) {
		char c = e.getKeyChar();
	    if((c<'0' || c>'9') && c!='.') e.consume();
	}
	protected void keyTypedTxtStockMax(KeyEvent e) {
		char c = e.getKeyChar();
	    if(c<'0' || c>'9' ) e.consume();
	}
}
