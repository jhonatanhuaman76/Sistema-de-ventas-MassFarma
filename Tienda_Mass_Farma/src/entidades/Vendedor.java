package entidades;

import java.math.BigDecimal;
import java.util.ArrayList;

import operaciones.ArregloFacturas;

public class Vendedor {
	private final int codigoVendedor;
	private int categoria;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String dni;
	public Vendedor(int codigoVendedor, int categoria, String nombres, String apellidos, String telefono, String dni) {
		this.codigoVendedor = codigoVendedor;
		this.categoria = categoria;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.dni = dni;
	}
	public int getCodigoVendedor() {
		return codigoVendedor;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public int getNumeroVentas() {
		int nroVentas = 0;
		ArrayList<Factura> listaFacturas = ArregloFacturas.buscarFacturasVendedor(codigoVendedor);
		for(Factura f: listaFacturas) {
			if(f.getEstado() == 0)
				nroVentas++;
		}
		return nroVentas;
	}
	
	public int getUnidadesVendidas() {
		int unidadesVendidas = 0;
		ArrayList<Factura> listaFacturas = ArregloFacturas.buscarFacturasVendedor(codigoVendedor);
		for(Factura f: listaFacturas) {
			if(f.getEstado() == 0)
				unidadesVendidas+=f.getTotalUnidades();
		}
		
		return unidadesVendidas;
	}
	
	public int getVentasCanceladas() {
		int ventasCanceladas = 0;
		ArrayList<Factura> listaFacturas = ArregloFacturas.buscarFacturasVendedor(codigoVendedor);
		for(Factura f: listaFacturas) {
			if(f.getEstado() == 1)
				ventasCanceladas++;
		}
		return ventasCanceladas;
	}
	
	public double getImporteTotal() {
		BigDecimal it = new BigDecimal("0");
		ArrayList<Factura> listaFacturas = ArregloFacturas.buscarFacturasVendedor(codigoVendedor);
		for(Factura f: listaFacturas){
			if(f.getEstado() == 0)
				it = it.add(new BigDecimal(""+f.getImporteTotal()));
		}
		return it.doubleValue();
	}
}
