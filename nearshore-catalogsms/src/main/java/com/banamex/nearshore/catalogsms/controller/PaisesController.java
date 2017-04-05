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

import com.banamex.nearshore.catalogsms.domain.Pais;
import com.banamex.nearshore.databasems.Data;
import com.banamex.nearshore.databasems.ResultBase;

@RestController
@RequestMapping("paises")
public class PaisesController {
	
	@Autowired
	DbMicroserviceClient databaseMicroserviceClient;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveAllCountry() {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		requestParams.put("tipoQuery", 2);
		requestParams.put("sql", "SELECT ID, DESCRIPCION FROM CAT_PAIS");
		
		Object resultBase = databaseMicroserviceClient.getResultQuery(requestParams);
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveCountryById(@PathVariable Integer id) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(id.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", 2);
		requestParams.put("sql", "SELECT ID, DESCRIPCION FROM CAT_PAIS WHERE ID = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = databaseMicroserviceClient.getResultQuery(requestParams);
		
		return resultBase;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public Object newCountry(@RequestBody Pais pais) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(pais.getId().toString());
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("STRING");
		queryParam02.setValue(pais.getDescripcion());
		queryParams.add(queryParam02);
		
		requestParams.put("tipoQuery", 1);
		requestParams.put("sql", "INSERT INTO CAT_PAIS (Id, Descripcion) VALUES (?, ?)");
		requestParams.put("data", queryParams);
		
		Object resultBase = databaseMicroserviceClient.getResultQuery(requestParams);
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public Object editCountry(@PathVariable Integer id, @RequestBody Pais pais) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("STRING");
		queryParam01.setValue(pais.getDescripcion());
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("INT");
		queryParam02.setValue(id.toString());
		queryParams.add(queryParam02);
		
		requestParams.put("tipoQuery", 1);
		requestParams.put("sql", "UPDATE CAT_PAIS SET Descripcion = ? WHERE Id = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = databaseMicroserviceClient.getResultQuery(requestParams);
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public Object removeCountry(@PathVariable Integer id) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(id.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", 1);
		requestParams.put("sql", "DELETE FROM CAT_PAIS WHERE Id = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = databaseMicroserviceClient.getResultQuery(requestParams);
		
		return resultBase;
	}
	
	//@FeignClient(name = "dbMcMysql")
	@FeignClient(name = "mcTDCdbMain")
	public interface DbMicroserviceClient {
		
		@RequestMapping(value = "/getResultBD", method = RequestMethod.POST, produces = "application/json")
		public ResultBase getResultQuery(@RequestBody HashMap<String, Object> datos);
		
	}
	
}
