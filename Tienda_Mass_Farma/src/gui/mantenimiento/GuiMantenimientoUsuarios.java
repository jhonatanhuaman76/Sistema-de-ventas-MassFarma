package gui.mantenimiento;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import entidades.Cliente;
import entidades.Producto;
import entidades.Usuario;
import entidades.Vendedor;
import gui.mantenimiento.agregar.GuiMantenimientoAgregarCliente;
import gui.mantenimiento.agregar.GuiMantenimientoAgregarProducto;
import gui.mantenimiento.agregar.GuiMantenimientoAgregarUsuario;
import gui.mantenimiento.modificar.GuiMantenimientoModificarCliente;
import gui.mantenimiento.modificar.GuiMantenimientoModificarProducto;
import gui.mantenimiento.modificar.GuiMantenimientoModificarUsuario;
import libreria.ModeloTabla;
import libreria.TablaButtonDetalle;
import libreria.TablaGeneral;
import libreria.Utilidades;
import operaciones.ArregloClientes;
import operaciones.ArregloProductos;
import operaciones.ArregloUsuarios;
import operaciones.ArregloVendedores;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.MouseEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class GuiMantenimientoUsuarios extends JDialog implements KeyListener, ActionListener, FocusListener {

	private final JPanel contentPanel = new JPanel();
	private TablaGeneral tbUsuarios;
	private JScrollPane scrollPane_1;
	private int cboIndice;
	private Usuario user;
	private JButton btnNuevo;
	private JTextField txtBuscar;
	private JComboBox cboBuscar;
	private JLabel lblBuscarPor;
	
	private String buscar = "Buscar aqui...";
	TableRowSorter<ModeloTabla> sorter;
	RowFilter<ModeloTabla, Object> rf;
	private JLabel label;
	private JButton btnModificar;
	private JButton btnEliminar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiMantenimientoUsuarios dialog = new GuiMantenimientoUsuarios(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiMantenimientoUsuarios(JFrame gcc, Usuario user) {
		super(gcc, true);
		this.user = user;
		setResizable(false);
		setTitle("Mantenimiento Usuarios");
		setBounds(100, 100, 920, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Utilidades.aplicarFlatLaft();
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 108, 884, 442);
		contentPanel.add(scrollPane_1);
		
		tbUsuarios = new TablaGeneral(
			new Object[][] {},
			new String[] {"<html>Codigo<br>Usuario<html>","Nombre usuario", "<html>Codigo<br>Vendedor<html>", "Nombre Vendedor"},
			new boolean[] {false, false, false, false}
		);
		tbUsuarios.setCellSelectionEnabled(false);
		tbUsuarios.setRowSelectionAllowed(true);
		scrollPane_1.setViewportView(tbUsuarios);
		
		//Ancho de columnas
		TableColumnModel modeloColumna = tbUsuarios.getColumnModel();
		modeloColumna.getColumn(1).setPreferredWidth(300);
		modeloColumna.getColumn(3).setPreferredWidth(300);
		
		sorter = new TableRowSorter<ModeloTabla>(tbUsuarios.getModeloTabla());
		rf = null;
		
		tbUsuarios.setAutoCreateRowSorter(true);
		tbUsuarios.setRowSorter(sorter);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNuevo.setIconTextGap(10);
		btnNuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNuevo.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/nuevo.png")));
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(10, 11, 121, 35);
		btnNuevo.setBackground(Color.decode("#2ecc71"));
		btnNuevo.setForeground(Color.WHITE);
		contentPanel.add(btnNuevo);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/buscar.png")));
		label.setBounds(554, 14, 24, 28);
		contentPanel.add(label);
		
		txtBuscar = new JTextField(buscar);
		txtBuscar.setMargin(new Insets(2, 10, 2, 2));
		txtBuscar.addKeyListener(this);
		txtBuscar.addFocusListener(this);
		txtBuscar.setBounds(141, 14, 437, 28);
		contentPanel.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		cboBuscar = new JComboBox();
		cboBuscar.setModel(new DefaultComboBoxModel(new String[] {"Nombre Usuario", "Codigo Usuario",  "Nombre Vendedor", "Codigo Vendedor"}));
		cboBuscar.setBounds(680, 14, 214, 28);
		contentPanel.add(cboBuscar);
		
		lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setBounds(607, 19, 74, 18);
		contentPanel.add(lblBuscarPor);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModificar.addActionListener(this);
		btnModificar.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/editar.png")));
		btnModificar.setIconTextGap(10);
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.setBackground(Color.decode("#FFC048"));
		btnModificar.setBounds(632, 62, 123, 35);
		contentPanel.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.addActionListener(this);
		btnEliminar.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/eliminar.png")));
		btnEliminar.setIconTextGap(10);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminar.setBackground(Color.decode("#F44336"));
		btnEliminar.setBounds(765, 62, 129, 35);
		contentPanel.add(btnEliminar);
		
		listarUsuarios();
	}
	
	public void keyPressed(KeyEvent e) {
		
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtBuscar) {
			keyReleasedTxtBuscar(e);
		}
	}
	public void keyTyped(KeyEvent e) {
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
		if(e.getSource() == btnModificar) {
			actionPerformedBtnModificar(e);
		}
		if(e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
	}
	
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		GuiMantenimientoAgregarUsuario gcc = new GuiMantenimientoAgregarUsuario(this);
		gcc.setLocationRelativeTo(this);
		gcc.setVisible(true);
		listarUsuarios();
	}
	
	protected void actionPerformedBtnModificar(ActionEvent e) {		
		modificar();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		eliminar();
	}
	
	private void modificar() {
		int fila = tbUsuarios.getSelectedRow();
		
		if(fila!=-1) {
			int codigo = Integer.parseInt(tbUsuarios.getValueAt(fila, 0).toString());
			Usuario p = ArregloUsuarios.buscarUsuario(codigo);
			GuiMantenimientoModificarUsuario gcc = new GuiMantenimientoModificarUsuario(this, p);
			gcc.setLocationRelativeTo(this);
			gcc.setVisible(true);
			listarUsuarios();
		}else
			Utilidades.errorMensaje(this, "NO SE HA SELECCIONADO NINGUN USUARIO");
	}
	
	private void eliminar() {
		int fila = tbUsuarios.getSelectedRow();
		
		if(fila!=-1) {
			int ok = Utilidades.confirmMensaje(this, "¿Está seguro de Eliminar?");
			if(ok==0) {
				int codigo = Integer.parseInt(tbUsuarios.getValueAt(fila, 0).toString());
				Usuario p = ArregloUsuarios.buscarUsuario(codigo);
				ArregloUsuarios.eliminarUsuarios(p);
				Utilidades.informationMensaje(this, "USUARIO ELIMINADO");
				listarUsuarios();
			}
		}else
			Utilidades.errorMensaje(this, "NO SE HA SELECCIONADO NINGUN USUARIO");

	}
	
	private void anadirATabla(Usuario p) {
		int codigo = p.getCodigoUsuario();
		int codigoVend = p.getCodigoVendedor();
		String user = p.getUser();
		Vendedor v = ArregloVendedores.buscarVendedor(codigoVend);
		String nombreVend = v.getNombres()+" "+v.getApellidos();
		
		/*Añado fila(producto) a la tabla*/
		((DefaultTableModel) tbUsuarios.getModel()).addRow(new Object[] {codigo, user, codigoVend, nombreVend});
	}
	
	private void listarUsuarios() {
		Utilidades.limpiarTabla((DefaultTableModel) tbUsuarios.getModel());
		/*Muestro el ArregloClientes en la tabla*/
		for(Usuario p: ArregloUsuarios.getListaUsuarios()) {
			anadirATabla(p);
		}
	}
	public void focusGained(FocusEvent e) {
		if (e.getSource() == txtBuscar) {
			focusGainedTxtBuscar(e);
		}
	}
	public void focusLost(FocusEvent e) {
		if (e.getSource() == txtBuscar) {
			focusLostTxtBuscar(e);
		}
	}
	
	protected void focusLostTxtBuscar(FocusEvent e) {
		if(txtBuscar.getText().isEmpty()) {
			txtBuscar.setText(buscar);
			txtBuscar.setForeground(Color.GRAY);
		}
	}
	protected void focusGainedTxtBuscar(FocusEvent e) {
		if(txtBuscar.getText().equals(buscar)) {
			txtBuscar.setText("");
			txtBuscar.setForeground(Color.BLACK);
			txtBuscar.requestFocus();
		}
	}
	protected void keyReleasedTxtBuscar(KeyEvent e) {
		filtroBuscar();
	}
	
	private void filtroBuscar() {
		//CONVERTIR TEXTO A EXPRESION REGULAR SIN IMPORTAR MINUSCULAS O MAYUSCULAS
		String busqueda = "(?i)" + Pattern.quote(txtBuscar.getText());

		switch (leerCboBuscar()) {
		case 0: 
			String[] palabras = txtBuscar.getText().split(" ");
			List<RowFilter<Object, Object>> filtrosPalabrasClave = new ArrayList<>();
			for(String s: palabras) {
				filtrosPalabrasClave.add(RowFilter.regexFilter("(?i)" + Pattern.quote(s), 1));
			}
			rf = RowFilter.andFilter(filtrosPalabrasClave);
			break;
		case 1:
			rf = RowFilter.regexFilter(busqueda, 0);
			break;
		case 2:
			String[] palabras2 = txtBuscar.getText().split(" ");
			List<RowFilter<Object, Object>> filtrosPalabrasClave2 = new ArrayList<>();
			for(String s: palabras2) {
				filtrosPalabrasClave2.add(RowFilter.regexFilter("(?i)" + Pattern.quote(s), 3));
			}
			rf = RowFilter.andFilter(filtrosPalabrasClave2);
			break;
		case 3:
			rf = RowFilter.regexFilter(busqueda, 2);
			break;
		}
		sorter.setRowFilter(rf);
	}
	
	private int leerCboBuscar() {
		return cboBuscar.getSelectedIndex();
	}
}
