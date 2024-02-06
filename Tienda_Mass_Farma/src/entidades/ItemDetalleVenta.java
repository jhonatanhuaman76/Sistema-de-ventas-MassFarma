package entidades;

import java.math.BigDecimal;

public class ItemDetalleVenta {
	private final int codDetalle;
	private final int codProducto;
	private final int codFactura;
	private String descripcion;
	private double precioUnitario;
	private int cantidad;
	
	public ItemDetalleVenta(int codDetalle, int codProducto, int codFactura, String descripcion, double precioUnitario, int cantidad) {
		this.codDetalle = codDetalle;
		this.codProducto = codProducto;
		this.codFactura = codFactura;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCodDetalle() {
		return codDetalle;
	}
	public int getCodProducto() {
		return codProducto;
	}
	public int getCodFactura() {
		return codFactura;
	}
	
	public double importe() {
		BigDecimal pre = new BigDecimal(""+precioUnitario);
		BigDecimal canti = new BigDecimal(""+cantidad);
		BigDecimal ip = pre.multiply(canti);
		return ip.doubleValue();
	}
}
