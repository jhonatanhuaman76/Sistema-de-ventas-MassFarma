package entidades;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;

import libreria.Utilidades;
import operaciones.ArregloDetalles;

public class Factura {
	private final int codigoFactura;
	private final int codigoCliente;
	private final int codigoVendedor;
	private String fecha;
	private int estado = 0;
	private String observacion = "";
	
	public Factura(int codigoFactura, int codigoCliente, int codigoVendedor, String fecha, int estado, String obs) {
		this.codigoFactura = codigoFactura;
		this.codigoCliente = codigoCliente;
		this.codigoVendedor = codigoVendedor;
		this.fecha = fecha;
		this.estado = estado;
		this.observacion = obs;
	}
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getCodigoFactura() {
		return codigoFactura;
	}
	public int getCodigoCliente() {
		return codigoCliente;
	}
	public int getCodigoVendedor() {
		return codigoVendedor;
	}
	public ArrayList<ItemDetalleVenta> getItemsDetalles() {
		return ArregloDetalles.buscarDetallesFactura(codigoFactura);
	}
	
	public int getTotalUnidades() {
		int totalUni = 0;
		for(ItemDetalleVenta i: getItemsDetalles()) {
			totalUni+=i.getCantidad();
		}
		return totalUni;
	}
	
	public double getSubTotal() {
		BigDecimal it = new BigDecimal("0");
		for(ItemDetalleVenta i: getItemsDetalles()) {
			it = it.add(new BigDecimal(""+i.importe()));
		}
		return it.doubleValue();
	}
	
	public double getImporteIgv() {
		BigDecimal st = new BigDecimal(""+getSubTotal());
		BigDecimal igv = new BigDecimal(""+Utilidades.getIGV());
		BigDecimal impIgv = st.multiply(igv);
		return impIgv.doubleValue();
	}
	
	public double getImporteTotal() {
		BigDecimal st = new BigDecimal(""+getSubTotal());
		BigDecimal igv = new BigDecimal(""+getImporteIgv());
		BigDecimal ip = st.add(igv);
		return ip.doubleValue();
	}
	
	public ItemDetalleVenta buscarDetalleProducto(int codPro) {
		for(ItemDetalleVenta p: getItemsDetalles()) 
			if(p.getCodProducto()== codPro) 
				return p;
			
		return null;
	}
}
