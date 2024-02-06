package gui.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;

import entidades.Usuario;
import entidades.Vendedor;
import gui.almacen.GuiAlmacen;
import gui.login.GuiLogin;
import gui.mantenimiento.GuiMantenimientoClientes;
import gui.mantenimiento.GuiMantenimientoProductos;
import gui.mantenimiento.GuiMantenimientoUsuarios;
import gui.mantenimiento.GuiMantenimientoVendedores;
import gui.reportes.GuiReportes;
import gui.ventas.GuiDevoluciones;
import gui.ventas.GuiEfectuarVenta;
import libreria.Utilidades;
import operaciones.ArregloUsuarios;
import operaciones.ArregloVendedores;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class GuiMenuTienda extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmVendedores;
	private JMenuItem mntmClientes;
	private JMenuItem mntmProductos;
	private JMenu mnNewMenu_2;
	private JMenuItem mntmNewMenuItem_5;
	private JMenu mnNewMenu_3;
	private JMenuItem mntmNewMenuItem_6;
	private JMenuItem mntmNewMenuItem_7;
	private JLabel lblUsuario;
	private Usuario user;
	private JMenuItem mntmUsuarios;
	private JMenu mnNewMenu_6;
	private JMenuItem mntmStockProductos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiMenuTienda frame = new GuiMenuTienda(null);
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
	public GuiMenuTienda(Usuario user) {
		setTitle("Tienda Mass Farma");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiMenuTienda.class.getResource("/image/logo.png")));
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(1000, 640));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 460);
		
		/*APLICAR LIBRERIA FLATLAFT*/
		Utilidades.aplicarFlatLaft();
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Archivo");
		mnNewMenu.setIcon(new ImageIcon(GuiMenuTienda.class.getResource("/image/logout.png")));
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Cerrar sesión");
		mntmNewMenuItem.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("Salir");
		mntmNewMenuItem_1.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem_1);
		
		mnNewMenu_1 = new JMenu("Mantenimiento");
		mnNewMenu_1.setIcon(new ImageIcon(GuiMenuTienda.class.getResource("/image/tool.png")));
		menuBar.add(mnNewMenu_1);
		
		mntmVendedores = new JMenuItem("Vendedores");
		mntmVendedores.addActionListener(this);
		mnNewMenu_1.add(mntmVendedores);
		
		mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(this);
		
		mntmUsuarios = new JMenuItem("Usuarios");
		mntmUsuarios.addActionListener(this);
		mnNewMenu_1.add(mntmUsuarios);
		mnNewMenu_1.add(mntmClientes);
		
		mntmProductos = new JMenuItem("Productos");
		mntmProductos.addActionListener(this);
		mnNewMenu_1.add(mntmProductos);
		
		mnNewMenu_2 = new JMenu("Ventas");
		mnNewMenu_2.setIcon(new ImageIcon(GuiMenuTienda.class.getResource("/image/peru.png")));
		menuBar.add(mnNewMenu_2);
		
		mntmNewMenuItem_5 = new JMenuItem("Efectuar Venta");
		mntmNewMenuItem_5.addActionListener(this);
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		mntmNewMenuItem_7 = new JMenuItem("Devoluciones");
		mntmNewMenuItem_7.addActionListener(this);
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		mnNewMenu_6 = new JMenu("Almacen");
		mnNewMenu_6.setIcon(new ImageIcon(GuiMenuTienda.class.getResource("/image/boxes.png")));
		menuBar.add(mnNewMenu_6);
		
		mntmStockProductos = new JMenuItem("Stock Productos");
		mntmStockProductos.addActionListener(this);
		mnNewMenu_6.add(mntmStockProductos);
		
		mnNewMenu_3 = new JMenu("Reportes");
		mnNewMenu_3.setIcon(new ImageIcon(GuiMenuTienda.class.getResource("/image/file.png")));
		menuBar.add(mnNewMenu_3);
		
		mntmNewMenuItem_6 = new JMenuItem("Generar Reportes");
		mntmNewMenuItem_6.addActionListener(this);
		mnNewMenu_3.add(mntmNewMenuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsuario.setBounds(10, 11, 331, 22);
		contentPane.add(lblUsuario);
		
		this.user = user;
		mostrarUsuario();
		restriccionesMantenimiento();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmStockProductos) {
			actionPerformedMntmStockProductos(e);
		}
		if (e.getSource() == mntmUsuarios) {
			actionPerformedMntmNewMenuItem_10(e);
		}
		if (e.getSource() == mntmNewMenuItem) {
			actionPerformedMntmNewMenuItem(e);
		}
		if (e.getSource() == mntmNewMenuItem_6) {
			actionPerformedMntmNewMenuItem_6(e);
		}
		if (e.getSource() == mntmNewMenuItem_7) {
			actionPerformedMntmNewMenuItem_7(e);
		}
		if (e.getSource() == mntmNewMenuItem_5) {
			actionPerformedMntmNewMenuItem_5(e);
		}
		if (e.getSource() == mntmProductos) {
			actionPerformedMntmNewMenuItem_4(e);
		}
		if (e.getSource() == mntmClientes) {
			actionPerformedMntmNewMenuItem_3(e);
		}
		if (e.getSource() == mntmVendedores) {
			actionPerformedMntmNewMenuItem_2(e);
		}
		if (e.getSource() == mntmNewMenuItem_1) {
			actionPerformedMntmNewMenuItem_1(e);
		}
	}
	/*CONECTAR MENU CON LAS DEMAS VENTANAS*/
	
	//SALIR
	protected void actionPerformedMntmNewMenuItem_1(ActionEvent e) {
		System.exit(0);
	}
	//CERRAR SESION
	protected void actionPerformedMntmNewMenuItem(ActionEvent e) {
		GuiLogin gcc = new GuiLogin();
		setVisible(false);
		gcc.setLocationRelativeTo(this);
		gcc.setVisible(true);
	}
	//MANTENIMIENTO VENDEDORES
	protected void actionPerformedMntmNewMenuItem_2(ActionEvent e) {
		GuiMantenimientoVendedores gcc = new GuiMantenimientoVendedores(this, user);
		gcc.setLocationRelativeTo(this);
		gcc.setVisible(true);

	}
	//MANTENIMIENTO USUARIOS
	protected void actionPerformedMntmNewMenuItem_10(ActionEvent e) {
		GuiMantenimientoUsuarios gcc = new GuiMantenimientoUsuarios(this, user);
		gcc.setLocationRelativeTo(this);
		gcc.setVisible(true);
	}
	//MANTENIMIENTO CLIENTES
	protected void actionPerformedMntmNewMenuItem_3(ActionEvent e) {
		GuiMantenimientoClientes gcc = new GuiMantenimientoClientes(this, user);
		gcc.setLocationRelativeTo(this);
		gcc.setVisible(true);
	}
	//MANTENIMIENTO PRODUCTOS
	protected void actionPerformedMntmNewMenuItem_4(ActionEvent e) {
		GuiMantenimientoProductos gcc = new GuiMantenimientoProductos(this, user);
		gcc.setLocationRelativeTo(this);
		gcc.setVisible(true);
	}
	//VENTANA GENERAR VENTA
	protected void actionPerformedMntmNewMenuItem_5(ActionEvent e) {
		GuiEfectuarVenta gcc = new GuiEfectuarVenta(this, user);
		gcc.setLocationRelativeTo(this);
		gcc.setVisible(true);
	}
	//VENTANA DEVOLCUCIONES
	protected void actionPerformedMntmNewMenuItem_7(ActionEvent e) {
		GuiDevoluciones gcc = new GuiDevoluciones(this);
		gcc.setLocationRelativeTo(this);
		gcc.setVisible(true);
	}
	//VENTANA REPORTES
	protected void actionPerformedMntmNewMenuItem_6(ActionEvent e) {
		GuiReportes gcc = new GuiReportes(this);
		gcc.setLocationRelativeTo(this);
		gcc.setVisible(true);
	}
	
	//VENTANA ALMACEN
	protected void actionPerformedMntmStockProductos(ActionEvent e) {
		GuiAlmacen gcc = new GuiAlmacen(this);
		gcc.setLocationRelativeTo(this);
		gcc.setVisible(true);
	}
	
	private void mostrarUsuario() {
		String s = (user==null)?"Usuario: user":leerNombreVendedor();
		lblUsuario.setText(s);
	}
	
	private String leerNombreVendedor() {
		Vendedor v = ArregloVendedores.buscarVendedor(user.getCodigoVendedor());
		if(v!=null)
			return ArregloVendedores.getCategorias()[v.getCategoria()]+": "+v.getNombres()+" "+v.getApellidos();
		else
			return "Vendedor no encontrado";
	}
	
	private void restriccionesMantenimiento() {
		mntmVendedores.setVisible(ArregloUsuarios.validarUsuarioAdmin(user));
		mntmUsuarios.setVisible(ArregloUsuarios.validarUsuarioAdmin(user));
	}
}
