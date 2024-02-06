package gui.seleccionar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import entidades.Cliente;
import entidades.Producto;
import entidades.Usuario;
import entidades.Vendedor;
import libreria.ModeloTabla;
import libreria.TablaGeneral;
import libreria.Utilidades;
import operaciones.ArregloClientes;
import operaciones.ArregloProductos;
import operaciones.ArregloUsuarios;
import operaciones.ArregloVendedores;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;

public class GuiSeleccionarUsuario extends JDialog implements ActionListener, FocusListener, KeyListener {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblBuscarPor;
	private JComboBox cboBuscar;
	private JTextField txtBuscar;
	private String buscar = "Buscar aqui...";
	private TablaGeneral tblLista;
	private JButton btnSeleccionar;
	private JScrollPane scrollPane;
	TableRowSorter<ModeloTabla> sorter;
	RowFilter<ModeloTabla, Object> rf;
	private Usuario p = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiSeleccionarUsuario dialog = new GuiSeleccionarUsuario(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiSeleccionarUsuario(JDialog gcc) {
		super(gcc, true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Usuarios");
		setBounds(100, 100, 645, 457);
		setLocationRelativeTo(gcc);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Utilidades.aplicarFlatLaft();
		
		lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBuscarPor.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarPor.setBounds(21, 18, 92, 20);
		contentPanel.add(lblBuscarPor);
		
		cboBuscar = new JComboBox();
		cboBuscar.setModel(new DefaultComboBoxModel(new String[] {"Nombre Usuario", "Codigo Usuario",  "Nombre Vendedor", "Codigo Vendedor"}));
		cboBuscar.setBounds(140, 12, 479, 31);
		contentPanel.add(cboBuscar);
		
		txtBuscar = new JTextField(buscar);
		txtBuscar.addKeyListener(this);
		txtBuscar.addFocusListener(this);
		txtBuscar.setBounds(21, 60, 598, 31);
		txtBuscar.setForeground(Color.GRAY);
		contentPanel.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 117, 598, 234);
		contentPanel.add(scrollPane);
		
		tblLista = new TablaGeneral(
			new Object[][] {},
			new String[] {"<html>Codigo<br>Usuario<html>", "Nombres Usuario", "<html>Codigo<br>Vendedor<html>", "Nombre Vendedor"},
			new boolean[] {false, false, false, false}
		);
		tblLista.setCellSelectionEnabled(false);
		tblLista.setRowSelectionAllowed(true);
		scrollPane.setViewportView(tblLista);
		
		//Ancho de columnas
		TableColumnModel modeloColumna = tblLista.getColumnModel();
		modeloColumna.getColumn(1).setPreferredWidth(150);
		modeloColumna.getColumn(3).setPreferredWidth(300);
		
		sorter = new TableRowSorter<ModeloTabla>(tblLista.getModeloTabla());
		rf=null;
		
		tblLista.setAutoCreateRowSorter(true);
		tblLista.setRowSorter(sorter);
		
		btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setIcon(new ImageIcon(GuiSeleccionarCliente.class.getResource("/image/seleccionar.png")));
		btnSeleccionar.setForeground(Color.WHITE);
		btnSeleccionar.addActionListener(this);
		btnSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSeleccionar.setBackground(Color.decode("#0396DA"));
		btnSeleccionar.setBounds(239, 372, 150, 35);
		btnSeleccionar.setIconTextGap(10);
		btnSeleccionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPanel.add(btnSeleccionar);
		
		listar();
		
		rootPane.setDefaultButton(btnSeleccionar);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSeleccionar) {
			actionPerformedBtnSeleccionar(e);
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
	
	private void listar() {
		tblLista.getModeloTabla().setRowCount(0);
		
		for(Usuario p: ArregloUsuarios.getListaUsuarios()) {
			anadirTabla(p);
		}
	}
	
	private void anadirTabla(Usuario p) {
		Vendedor v = ArregloVendedores.buscarVendedor(p.getCodigoVendedor());
		tblLista.getModeloTabla().addRow( new Object[] {
			p.getCodigoUsuario(),
			p.getUser(),
			p.getCodigoVendedor(),
			v.getNombres()+" "+v.getApellidos()
		});
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
	protected void keyReleasedTxtBuscar(KeyEvent e) {
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
	protected void actionPerformedBtnSeleccionar(ActionEvent e) {
		seleccionar();
	}
	private void seleccionar() {
		if(tblLista.getSelectedRow()==-1)
			Utilidades.errorMensaje(this, "SELECCIONE UN ELEMENTO EN LA TABLA");
		else {
			int codigo = (int) tblLista.getValueAt(tblLista.getSelectedRow(), 0);
			p = ArregloUsuarios.buscarUsuario(codigo);
			dispose();
		}
	}
	public Usuario getUsuario() {
		return p;
	}
}
