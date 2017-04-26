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
@RequestMapping("puestos/proveedores")
public class PuestoProveedorController {
	
	@Autowired
	private DatabaseMicroserviceClientService databaseMicroserviceClientService;

	/*
	 * GET PUESTO(PROVEEDOR)
	 * El endpoint devuelve un listado de los nombres de los puestos de trabajo registrados para el personal de los proveedores.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveAllPuestosCiti() {
		HashMap<String, Object> requestParams = new HashMap<>();

		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT ID, DESCRIPCION FROM "+Constants.CAT_PUESTO_PROVEEDOR);
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}

	/*
	 * GET PUESTO(PROVEEDOR)
	 * Enpoint que obtiene el puesto de un proveedor por idPuesto.
	 */
	@RequestMapping(value = "/{idPuesto}", method = RequestMethod.GET, produces = "application/json")
	public Object retrievePuestoCitiById(@PathVariable Integer idPuesto) {

		HashMap<String, Object> requestParams = new HashMap<>();
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idPuesto.toString());
		queryParams.add(queryParam01);

		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT ID, DESCRIPCION FROM "+Constants.CAT_PUESTO_PROVEEDOR+" WHERE ID = ?");
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
	 * POST PUESTO(PROVEEDOR)
	 * Endpoint que agrega un puesto a un determinado proveedor.
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
		requestParams.put("sql", "INSERT INTO "+Constants.CAT_PUESTO_PROVEEDOR+" (DESCRIPCION) values (?)");
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
	 * PUT PUESTO(PROVEEDOR)
	 * Endpoint que actualiza un puesto de un determinado proveedor.
	 */
	@RequestMapping(value = "/{idPuesto}", method = RequestMethod.PUT, produces = "application/json")
	public Object editProveedor(@PathVariable Integer idPuesto, @RequestBody Proveedor proveedor) {

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
		queryParam02.setValue(idPuesto.toString());
		queryParams.add(queryParam02);

		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "UPDATE "+Constants.CAT_PUESTO_PROVEEDOR+" SET Descripcion = ? WHERE id = ?");
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
	 * DELETE PUESTO(PROVEEDOR)
	 * Enpoint que elimina un puesto de un proveedor.
	 */
	@RequestMapping(value = "/{idPuesto}", method = RequestMethod.DELETE, produces = "application/json")
	public Object removeProveedor(@PathVariable Integer idPuesto) {

		HashMap<String, Object> requestParams = new HashMap<String, Object>();

		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idPuesto.toString());
		queryParams.add(queryParam01);

		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "DELETE FROM "+Constants.CAT_PUESTO_PROVEEDOR+" WHERE Id = ?");
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
