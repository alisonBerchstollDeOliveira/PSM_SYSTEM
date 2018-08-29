package py.edu.facitec.psmsystem.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tb_deudaCliente")
public class DeudaCliente {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(strategy="increment",name="increment")
	private int id;
	
	@Column(name="deu_fecha_inicio", nullable=false)
	private Date fechaInicio;
	
	@Column(name="deu_fecha_vencimiento", nullable=false)
	private Date fechaVencimiento;
	
	@Column(name="deu_valor", nullable=false)
	private double valor;
	
	@Column(name="deu_estado", nullable=false)
	private int estado;
	
	@ManyToOne
	@JoinColumn(name="Empenonumero", referencedColumnName="numero")
	private Empeno empeno;
	
	@ManyToOne
	@JoinColumn(name="Cobranzaid", referencedColumnName="id")
	private Cobranza cobranza;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Empeno getEmpenho() {
		return empeno;
	}
	public void setEmpenho(Empeno empeno) {
		this.empeno = empeno;
	}
	public Empeno getEmpeno() {
		return empeno;
	}
	public Cobranza getCobranza() {
		return cobranza;
	}
	

}
