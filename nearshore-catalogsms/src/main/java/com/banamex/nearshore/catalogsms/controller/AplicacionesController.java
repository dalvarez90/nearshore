package com.banamex.nearshore.catalogsms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banamex.nearshore.catalogsms.domain.Aplicacion;
import com.banamex.nearshore.catalogsms.domain.AplicacionProveedor;
import com.banamex.nearshore.catalogsms.exception.NearshoreDatabaseMicroserviceException;
import com.banamex.nearshore.databasems.Data;
import com.banamex.nearshore.databasems.DatabaseMicroserviceClientService;
import com.banamex.nearshore.databasems.ResultBase;
import com.banamex.nearshore.util.Constants;
import com.banamex.nearshore.util.Util;

@RestController
@RequestMapping("aplicaciones")
public class AplicacionesController {
	
	@Autowired
	private DatabaseMicroserviceClientService databaseMicroserviceClientService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveAllApplications() {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM " + Constants.APLICACION);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{csiId}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveApplicationById(@PathVariable Integer csiId) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(csiId.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM " + Constants.APLICACION + " WHERE Csi_Id = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		return resultBase;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public Object newAplication(@RequestBody @Valid Aplicacion aplicacion) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = getQueryParamsAplicacion(aplicacion,false);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "INSERT INTO " + Constants.APLICACION + " (Id_Dominio , PTB_ID , Descripcion_Corta , Descripcion_Larga , Id_L1 , Id_L2 , Id_L3 , Id_Plat1 , Id_Plat2 , Id_Plat3 , Comentarios) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		return resultBase;
	}
	
	@RequestMapping(value = "/{csiId}", method = RequestMethod.PUT, produces = "application/json")
	public Object editAplication(@PathVariable Integer csiId, @RequestBody @Valid Aplicacion aplicacion) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		aplicacion.setCsiId(csiId);
		List<Data> queryParams = getQueryParamsAplicacion(aplicacion,true);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "UPDATE " + Constants.APLICACION + " SET Id_Dominio = ? , Ptb_Id = ? , Descripcion_Corta = ? , Descripcion_Larga = ? , Id_L1 = ? , Id_L2 = ? , Id_L3 = ? , Id_Plat1 = ? , Id_Plat2 = ? , Id_Plat3 = ? , Comentarios = ? WHERE Csi_Id = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{csiId}", method = RequestMethod.DELETE, produces = "application/json")
	public Object removeAplication(@PathVariable Integer csiId) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(csiId.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "DELETE FROM " + Constants.APLICACION + " WHERE Csi_Id = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		return resultBase;
	}
	
	@RequestMapping(value = "/{csiId}/empleados/proveedores", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveAllAplicacionProveedor(@PathVariable Integer csiId) {
	HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(csiId.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM " + Constants.APLICACION_PROVEEDOR + " WHERE Csi_Id = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		return resultBase;
	}
	
	@RequestMapping(value = "/{csiId}/empleados/proveedores", method = RequestMethod.POST, produces = "application/json")
	public Object newAplicacionProveedor(@PathVariable Integer csiId , @RequestBody @Valid AplicacionProveedor aplicacionProveedor) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		aplicacionProveedor.setCsiId(csiId);
		List<Data> queryParams = getQueryParamsAplicacionProveedor(aplicacionProveedor);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "INSERT INTO " + Constants.APLICACION_PROVEEDOR + " (Id_Proveedor , L1_Primario, L1_Backup, L2_Primario, L2_Backup, L3_Primario, L3_Backup, Csi_Id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		return resultBase;
	}
	
	@RequestMapping(value = "/{csiId}/empleados/proveedores", method = RequestMethod.PUT, produces = "application/json")
	public Object editAplicacionProveedor(@PathVariable Integer csiId, @RequestBody @Valid AplicacionProveedor aplicacionProveedor) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		aplicacionProveedor.setCsiId(csiId);
		List<Data> queryParams = getQueryParamsAplicacionProveedor(aplicacionProveedor);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "UPDATE "+Constants.APLICACION_PROVEEDOR + " SET Id_Proveedor = ? , L1_Primario = ? , L1_Backup = ? , L2_Primario = ? , L2_Backup = ? , L3_Primario = ? , L3_Backup = ? WHERE Csi_Id = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}

	@RequestMapping(value = "/{csiId}/empleados/proveedores", method = RequestMethod.DELETE, produces = "application/json")
	public Object removeAplicacionProveedor(@PathVariable Integer csiId) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(csiId.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "DELETE FROM " + Constants.APLICACION_PROVEEDOR + " WHERE Csi_Id = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		return resultBase;
	}
	
	private List<Data> getQueryParamsAplicacionProveedor(AplicacionProveedor aplicacionProveedor) {
		List<Data> queryParams = new ArrayList<Data>();
		
		Data datos []= new Data[8];
		
		datos[0] = Util.createDataObj(aplicacionProveedor.getIdProveedor(), "INT", 1);
		datos[1] = Util.createDataObj(aplicacionProveedor.getL1Primario(), "INT", 2);
		datos[2] = Util.createDataObj(aplicacionProveedor.getL1Backup(), "INT", 3);
		datos[3] = Util.createDataObj(aplicacionProveedor.getL2Primario(), "INT", 4);
		datos[4] = Util.createDataObj(aplicacionProveedor.getL2Backup(), "INT", 5);
		datos[5] = Util.createDataObj(aplicacionProveedor.getL3Primario(), "INT", 6);
		datos[6] = Util.createDataObj(aplicacionProveedor.getL3Backup(), "INT", 7);
		datos[7] = Util.createDataObj(aplicacionProveedor.getCsiId(), "INT", 8);
		
		for(Data dato : datos) {
			queryParams.add(dato);
		}
				
		return queryParams;
	}
	
	private List<Data> getQueryParamsAplicacion(Aplicacion aplicacion, boolean isUpdate) {
		List<Data> queryParams = new ArrayList<Data>();
		Data datos [];
		if(isUpdate){
			datos = new Data[12]; 
			datos[11] = Util.createDataObj(aplicacion.getCsiId(), "INT", 12);
		}else {
			datos = new Data[11];
		}
		
		datos[0] = Util.createDataObj(aplicacion.getIdDominio(), "INT", 1);
		datos[1] = Util.createDataObj(aplicacion.getPtbId(), "STRING", 2);
		datos[2] = Util.createDataObj(aplicacion.getDescripcionCorta(), "STRING", 3);
		datos[3] = Util.createDataObj(aplicacion.getDescripcionLarga(), "STRING", 4);
		datos[4] = Util.createDataObj(aplicacion.getIdL1(), "INT", 5);
		datos[5] = Util.createDataObj(aplicacion.getIdL2(), "INT", 6);
		datos[6] = Util.createDataObj(aplicacion.getIdL3(), "INT", 7);
		datos[7] = Util.createDataObj(aplicacion.getIdPlat1(), "INT", 8);
		datos[8] = Util.createDataObj(aplicacion.getIdPlat2(), "INT", 9);
		datos[9] = Util.createDataObj(aplicacion.getIdPlat3(), "INT", 10);
		datos[10] = Util.createDataObj(aplicacion.getComentarios(), "STRING", 11);
		
		for(Data dato : datos) {
			queryParams.add(dato);
		}
				
		return queryParams;
	}
	
	@FeignClient(name = "mcTDCdbMain")
	public interface DatabaseMicroServiceClient {
		
		@RequestMapping(value = "/getResultBD", method = RequestMethod.POST, produces = "application/json")
		public ResultBase getResultQuery(@RequestBody HashMap<String, Object> datos);
		
	}

}