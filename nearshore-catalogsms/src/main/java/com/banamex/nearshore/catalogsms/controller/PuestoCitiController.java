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

import com.banamex.nearshore.catalogsms.domain.Proveedor;
import com.banamex.nearshore.client.DatabaseMicroserviceClientService;
import com.banamex.nearshore.util.Data;
import com.banamex.nearshore.util.ResultBase;

@RestController
@RequestMapping("puestos/citi")
public class PuestoCitiController {

	@Autowired
	private DatabaseMicroserviceClientService databaseMicroserviceClientService;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveAllPuestosCiti() {
		HashMap<String, Object> requestParams = new HashMap<>();

		requestParams.put("tipoQuery", 2);
		requestParams.put("sql", "SELECT ID, DESCRIPCION FROM CAT_PUESTOCITI");
		Object resp = databaseMicroserviceClientService.callBase(requestParams);
		return resp;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Object retrievePuestoCitiById(@PathVariable Integer id) {

		HashMap<String, Object> requestParams = new HashMap<>();
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(id.toString());
		queryParams.add(queryParam01);

		requestParams.put("tipoQuery", 2);
		requestParams.put("sql", "SELECT ID, DESCRIPCION FROM CAT_PUESTOCITI WHERE ID = ?");
		requestParams.put("data", queryParams);
		Object resultBase = databaseMicroserviceClientService.callBase(requestParams);
		return resultBase;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public Object newProveedor(@RequestBody Proveedor proveedor) {

		HashMap<String, Object> requestParams = new HashMap<String, Object>();

		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(proveedor.getId().toString());
		queryParams.add(queryParam01);

		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("STRING");
		queryParam02.setValue(proveedor.getDescripcion());
		queryParams.add(queryParam02);

		requestParams.put("tipoQuery", 1);
		requestParams.put("sql", "INSERT INTO CAT_PUESTOCITI (ID, DESCRIPCION) values (?,?)");
		requestParams.put("data", queryParams);

		Object resultBase = databaseMicroserviceClientService.callBase(requestParams);
		return resultBase;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public Object editProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedor) {

		HashMap<String, Object> requestParams = new HashMap<String, Object>();

		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("STRING");
		queryParam01.setValue(proveedor.getDescripcion());
		queryParams.add(queryParam01);

		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("INT");
		queryParam02.setValue(id.toString());
		queryParams.add(queryParam02);

		requestParams.put("tipoQuery", 1);
		requestParams.put("sql", "UPDATE CAT_PUESTOCITI SET Descripcion = ? WHERE id = ?");
		requestParams.put("data", queryParams);

		Object resultBase = databaseMicroserviceClientService.callBase(requestParams);
		return resultBase;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public Object removeProveedor(@PathVariable Integer id) {

		HashMap<String, Object> requestParams = new HashMap<String, Object>();

		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(id.toString());
		queryParams.add(queryParam01);

		requestParams.put("tipoQuery", 1);
		requestParams.put("sql", "DELETE FROM CAT_PUESTOCITI WHERE Id = ?");
		requestParams.put("data", queryParams);

		Object resultBase = databaseMicroserviceClientService.callBase(requestParams);

		return resultBase;
	}

	@FeignClient(name = "mcTDCdbMain")
	public interface DatabaseMicroserviceClient {

		@RequestMapping(value = "/getResultBD", method = RequestMethod.POST, produces = "application/json")
		public ResultBase getResultQuery(@RequestBody HashMap<String, Object> datos);

	}
	
}
