package gui.mantenimiento.modificar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Cliente;
import entidades.Producto;
import gui.mantenimiento.GuiMantenimientoClientes;
import libreria.JTextFieldLimit;
import libreria.Utilidades;
import operaciones.ArregloClientes;
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

public class GuiMantenimientoModificarCliente extends JDialog implements KeyListener, ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JPanel panelCliente;
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
	private JButton btnModificar;
	
	private final String exrNumber = "\\d+";
	
	private Cliente p;
	private JLabel lblModificarCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiMantenimientoModificarCliente dialog = new GuiMantenimientoModificarCliente(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiMantenimientoModificarCliente(JDialog gcc, Cliente p) {
		super(gcc, true);
		setBounds(100, 100, 711, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Utilidades.aplicarFlatLaft();
		
		this.p = p;
		
		panelCliente = new JPanel();
		panelCliente.setLayout(null);
		panelCliente.setBackground(Color.WHITE);
		panelCliente.setBounds(10, 11, 675, 401);
		contentPanel.add(panelCliente);
		
		lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 81, 126, 14);
		panelCliente.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(this);
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(10, 99, 312, 31);
		panelCliente.add(txtCodigo);
		
		lblNewLabel_1 = new JLabel("Nombres");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 141, 127, 14);
		panelCliente.add(lblNewLabel_1);
		
		txtNombres = new JTextField();
		txtNombres.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombres.setColumns(10);
		txtNombres.setBounds(11, 162, 654, 31);
		panelCliente.add(txtNombres);
		
		lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 208, 126, 14);
		panelCliente.add(lblNewLabel_2);
		
		txtApellidos = new JTextField();
		txtApellidos.addKeyListener(this);
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(10, 225, 655, 31);
		panelCliente.add(txtApellidos);
		
		lblStock = new JLabel("Telefono");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStock.setBounds(10, 267, 126, 14);
		panelCliente.add(lblStock);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(this);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(10, 286, 312, 31);
		txtTelefono.setDocument(new JTextFieldLimit(9));
		panelCliente.add(txtTelefono);
		
		lblStockMaximo = new JLabel("DNI");
		lblStockMaximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStockMaximo.setBounds(353, 268, 126, 14);
		panelCliente.add(lblStockMaximo);
		
		txtDNI = new JTextField();
		txtDNI.addKeyListener(this);
		txtDNI.setColumns(10);
		txtDNI.setBounds(353, 286, 312, 31);
		txtDNI.setDocument(new JTextFieldLimit(8));
		panelCliente.add(txtDNI);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModificar.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/editar.png")));
		btnModificar.setIconTextGap(10);
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.setBackground(Color.decode("#FFC048"));
		btnModificar.addActionListener(this);
		btnModificar.setBounds(276, 355, 123, 35);
		panelCliente.add(btnModificar);
		
		lblModificarCliente = new JLabel("Modificar Cliente");
		lblModificarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificarCliente.setForeground(new Color(24, 24, 24));
		lblModificarCliente.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblModificarCliente.setBounds(217, 11, 241, 42);
		panelCliente.add(lblModificarCliente);
		
		mostrarDatosEnCajas();
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
			
			if(ok == 0 && validarCamposVacios() && validarDNI() && validarTelefono()) {
				/*OBTENGO LOS DATOS*/
				int codigo = leerCodigo();
				String nombres = leerNombre();
				String apellidos = leerApellidos();
				String telefono = leerTelefono();
				String dni = leerDNI();
				
				/*Actualizo mi producto*/
				ArregloClientes.actualizarCliente(
						new Cliente(codigo, nombres, apellidos, telefono, dni)
				);
				Utilidades.informationMensaje(this, "CLIENTE ACTUALIZADO");
				dispose();
			}
		} catch (Exception e) {
			Utilidades.errorMensaje(this, "DATOS INVALIDOS");
		}
	}
	
	private void mostrarDatosEnCajas() {
		try {
			txtCodigo.setText(""+p.getCodigoCliente());
			txtNombres.setText(p.getNombres());
			txtApellidos.setText(""+p.getApellidos());
			txtTelefono.setText(""+p.getTelefono());
			txtDNI.setText(""+p.getDni());
			txtCodigo.requestFocus();
		} catch (Exception e) {
			Utilidades.errorMensaje(this, "CLIENTE NO ENCONTRADO");
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
	private int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
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
