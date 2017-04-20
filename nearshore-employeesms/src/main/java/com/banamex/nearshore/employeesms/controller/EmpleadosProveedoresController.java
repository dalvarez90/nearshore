package com.banamex.nearshore.employeesms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banamex.nearshore.databasems.Data;
import com.banamex.nearshore.databasems.DatabaseMicroserviceClientService;
import com.banamex.nearshore.databasems.ResultBase;
import com.banamex.nearshore.employeesms.exception.NearshoreDatabaseMicroserviceException;
import com.banamex.nearshore.util.Constants;

@RestController
@RequestMapping("empleados/proveedores")
public class EmpleadosProveedoresController {
	
	@Autowired
	private DatabaseMicroserviceClientService databaseMicroserviceClientService;
	
	/*
	 * Miembro que devuelve los empleados proveedor de un proveedor especifico
	 */
	@RequestMapping(value = "/proveedor/{idProveedor}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveEmployeesByIdSupplier(@PathVariable Integer idProveedor) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();		
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idProveedor.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT recPro.* FROM RECURSO_PROVEEDOR recPro WHERE recPro.Id_Proveedor = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}
	
	/*
	 * Miembro que devuelve los empleados proveedores de una determinanda ciudad
	 */
	@RequestMapping(value = "/ciudad/{idCiudad}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveSupplierEmployeesByIdCiudad(@PathVariable Integer idCiudad) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();		
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idCiudad.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT recPro.* FROM RECURSO_PROVEEDOR recPro WHERE recPro.Id_Ciudad = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}
	
	/*
	 * Miembro que devuelve los empleados proveedores de un determinado puesto
	 */
	@RequestMapping(value = "/puesto/{idPuesto}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveSupplierEmployeesByIdJob(@PathVariable Integer idPuesto) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();		
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idPuesto.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM RECURSO_PROVEEDOR recPro WHERE recPro.Id_Puesto = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}
	
	/*
	 * Miembro que devuelve los empleados proveedores de una determinada ciudad y determinado puesto
	 */
	@RequestMapping(value = "/ciudad/{idCiudad}/puesto/{idPuesto}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveSupplierEmployeesByIdCityAndIdJob(@PathVariable Integer idCiudad , @PathVariable Integer idPuesto) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();		
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idCiudad.toString());
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("INT");
		queryParam02.setValue(idPuesto.toString());
		queryParams.add(queryParam02);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM RECURSO_PROVEEDOR recPro WHERE recPro.Id_Ciudad = ? and recPro.Id_Puesto = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}
	
	@FeignClient(name = "mcTDCdbMain")
	public interface DatabaseMicroserviceClient {
		
		@RequestMapping(value = "/getResultBD", method = RequestMethod.POST, produces = "application/json")
		public ResultBase getResultQuery(@RequestBody HashMap<String, Object> datos);
		
	}

}
