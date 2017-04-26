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
import com.banamex.nearshore.catalogsms.exception.NearshoreDatabaseMicroserviceException;
import com.banamex.nearshore.databasems.Data;
import com.banamex.nearshore.databasems.DatabaseMicroserviceClientService;
import com.banamex.nearshore.databasems.ResultBase;
import com.banamex.nearshore.util.Constants;

@RestController
@RequestMapping("proveedores")
public class ProveedoresController {

	@Autowired
	private DatabaseMicroserviceClientService databaseMicroserviceClientService;

	/*
	 * GET PROVEEDOR
	 * El endpoint devuelve un listado con los proveedores de aplicaciones de CitiBanamex.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveAllProovedores() {
		HashMap<String, Object> requestParams = new HashMap<>();

		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT ID, DESCRIPCION FROM "+Constants.CAT_PROVEEDOR);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}

	/*
	 * GET PROVEEDOR
	 * Endpoint que devuelve un proveedor por id.
	 */
	@RequestMapping(value = "/{idProveedor}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveProveedorById(@PathVariable Integer idProveedor) {

		HashMap<String, Object> requestParams = new HashMap<>();
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idProveedor.toString());
		queryParams.add(queryParam01);

		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT ID, DESCRIPCION FROM "+Constants.CAT_PROVEEDOR+" WHERE ID = ?");
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
	 * POST PROVEEDOR
	 * Endpoint que agreda un nuevo proveedor.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public Object newProveedor(@RequestBody Proveedor proveedor) {

		HashMap<String, Object> requestParams = new HashMap<String, Object>();

		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("STRING");
		queryParam01.setValue(proveedor.getDescripcion());
		queryParams.add(queryParam01);

		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "INSERT INTO "+Constants.CAT_PROVEEDOR+" (Descripcion) values (?)");
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
	 * PUT PROVEEDOR
	 * Endpoint que actualiza la informacion de un proveedor.
	 */
	@RequestMapping(value = "/{idProveedor}", method = RequestMethod.PUT, produces = "application/json")
	public Object editProveedor(@PathVariable Integer idProveedor, @RequestBody Proveedor proveedor) {

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
		queryParam02.setValue(idProveedor.toString());
		queryParams.add(queryParam02);

		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "UPDATE "+Constants.CAT_PROVEEDOR+" SET Descripcion = ? WHERE id = ?");
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
	 * DELETE PROVEEDOR
	 * Endpoint que elimina un proveedor.
	 */
	@RequestMapping(value = "/{idProveedor}", method = RequestMethod.DELETE, produces = "application/json")
	public Object removeProveedor(@PathVariable Integer idProveedor) {

		HashMap<String, Object> requestParams = new HashMap<String, Object>();

		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idProveedor.toString());
		queryParams.add(queryParam01);

		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "DELETE FROM "+Constants.CAT_PROVEEDOR+" WHERE Id = ?");
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
