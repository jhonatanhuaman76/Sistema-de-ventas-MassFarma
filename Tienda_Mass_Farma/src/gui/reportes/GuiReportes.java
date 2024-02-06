package gui.reportes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import entidades.Producto;
import libreria.Utilidades;
import operaciones.ArregloProductos;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class GuiReportes extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JComboBox cboReporte;
	private JLabel lblNewLabel;
	private JPanel panel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuiReportes dialog = new GuiReportes(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiReportes(JFrame gcc) {
		super(gcc, true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiReportes.class.getResource("/image/logo.png")));
		setTitle("Reportes");
		setResizable(false);
		setBounds(100, 100, 823, 532);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Utilidades.aplicarFlatLaft();
		
		cboReporte = new JComboBox();
		cboReporte.addActionListener(this);
		cboReporte.setModel(new DefaultComboBoxModel(new String[] {"Reporte general por productos", "Reporte general por vendedores", "Reporte por vendedor", "Reporte por producto","Reporte de precios"}));
		cboReporte.setBounds(194, 16, 603, 35);
		contentPanel.add(cboReporte);
		
		lblNewLabel = new JLabel("Selecciona el tipo de reporte: ");
		lblNewLabel.setBounds(10, 26, 174, 14);
		contentPanel.add(lblNewLabel);
		
		panel = new ReporteGeneralProductos();
		panel.setBounds(10, 61, 787, 421);
		contentPanel.add(panel);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cboReporte) {
			actionPerformedComboBox(e);
		}
	}
	protected void actionPerformedComboBox(ActionEvent e) {
		cboReporte();
	}
	
	private void cboReporte() {
		int index = cboReporte.getSelectedIndex();
		switch(index) {
		case 0:
			ReporteGeneralProductos p0 = new ReporteGeneralProductos();
			cambiarPanel(p0);
			break;
		case 1:
			ReporteGeneralVendedores p1 = new ReporteGeneralVendedores();
			cambiarPanel(p1);
			break;
		case 2:
			ReportePorVendedor p2 = new ReportePorVendedor(this);
			cambiarPanel(p2);
			break;
		case 3:
			ReportePorProducto p3 = new ReportePorProducto(this);
			cambiarPanel(p3);
			break;
		case 4:
			ReportePrecios p4 = new ReportePrecios();
			cambiarPanel(p4);
			break;
		}
	}
	
	private void cambiarPanel(JPanel panelActual) {
		panel.removeAll();
	    panel.add(panelActual);
	    panel.repaint();
	    panel.revalidate();
	}
}
