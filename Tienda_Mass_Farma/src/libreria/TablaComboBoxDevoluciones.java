package libreria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;

import gui.ventas.GuiDevoluciones;
import operaciones.ArregloFacturas;

public class TablaComboBoxDevoluciones extends TablaButtonDetalle{
	private JComboBox cboEstado;
	private int editCombo;
	private DefaultCellEditor editor;
	private ModeloTablaDevoluciones modelo;
	 
	 /*CONTRUCTOR DE TABLA COMBO SIN EVENTO CLICK DE DETALLE*/
	 public TablaComboBoxDevoluciones(Object[][]data, Object[]column, boolean[]editables, int editCombo) {
		 super(data, column, editables);
		 modelo = new ModeloTablaDevoluciones(data, column, editables);
		 setModel(modelo);
		 inicializarCombo(editCombo);
	 }

	/*CONSTRUCTOR DE TABLA COMBO CON EVENTO CLICK DE DETALLE*/
	public TablaComboBoxDevoluciones(Object[][] data, Object[] column, boolean[] editables, JDialog gcc, int editCombo) {
		super(data, column, editables, gcc);
		modelo = new ModeloTablaDevoluciones(data, column, editables);
		setModel(modelo);
		inicializarCombo(editCombo);
	}
	
	@Override
	public ModeloTablaDevoluciones getModeloTabla() {
		return modelo;
	}

	private void inicializarCombo(int editCombo) {
		this.editCombo = editCombo;
		cboEstado = new JComboBox(ArregloFacturas.ESTADO);
		editor = new DefaultCellEditor(cboEstado) {
			//Actualizar datos al terminar de escribir en la celda de cantidad
			@Override
		    public boolean stopCellEditing() {
				Object valorColumna6 = getCellEditorValue(); // Valor de la columna 6 en la fila actual
				
				// Verifica la condición específica para permitir la edición de la celda en la columna 7
				if (valorColumna6 != null && valorColumna6.equals(ArregloFacturas.ESTADO[0]) && getEditingRow() != -1) {
					setValueAt("", getEditingRow(), GuiDevoluciones.editObservacion);
				}
		        return super.stopCellEditing();
		    }
		};
		getColumnModel().getColumn(editCombo).setCellEditor(editor);
	}
	
	private JComboBox getCboEstado() {
		return cboEstado;
	}
}