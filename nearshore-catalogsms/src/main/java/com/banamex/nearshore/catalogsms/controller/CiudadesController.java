package com.banamex.nearshore.catalogsms.controller;

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

import com.banamex.nearshore.catalogsms.domain.Ciudad;
import com.banamex.nearshore.catalogsms.exception.NearshoreDatabaseMicroserviceException;
import com.banamex.nearshore.databasems.Data;
import com.banamex.nearshore.databasems.DatabaseMicroserviceClientService;
import com.banamex.nearshore.databasems.ResultBase;
import com.banamex.nearshore.util.Constants;

@RestController
@RequestMapping("ciudades")
public class CiudadesController{
	
	@Autowired
	private DatabaseMicroserviceClientService databaseMicroserviceClientService;
	
	/*
	 * GET CIUDADES
	 * El endpoint aplicaciones devuelve un listado de ciudades.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveAllCities() {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM " + Constants.CAT_CIUDAD);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}
	
	/*
	 * GET CIUDADES
	 * Devuelve una ciudad por id.
	 */
	@RequestMapping(value = "/{idCiudad}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveCityById(@PathVariable Integer idCiudad) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idCiudad.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM " + Constants.CAT_CIUDAD + " WHERE ID = ?");
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
	 * GET CIUDADES
	 * Devuelve las ciudades de determinado país.
	 */
	@RequestMapping(value = "/paises/{idPais}/ciudades", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveCityByIdCountry(@PathVariable Integer idPais) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idPais.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT CIUDAD.* FROM " + Constants.CAT_CIUDAD + " CIUDAD INNER JOIN " + Constants.CAT_PAIS + " PAIS ON CIUDAD.ID_PAIS = PAIS.ID WHERE PAIS.ID = ?");
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
	 * POST CIUDADES
	 * El endpoint que agrega una nueva ciudad a un país determinado.
	 */
	@RequestMapping(value = "/paises/{idPais}/ciudades", method = RequestMethod.POST, produces = "application/json")
	public Object newCity(@RequestBody Ciudad ciudad, @PathVariable Integer idPais) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idPais.toString());
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("STRING");
		queryParam02.setValue(ciudad.getDescripcion());
		queryParams.add(queryParam02);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "INSERT INTO "+Constants.CAT_CIUDAD+" ( Id_Pais ,Descripcion) VALUES ( ?, ?)");
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
	 * PUT CIUADES
	 * Edita la información de una ciudad.
	 */
	@RequestMapping(value = "/{idCiudad}", method = RequestMethod.PUT, produces = "application/json")
	public Object editCity(@PathVariable Integer idCiudad, @RequestBody Ciudad ciudad) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("STRING");
		queryParam01.setValue(ciudad.getDescripcion());
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("INT");
		queryParam02.setValue(ciudad.getPais().getId().toString());
		queryParams.add(queryParam02);
		
		Data queryParam03 = new Data();
		queryParam03.setIndex(3);
		queryParam03.setType("INT");
		queryParam03.setValue(idCiudad.toString());
		queryParams.add(queryParam03);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "UPDATE " + Constants.CAT_CIUDAD + " SET Descripcion = ? , Id_Pais = ? WHERE Id = ?");
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
	 * DELETE CIUDADES
	 * Endpoint para eliminar una ciudad por id.
	 */
	@RequestMapping(value = "/{idCiudad}", method = RequestMethod.DELETE, produces = "application/json")
	public Object removeCity(@PathVariable Integer idCiudad) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idCiudad.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "DELETE FROM " + Constants.CAT_CIUDAD + " WHERE Id = ?");
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
	public interface DatabaseMicroServiceClient {
		
		@RequestMapping(value = "/getResultBD", method = RequestMethod.POST, produces = "application/json")
		public ResultBase getResultQuery(@RequestBody HashMap<String, Object> datos);
		
	}

}
