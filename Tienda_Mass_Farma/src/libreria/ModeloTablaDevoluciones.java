package libreria;

import gui.ventas.GuiDevoluciones;
import operaciones.ArregloFacturas;

public class ModeloTablaDevoluciones extends ModeloTabla {
	private boolean[] editables;
	
	public ModeloTablaDevoluciones(Object[][] data, Object[] columnas, boolean[]editables) {
		super(data, columnas, editables);
		this.editables = editables;
	}

	@Override
	public boolean[] getEditables() {
		return editables;
	}
	@Override
	public void setEditable(int index, boolean editable) {
		this.editables[index] = editable;
	}
	@Override
	public boolean isCellEditable(int row, int column) {

		// Verifica la condición para cada celda individualmente
		if (column == GuiDevoluciones.editObservacion) { // Columna 7
			Object valorColumna6 = getValueAt(row, GuiDevoluciones.editCombo); // Valor de la columna 6 en la fila actual
//			System.out.println(valorColumna6);
			// Verifica la condición específica para permitir la edición de la celda en la columna 7
			if (valorColumna6 != null && valorColumna6.equals(ArregloFacturas.ESTADO[1])) {
				return true; // La celda en la columna 7 es editable si la condición se cumple
			}
		}
		
		if(column == GuiDevoluciones.editCombo) {
			Object valorColumna6 = getValueAt(row, GuiDevoluciones.editCombo); // Valor de la columna 6 en la fila actual
//			System.out.println(valorColumna6);
		}

		return editables[column];
	}
}
