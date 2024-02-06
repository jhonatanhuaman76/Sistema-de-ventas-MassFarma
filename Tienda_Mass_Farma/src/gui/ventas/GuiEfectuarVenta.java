package gui.ventas;

import java.awt.BorderLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;

import entidades.Cliente;
import entidades.Factura;
import entidades.ItemDetalleVenta;
import entidades.Producto;
import entidades.Usuario;
import entidades.Vendedor;
import gui.mantenimiento.GuiMantenimientoClientes;
import gui.seleccionar.GuiSeleccionarCliente;
import gui.seleccionar.GuiSeleccionarProducto;
import libreria.MensajeException;
import libreria.TablaButtonDetalle;
import libreria.TablaGeneral;
import libreria.Utilidades;
import libreria.Validaciones;
import operaciones.ArregloClientes;
import operaciones.ArregloDetalles;
import operaciones.ArregloFacturas;
import operaciones.ArregloProductos;
import operaciones.ArregloVendedores;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class GuiEfectuarVenta extends JDialog implements ActionListener, MouseListener, KeyListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigoProducto;
	private JTextField txtNombreProducto;
	private JTextField txtCantidad;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JTextField txtCodigoCliente;
	private JTextField txtNombreCliente;
	private TablaGeneral tblVenta;
	private DefaultCellEditor editor;
	private JTextField txtTotalPagar;
	private JLabel lblNumeroVenta;
	private JButton btnNueva;
	private JButton btnGenerar;
	private JButton btnCancelar;
	private JScrollPane scrollPane;
	private JButton btnAnadir;
	private JButton btnBuscarProducto;
	private JButton btnBuscarCliente;
	private JButton btnModificar1;
	private JButton btnEliminar2;
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
	private Utilidades util;
	private JLabel lblFecha;
	private JTextField txtCantidadTable;
	
	private final int editColumn = 3;
	private Usuario user;
	private final String exrNumber = "\\d+";
	private final String exrDecimal = "\\d+(\\.\\d+)?";
	private JLabel lblDatosDelCliente;
	private JPanel panel;
	private JPanel panel_2;
	private JLabel lblDatosDelProducto;
	private JTextField txtImpIgv;
	private JTextField txtSubTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiEfectuarVenta dialog = new GuiEfectuarVenta(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiEfectuarVenta(JFrame gcc, Usuario user) {
		super(gcc, true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiEfectuarVenta.class.getResource("/image/logo.png")));
		this.user = user;
		setResizable(false);
		setTitle("Efectuar Venta");
		setBounds(100, 100, 903, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Utilidades.aplicarFlatLaft();
		
		btnGenerar = new JButton("Vender");
		btnGenerar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGenerar.setIcon(new ImageIcon(GuiEfectuarVenta.class.getResource("/image/compra.png")));
		btnGenerar.setIconTextGap(10);
		btnGenerar.setBackground(Color.decode("#2ecc71"));
		btnGenerar.setForeground(Color.WHITE);
		btnGenerar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGenerar.addActionListener(this);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(21, 175, 162, 29);
		contentPanel.add(panel_2);
		
		lblDatosDelProducto = new JLabel("Datos del Producto");
		lblDatosDelProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatosDelProducto.setBackground(Color.WHITE);
		lblDatosDelProducto.setBounds(10, 5, 142, 17);
		panel_2.add(lblDatosDelProducto);
		
		panel = new JPanel();
		panel.setBounds(21, 77, 139, 29);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		lblDatosDelCliente = new JLabel("Datos del Cliente");
		lblDatosDelCliente.setBounds(10, 5, 115, 17);
		panel.add(lblDatosDelCliente);
		lblDatosDelCliente.setBackground(Color.WHITE);
		lblDatosDelCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGenerar.setBounds(153, 550, 122, 35);
		contentPanel.add(btnGenerar);

		btnNueva = new JButton("Nuevo");
		btnNueva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNueva.setIcon(new ImageIcon(GuiEfectuarVenta.class.getResource("/image/nueva-venta.png")));
		btnNueva.setIconTextGap(10);
		btnNueva.addActionListener(this);
		btnNueva.setBackground(Color.decode("#27536b"));
		btnNueva.setForeground(Color.WHITE);
		btnNueva.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNueva.setBounds(285, 550, 122, 35);
		contentPanel.add(btnNueva);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setIconTextGap(10);
		btnCancelar.setIcon(new ImageIcon(GuiEfectuarVenta.class.getResource("/image/cancelar.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setBackground(Color.decode("#F44336"));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(21, 550, 122, 35);
		contentPanel.add(btnCancelar);

		JLabel lblPuntoDeVenta = new JLabel("PUNTO DE VENTA \"MASS FARMA\"");
		lblPuntoDeVenta.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPuntoDeVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntoDeVenta.setBounds(195, 11, 496, 35);
		contentPanel.add(lblPuntoDeVenta);

		JLabel lblNewLabel = new JLabel("Tel: 0000-00000");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(251, 48, 122, 17);
		contentPanel.add(lblNewLabel);

		lblNumeroVenta = new JLabel("NRO VENTA: 00000");
		lblNumeroVenta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeroVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroVenta.setBounds(362, 46, 162, 20);
		contentPanel.add(lblNumeroVenta);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 90, 867, 69);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCodigo_1 = new JLabel("Codigo Cliente: ");
		lblCodigo_1.setBounds(10, 26, 90, 26);
		panel_1.add(lblCodigo_1);

		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setEditable(false);
		txtCodigoCliente.addKeyListener(this);
		txtCodigoCliente.setBounds(116, 24, 131, 31);
		panel_1.add(txtCodigoCliente);
		txtCodigoCliente.setColumns(10);

		btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarCliente.setIconTextGap(10);
		btnBuscarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarCliente.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/buscar-blanco.png")));
		btnBuscarCliente.setBackground(Color.decode("#0396DA"));
		btnBuscarCliente.setForeground(Color.WHITE);
		btnBuscarCliente.addActionListener(this);
		btnBuscarCliente.setBounds(257, 22, 123, 35);
		panel_1.add(btnBuscarCliente);

		JLabel lblDescripcion_1 = new JLabel("Cliente: ");
		lblDescripcion_1.setBounds(400, 32, 57, 14);
		panel_1.add(lblDescripcion_1);

		txtNombreCliente = new JTextField();
		txtNombreCliente.setEditable(false);
		txtNombreCliente.setColumns(10);
		txtNombreCliente.setBounds(467, 24, 390, 31);
		panel_1.add(txtNombreCliente);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setBounds(10, 190, 867, 105);
		contentPanel.add(panel_1_1);

		JLabel lblCodigo = new JLabel("Codigo Producto: ");
		lblCodigo.setBounds(10, 23, 133, 23);
		panel_1_1.add(lblCodigo);

		txtCodigoProducto = new JTextField();
		txtCodigoProducto.setEditable(false);
		txtCodigoProducto.addKeyListener(this);
		txtCodigoProducto.setBounds(114, 19, 133, 31);
		panel_1_1.add(txtCodigoProducto);
		txtCodigoProducto.setColumns(10);

		btnBuscarProducto = new JButton("Buscar");
		btnBuscarProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarProducto.setIconTextGap(10);
		btnBuscarProducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarProducto.setIcon(new ImageIcon(GuiMantenimientoClientes.class.getResource("/image/buscar-blanco.png")));
		btnBuscarProducto.setBackground(Color.decode("#0396DA"));
		btnBuscarProducto.setForeground(Color.WHITE);
		btnBuscarProducto.addActionListener(this);
		btnBuscarProducto.setBounds(257, 17, 123, 35);
		panel_1_1.add(btnBuscarProducto);

		JLabel lblDescripcion = new JLabel("Producto: ");
		lblDescripcion.setBounds(399, 27, 60, 14);
		panel_1_1.add(lblDescripcion);

		txtNombreProducto = new JTextField();
		txtNombreProducto.setEditable(false);
		txtNombreProducto.setBounds(469, 19, 383, 31);
		panel_1_1.add(txtNombreProducto);
		txtNombreProducto.setColumns(10);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(399, 71, 60, 14);
		panel_1_1.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(469, 63, 134, 31);
		panel_1_1.add(txtPrecio);
		txtPrecio.setColumns(10);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(625, 71, 66, 14);
		panel_1_1.add(lblStock);

		txtStock = new JTextField();
		txtStock.setEditable(false);
		txtStock.setBounds(663, 63, 189, 31);
		panel_1_1.add(txtStock);
		txtStock.setColumns(10);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 66, 63, 14);
		panel_1_1.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(this);
		txtCantidad.setBounds(114, 61, 133, 31);
		panel_1_1.add(txtCantidad);
		txtCantidad.setColumns(10);

		btnAnadir = new JButton("Añadir");
		btnAnadir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAnadir.setIconTextGap(10);
		btnAnadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnadir.setIcon(new ImageIcon(GuiEfectuarVenta.class.getResource("/image/nuevo.png")));
		btnAnadir.setBackground(Color.decode("#2ecc71"));
		btnAnadir.setForeground(Color.WHITE);
		btnAnadir.addActionListener(this);
		btnAnadir.setBounds(257, 59, 123, 35);
		panel_1_1.add(btnAnadir);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 317, 867, 222);
		contentPanel.add(scrollPane);
		
		txtCantidadTable = new JTextField();
		txtCantidadTable.addKeyListener(this);
		
		final GuiEfectuarVenta gui = this;
		editor = new DefaultCellEditor(txtCantidadTable) {
			
			//Actualizar datos al terminar de escribir en la celda de cantidad
			@Override
		    public boolean stopCellEditing() {
		        try {
		        	actualizarDatos();
		        } catch (NumberFormatException e) {
		        	Utilidades.errorMensaje(gui, "LA CANTIDAD ES DEMASIADO GRANDE");
		            return false;
		        } catch (Exception e) {
					return false;
				}
		        // Continuar con el proceso de edición
		        return super.stopCellEditing();
		    }
		};
		tblVenta = new TablaGeneral(
			new Object[][] {},
			new String[] { "Codigo", "Descripcion", "<html>Precio<br>Unitario<html>", "Cantidad", "Importe", "Actualizar", "Eliminar" },
			new boolean[] {false, false, false, false, false, false, false}
		);
		tblVenta.addMouseListener(this);
		tblVenta.getColumnModel().getColumn(editColumn).setCellEditor(editor);		
		scrollPane.setViewportView(tblVenta);
		
		//Ancho de columnas
		TableColumnModel modeloColumna = tblVenta.getColumnModel();
		modeloColumna.getColumn(1).setPreferredWidth(300);

		JLabel lblNewLabel_1 = new JLabel("Total a Pagar: ");
		lblNewLabel_1.setBounds(773, 550, 88, 14);
		contentPanel.add(lblNewLabel_1);

		txtTotalPagar = new JTextField();
		txtTotalPagar.setEditable(false);
		txtTotalPagar.setBounds(774, 569, 103, 31);
		contentPanel.add(txtTotalPagar);
		txtTotalPagar.setColumns(10);

		lblFecha = new JLabel(formatoFecha.format(new Date()));
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(508, 48, 136, 17);
		contentPanel.add(lblFecha);
		
		btnModificar1 = new JButton();
		btnModificar1.setName("m");
		btnModificar1.setIcon(new ImageIcon(GuiEfectuarVenta.class.getResource("/image/editar.png")));
		btnModificar1.setBackground(Color.decode("#FFC048"));
		btnEliminar2 = new JButton();
		btnEliminar2.setName("e");
		btnEliminar2.setIcon(new ImageIcon(GuiEfectuarVenta.class.getResource("/image/eliminar.png")));
		btnEliminar2.setBackground(Color.decode("#F44336"));
		
		util = new Utilidades(
			txtCodigoCliente, txtNombreCliente, txtCodigoProducto, txtNombreProducto, txtCantidad,
			txtPrecio, txtStock, txtTotalPagar
		);
		
		txtImpIgv = new JTextField();
		txtImpIgv.setEditable(false);
		txtImpIgv.setColumns(10);
		txtImpIgv.setBounds(648, 569, 103, 31);
		contentPanel.add(txtImpIgv);
		
		JLabel lblNewLabel_1_1 = new JLabel("Importe IGV:");
		lblNewLabel_1_1.setBounds(648, 550, 88, 14);
		contentPanel.add(lblNewLabel_1_1);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setEditable(false);
		txtSubTotal.setColumns(10);
		txtSubTotal.setBounds(520, 569, 103, 31);
		contentPanel.add(txtSubTotal);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("SubTotal:");
		lblNewLabel_1_1_1.setBounds(520, 550, 88, 14);
		contentPanel.add(lblNewLabel_1_1_1);
		
		actualizarNroVenta();
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtCantidad) {
			keyTypedTxtCantidad(e);
		}
		if (e.getSource() == txtCodigoProducto) {
			keyTypedTxtCodigoProducto(e);
		}
		if (e.getSource() == txtCodigoCliente) {
			keyTypedTxtCodigoCliente(e);
		}
		if(e.getSource() == txtCantidadTable) {
			keyTypedTxtCantidadTable(e);
		}
	}
	protected void keyTypedTxtCodigoCliente(KeyEvent e) {
		char c = e.getKeyChar();
	    if(c<'0' || c>'9' ) e.consume();
	}
	protected void keyTypedTxtCodigoProducto(KeyEvent e) {
		char c = e.getKeyChar();
	    if(c<'0' || c>'9' ) e.consume();
	}
	
	protected void keyTypedTxtCantidad(KeyEvent e) {
		char c = e.getKeyChar();
	    if(c<'0' || c>'9' ) e.consume();
	}
	protected void keyTypedTxtCantidadTable(KeyEvent e) {
		char c = e.getKeyChar();
	    if(c<'0' || c>'9' ) e.consume();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
		if (e.getSource() == btnNueva) {
			actionPerformedBtnNueva(e);
		}
		if (e.getSource() == btnGenerar) {
			actionPerformedBtnGenerar(e);
		}
		if (e.getSource() == btnAnadir) {
			actionPerformedBtnAnadir(e);
		}
		if (e.getSource() == btnBuscarProducto) {
			actionPerformedBtnBuscarProducto(e);
		}
		if (e.getSource() == btnBuscarCliente) {
			actionPerformedBtnBuscarCliente(e);
		}
		actualizarTotales();
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblVenta) {
			mouseClickedTblVenta(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	
	protected void actionPerformedBtnBuscarCliente(ActionEvent e) {
		buscarCliente();
	}
	
	protected void actionPerformedBtnBuscarProducto(ActionEvent e) {
		buscarProducto();
	}
	
	protected void mouseClickedTblVenta(MouseEvent e) {
		clickTable(e);
	}
	
	protected void actionPerformedBtnAnadir(ActionEvent e) {
		anadirProducto();
	}
	
	protected void actionPerformedBtnGenerar(ActionEvent e) {
		vender();
	}
	
	protected void actionPerformedBtnNueva(ActionEvent e) {
		nuevo();
	}
	
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		dispose();
	}
	
	private void buscarCliente() {
		try {
			GuiSeleccionarCliente gcc = new GuiSeleccionarCliente(this);
			gcc.setVisible(true);

			Cliente c = gcc.getCliente();
			
			txtCodigoCliente.setText(""+c.getCodigoCliente());
			txtNombreCliente.setText(c.getNombres() + " " + c.getApellidos());
		} catch(NumberFormatException e) {
			Utilidades.errorMensaje(this, "NUMERO DEMASIADO GRANDE");
		}catch (Exception e) {
//			Utilidades.errorMensaje(this,"CODIGO DE CLIENTE NO ENCONTRADO");
		}
	}
	
	private void buscarProducto() {
		try {
			GuiSeleccionarProducto gcc = new GuiSeleccionarProducto(this);
			gcc.setVisible(true);
			
			Producto p = gcc.getProducto();
			
			txtCodigoProducto.setText(""+p.getCodigoProducto());
			txtNombreProducto.setText(p.getDescripcion());
			txtPrecio.setText("" + p.getPrecio());
			txtStock.setText("" + p.getStockActual());
			
		}catch (Exception e) {
//			Utilidades.errorMensaje(this,"PRODUCTO NO ENCONTRADO");
		}
	}

	private void anadirProducto() {
		try {
			if(!validarCodigoProducto())return;
			
			int codigo = leerCodigoProducto();
			Producto p = ArregloProductos.buscarProducto(codigo);
			
			if(Validaciones.buscarElementoEnTabla(tblVenta, codigo)) {				
				Utilidades.errorMensaje(this, "PRODUCTO YA EXISTE. ACTUALICE SU CANTIDAD");
			}else if(p==null) {
				Utilidades.errorMensaje(this, "CODIGO DE PRODUCTO NO ENCONTRADO");
			}else if(!validarCantidad(txtCantidad.getText(), p)) {
				return;
			}else {
				anadirATabla(p);
				Utilidades.informationMensaje(this,"PRODUCTO AÑADIDO A LA TABLA");
			}

		}catch(NumberFormatException e) {
			Utilidades.errorMensaje(this, "NUMERO DEMASIADO GRANDE");
		}catch (Exception e) {
			Utilidades.errorMensaje(this,"OCURRIÓ UN ERROR "+e.getMessage());
		}
	}
	
	private void clickTable(MouseEvent e) {
		int fila = tblVenta.rowAtPoint(e.getPoint());
		int columna = tblVenta.columnAtPoint(e.getPoint());

		Object value = tblVenta.getValueAt(fila, columna);

		if(value instanceof JButton) {
			((JButton) value).doClick();
			JButton boton = (JButton) value;

			//Accion del boton Actualizar
			if(boton.getName().equals("m")) {
				tblVenta.getModeloTabla().setEditable(editColumn,true);
				tblVenta.editCellAt(fila, editColumn);
				tblVenta.transferFocus();
			} 

			//Accion del boton Eliminar
			if(boton.getName().equals("e")) {
				eliminarDatos(fila);
			}
		}
	}
	
	public void actualizarDatos() throws Exception {
		int codigo = Integer.parseInt(tblVenta.getValueAt(tblVenta.getEditingRow(), 0).toString());
		Producto p = ArregloProductos.buscarProducto(codigo);
		
		// Obtener la nueva cantidad editada
        String editedValue = (String) tblVenta.getCellEditor().getCellEditorValue();
        
        if(!validarCantidad(editedValue, p)) throw new Exception();
        
        BigDecimal cantidad = new BigDecimal(editedValue);

        // Calcular el total (aquí asumo que el precio unitario está en la columna 2)
        BigDecimal precio = new BigDecimal(tblVenta.getValueAt(tblVenta.getEditingRow(), 2).toString());
        BigDecimal ip = precio.multiply(cantidad);

        // Actualizar el importe a pagar en la columna 4
        tblVenta.setValueAt(ip, tblVenta.getEditingRow(), 4); 
        tblVenta.getModeloTabla().setEditable(3,false);
        
        //Actualizar totalPagar
        actualizarTotales();
	}

	public void eliminarDatos(int fila) {
		int ok = Utilidades.confirmMensaje(this, "¿Eliminar producto de la tabla?");
		if(ok == 0) {			
			tblVenta.getModeloTabla().removeRow(fila);
			actualizarTotales();
			Utilidades.informationMensaje(this, "Producto eliminado");
		}
	}
	
	private void vender() {
		try {
			int ok = Utilidades.confirmMensaje(this, "¿Confirmar venta?");
			if(ok == 0) {
				
				TableCellEditor editor = tblVenta.getCellEditor();
	            if (editor != null) {
	            	Utilidades.errorMensaje(this, "TERMINE DE INGRESAR LA CANTIDAD");
	                return;
	            }
	            int codigoVendedor = leerCodigoVendedor();
	            int codigoFactura = ArregloFacturas.codigoCorrelativo();
				
				for(int i=0; i<tblVenta.getRowCount();i++) {
					int codigoDetalle = ArregloDetalles.codigoCorrelativo();
					int codigoProducto = Integer.parseInt(tblVenta.getValueAt(i, 0).toString());
					String descripcion = tblVenta.getValueAt(i, 1).toString();
					double precio = (new BigDecimal(tblVenta.getValueAt(i, 2).toString())).doubleValue();
					int cantidad = (new BigDecimal(tblVenta.getValueAt(i, 3).toString())).intValue();
					ArregloDetalles.ingresarDetalles(new ItemDetalleVenta(codigoDetalle, codigoProducto, codigoFactura, descripcion, precio, cantidad));
					
					//Actualizar stock
					ArregloProductos.disminuirStockProducto(codigoProducto, cantidad);
				}
				
				int codigoCliente = leerCodigoCliente();
				String fecha = formatoFecha.format(new Date());
				/*POR AHORA PONDRE QUE EL VENDEDOR SIEMPRE SEA EL NUMERO 1001 PORQUE TODAVIA NO ESTA EL LOGIN*/
				ArregloFacturas.ingresarFacturas(new Factura(codigoFactura, codigoCliente, codigoVendedor, fecha, 0, ""));
				
				Utilidades.informationMensaje(this, "VENTA REGISTRADA");
				util.limpiarCajas();
				Utilidades.limpiarTabla(tblVenta.getModeloTabla());
				actualizarNroVenta();
			}
		}catch(MensajeException e) {
			Utilidades.errorMensaje(this, e.getMessage());
		}catch (Exception e) {
			Utilidades.errorMensaje(this, "ERROR AL REGISTRAR. VERIFIQUE CAMPOS"+e.getMessage()); 
		}
		
	}
	
	private void nuevo() {
		util.limpiarCajas();
		Utilidades.limpiarTabla(tblVenta.getModeloTabla());
		actualizarNroVenta();
		Utilidades.informationMensaje(this, "BOLETA DE VENTA LIMPIA");
	}
	
	private void actualizarNroVenta() {
		int nroVenta = ArregloFacturas.getListaFacturas().size()+1;
		lblNumeroVenta.setText("N\u00B0 "+String.format("%06d", nroVenta));
	}
	
	private boolean validarCantidad(String cantidad, Producto p) {
		if(cantidad.isEmpty()) {
			Utilidades.errorMensaje(this, "INGRESE UNA CANTIDAD");
			return false;
		}else if(!(cantidad.matches(exrNumber))) {
			Utilidades.errorMensaje(this, "LA CANTIDAD NO ES VALIDA");
			return false;
		}else if(Integer.parseInt(cantidad)==0) {
			Utilidades.errorMensaje(this, "LA CANTIDAD DEBE SER MAYOR A O");
			return false;
		} else if(Integer.parseInt(cantidad)>p.getStockActual()){
			Utilidades.errorMensaje(this, "<html><center>LA CANTIDAD SUPERA AL STOCK DISPONIBLE<br>"
											+ "DEL PRODUCTO "+p.getCodigoProducto()+"<center><html>");
			return false;
		}else {
			return true;
		}
	}
	
	private boolean validarCodigoCliente() {
		String codigo = txtCodigoCliente.getText();
		if(codigo.isEmpty()) {
			Utilidades.errorMensaje(this, "INGRESE EL CODIGO DEL CLIENTE");
			return false;
		}else if(!codigo.matches(exrNumber)) {
			Utilidades.errorMensaje(this, "CODIGO DE CLIENTE NO VALIDO");
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validarCodigoProducto() {
		String codigo = txtCodigoProducto.getText();
		if(codigo.isEmpty()) {
			Utilidades.errorMensaje(this, "INGRESE EL CODIGO DEL PRODUCTO");
			return false;
		}else if(!codigo.matches(exrNumber)) {
			Utilidades.errorMensaje(this, "CODIGO DE PRODUCTO NO VALIDO");
			return false;
		} else {
			return true;
		}
	}
	
	private void anadirATabla(Producto p) {
		int codigo = p.getCodigoProducto();
		String descripcion = p.getDescripcion();
		BigDecimal precio = new BigDecimal("" + p.getPrecio());
		BigDecimal cantidad = new BigDecimal("" + leerCantidad());
		BigDecimal ip = precio.multiply(cantidad);
		
		/* Añado fila(producto) a la tabla */
		tblVenta.getModeloTabla().addRow(new Object[] { codigo, descripcion, precio, cantidad, ip, btnModificar1, btnEliminar2 });
	}
	
	private BigDecimal calcularSubTotal() {
		BigDecimal totalPagar = new BigDecimal("0.0");
	    for(int i=0; i<tblVenta.getRowCount(); i++){
	    	BigDecimal ip = new BigDecimal(tblVenta.getValueAt(i, 4).toString());
	    	totalPagar = totalPagar.add(ip);
	    }
	    
	    return totalPagar;
	}
	
	private BigDecimal calcularImporteIgv() {
		BigDecimal igv = new BigDecimal(""+Utilidades.getIGV());
		return calcularSubTotal().multiply(igv);
	}
	
	private BigDecimal calcularImportePagar() {
		return calcularSubTotal().add(calcularImporteIgv());
	}
	
	private void actualizarTotales() {
		txtSubTotal.setText(calcularSubTotal().toString());
		txtImpIgv.setText(calcularImporteIgv().toString());
		txtTotalPagar.setText(calcularImportePagar().toString());
	}
	
	private int leerCodigoVendedor() throws MensajeException {
		if (user == null) {
	        throw new MensajeException("USUARIO NO ENCONTRADO");
	    }

	    Vendedor v = ArregloVendedores.buscarVendedor(user.getCodigoVendedor());
	    if (v != null) {
	        return v.getCodigoVendedor();
	    } else {
	        throw new MensajeException("VENDEDOR NO ENCONTRADO");
	    }
	}

	private int leerCodigoCliente() {
		return Integer.parseInt(txtCodigoCliente.getText());
	}

	private int leerCodigoProducto() {
		return Integer.parseInt(txtCodigoProducto.getText());
	}

	private int leerCantidad() {
		return Integer.parseInt(txtCantidad.getText());
	}
}