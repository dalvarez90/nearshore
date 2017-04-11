package com.banamex.nearshore.catalogsms.domain;

//import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

public class RecursoCiti {

//	@NotEmpty
	private String soe_id;
	private String apellidoPaterno; //45
	private String apellidoMaterno;//45
	private String primerNombre; //45
	private String segundoNombre;//45
	
	@Autowired
	Dominio dominio;
	@Autowired
	PuestoCiti puestoCiti;
	@Autowired
	Ciudad ciudad;
	
	private String ext; //10
	private String movil; //10
	private String telefono;//10
	private String email; //60
	
	@Autowired
	RecursoCiti reportaA;
	
	private String comentarios; //2000
	

	public String getSoe_id() {
		return soe_id;
	}

	public void setSoe_id(String soe_id) {
		this.soe_id = soe_id;
	}

	public Dominio getDominio() {
		return dominio;
	}

	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
	}

	public PuestoCiti getPuestoCiti() {
		return puestoCiti;
	}

	public void setPuestoCiti(PuestoCiti puestoCiti) {
		this.puestoCiti = puestoCiti;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public RecursoCiti getReportaA() {
		return reportaA;
	}

	public void setReportaA(RecursoCiti reportaA) {
		this.reportaA = reportaA;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
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

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
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

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	
	

}
