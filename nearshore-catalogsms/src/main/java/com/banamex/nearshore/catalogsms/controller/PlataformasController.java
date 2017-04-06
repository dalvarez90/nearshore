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

import com.banamex.nearshore.catalogsms.domain.Plataforma;
import com.banamex.nearshore.catalogsms.exception.NearshoreDatabaseMicroserviceException;
import com.banamex.nearshore.databasems.Data;
import com.banamex.nearshore.databasems.DatabaseMicroserviceClientService;
import com.banamex.nearshore.databasems.ResultBase;
import com.banamex.nearshore.util.Constants;

@RestController
@RequestMapping("plataformas")
public class PlataformasController {
	
	@Autowired
	private DatabaseMicroserviceClientService databaseMicroserviceClientService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveAllPlatforms() {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM "+Constants.CAT_PLATAFORMA);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Object retrievePlatformById(@PathVariable Integer id) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(id.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT * FROM "+Constants.CAT_PLATAFORMA+" WHERE ID = ?");
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
	public Object newPlatform(@RequestBody Plataforma plataforma) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(plataforma.getId().toString());
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("STRING");
		queryParam02.setValue(plataforma.getDescripcion().toString());
		queryParams.add(queryParam02);
		
		Data queryParam03 = new Data();
		queryParam03.setIndex(3);
		queryParam03.setType("STRING");
		queryParam03.setValue(plataforma.getComentarios().toString());
		queryParams.add(queryParam03);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "INSERT INTO "+Constants.CAT_PLATAFORMA+" (Id, Descripcion, Comentarios) VALUES (?, ?, ?)");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public Object editPlatform(@PathVariable Integer id, @RequestBody Plataforma plataforma) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("STRING");
		queryParam01.setValue(plataforma.getDescripcion());
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("STRING");
		queryParam02.setValue(plataforma.getComentarios());
		queryParams.add(queryParam02);
		
		Data queryParam03 = new Data();
		queryParam03.setIndex(3);
		queryParam03.setType("INT");
		queryParam03.setValue(id.toString());
		queryParams.add(queryParam03);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "UPDATE "+Constants.CAT_PLATAFORMA+" SET Descripcion = ? , Comentarios = ? WHERE Id = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public Object removePlatform(@PathVariable Integer id) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(id.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "DELETE FROM "+Constants.CAT_PLATAFORMA+" WHERE Id = ?");
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
