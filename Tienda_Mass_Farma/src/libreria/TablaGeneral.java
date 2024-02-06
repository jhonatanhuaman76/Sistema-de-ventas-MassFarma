package libreria;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.JTableHeader;

/*PERSONALIZANDO LOS JTABLE BASE*/
public class TablaGeneral extends JTable {
	private ModeloTabla modelo;
	private CellRenderComponents render;
	
	public TablaGeneral(Object[][] data, Object[]columnas, boolean[] editables) {
		modelo = new ModeloTabla(data, columnas, editables);
		super.setModel(modelo);
		configuraciones();
		setupRender();
	}
	
	/*RENDERIZADO ESPECIAL A LAS CELDAS*/
	private void setupRender() {
		render = new CellRenderComponents();
		setDefaultRenderer(Object.class, render);
	}
	
	public CellRenderComponents getRenderTabla() {
		return render;
	}
	
	public ModeloTabla getModeloTabla() {
		return modelo;
	}
	
	/*CONFIGURANDO ALGUNOS ESTILOS*/
	private void configuraciones() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setRowHeight(25);
		setCellSelectionEnabled(true);
		
		 //Deshabilitamos el mover las columnas y el cambiar su tamanio
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);
        
        //Aplicamos un estilo personalizado al encabezado de la tabla
        tableHeader.setDefaultRenderer(new MiHeaderRender());
        tableHeader.setPreferredSize( new Dimension(0,40));
        
        
        //Aplicamos un estilo personalizadoa  las celdas
        setRowHeight(35);
	}
}
