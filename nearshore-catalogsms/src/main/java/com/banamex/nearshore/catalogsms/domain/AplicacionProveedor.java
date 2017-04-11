package com.banamex.nearshore.catalogsms.domain;

import javax.validation.constraints.NotNull;

public class AplicacionProveedor {

	private Integer idAplicacionProveedor;
	private Integer idProveedor;
	private Integer csiId;
	private Integer l1Primario;
	private Integer l1Backup;
	private Integer l2Primario;
	private Integer l2Backup;
	private Integer l3Primario;
	private Integer l3Backup;
		
	public Integer getIdAplicacionProveedor() {
		return idAplicacionProveedor;
	}
	public void setIdAplicacionProveedor(Integer idAplicacionProveedor) {
		this.idAplicacionProveedor = idAplicacionProveedor;
	}
	public Integer getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}
	public Integer getCsiId() {
		return csiId;
	}
	public void setCsiId(Integer csiId) {
		this.csiId = csiId;
	}
	public Integer getL1Primario() {
		return l1Primario;
	}
	public void setL1Primario(Integer l1Primario) {
		this.l1Primario = l1Primario;
	}
	public Integer getL1Backup() {
		return l1Backup;
	}
	public void setL1Backup(Integer l1Backup) {
		this.l1Backup = l1Backup;
	}
	public Integer getL2Primario() {
		return l2Primario;
	}
	public void setL2Primario(Integer l2Primario) {
		this.l2Primario = l2Primario;
	}
	public Integer getL2Backup() {
		return l2Backup;
	}
	public void setL2Backup(Integer l2Backup) {
		this.l2Backup = l2Backup;
	}
	public Integer getL3Primario() {
		return l3Primario;
	}
	public void setL3Primario(Integer l3Primario) {
		this.l3Primario = l3Primario;
	}
	public Integer getL3Backup() {
		return l3Backup;
	}
	public void setL3Backup(Integer l3Backup) {
		this.l3Backup = l3Backup;
	}
	
	
}
