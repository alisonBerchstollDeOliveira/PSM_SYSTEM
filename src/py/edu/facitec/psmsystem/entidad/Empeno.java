package py.edu.facitec.psmsystem.entidad;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_empeno")
public class Empeno {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(strategy = "increment", name = "increment")
	private int numero;

	@Column(name = "emp_fecha_dia", nullable = false)
	private Date fechaDia;

	@Column(name = "emp_fecha_vencimiento", nullable = false)
	private Date fechaVencimiento;

	@Column(name = "emp_valor_total", nullable = false)
	private double valorTotal;

	@Column(name = "emp_estado", nullable = false)
	private int estado;

	@Column(name = "emp_obs")
	private String observacion;

	@ManyToOne
	@JoinColumn(name = "Clienteid", referencedColumnName = "id")
	private Cliente cliente;

	@OneToMany(mappedBy = "empeno")
	private List<DeudaCliente> deudaClientes;

	@OneToOne
	private Producto producto;


	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getFechaDia() {
		return fechaDia;
	}
	public void setFechaDia(Date fechaDia) {
		this.fechaDia = fechaDia;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
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
	public List<DeudaCliente> getDeudaClientes() {
		return deudaClientes;
	}
	public Producto getProducto() {
		return producto;
	}


}
