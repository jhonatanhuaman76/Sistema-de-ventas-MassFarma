package gui.reportes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import entidades.ItemDetalleVenta;
import libreria.TablaButtonDetalle;
import libreria.TablaGeneral;
import libreria.Utilidades;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GuiDetalleFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private TablaGeneral table;
	private JScrollPane scrollPane;
	private ArrayList<ItemDetalleVenta> items;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiDetalleFactura dialog = new GuiDetalleFactura(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiDetalleFactura(JDialog gcc, ArrayList<ItemDetalleVenta> items) {
		super(gcc, true);
		setResizable(false);
		setTitle("Detalle Factura");
		setBounds(0, 0, 551, 390);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		this.items = items;
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 508, 310);
		contentPanel.add(scrollPane);
		
		table = new TablaGeneral(
			new Object[][] {},
			new String[] {"Codigo Producto", "Descripcion", "Precio", "Cantidad", "Importe"},
			new boolean[] {false, false, false, false, false}
		);
		
		//Ancho de columnas
		TableColumnModel modeloColumna = table.getColumnModel();
		modeloColumna.getColumn(1).setPreferredWidth(300);
		
		scrollPane.setViewportView(table);
		llenarTabla();
	}
	
	private void llenarTabla() {
		Utilidades.limpiarTabla(table.getModeloTabla());
		for(ItemDetalleVenta item: items) {
			int codigoProducto = item.getCodProducto();
			String descripcion = item.getDescripcion();
			double precio = item.getPrecioUnitario();
			int cantidad = item.getCantidad();
			double ip = item.importe();
			
			table.getModeloTabla().addRow(new Object[] {codigoProducto, descripcion, precio , cantidad, ip});
		}
	}
}
