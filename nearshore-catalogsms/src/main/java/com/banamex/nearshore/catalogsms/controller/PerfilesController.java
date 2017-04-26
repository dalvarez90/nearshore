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

import com.banamex.nearshore.catalogsms.domain.Perfil;
import com.banamex.nearshore.catalogsms.exception.NearshoreDatabaseMicroserviceException;
import com.banamex.nearshore.databasems.Data;
import com.banamex.nearshore.databasems.DatabaseMicroserviceClientService;
import com.banamex.nearshore.databasems.ResultBase;
import com.banamex.nearshore.util.Constants;

@RestController
@RequestMapping("perfiles")
public class PerfilesController {
	
	@Autowired
	private DatabaseMicroserviceClientService databaseMicroserviceClientService;
	
	/*
	 * GET PERFILES
	 * El endpoint aplicaciones devuelve un listado de todos los perfiles.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveAllDomains() {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT ID_Perfil, DESCRIPCION FROM "+Constants.CAT_PERFIL);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}
	
	/*
	 * GET PERFILES
	 * Devuelve un perfil por id.
	 */
	@RequestMapping(value = "/{idPerfil}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveDomainById(@PathVariable Integer idPerfil) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idPerfil.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT ID_Perfil, DESCRIPCION FROM "+Constants.CAT_PERFIL+" WHERE ID_Perfil = ?");
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
	 * POST PERFILES
	 * El endpoint para agregar un nuevo perfil.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public Object newDomain(@RequestBody Perfil perfil) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("STRING");
		queryParam01.setValue(perfil.getDescripcion());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "INSERT INTO "+Constants.CAT_PERFIL+" (Descripcion) VALUES (?)");
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
	 * PUT PERFILES
	 * Edita la información de un perfil.
	 */
	@RequestMapping(value = "/{idPerfil}", method = RequestMethod.PUT, produces = "application/json")
	public Object editDomain(@PathVariable Integer idPerfil, @RequestBody Perfil perfil) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("STRING");
		queryParam01.setValue(perfil.getDescripcion());
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("INT");
		queryParam02.setValue(idPerfil.toString());
		queryParams.add(queryParam02);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "UPDATE "+Constants.CAT_PERFIL+" SET Descripcion = ? WHERE Id_Perfil = ?");
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
	 * DELETE PERFILES
	 * Endpoint que elimina un perfil por id.
	 */
	@RequestMapping(value = "/{idPerfil}", method = RequestMethod.DELETE, produces = "application/json")
	public Object removeDomain(@PathVariable Integer idPerfil) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idPerfil.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "DELETE FROM "+Constants.CAT_PERFIL+" WHERE Id_Perfil = ?");
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
