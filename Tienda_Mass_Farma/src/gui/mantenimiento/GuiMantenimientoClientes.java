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

import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;

import entidades.Cliente;
import entidades.Producto;
import entidades.Usuario;
import gui.mantenimiento.agregar.GuiMantenimientoAgregarCliente;
import gui.mantenimiento.agregar.GuiMantenimientoAgregarProducto;
import gui.mantenimiento.modificar.GuiMantenimientoModificarCliente;
import gui.mantenimiento.modificar.GuiMantenimientoModificarProducto;
import libreria.ModeloTabla;
import libreria.TablaButtonDetalle;
import libreria.TablaGeneral;
import libreria.Utilidades;
import operaciones.ArregloClientes;
import operaciones.ArregloProductos;
import operaciones.ArregloUsuarios;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
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
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Component;
import java.awt.event.MouseMotionListener;

public class GuiMantenimientoClientes extends JDialog implements KeyListener, ActionListener, FocusListener{

	private final JPanel contentPanel = new JPanel();
	private TablaGeneral tbClientes;
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
	private JButton btnModificar;
	private JButton btnEliminar;
	private JLabel label;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiMantenimientoClientes dialog = new GuiMantenimientoClientes(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiMantenimientoClientes(JFrame gcc, Usuario user) {
		super(gcc, true);
		this.user = user;
		setResizable(false);
		setTitle("Mantenimiento Clientes");
		setBounds(100, 100, 920, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		/*APLICAR LIBRERIA FLATLAFT*/
		Utilidades.aplicarFlatLaft();
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/buscar.png")));
		label.setBounds(554, 14, 24, 28);
		contentPanel.add(label);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 108, 884, 442);
		contentPanel.add(scrollPane_1);
		
		tbClientes = new TablaGeneral(
			new Object[][] {},
			new String[] {"Codigo", "Nombres y Apellidos", "Telefono", "DNI"},
			new boolean[] {false, false, false, false}
		);
		tbClientes.setCellSelectionEnabled(false);
		tbClientes.setRowSelectionAllowed(true);
		scrollPane_1.setViewportView(tbClientes);
		
		//Ancho de columnas
		TableColumnModel modeloColumna = tbClientes.getColumnModel();
		modeloColumna.getColumn(1).setPreferredWidth(400);
		
		sorter = new TableRowSorter<ModeloTabla>(tbClientes.getModeloTabla());
		rf = null;
		
		tbClientes.setAutoCreateRowSorter(true);
		tbClientes.setRowSorter(sorter);
		
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
		
		cboBuscar = new JComboBox();
		cboBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboBuscar.setModel(new DefaultComboBoxModel(new String[] {"Nombres y Apellidos", "Codigo", "DNI"}));
		cboBuscar.setBounds(680, 14, 214, 28);
		contentPanel.add(cboBuscar);
		
		txtBuscar = new JTextField(buscar);
		txtBuscar.setMargin(new Insets(2, 10, 2, 2));
		txtBuscar.addKeyListener(this);
		txtBuscar.addFocusListener(this);
		txtBuscar.setBounds(141, 14, 437, 28);
		contentPanel.add(txtBuscar);
		txtBuscar.setColumns(10);
		
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
		listarClientes();
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
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnModificar) {
			actionPerformedBtnModificar(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		GuiMantenimientoAgregarCliente gcc = new GuiMantenimientoAgregarCliente(this);
		gcc.setLocationRelativeTo(this);
		gcc.setVisible(true);
		listarClientes();
	}
	
	protected void actionPerformedBtnModificar(ActionEvent e) {		
		modificar();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		eliminar();
	}
	
	private void modificar() {
		int fila = tbClientes.getSelectedRow();
		
		if(fila!=-1) {
			int codigo = Integer.parseInt(tbClientes.getValueAt(fila, 0).toString());
			Cliente p = ArregloClientes.buscarCliente(codigo);
			GuiMantenimientoModificarCliente gcc = new GuiMantenimientoModificarCliente(this, p);
			gcc.setLocationRelativeTo(this);
			gcc.setVisible(true);
			listarClientes();
		}else
			Utilidades.errorMensaje(this, "NO SE HA SELECCIONADO NINGUN CLIENTE");
	}
	
	private void eliminar() {
		int fila = tbClientes.getSelectedRow();
		
		if(fila!=-1) {
			int ok = Utilidades.confirmMensaje(this, "¿Está seguro de Eliminar?");
			if(ok==0) {
				int codigo = Integer.parseInt(tbClientes.getValueAt(fila, 0).toString());
				Cliente P = ArregloClientes.buscarCliente(codigo);
				ArregloClientes.eliminarClientes(P);
				Utilidades.informationMensaje(this, "CLIENTE ELIMINADO");
				listarClientes();
			}
		}else
			Utilidades.errorMensaje(this, "NO SE HA SELECCIONADO NINGUN CLIENTE");

	}
	
	private void anadirATabla(Cliente p) {
		int codigo = p.getCodigoCliente();
		String nombreCompleto = p.getNombres()+" "+p.getApellidos();
		String telefono = p.getTelefono();
		String dni = p.getDni();
		
		/*Añado fila(producto) a la tabla*/
		((DefaultTableModel) tbClientes.getModel()).addRow(new Object[] {codigo, nombreCompleto, telefono, dni});
	}
	
	private void listarClientes() {
		Utilidades.limpiarTabla((DefaultTableModel) tbClientes.getModel());
		/*Muestro el ArregloClientes en la tabla*/
		for(Cliente p: ArregloClientes.getListaClientes()) {
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
			rf = RowFilter.regexFilter(busqueda, 3);
			break;
		}
		sorter.setRowFilter(rf);
	}
	
	private int leerCboBuscar() {
		return cboBuscar.getSelectedIndex();
	}
}
