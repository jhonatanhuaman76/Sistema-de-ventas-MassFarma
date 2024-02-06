package operaciones;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entidades.Usuario;
import entidades.Vendedor;
import libreria.Utilidades;
import entidades.Usuario;

public class ArregloUsuarios {
	private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	private static String rutaArchivo = "src/data/usuarios.txt";
	
	static {
		cargarDatos();
	}
	
	public static ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	
	public static void ingresarUsuarios(Usuario p) {
		listaUsuarios.add(p);
		guardarDatos();
	}
	
	public static void actualizarUsuario(Usuario n) {
		Usuario p = buscarUsuario(n.getCodigoUsuario());
		if (p != null) {
			p.setUser(n.getUser());
			p.setPass(n.getPass());
			guardarDatos();
		}else {
	        System.out.println("Registro no encontrado con ID: " + n.getCodigoUsuario());
	    }
	}
	
	public static void eliminarUsuarios(Usuario p) {
		listaUsuarios.remove(p);
		guardarDatos();
	}
	
	public static int tamanio() {
		return listaUsuarios.size();
	}
	
	public static Usuario obtenerUsuario(int x) {
		return listaUsuarios.get(x);
	}
	
	public static Usuario buscarUsuario(int codigo) {
		for(Usuario p: listaUsuarios) {
			if(p.getCodigoUsuario() == codigo)
				return p;
		}
		return null;
	}
	
	public static Usuario buscarUsuarioNombre(String user) {
		for(Usuario p: listaUsuarios)
			if(p.getUser().equals(user))
				return p;
		return null;
	}
	
	public static Usuario buscarUsuarioVendedor(int codigo) {
		for(Usuario p: listaUsuarios)
			if(p.getCodigoVendedor() == codigo)
				return p;
		return null;
	}
	
	public static boolean validarUsuario(String user, String pass) {
		for(Usuario p: listaUsuarios)
			if(p.getUser().equals(user)&&p.getPass().equals(pass))
				return true;
		return false;
	}
	
	public static boolean validarUsuarioAdmin(Usuario user) {
		if(user!=null) {
			Vendedor p = ArregloVendedores.buscarVendedor(user.getCodigoVendedor());
			if(p!=null && p.getCategoria()==0 )
				return true;
		}
		
//		Utilidades.errorMensaje(gcc, "No eres una cuenta administradora");
		return false;
	}
	
	public static int codigoCorrelativo() {
		if(tamanio() == 0)
			return 1001;
		else {
			return obtenerUsuario(tamanio()-1)
					.getCodigoUsuario()+1;
		}
	}
	
	public static void cargarDatos() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
			String linea;
			while((linea = br.readLine())!= null) {
				StringTokenizer mitokens = new StringTokenizer(linea, "\t");
				int codigoUser = Integer.parseInt(mitokens.nextToken());
				int codigoVend = Integer.parseInt(mitokens.nextToken());
				String user = mitokens.nextToken();
				String pass = mitokens.nextToken();
				
				ingresarUsuarios(new Usuario(codigoUser,codigoVend, user, pass));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void guardarDatos() {
		try {
			FileWriter wr = new FileWriter(rutaArchivo);
			for(Usuario v: listaUsuarios) {
				String codU = v.getCodigoUsuario()+"\t";
				String codV = v.getCodigoVendedor()+"\t";
				String user = v.getUser()+"\t";
				String pass = v.getPass()+"\r\n";
				
				wr.write(codU+codV+user+pass);
			}
			wr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
