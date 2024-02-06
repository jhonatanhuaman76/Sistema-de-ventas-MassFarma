package gui.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;

import gui.menu.GuiMenuTienda;
import libreria.Utilidades;
import operaciones.ArregloUsuarios;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;

public class GuiLogin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JButton btnIngresar;
	private JButton btnSalir;
	private int intentos = 3;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblUsuario_1;
	private JLabel lblContrasea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiLogin frame = new GuiLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiLogin.class.getResource("/image/logo.png")));
		setTitle("Iniciar Sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 467);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*APLICAR LIBRERIA FLATLAFT*/
		Utilidades.aplicarFlatLaft();
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(GuiLogin.class.getResource("/image/img-login.png")));
		lblNewLabel_1.setBounds(0, 1, 385, 432);
		contentPane.add(lblNewLabel_1);
		
		panel = new JPanel();
		panel.setBounds(384, 1, 385, 432);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(21, 25, 154, 53);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(GuiLogin.class.getResource("/image/logo-completo.png")));
		
		lblTitulo = new JLabel("INICIAR SESIÓN");
		lblTitulo.setBounds(72, 107, 241, 42);
		panel.add(lblTitulo);
		lblTitulo.setForeground(new Color(24, 24, 24));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		txtUser = new JTextField();
		txtUser.setBounds(35, 212, 311, 31);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(35, 296, 311, 30);
		panel.add(txtPass);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnIngresar.setBounds(72, 371, 110, 35);
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setBackground(Color.decode("#0396DA"));
		panel.add(btnIngresar);
		btnIngresar.addActionListener(this);
		
		rootPane.setDefaultButton(btnIngresar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setBounds(206, 371, 110, 35);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.decode("#FF6347"));
		panel.add(btnSalir);
		
		lblUsuario_1 = new JLabel("USUARIO:");
		lblUsuario_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario_1.setBounds(35, 184, 92, 20);
		panel.add(lblUsuario_1);
		
		lblContrasea = new JLabel("CONTRASEÑA:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasea.setBounds(35, 265, 127, 20);
		panel.add(lblContrasea);
		btnSalir.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalir) {
			actionPerformedBtnSalir(e);
		}
		if (e.getSource() == btnIngresar) {
			actionPerformedBtnIngresar(e);
		}
	}
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		ingresar();
	}
	protected void actionPerformedBtnSalir(ActionEvent e) {
		System.exit(0);
	}
	
	private void ingresar() {
		if(ArregloUsuarios.validarUsuario(leerUser(), leerPass())) {
			Utilidades.informationMensaje(this, "Bienvenido "+leerUser());
			intentos = 3;
			setVisible(false);
			GuiMenuTienda gcc = new GuiMenuTienda(ArregloUsuarios.buscarUsuarioNombre(leerUser()));
			gcc.setLocationRelativeTo(this);
			gcc.setVisible(true);
			return;
		}
		
		intentos--;
		
		if(intentos==0) {
			Utilidades.errorMensaje(this, "No te quedan más intentos, el programa se cerrará");
			System.exit(0);
		} 
		else if(intentos==1)
			Utilidades.errorMensaje(this, "Usuario y/o contraseña erróneos, te queda "+intentos+ " intento");
		else
			Utilidades.errorMensaje(this, "Usuario y/o contraseña erróneos, te quedan "+intentos+ " intentos");
	}
	
	private String leerUser() {
		return txtUser.getText();
	}
	private String leerPass() {
		return new String(txtPass.getPassword());
	}
}
