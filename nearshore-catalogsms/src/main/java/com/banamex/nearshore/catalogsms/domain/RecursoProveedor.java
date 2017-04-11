package com.banamex.nearshore.catalogsms.domain;

import javax.validation.constraints.NotNull;

//import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

public class RecursoProveedor {

	private Integer id;
	@NotNull
	@Autowired
	Proveedor proveedor;
	private Integer claveEmpleado;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String primerNombre; 
	private String segundoNombre;
	@Autowired
	Ciudad ciudad;
	private String movilPersonal;
	private String telefonoParticular;
	private String emailPersonal; 
	@Autowired
	PuestoProveedor puesto;
	private Integer idReportaA; 
	private String telefonoProveedor;
	private String extProveedor;
	private String emailProveedor;
	private String soe_id; 
	private String extCiti;
	private String emailCiti;
	private String comentarios;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Integer getClaveEmpleado() {
		return claveEmpleado;
	}
	public void setClaveEmpleado(Integer claveEmpleado) {
		this.claveEmpleado = claveEmpleado;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public String getMovilPersonal() {
		return movilPersonal;
	}
	public void setMovilPersonal(String movilPersonal) {
		this.movilPersonal = movilPersonal;
	}
	public String getTelefonoParticular() {
		return telefonoParticular;
	}
	public void setTelefonoParticular(String telefonoParticular) {
		this.telefonoParticular = telefonoParticular;
	}
	public String getEmailPersonal() {
		return emailPersonal;
	}
	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}
	public PuestoProveedor getPuesto() {
		return puesto;
	}
	public void setPuesto(PuestoProveedor puesto) {
		this.puesto = puesto;
	}
	public Integer getIdReportaA() {
		return idReportaA;
	}
	public void setIdReportaA(Integer idReportaA) {
		this.idReportaA = idReportaA;
	}
	public String getTelefonoProveedor() {
		return telefonoProveedor;
	}
	public void setTelefonoProveedor(String telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}
	public String getExtProveedor() {
		return extProveedor;
	}
	public void setExtProveedor(String extProveedor) {
		this.extProveedor = extProveedor;
	}
	public String getEmailProveedor() {
		return emailProveedor;
	}
	public void setEmailProveedor(String emailProveedor) {
		this.emailProveedor = emailProveedor;
	}
	public String getSoe_id() {
		return soe_id;
	}
	public void setSoe_id(String soe_id) {
		this.soe_id = soe_id;
	}
	public String getExtCiti() {
		return extCiti;
	}
	public void setExtCiti(String extCiti) {
		this.extCiti = extCiti;
	}
	public String getEmailCiti() {
		return emailCiti;
	}
	public void setEmailCiti(String emailCiti) {
		this.emailCiti = emailCiti;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	
}
