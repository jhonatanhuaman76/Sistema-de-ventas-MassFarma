package operaciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entidades.Cliente;
import entidades.Factura;
import entidades.ItemDetalleVenta;

public class ArregloFacturas {
	private static ArrayList<Factura> listaFacturas = new ArrayList<>();
	public static final String[] ESTADO = new String[]{"PROCESADO", "CANCELADO"};
	private static final String rutaArchivo = "src/data/facturas.txt";
	
	static {
		cargarDatos();
	}

	public static ArrayList<Factura> getListaFacturas() {
		return listaFacturas;
	}
	
	public static void ingresarFacturas(Factura p) {
		listaFacturas.add(p);
		guardarDatos();
	}
	
	public static void actualizarEstadoFacturas(int codFact, int estado, String obs) {
		Factura p = buscarFactura(codFact);
		if (p != null) {
			p.setEstado(estado);
			p.setObservacion(obs);
			guardarDatos();
		}else {
	        System.out.println("Registro no encontrado con ID: " + codFact);
	    }
	}
	
	public static void eliminarFacturas(Factura p) {
		listaFacturas.remove(p);
	}
	
	public static int tamanio() {
		return listaFacturas.size();
	}
	
	public static Factura obtenerFactura(int x) {
		return listaFacturas.get(x);
	}
	
	public static Factura buscarFactura(int codigo) {
		for(Factura p: listaFacturas) {
			if(p.getCodigoFactura() == codigo)
				return p;
		}
		return null;
	}
	
	public static ArrayList<Factura> buscarFacturasVendedor(int codigo) {
		ArrayList<Factura> f = new ArrayList<>();
		for(Factura p: listaFacturas) 
			if(p.getCodigoVendedor() == codigo)
				f.add(p);
		
		return f;
	}
	
	public static ArrayList<Factura> buscarFacturasProcesadasVendedor(int codigo) {
		ArrayList<Factura> f = new ArrayList<>();
		for(Factura p: listaFacturas) 
			if(p.getCodigoVendedor() == codigo && p.getEstado() == 0)
				f.add(p);
		
		return f;
	}
	
	public static ArrayList<Factura> buscarFacturasCanceladasVendedor(int codigo) {
		ArrayList<Factura> f = new ArrayList<>();
		for(Factura p: listaFacturas) 
			if(p.getCodigoVendedor() == codigo && p.getEstado() == 1)
				f.add(p);
		
		return f;
	}
	
	public static ArrayList<Factura> buscarFacturasCliente(int codigo) {
		ArrayList<Factura> f = new ArrayList<>();
		for(Factura p: listaFacturas) 
			if(p.getCodigoCliente() == codigo)
				f.add(p);
		
		return f;
	}
	
	public static ArrayList<Factura> buscarFacturasProcesadasCliente(int codigo) {
		ArrayList<Factura> f = new ArrayList<>();
		for(Factura p: listaFacturas) 
			if(p.getCodigoCliente() == codigo && p.getEstado() == 0)
				f.add(p);
		
		return f;
	}
	
	public static ArrayList<Factura> buscarFacturasCanceladasCliente(int codigo) {
		ArrayList<Factura> f = new ArrayList<>();
		for(Factura p: listaFacturas) 
			if(p.getCodigoCliente() == codigo && p.getEstado() == 1)
				f.add(p);
		
		return f;
	}
	
	public static ArrayList<Factura> buscarFacturasProducto(int codigo){
		ArrayList<Factura> f = new ArrayList<>();
		for(Factura p: listaFacturas) {
			ArrayList<ItemDetalleVenta> items = p.getItemsDetalles();
			for(ItemDetalleVenta i: items) {
				if(i.getCodProducto() == codigo) {
					f.add(p);
					break;
				}
			}
		}
		return f;
	}
	
	public static ArrayList<Factura> buscarFacturasProcesadasProducto(int codigo){
		ArrayList<Factura> f = new ArrayList<>();
		for(Factura p: listaFacturas) {
			ArrayList<ItemDetalleVenta> items = p.getItemsDetalles();
			for(ItemDetalleVenta i: items) {
				if(i.getCodProducto() == codigo && p.getEstado() == 0) {
					f.add(p);
					break;
				}
			}
		}
		return f;
	}
	
	public static ArrayList<Factura> buscarFacturasCanceladasProducto(int codigo){
		ArrayList<Factura> f = new ArrayList<>();
		for(Factura p: listaFacturas) {
			ArrayList<ItemDetalleVenta> items = p.getItemsDetalles();
			for(ItemDetalleVenta i: items) {
				if(i.getCodProducto() == codigo && p.getEstado() == 1) {
					f.add(p);
					break;
				}
			}
		}
		return f;
	}
	
	public static ArrayList<Factura> getListaFacturasProcesadas(){
		ArrayList<Factura> f = new ArrayList<>();
		for(Factura p: listaFacturas)
			if(p.getEstado() == 0)
				f.add(p);
		return f;
	}
	
	public static ArrayList<Factura> getListaFacturasCanceladas(){
		ArrayList<Factura> f = new ArrayList<>();
		for(Factura p: listaFacturas)
			if(p.getEstado() == 1)
				f.add(p);
		return f;
	}
	
	public static int getIndiceEstado(String e) {
		for(int i=0; i< ESTADO.length;i++) 
			if(ESTADO[i] == e)
				return i;
		
		return -1;
	}
	
	public static int codigoCorrelativo() {
		if(tamanio() == 0)
			return 1001;
		else {
			return obtenerFactura(tamanio()-1)
					.getCodigoFactura()+1;
		}
	}
	
	public static void cargarDatos() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
			String linea;
			while((linea = br.readLine())!= null) {
				StringTokenizer mitokens = new StringTokenizer(linea, "\t");
				int codFact = Integer.parseInt(mitokens.nextToken());
				int codClie = Integer.parseInt(mitokens.nextToken());
				int codVend = Integer.parseInt(mitokens.nextToken());
				String fecha = mitokens.nextToken();
				int estado = Integer.parseInt(mitokens.nextToken());
				String obs = mitokens.hasMoreTokens() ? mitokens.nextToken().trim() : "";
				
				ingresarFacturas(new Factura(codFact, codClie, codVend, fecha, estado, obs));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void guardarDatos() {
		try {
			FileWriter wr = new FileWriter(rutaArchivo);
			for(Factura f: listaFacturas) {
				String codFact = f.getCodigoFactura()+"\t";
				String codClie = f.getCodigoCliente()+"\t";
				String codVend = f.getCodigoVendedor()+"\t";
				String fecha = f.getFecha()+"\t";
				String estado = f.getEstado()+"\t";
				String obs = f.getObservacion()+"\r\n";
				
				wr.write(codFact+codClie+codVend+fecha+estado+obs);
			}
			wr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
