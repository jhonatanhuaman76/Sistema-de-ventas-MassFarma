package libreria;

import javax.swing.JTable;

public class Validaciones {
	public static boolean buscarElementoEnTabla(JTable table, int cod) {
	    for (int i = 0; i < table.getRowCount(); i++) 
	        if ((int) table.getValueAt(i, 0) == cod) 
	            return true;
	    
	    return false;
	}

}
