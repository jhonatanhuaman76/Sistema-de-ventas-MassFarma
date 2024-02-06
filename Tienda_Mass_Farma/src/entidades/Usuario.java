package entidades;

public class Usuario {
	private final int codigoUsuario;
	private final int codigoVendedor;
	private String user;
	private String pass;
	
	public Usuario(int codigoUsuario, int codigoVendedor,String user, String pass) {
		this.codigoUsuario = codigoUsuario;
		this.codigoVendedor = codigoVendedor;
		this.user = user;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public int getCodigoVendedor() {
		return codigoVendedor;
	}
	
}
