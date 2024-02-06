package gui.mantenimiento.modificar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Producto;
import gui.mantenimiento.GuiMantenimientoClientes;
import libreria.Utilidades;
import operaciones.ArregloProductos;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiMantenimientoModificarProducto extends JDialog implements KeyListener, ActionListener {

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
	private JButton btnModificar;
	
	private Producto p;
	private JLabel lblModificarProducto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiMantenimientoModificarProducto dialog = new GuiMantenimientoModificarProducto(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiMantenimientoModificarProducto(JDialog gcc, Producto p) {
		super(gcc, true);
		setBounds(100, 100, 711, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Utilidades.aplicarFlatLaft();
		
		this.p = p;
		
		panelProductos = new JPanel();
		panelProductos.setLayout(null);
		panelProductos.setBackground(Color.WHITE);
		panelProductos.setBounds(10, 11, 675, 401);
		contentPanel.add(panelProductos);
		
		lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 56, 126, 14);
		panelProductos.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(this);
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(10, 74, 312, 31);
		panelProductos.add(txtCodigo);
		
		lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 116, 127, 14);
		panelProductos.add(lblNewLabel_1);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(11, 137, 654, 31);
		panelProductos.add(txtDescripcion);
		
		lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 179, 126, 14);
		panelProductos.add(lblNewLabel_2);
		
		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(this);
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(10, 196, 312, 31);
		panelProductos.add(txtPrecio);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCategoria.setBounds(353, 179, 126, 14);
		panelProductos.add(lblCategoria);
		
		cboCategoria = new JComboBox(ArregloProductos.getCategorias());
		cboCategoria.setBounds(353, 196, 312, 31);
		panelProductos.add(cboCategoria);
		
		lblStockMaximo = new JLabel("Stock Maximo");
		lblStockMaximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStockMaximo.setBounds(10, 238, 126, 14);
		panelProductos.add(lblStockMaximo);
		
		txtStockMax = new JTextField();
		txtStockMax.addKeyListener(this);
		txtStockMax.setColumns(10);
		txtStockMax.setBounds(10, 256, 312, 31);
		panelProductos.add(txtStockMax);
		
		lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMarca.setBounds(353, 238, 126, 14);
		panelProductos.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(353, 257, 312, 31);
		panelProductos.add(txtMarca);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModificar.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/editar.png")));
		btnModificar.setIconTextGap(10);
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.setBackground(Color.decode("#FFC048"));
		btnModificar.addActionListener(this);
		btnModificar.setBounds(276, 355, 123, 35);
		panelProductos.add(btnModificar);
		
		lblModificarProducto = new JLabel("Modificar Producto");
		lblModificarProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificarProducto.setForeground(new Color(24, 24, 24));
		lblModificarProducto.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblModificarProducto.setBounds(204, 11, 266, 34);
		panelProductos.add(lblModificarProducto);
		
		mostrarDatosEnCajas();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnModificar) {
			actionPerformedBtnActualizar(e);
		}
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizar();
	}
	
	private void actualizar() {
		try {
			int ok = Utilidades.confirmMensaje(this,"¿Guardar cambios?");
			
			if(ok == 0 && validarCamposVacios()) {
				/*OBTENGO LOS DATOS*/
				int codigo = leerCodigo();
				String descripcion = leerDescripcion();
				double precio = leerPrecio();
				int categoria = leerCategoria();
				int stockMax = leerStockMax();
				String marca = leerMarca();
				
				/*Actualizo mi producto*/
				ArregloProductos.actualizarProductos(new Producto(codigo, descripcion, precio, categoria, 0, stockMax,  marca));
				Utilidades.informationMensaje(this, "PRODUCTO ACTUALIZADO");
				dispose();
			}
		} catch (Exception e) {
			Utilidades.errorMensaje(this, "DATOS INVALIDOS");
		}
	}
	
	private void mostrarDatosEnCajas() {
		try {
			txtCodigo.setText(""+p.getCodigoProducto());
			txtDescripcion.setText(p.getDescripcion());
			txtPrecio.setText(""+p.getPrecio());
			cboCategoria.setSelectedIndex(p.getCategoria());
			txtStockMax.setText(""+p.getStockMaximo());
			txtMarca.setText(p.getMarca());
			txtCodigo.requestFocus();
		} catch (Exception e) {
			Utilidades.errorMensaje(this, "PRODUCTO NO ENCONTRADO");
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
	private int leerStockMax() {
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
