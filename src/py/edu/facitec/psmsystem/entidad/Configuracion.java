package py.edu.facitec.psmsystem.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_configuracion")
public class Configuracion {

	@Id
	private Integer id;

	@Column(name="conf_nombre")
	private String nombre;

	@Column(name="conf_ruc")
	private String ruc;

	@Column(name="conf_telefono")
	private String telefono;

	@Column(name="conf_email")
	private String email;
	
	@Column(name="conf_interes")
	public String interes;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return ruc;
	}
	public void setDireccion(String direccion) {
		this.ruc = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getInteres() {
		return interes;
	}
	public void setInteres(String interes) {
		this.interes = interes;
	}



}
