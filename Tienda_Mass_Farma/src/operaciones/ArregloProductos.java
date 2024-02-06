package operaciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entidades.Cliente;
import entidades.Producto;

public class ArregloProductos {
	private static ArrayList<Producto> listaProductos = new ArrayList<>();
	private static String[] categorias = {"CUIDADO PERSONAL Y BELLEZA", "MAMA Y BEBE", "MEDICINAS Y TRATAMIENTOS", "PRIMEROS AUXILIOS", "VITAMINAS Y SUPLEMENTOS", "FITNESS Y NUTRICION"};
	private static String rutaArchivo = "src/data/productos.txt";
	
	static {
		cargarDatos();
	}
	
	public static String[] getCategorias() {
		return categorias;
	}

	public static ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}
	
	public static void ingresarProductos(Producto p) {
		listaProductos.add(p);
		guardarDatos();
	}
	
	public static void actualizarProductos(Producto n) {
		Producto p = buscarProducto(n.getCodigoProducto());
		if(p!=null) {
			p.setDescripcion(n.getDescripcion());
			p.setPrecio(n.getPrecio());
			p.setCategoria(n.getCategoria());
			p.setStockMaximo(n.getStockMaximo());
			p.setMarca(n.getMarca());
			guardarDatos();
		}else {
//			System.out.println("Registro no encontrado con ID: " + n.getCodigoProducto());
		}
	}
	
	public static void eliminarProductos(Producto p) {
		listaProductos.remove(p);
		guardarDatos();
	}
	
	public static void aumentarStockProducto(int codigo, int cantidad){
		Producto p = buscarProducto(codigo);
		if(p!=null) {
			p.setStockActual(p.getStockActual()+cantidad);
			guardarDatos();
		}
	}
	
	public static void disminuirStockProducto(int codigo, int cantidad){
		Producto p = buscarProducto(codigo);
		if(p!=null) {
			p.setStockActual(p.getStockActual()-cantidad);
			guardarDatos();
		}
	}
	
	public static int tamanio() {
		return listaProductos.size();
	}
	
	public static Producto obtenerProducto(int x) {
		return listaProductos.get(x);
	}
	
	public static Producto buscarProducto(int codigo) {
		for(Producto p: listaProductos) {
			if(p.getCodigoProducto() == codigo)
				return p;
		}
		return null;
	}
	
	public static double precioPromedioProductos() {
		double suma = 0.0;
		
		for(Producto p: listaProductos)
			suma+=p.getPrecio();
		
		return suma/tamanio();
	}
	
	public static double precioMayor() {
		double precioMayor = listaProductos.get(0).getPrecio();
		for(Producto p:listaProductos)
			if(p.getPrecio()>precioMayor)
				precioMayor = p.getPrecio();
		
		return precioMayor;
	}
	
	public static Producto productoPrecioMayor() {
		double precioMayor = listaProductos.get(0).getPrecio();
		Producto pMayor = listaProductos.get(0);
		for(Producto p:listaProductos) {
			if(p.getPrecio()>precioMayor) {
				precioMayor = p.getPrecio();
				pMayor = p;
			}
		}
		
		return pMayor;
	}
	
	public static double precioMenor() {
		double precioMenor = listaProductos.get(0).getPrecio();
		for(Producto p: listaProductos)
			if(p.getPrecio()<precioMenor)
				precioMenor = p.getPrecio();
		return precioMenor;
	}
	
	public static Producto productoPrecioMenor() {
		double precioMenor = listaProductos.get(0).getPrecio();
		Producto pMenor = listaProductos.get(0);
		for(Producto p:listaProductos) {
			if(p.getPrecio()<precioMenor) {
				precioMenor = p.getPrecio();
				pMenor = p;
			}
		}
		
		return pMenor;
	}
	
	public static int codigoCorrelativo() {
		if(tamanio() == 0)
			return 1001;
		else {
			return obtenerProducto(tamanio()-1)
					.getCodigoProducto()+1;
		}
	}
	
	public static void cargarDatos() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
			String linea;
			while((linea = br.readLine())!= null) {
				StringTokenizer mitokens = new StringTokenizer(linea, "\t");
				int codigo = Integer.parseInt(mitokens.nextToken());
				String des = mitokens.nextToken();
				double pre = Double.parseDouble(mitokens.nextToken());
				int cate = Integer.parseInt(mitokens.nextToken());
				int stockA = Integer.parseInt(mitokens.nextToken());
				int stockMax = Integer.parseInt(mitokens.nextToken());
				String marca = mitokens.nextToken();
				
				ingresarProductos(new Producto(codigo, des, pre, cate, stockA, stockMax, marca));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void guardarDatos() {
		try {
			FileWriter wr = new FileWriter(rutaArchivo);
			for(Producto p: listaProductos) {
				String cod = p.getCodigoProducto()+"\t";
				String des = p.getDescripcion()+"\t";
				String pre = p.getPrecio()+"\t";
				String cate = p.getCategoria()+"\t";
				String stockA = p.getStockActual()+"\t";
				String stockMax = p.getStockMaximo()+"\t";
				String marca = p.getMarca()+"\r\n";
				
				wr.write(cod+des+pre+cate+stockA+stockMax+marca);
			}
			wr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
