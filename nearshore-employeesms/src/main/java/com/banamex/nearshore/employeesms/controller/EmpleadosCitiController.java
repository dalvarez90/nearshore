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
@RequestMapping("empleados/citi")
public class EmpleadosCitiController {
	
	@Autowired
	private DatabaseMicroserviceClientService databaseMicroserviceClientService;
	
	/*
	 * Miembro que devuelve los empleados de citi de un id de dominio
	 */
	@RequestMapping(value = "/dominio/{id}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveCitiEmployeesByDomain(@PathVariable Integer id) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(id.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM RECURSO_CITI WHERE Id_Dominio = ?");
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
	 * Miembro que devuelve un empleado de citi por soeid e id de dominio
	 */
	@RequestMapping(value = "/{soeId}/dominio/{idDominio}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveCitiEmployeesBySoeidAndByDomain(@PathVariable String soeId ,@PathVariable Integer idDominio) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("String");
		queryParam01.setValue(soeId);
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("INT");
		queryParam02.setValue(idDominio.toString());
		queryParams.add(queryParam02);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM RECURSO_CITI WHERE Soe_Id = ? and Id_Dominio = ?");
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
	 * Miembro que devuelve los empleados de citi de acuerdo a un puesto especifico
	 */
	@RequestMapping(value = "/puestos/citi/{idPuesto}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveCitiEmployeesByJob(@PathVariable Integer idPuesto) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idPuesto.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM RECURSO_CITI WHERE Id_Puesto = ?");
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
	 * Miembro que devuelve un empleado de citi por soeid y un puesto especifico
	 */
	@RequestMapping(value = "/{soeId}/puestos/citi/{idPuesto}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveCitiEmployeesBySoeidAndByJob(@PathVariable String soeId ,@PathVariable Integer idPuesto) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("String");
		queryParam01.setValue(soeId);
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("INT");
		queryParam02.setValue(idPuesto.toString());
		queryParams.add(queryParam02);
	
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM RECURSO_CITI WHERE Soe_Id = ? and Id_Puesto = ?");
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
	 * Miembro que devuelve los empleados que tienen asignados cierto dominio y un puesto en especifico
	 */
	@RequestMapping(value = "/dominio/{idDominio}/puestos/citi/{idPuesto}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveCitiEmployeesByDomainAndByJob(@PathVariable Integer idDominio ,@PathVariable Integer idPuesto) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idDominio.toString());
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("INT");
		queryParam02.setValue(idPuesto.toString());
		queryParams.add(queryParam02);
	
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM RECURSO_CITI WHERE Id_Dominio = ? and Id_Puesto = ?");
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
	 * Miembro que devuelve un empleado que tiene asignado cierto SOEID, cierto dominio y cierto puesto
	 */
	@RequestMapping(value = "/{soeId}/dominio/{idDominio}/puestos/citi/{idPuesto}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveCitiEmployeesBySoeidAndByDomainAndByJob(@PathVariable String soeId ,@PathVariable Integer idDominio ,@PathVariable Integer idPuesto) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("STRING");
		queryParam01.setValue(soeId);
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("INT");
		queryParam02.setValue(idDominio.toString());
		queryParams.add(queryParam02);
		
		Data queryParam03 = new Data();
		queryParam03.setIndex(3);
		queryParam03.setType("INT");
		queryParam03.setValue(idPuesto.toString());
		queryParams.add(queryParam03);
	
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM RECURSO_CITI WHERE Soe_Id = ? and Id_Dominio = ? and Id_Puesto = ?");
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
	 * Miembro que devuelve los empleados que reportan a un determinado empleado
	 */
	@RequestMapping(value = "/{soeId}/reporta", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveCitiEmployeesBySoeidReports(@PathVariable String soeId) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("STRING");
		queryParam01.setValue(soeId);
		queryParams.add(queryParam01);
			
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM RECURSO_CITI WHERE Id_ReportaA = ?");
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
	 * Miembro que devuelve el empleado quien un determinado recurso reporta
	 */
	@RequestMapping(value = "/reporta/{soeId}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveCitiEmployedReportsBySoeid(@PathVariable String soeId) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();		
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("STRING");
		queryParam01.setValue(soeId);
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT rcUno.* FROM RECURSO_CITI rcUno WHERE rcUno.Soe_Id = (SELECT rcDos.Id_ReportaA FROM RECURSO_CITI rcDos WHERE rcDos.Soe_Id = ?)");
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
