package operaciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entidades.Cliente;
import entidades.Vendedor;

public class ArregloVendedores {
	private static ArrayList<Vendedor> listaVendedores = new ArrayList<>();
	private static String[] categorias = {"Administrador", "Cajero", "Farmaceutico"};
	private static String rutaArchivo = "src/data/empleados.txt";
	
	static {
		cargarDatos();
	}
	
	public static String[] getCategorias() {
		return categorias;
	}

	public static ArrayList<Vendedor> getListaVendedores() {
		return listaVendedores;
	}
	
	public static void ingresarVendedores(Vendedor p) {
		listaVendedores.add(p);
		guardarDatos();
	}
	
	public static void actualizarVendedor(Vendedor n) {
		Vendedor p = buscarVendedor(n.getCodigoVendedor());
		if (p != null) {
			p.setNombres(n.getNombres());
			p.setCategoria(n.getCategoria());
			p.setApellidos(n.getApellidos());
			p.setTelefono(n.getTelefono());
			p.setDni(n.getDni());
			guardarDatos();
		}else {
	        System.out.println("Registro no encontrado con ID: " + n.getCodigoVendedor());
	    }
	}
	
	public static void eliminarVendedores(Vendedor p) {
		listaVendedores.remove(p);
		guardarDatos();
	}
	
	public static int tamanio() {
		return listaVendedores.size();
	}
	
	public static Vendedor obtenerVendedor(int x) {
		return listaVendedores.get(x);
	}
	
	public static Vendedor buscarVendedor(int codigo) {
		for(Vendedor p: listaVendedores) {
			if(p.getCodigoVendedor() == codigo)
				return p;
		}
		return null;
	}
	
	public static int codigoCorrelativo() {
		if(tamanio() == 0)
			return 1001;
		else {
			return obtenerVendedor(tamanio()-1)
					.getCodigoVendedor()+1;
		}
	}
	
	public static void cargarDatos() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
			String linea;
			while((linea = br.readLine())!= null) {
				StringTokenizer mitokens = new StringTokenizer(linea, "\t");
				int codigo = Integer.parseInt(mitokens.nextToken());
				int cate = Integer.parseInt(mitokens.nextToken());
				String nombres = mitokens.nextToken();
				String apellidos = mitokens.nextToken();
				String telefono = mitokens.nextToken();
				String dni = mitokens.nextToken();
				
				ingresarVendedores(new Vendedor(codigo, cate, nombres, apellidos, telefono, dni));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void guardarDatos() {
		try {
			FileWriter wr = new FileWriter(rutaArchivo);
			for(Vendedor v: listaVendedores) {
				String cod = v.getCodigoVendedor()+"\t";
				String cate = v.getCategoria()+"\t";
				String nom = v.getNombres()+"\t";
				String ape = v.getApellidos()+"\t";
				String tel = v.getTelefono()+"\t";
				String dni = v.getDni()+"\r\n";
				
				wr.write(cod+cate+nom+ape+tel+dni);
			}
			wr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
