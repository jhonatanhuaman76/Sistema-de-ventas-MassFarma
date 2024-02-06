package gui.mantenimiento.agregar;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Cliente;
import entidades.Producto;
import entidades.Vendedor;
import gui.mantenimiento.GuiMantenimientoClientes;
import libreria.JTextFieldLimit;
import libreria.Utilidades;
import operaciones.ArregloClientes;
import operaciones.ArregloProductos;
import operaciones.ArregloVendedores;

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

public class GuiMantenimientoAgregarVendedor extends JDialog implements ActionListener, KeyListener {

	private final JPanel contentPanel = new JPanel();
	private JPanel panelClientes;
	private JLabel lblNewLabel;
	private JTextField txtCodigo;
	private JLabel lblNewLabel_1;
	private JTextField txtNombres;
	private JLabel lblNewLabel_2;
	private JTextField txtApellidos;
	private JLabel lblStock;
	private JTextField txtTelefono;
	private JLabel lblStockMaximo;
	private JTextField txtDNI;
	private JButton btnAgregar;
	private final String exrNumber = "\\d+";
	private JLabel lblCategoria;
	private JComboBox cboCategoria;
	private JLabel lblNuevoVendedor;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiMantenimientoAgregarVendedor dialog = new GuiMantenimientoAgregarVendedor(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiMantenimientoAgregarVendedor(JDialog gcc) {
		super(gcc, true);
		setResizable(false);
		setTitle("Nuevo");
		setBounds(100, 100, 711, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Utilidades.aplicarFlatLaft();
		
		panelClientes = new JPanel();
		panelClientes.setBackground(Color.WHITE);
		panelClientes.setBounds(10, 11, 675, 401);
		contentPanel.add(panelClientes);
		panelClientes.setLayout(null);
		
		lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 70, 126, 14);
		panelClientes.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(this);
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(10, 88, 312, 31);
		panelClientes.add(txtCodigo);
		
		lblNewLabel_1 = new JLabel("Nombres");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(11, 130, 127, 14);
		panelClientes.add(lblNewLabel_1);
		
		txtNombres = new JTextField();
		txtNombres.addKeyListener(this);
		txtNombres.setColumns(10);
		txtNombres.setBounds(11, 151, 654, 31);
		panelClientes.add(txtNombres);
		
		lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 193, 126, 14);
		panelClientes.add(lblNewLabel_2);
		
		txtApellidos = new JTextField();
		txtApellidos.addKeyListener(this);
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(10, 210, 655, 31);
		panelClientes.add(txtApellidos);
		
		lblStock = new JLabel("Telefono");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStock.setBounds(10, 252, 126, 14);
		panelClientes.add(lblStock);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(this);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(10, 271, 312, 31);
		txtTelefono.setDocument(new JTextFieldLimit(9));
		panelClientes.add(txtTelefono);
		
		lblStockMaximo = new JLabel("DNI");
		lblStockMaximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStockMaximo.setBounds(352, 253, 126, 14);
		panelClientes.add(lblStockMaximo);
		
		txtDNI = new JTextField();
		txtDNI.addKeyListener(this);
		txtDNI.setColumns(10);
		txtDNI.setBounds(352, 271, 312, 31);
		txtDNI.setDocument(new JTextFieldLimit(8));
		panelClientes.add(txtDNI);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAgregar.setIconTextGap(10);
		btnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAgregar.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/nuevo.png")));
		btnAgregar.setBackground(Color.decode("#2ecc71"));
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(277, 351, 121, 35);
		panelClientes.add(btnAgregar);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(352, 71, 126, 14);
		panelClientes.add(lblCategoria);
		
		cboCategoria = new JComboBox();
		cboCategoria.setModel(new DefaultComboBoxModel(ArregloVendedores.getCategorias()));
		cboCategoria.setBounds(352, 88, 313, 31);
		panelClientes.add(cboCategoria);
		
		lblNuevoVendedor = new JLabel("Nuevo Vendedor");
		lblNuevoVendedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoVendedor.setForeground(new Color(24, 24, 24));
		lblNuevoVendedor.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNuevoVendedor.setBounds(217, 11, 241, 42);
		panelClientes.add(lblNuevoVendedor);
		
		insertarCodigo();
	}
	
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtNombres) {
			keyTypedTxtNombres(e);
		}
		if (e.getSource() == txtDNI) {
			keyTypedTxtDNI(e);
		}
		if (e.getSource() == txtTelefono) {
			keyTypedTxtTelefono(e);
		}
		if (e.getSource() == txtApellidos) {
			keyTypedTxtApellidos(e);
		}
		if (e.getSource() == txtCodigo) {
			keyTypedTxtCodigo(e);
		}
	}
	protected void keyTypedTxtCodigo(KeyEvent e) {
		char c = e.getKeyChar();
		if(c<'0' || c>'9' ) e.consume();
	}
	protected void keyTypedTxtNombres(KeyEvent e) {
		char c = e.getKeyChar();
		if((c<'a' || c>'z') && (c<'A' || c>'Z')  && c!=' ') e.consume();
	}
	protected void keyTypedTxtApellidos(KeyEvent e) {
		char c = e.getKeyChar();
		if((c<'a' || c>'z') && (c<'A' || c>'Z')  && c!=' ') e.consume();
	}
	protected void keyTypedTxtTelefono(KeyEvent e) {
		char c = e.getKeyChar();
	    if(c<'0' || c>'9' ) e.consume();
	}
	protected void keyTypedTxtDNI(KeyEvent e) {
		char c = e.getKeyChar();
	    if(c<'0' || c>'9' ) e.consume();
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
			if(validarCamposVacios() && validarDNI() && validarTelefono()) {
				/*OBTENGO LOS DATOS*/
				int codigo = leerCodigo();
				int categoria = leerCategoria();
				String nombres = leerNombre();
				String apellidos = leerApellidos();
				String telefono = leerTelefono();
				String dni = leerDNI();
				
				/*AGREGO EL CLIENTE A MI ARREGLO DE CLIENTES*/
				ArregloVendedores.ingresarVendedores(
						new Vendedor(codigo, categoria, nombres, apellidos, telefono, dni)
				);
				Utilidades.informationMensaje(this, "VENDEDOR AGREGADO");
				dispose();
			}	
		} catch (Exception e) {
			Utilidades.errorMensaje(this, "DATOS INVALIDOS");
		}
	}
	
	private boolean validarCamposVacios() {
		if(leerNombre().isEmpty()) {
			Utilidades.errorMensaje(this, "CAMPO NOMBRES ESTA VACIO");
			return false;
		}else if(leerApellidos().isEmpty()) {
			Utilidades.errorMensaje(this, "CAMPO APELLIDOS ESTA VACIO");
			return false;
		}else if(leerTelefono().isEmpty()) {
			Utilidades.errorMensaje(this, "CAMPO TELEFONO ESTA VACIO");
			return false;
		}else if(leerDNI().isEmpty()) {
			Utilidades.errorMensaje(this, "CAMPO DNI ESTA VACIO");
			return false;
		}else {
			return true;
		}
	}
	
	private void insertarCodigo() {
		txtCodigo.setText(""+ArregloVendedores.codigoCorrelativo());
	}
	
	private int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}
	
	private int leerCategoria() {
		return cboCategoria.getSelectedIndex();
	}
	
	private String leerNombre() {
		return txtNombres.getText();
	}
	
	private String leerApellidos() {
		return txtApellidos.getText();
	}
	
	private String leerTelefono() {
		return txtTelefono.getText();
	}
	
	private String leerDNI() {
		return txtDNI.getText();
	}
	
	private boolean validarDNI() {
		if(leerDNI().length()<8) {
			Utilidades.errorMensaje(this, "EL DNI DEBE SER DE 8 DIGITOS");
			return false;
		}else if(!leerDNI().matches(exrNumber)){
			Utilidades.errorMensaje(this, "NUMERO DE DNI NO VALIDO");
			return false;
		}else {
			return true;
		}
	}
	
	private boolean validarTelefono() {
		if(leerTelefono().length()<9) {
			Utilidades.errorMensaje(this, "EL TELEFONO DEBE SER DE 9 DIGITOS");
			return false;
		}else if(!leerTelefono().matches(exrNumber)){
			Utilidades.errorMensaje(this, "NUMERO DE TELEFONO NO VALIDO");
			return false;
		}else {
			return true;
		}
	}
}
