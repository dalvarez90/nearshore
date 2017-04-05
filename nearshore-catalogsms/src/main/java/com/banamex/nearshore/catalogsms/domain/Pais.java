package com.banamex.nearshore.catalogsms.domain;

public class Pais {

	private Integer id;
	private String descripcion;
	
	public Pais(){
	}
	
	public Pais(Integer id,String descripcion){
		this.id = id;
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
	
}
