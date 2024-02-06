package gui.mantenimiento.agregar;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Cliente;
import entidades.Producto;
import entidades.Usuario;
import entidades.Vendedor;
import gui.mantenimiento.GuiMantenimientoClientes;
import gui.seleccionar.GuiSeleccionarVendedores;
import libreria.JTextFieldLimit;
import libreria.Utilidades;
import operaciones.ArregloClientes;
import operaciones.ArregloProductos;
import operaciones.ArregloUsuarios;
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
import javax.swing.JPasswordField;

public class GuiMantenimientoAgregarUsuario extends JDialog implements ActionListener, KeyListener {

	private final JPanel contentPanel = new JPanel();
	private JPanel panelClientes;
	private JLabel lblNewLabel;
	private JTextField txtCodigo;
	private JLabel lblNewLabel_1;
	private JTextField txtUsuario;
	private JLabel lblNewLabel_2;
	private JButton btnAgregar;
	private final String exrNumber = "\\d+";
	private JPasswordField txtContrasena;
	private JLabel lblCodigoVenedor;
	private JTextField txtCodigoVend;
	private JButton btnBuscar;
	private JLabel lblNuevoUsuarip;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiMantenimientoAgregarUsuario dialog = new GuiMantenimientoAgregarUsuario(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiMantenimientoAgregarUsuario(JDialog gcc) {
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
		txtCodigo.setBounds(10, 88, 322, 31);
		panelClientes.add(txtCodigo);
		
		lblNewLabel_1 = new JLabel("Nombre Usuario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 135, 127, 14);
		panelClientes.add(lblNewLabel_1);
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(this);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(11, 156, 654, 31);
		panelClientes.add(txtUsuario);
		
		lblNewLabel_2 = new JLabel("Contraseña:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 198, 126, 14);
		panelClientes.add(lblNewLabel_2);
		
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
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(10, 223, 655, 31);
		panelClientes.add(txtContrasena);
		
		lblCodigoVenedor = new JLabel("Codigo Vendedor:");
		lblCodigoVenedor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigoVenedor.setBounds(342, 70, 126, 14);
		panelClientes.add(lblCodigoVenedor);
		
		txtCodigoVend = new JTextField();
		txtCodigoVend.setEditable(false);
		txtCodigoVend.setColumns(10);
		txtCodigoVend.setBounds(342, 88, 192, 31);
		panelClientes.add(txtCodigoVend);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setIconTextGap(10);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/buscar-blanco.png")));
		btnBuscar.setBackground(Color.decode("#0396DA"));
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(544, 86, 121, 35);
		panelClientes.add(btnBuscar);
		
		lblNuevoUsuarip = new JLabel("Nuevo Usuario");
		lblNuevoUsuarip.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoUsuarip.setForeground(new Color(24, 24, 24));
		lblNuevoUsuarip.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNuevoUsuarip.setBounds(217, 11, 241, 42);
		panelClientes.add(lblNuevoUsuarip);
		
		insertarCodigo();
	}
	
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtCodigoVend) {
			keyTypedTxtCodigoVend(e);
		}
		if (e.getSource() == txtCodigo) {
			keyTypedTxtCodigo(e);
		}
	}
	protected void keyTypedTxtCodigo(KeyEvent e) {
		char c = e.getKeyChar();
		if(c<'0' || c>'9' ) e.consume();
	}
	protected void keyTypedTxtCodigoVend(KeyEvent e) {
		char c = e.getKeyChar();
		if(c<'0' || c>'9' ) e.consume();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		agregar();
	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		try {
			GuiSeleccionarVendedores gcc = new GuiSeleccionarVendedores(this);
			gcc.setLocationRelativeTo(this);
			gcc.setVisible(true);
			Vendedor v = gcc.getVendedor();
			
			txtCodigoVend.setText(""+v.getCodigoVendedor());
		} catch (Exception e2) {
			
		}
		
	}
	private void agregar() {
		try {
			if(validarCamposVacios() && validarCodigoVendedor() && validarNombreUsuario()) {
				/*Obtengo los datos*/
				int codigo = leerCodigo();
				int codigoVend = leerCodigoVend();
				String user = leerUser();
				String pass = leerPass();
				
				/*AGREGO EL CLIENTE A MI ARREGLO DE CLIENTES*/
				ArregloUsuarios.ingresarUsuarios(
						new Usuario(codigo, codigoVend, user, pass)
				);
				Utilidades.informationMensaje(this, "USUARIO AGREGADO");
				dispose();
			}	
		} catch (Exception e) {
			Utilidades.errorMensaje(this, "DATOS INVALIDOS");
		}
	}
	
	private boolean validarCamposVacios() {
		String user = leerUser();
		String pass = leerPass();
		String codVend = txtCodigoVend.getText();
		
		if(codVend.isEmpty()) {
			Utilidades.errorMensaje(this, "CODIGO DE VENDEDOR ESTA VACIO");
			return false;
		}else if(user.isEmpty()) {
			Utilidades.errorMensaje(this, "NOMBRE DE USUARIO ESTA VACIO");
			return false;
		}else if(pass.isEmpty()) {
			Utilidades.errorMensaje(this, "CONTRASEÑA ESTA VACIO");
			return false;
		}else {
			return true;
		}
	}
	
	private void insertarCodigo() {
		txtCodigo.setText(""+ArregloUsuarios.codigoCorrelativo());
	}
	
	private int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}
	
	private int leerCodigoVend() {
		return Integer.parseInt(txtCodigoVend.getText());
	}
	
	private String leerUser() {
		return txtUsuario.getText();
	}
	
	private String leerPass() {
		return new String(txtContrasena.getPassword());
	}
	
	public boolean validarNombreUsuario() {
		Usuario u = ArregloUsuarios.buscarUsuarioNombre(leerUser());
		if(u!=null) {
			Utilidades.errorMensaje(this, "<html><center>NOMBRE DE USUARIO<br>SE ENCUENTRA ASOCIADO A OTRO USUARIO<center><html>");
			return false;
		}else {
			return true;
		}
	}
	
	public boolean validarCodigoVendedor() {
		Vendedor v = ArregloVendedores.buscarVendedor(leerCodigoVend());
		Usuario u = ArregloUsuarios.buscarUsuarioVendedor(leerCodigoVend());
		if(v==null) {
			Utilidades.errorMensaje(this, "<html><center>EL CODIGO DEL VENDEDOR<br>NO SE ENCUENTRA REGISTRADO<center><html>");
			return false;
		}else if(u!=null) {
			Utilidades.errorMensaje(this, "<html><center>EL CODIGO DEL VENDEDOR<br>SE ENCUENTRA ASOCIADO A OTRO USUARIO<center><html>");
			return false;
		}else {
			return true;
		}
	}
}
