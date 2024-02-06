package entidades;

import java.math.BigDecimal;

import operaciones.ArregloFacturas;

public class Producto {
	private final int codigoProducto;
	private int categoria, stockActual, stockMaximo;
	private String descripcion, marca;
	private double precio;
	
	public Producto(int codigoProducto, String descripcion, double precio, int categoria, int stockA, int stockMax, String marca) {
		this.codigoProducto = codigoProducto;
		this.descripcion = descripcion;
		this.precio = precio;
		this.categoria = categoria;
		this.stockActual = stockA;
		this.stockMaximo = stockMax;
		this.marca = marca;
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public int getStockActual() {
		return stockActual;
	}
	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getStockMaximo() {
		return stockMaximo;
	}
	public void setStockMaximo(int stockMaximo) {
		this.stockMaximo = stockMaximo;
	}
	
	public int getNumeroVentas() {
		int nroventas = 0;
		for(Factura f: ArregloFacturas.getListaFacturasProcesadas()) {
			ItemDetalleVenta i = f.buscarDetalleProducto(codigoProducto);
			if(i!=null) 
				nroventas++;		
		}
		return nroventas;
	}
	
	public int getUnidadesVendidas() {
		int unidadesVendidas = 0;
		for(Factura f: ArregloFacturas.getListaFacturasProcesadas()) {
			ItemDetalleVenta i = f.buscarDetalleProducto(codigoProducto);
			if(i!=null) 
				unidadesVendidas+=i.getCantidad();
		}
		return unidadesVendidas;
	}
	
	public double getImporteTotal() {
		BigDecimal it = new BigDecimal("0");
		for(Factura f: ArregloFacturas.getListaFacturasProcesadas()){
			ItemDetalleVenta i = f.buscarDetalleProducto(codigoProducto);
			if(i!=null)
				it = it.add(new BigDecimal(""+i.importe()));
		}
		return it.doubleValue();
	}
	
	public int getVentasCanceladas() {
		int nroVentasCanceladas = 0;
		for(Factura f: ArregloFacturas.getListaFacturasCanceladas()) {
			ItemDetalleVenta i = f.buscarDetalleProducto(codigoProducto);
			if(i!=null)
				nroVentasCanceladas++;
		}
		return nroVentasCanceladas;
	}
}
