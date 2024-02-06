package libreria;

import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel {
	private boolean[] editables;
	
	public ModeloTabla(Object[][]data, Object[]column, boolean[]editables) {
		super(data, column);
		 if (column.length != editables.length) {
	            throw new IllegalArgumentException("El número de columnas debe ser igual al número de valores editables.");
	     }
		this.editables = editables;
	}
	public boolean[] getEditables() {
		return editables;
	}
	public void setEditable(int index, boolean editable) {
		this.editables[index] = editable;
	}
	@Override
	public boolean isCellEditable(int row, int column) {
		return editables[column];
	}
}
