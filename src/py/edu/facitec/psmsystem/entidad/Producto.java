package py.edu.facitec.psmsystem.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tb_producto")
public class Producto {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(strategy="increment",name="increment")
	private int codigo;
	
	@Column(name="pro_descricion", nullable=false, unique=true, length=100)
	private String descripcion;
	
	@Column(name="pro_precio_compra", nullable=false)
	private Double precioCompra;
	
	@Column(name="pro_precio_venta", nullable=false)
	private Double precioVenta;
	
	@Column(name="pro_detalle", length=100)
	private String detalle;
	
	@Column(name="pro_estado", nullable=false)
	private int estado;
	
	
	@OneToOne
	private Empeno empeno;
	
	
	public int getId() {
		return codigo;
	}
	public void setId(int id) {
		this.codigo = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public Double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getCodigo() {
		return codigo;
	}
	public Empeno getEmpeno() {
		return empeno;
	}
	
	
	
	
}
