package com.banamex.nearshore.catalogsms.domain;

import org.springframework.beans.factory.annotation.Autowired;

public class Ciudad {

	private Integer id;
	@Autowired
	private Pais pais;
	private String descripcion;

	public Ciudad(){
	}
	
	public Ciudad(Integer id, Pais pais, String descripcion){
		this.id = id;
		this.pais = pais;
		this.descripcion = descripcion;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
}
