package com.banamex.nearshore.catalogsms.domain;

import javax.validation.constraints.NotNull;

public class Aplicacion {

	private Integer csiId;
	private Integer idDominio;
	private String ptbId;
	private String descripcionCorta;
	private String descripcionLarga;
	private Integer idL1;
	private Integer idL2;
	private Integer idL3;
	private Integer idPlat1;
	private Integer idPlat2;
	private Integer idPlat3;
	private String comentarios;
	
	public Integer getCsiId() {
		return csiId;
	}
	public void setCsiId(Integer csiId) {
		this.csiId = csiId;
	}
	public Integer getIdDominio() {
		return idDominio;
	}
	public void setIdDominio(Integer idDominio) {
		this.idDominio = idDominio;
	}
	public String getPtbId() {
		return ptbId;
	}
	public void setPtbId(String ptbId) {
		this.ptbId = ptbId;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	public String getDescripcionLarga() {
		return descripcionLarga;
	}
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}
	public Integer getIdL1() {
		return idL1;
	}
	public void setIdL1(Integer idL1) {
		this.idL1 = idL1;
	}
	public Integer getIdL2() {
		return idL2;
	}
	public void setIdL2(Integer idL2) {
		this.idL2 = idL2;
	}
	public Integer getIdL3() {
		return idL3;
	}
	public void setIdL3(Integer idL3) {
		this.idL3 = idL3;
	}
	public Integer getIdPlat1() {
		return idPlat1;
	}
	public void setIdPlat1(Integer idPlat1) {
		this.idPlat1 = idPlat1;
	}
	public Integer getIdPlat2() {
		return idPlat2;
	}
	public void setIdPlat2(Integer idPlat2) {
		this.idPlat2 = idPlat2;
	}
	public Integer getIdPlat3() {
		return idPlat3;
	}
	public void setIdPlat3(Integer idPlat3) {
		this.idPlat3 = idPlat3;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
}
