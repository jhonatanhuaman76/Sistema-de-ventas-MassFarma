package operaciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entidades.ItemDetalleVenta;
import entidades.Vendedor;

public class ArregloDetalles {
	private static ArrayList<ItemDetalleVenta> items = new ArrayList<>();
	private static String rutaArchivo = "src/data/detalles.txt";
	
	static {
		cargarDatos();
	}
	
	public static ArrayList<ItemDetalleVenta> getListaDetalles(){
		return items;
	}
	
	public static void ingresarDetalles(ItemDetalleVenta p) {
		items.add(p);
		guardarDatos();
	}
	
	public static int tamanio() {
		return items.size();
	}
	
	public static ItemDetalleVenta obtenerDetalle(int x) {
		return items.get(x);
	}
	
	public static ArrayList<ItemDetalleVenta> buscarDetallesFactura(int codFact) {
		ArrayList<ItemDetalleVenta> detalles = new ArrayList<>();
		for(ItemDetalleVenta p: items) 
			if(p.getCodFactura()== codFact) 
				detalles.add(p);
		
		return detalles;
	}
	
	public static int codigoCorrelativo() {
		if(tamanio() == 0)
			return 1001;
		else {
			return obtenerDetalle(tamanio()-1)
					.getCodDetalle()+1;
		}
	}
	
	public static void cargarDatos() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
			String linea;
			while((linea = br.readLine())!= null) {
				StringTokenizer mitokens = new StringTokenizer(linea, "\t");
				int codDet = Integer.parseInt(mitokens.nextToken());
				int codPro = Integer.parseInt(mitokens.nextToken());
				int codFact = Integer.parseInt(mitokens.nextToken());
				String des = mitokens.nextToken();
				double preUni = Double.parseDouble(mitokens.nextToken());
				int canti = Integer.parseInt(mitokens.nextToken());
				
				ingresarDetalles(new ItemDetalleVenta(codDet, codPro, codFact, des, preUni, canti));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void guardarDatos() {
		try {
			FileWriter wr = new FileWriter(rutaArchivo);
			for(ItemDetalleVenta v: items) {
				String codDet = v.getCodDetalle()+"\t";
				String codPro = v.getCodProducto()+"\t";
				String codFact = v.getCodFactura()+"\t";
				String des = v.getDescripcion()+"\t";
				String preUni = v.getPrecioUnitario()+"\t";
				String canti = v.getCantidad()+"\r\n";
				
				wr.write(codDet+codPro+codFact+des+preUni+canti);
			}
			wr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
