package operaciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entidades.Cliente;

public class ArregloClientes {
	private static ArrayList<Cliente> listaClientes = new ArrayList<>();
	private static String rutaArchivo = "src/data/clientes.txt";
	
	static {
		cargarDatos();
	}

	public static ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}
	
	public static void ingresarClientes(Cliente p) {
		listaClientes.add(p);
		guardarDatos();
	}
	
	public static void actualizarCliente(Cliente n) {
		Cliente p = buscarCliente(n.getCodigoCliente());
		if (p != null) {
			p.setNombres(n.getNombres());
			p.setApellidos(n.getApellidos());
			p.setTelefono(n.getTelefono());
			p.setDni(n.getDni());
			guardarDatos();
		}else {
	        System.out.println("Registro no encontrado con ID: " + n.getCodigoCliente());
	    }
	}
	
	public static void eliminarClientes(Cliente p) {
		listaClientes.remove(p);
		guardarDatos();
	}
	
	public static int tamanio() {
		return listaClientes.size();
	}
	
	public static Cliente obtenerCliente(int x) {
		return listaClientes.get(x);
	}
	
	public static Cliente buscarCliente(int codigo) {
		for(Cliente p: listaClientes) {
			if(p.getCodigoCliente() == codigo)
				return p;
		}
		return null;
	}
	
	public static int codigoCorrelativo() {
		if(tamanio() == 0)
			return 1001;
		else {
			return obtenerCliente(tamanio()-1)
					.getCodigoCliente()+1;
		}
	}
	
	public static void cargarDatos() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
			String linea;
			while((linea = br.readLine())!= null) {
				StringTokenizer mitokens = new StringTokenizer(linea, "\t");
				int codigo = Integer.parseInt(mitokens.nextToken());
				String nombres = mitokens.nextToken();
				String apellidos = mitokens.nextToken();
				String telefono = mitokens.nextToken();
				String dni = mitokens.nextToken();
				
				ingresarClientes(new Cliente(codigo, nombres, apellidos, telefono, dni));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void guardarDatos() {
		try {
			FileWriter wr = new FileWriter(rutaArchivo);
			for(Cliente c: listaClientes) {
				String cod = c.getCodigoCliente()+"\t";
				String nom = c.getNombres()+"\t";
				String ape = c.getApellidos()+"\t";
				String tel = c.getTelefono()+"\t";
				String dni = c.getDni()+"\r\n";
				
				wr.write(cod+nom+ape+tel+dni);
			}
			wr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
