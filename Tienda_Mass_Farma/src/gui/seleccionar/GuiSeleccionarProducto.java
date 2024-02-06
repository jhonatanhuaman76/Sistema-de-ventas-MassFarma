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

import entidades.Producto;
import libreria.ModeloTabla;
import libreria.TablaGeneral;
import libreria.Utilidades;
import operaciones.ArregloProductos;

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

public class GuiSeleccionarProducto extends JDialog implements ActionListener, FocusListener, KeyListener {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblBuscarPor;
	private JLabel lblElegirCategoria;
	private JComboBox cboBuscar;
	private JComboBox cboCate;
	private JTextField txtBuscar;
	private String buscar = "Buscar aqui...";
	private TablaGeneral tblLista;
	private JButton btnSeleccionar;
	private JScrollPane scrollPane;
	TableRowSorter<ModeloTabla> sorter;
	RowFilter<ModeloTabla, Object> rf1;
	RowFilter<ModeloTabla, Object> rf2;
	RowFilter<ModeloTabla, Object> rfCombinado;
	private Producto p = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiSeleccionarProducto dialog = new GuiSeleccionarProducto(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiSeleccionarProducto(JDialog gcc) {
		super(gcc, true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Productos");
		setBounds(100, 100, 641, 486);
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
		
		lblElegirCategoria = new JLabel("Categoria: ");
		lblElegirCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		lblElegirCategoria.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblElegirCategoria.setBounds(21, 58, 92, 20);
		contentPanel.add(lblElegirCategoria);
		
		cboBuscar = new JComboBox();
		cboBuscar.setModel(new DefaultComboBoxModel(new String[] {"Descripcion", "Codigo"}));
		cboBuscar.setBounds(133, 12, 482, 31);
		contentPanel.add(cboBuscar);
		
		String[] categorias = new String[ArregloProductos.getCategorias().length+1];
		categorias[0] = "--SELECCIONAR CATEGORIA--";
		System.arraycopy(ArregloProductos.getCategorias(), 0, categorias, 1, ArregloProductos.getCategorias().length);
		
		
		cboCate = new JComboBox();
		cboCate.addActionListener(this);
		cboCate.setModel(new DefaultComboBoxModel(categorias));
		cboCate.setBounds(133, 52, 482, 31);
		contentPanel.add(cboCate);
		
		txtBuscar = new JTextField(buscar);
		txtBuscar.addKeyListener(this);
		txtBuscar.addFocusListener(this);
		txtBuscar.setBounds(21, 96, 594, 31);
		txtBuscar.setForeground(Color.GRAY);
		contentPanel.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 153, 594, 234);
		contentPanel.add(scrollPane);
		
		tblLista = new TablaGeneral(
			new Object[][] {},
			new String[] {"Codigo", "Descripcion", "Precio", "Categoria", "<html>Stock<br>Actual<html>", "Marca"},
			new boolean[] {false, false, false, false, false, false}
		);
		tblLista.setCellSelectionEnabled(false);
		tblLista.setRowSelectionAllowed(true);
		scrollPane.setViewportView(tblLista);
		
		//Ancho de columnas
		TableColumnModel modeloColumna = tblLista.getColumnModel();
		modeloColumna.getColumn(1).setPreferredWidth(400);
		modeloColumna.getColumn(3).setPreferredWidth(150);
		modeloColumna.getColumn(5).setPreferredWidth(150);
		
		sorter = new TableRowSorter<ModeloTabla>(tblLista.getModeloTabla());
		rf1=null;
		rf2=null;
		
		tblLista.setAutoCreateRowSorter(true);
		tblLista.setRowSorter(sorter);
		
		btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setIcon(new ImageIcon(GuiSeleccionarCliente.class.getResource("/image/seleccionar.png")));
		btnSeleccionar.setForeground(Color.WHITE);
		btnSeleccionar.addActionListener(this);
		btnSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSeleccionar.setBackground(Color.decode("#0396DA"));
		btnSeleccionar.setBounds(237, 401, 150, 35);
		btnSeleccionar.setIconTextGap(10);
		btnSeleccionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPanel.add(btnSeleccionar);
		
		listar();
		
		rootPane.setDefaultButton(btnSeleccionar);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cboCate) {
			actionPerformedCboCate(e);
		}
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
		
		for(Producto p: ArregloProductos.getListaProductos()) {
			anadirTabla(p);
		}
	}
	
	private void anadirTabla(Producto p) {
		tblLista.getModeloTabla().addRow( new Object[] {
			p.getCodigoProducto(),
			p.getDescripcion(),
			p.getPrecio(),
			ArregloProductos.getCategorias()[p.getCategoria()],
			p.getStockActual(),
			p.getMarca()
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
		filtroBuscar();
		filtroCate();
		List<RowFilter<ModeloTabla, Object>> filtros = new ArrayList<>();
	    if (rf1 != null) {
	        filtros.add(rf1);
	    }

	    if (rf2 != null) {
	        filtros.add(rf2);
	    }
		rfCombinado = RowFilter.andFilter(filtros);
		sorter.setRowFilter(rfCombinado);
	}
	
	private void filtroBuscar() {
		//CONVERTIR TEXTO A EXPRESION REGULAR SIN IMPORTAR MINUSCULAS O MAYUSCULAS
		String busqueda = "(?i)" + Pattern.quote(txtBuscar.getText());

		switch (leerCboBuscar()) {
		case 0: 
			rf1 = RowFilter.regexFilter(busqueda, 1);
			break;
		case 1:
			rf1 = RowFilter.regexFilter(busqueda, 0);
			break;
		}
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
			p = ArregloProductos.buscarProducto(codigo);
			dispose();
		}
	}
	public Producto getProducto() {
		return p;
	}
	protected void actionPerformedCboCate(ActionEvent e) {
		cboCate();
	}
	private void cboCate() {
		if(txtBuscar.getForeground()==Color.GRAY&&txtBuscar.getText().equals(buscar)) {
			rf1 = null;
		}else {
			filtroBuscar();			
		}
			
		filtroCate();
		List<RowFilter<ModeloTabla, Object>> filtros = new ArrayList<>();
	    if (rf1 != null) {
	        filtros.add(rf1);
	    }

	    if (rf2 != null) {
	        filtros.add(rf2);
	    }
		rfCombinado = RowFilter.andFilter(filtros);
		sorter.setRowFilter(rfCombinado);
	}
	private void filtroCate() {
		if(leerCboCate()==0) {
			rf2 = RowFilter.regexFilter(".*");
		}else {
			String busqueda = ArregloProductos.getCategorias()[leerCboCate()-1];
			rf2 = RowFilter.regexFilter(busqueda, 3);
		}
	}
	private int leerCboCate() {
		return cboCate.getSelectedIndex();
	}
}
