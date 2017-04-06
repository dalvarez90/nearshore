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

import com.banamex.nearshore.catalogsms.domain.Aplicacion;
import com.banamex.nearshore.databasems.Data;
import com.banamex.nearshore.databasems.DatabaseMicroserviceClientService;
import com.banamex.nearshore.databasems.ResultBase;
import com.banamex.nearshore.util.Constants;

@RestController
@RequestMapping("aplicaciones")
public class ApplicationsController{
	
	@Autowired
	private DatabaseMicroserviceClientService databaseClientService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveAllApplications() {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		requestParams.put("tipoQuery", 2);
		requestParams.put("sql", "SELECT * FROM "+Constants.APLICACION);
		
		Object resultBase = databaseClientService.callBase(requestParams);
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveApplicationById(@PathVariable Integer id) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(id.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", 2);
		requestParams.put("sql", "SELECT * FROM "+Constants.APLICACION+" WHERE CSI_ID = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = databaseClientService.callBase(requestParams);
		
		return resultBase;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public Object newCity(@RequestBody Aplicacion aplicacion) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		Data queryParam02 = new Data();
		Data queryParam03 = new Data();
		Data queryParam04 = new Data();
		Data queryParam05 = new Data();
		Data queryParam06 = new Data();
		Data queryParam07 = new Data();
		Data queryParam08 = new Data();
		Data queryParam09 = new Data();
		Data queryParam10 = new Data();
		Data queryParam11 = new Data();
		Data queryParam12 = new Data();
		Data queryParam13 = new Data();
		Data queryParam14 = new Data();
		
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(aplicacion.getCsiId().toString());
		queryParams.add(queryParam01);
		
		queryParam02.setIndex(2);
		String idDominio = (aplicacion.getIdDominio() != null)?aplicacion.getIdDominio().toString():null;
		queryParam02.setType(idDominio != null ? "INT" : "OBJECT");
		queryParam02.setValue(idDominio);
		queryParams.add(queryParam02);
		
		queryParam03.setIndex(3);
		queryParam03.setType("STRING");
		queryParam03.setValue(aplicacion.getPtbId());
		queryParams.add(queryParam03);
		
		queryParam04.setIndex(4);
		queryParam04.setType("STRING");
		queryParam04.setValue(aplicacion.getDescripcionCorta());
		queryParams.add(queryParam04);
		
		queryParam05.setIndex(5);
		queryParam05.setType("STRING");
		queryParam05.setValue(aplicacion.getDescripcionLarga());
		queryParams.add(queryParam05);
		
		queryParam06.setIndex(6);
		String idL1 = aplicacion.getIdL1() != null ? aplicacion.getIdL1().toString() : null;  
		queryParam06.setType((idL1 != null)?"INT":"OBJECT");
		queryParam06.setValue(idL1);
		queryParams.add(queryParam06);
		
		queryParam07.setIndex(7);
		String idL2 = aplicacion.getIdL2() != null ? aplicacion.getIdL2().toString() : null;  
		queryParam07.setType((aplicacion.getIdL2() != null)?"INT":"OBJECT");
		queryParam07.setValue(idL2);
		queryParams.add(queryParam07);
		
		queryParam08.setIndex(8);
		String idL3 = aplicacion.getIdL3() != null ? aplicacion.getIdL3().toString() : null;  
		queryParam08.setType((idL3 != null)?"INT":"OBJECT");
		queryParam08.setValue(idL3);
		queryParams.add(queryParam08);
		
		queryParam09.setIndex(9);
		String idPlat1 = (aplicacion.getIdPlat1() != null)? aplicacion.getIdPlat1().toString():null;
		queryParam09.setType((idPlat1 != null)?"INT":"OBJECT");
		queryParam09.setValue(idPlat1);
		queryParams.add(queryParam09);
		
		queryParam10.setIndex(10);
		String idPlat2 = (aplicacion.getIdPlat2() != null)? aplicacion.getIdPlat2().toString():null;
		queryParam10.setType((idPlat2 != null)?"INT":"OBJECT");
		queryParam10.setValue(idPlat2);
		queryParams.add(queryParam10);
		
		queryParam11.setIndex(11);
		String idPlat3 = (aplicacion.getIdPlat3() != null)? aplicacion.getIdPlat3().toString():null;
		queryParam11.setType((idPlat3 != null)?"INT":"OBJECT");
		queryParam11.setValue(idPlat3);
		queryParams.add(queryParam11);
		
		queryParam12.setIndex(12);
		queryParam12.setType("STRING");
		queryParam12.setValue(aplicacion.getComentarios());
		queryParams.add(queryParam12);
		
		queryParam13.setIndex(13);
		queryParam13.setType("INT");
		queryParam13.setValue(aplicacion.getContactosProveedorProveedor().toString());
		queryParams.add(queryParam13);
		
		queryParam14.setIndex(14);
		String aplicacionCol = (aplicacion.getAplicacionCol() != null)?aplicacion.getAplicacionCol():null;
		queryParam14.setType((aplicacionCol != null)?"STRING" : "OBJECT");
		queryParam14.setValue(aplicacionCol);
		queryParams.add(queryParam14);
		
		requestParams.put("tipoQuery", 1);
		requestParams.put("sql", "INSERT INTO "+Constants.APLICACION+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		requestParams.put("data", queryParams);
		
		Object resultBase = databaseClientService.callBase(requestParams);
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public Object editCity(@PathVariable Integer id, @RequestBody Aplicacion aplicacion) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		Data queryParam01 = new Data();
		Data queryParam02 = new Data();
		Data queryParam03 = new Data();
		Data queryParam04 = new Data();
		Data queryParam05 = new Data();
		Data queryParam06 = new Data();
		Data queryParam07 = new Data();
		Data queryParam08 = new Data();
		Data queryParam09 = new Data();
		Data queryParam10 = new Data();
		Data queryParam11 = new Data();
		Data queryParam12 = new Data();
		Data queryParam13 = new Data();
		Data queryParam14 = new Data();
		
		List<Data> queryParams = new ArrayList<>();
		
		queryParam01.setIndex(1);
		String idDominio = (aplicacion.getIdDominio() != null)?aplicacion.getIdDominio().toString():null;
		queryParam01.setType(idDominio != null ? "INT" : "OBJECT");
		queryParam01.setValue(idDominio);
		queryParams.add(queryParam01);
		
		queryParam02.setIndex(2);
		queryParam02.setType("STRING");
		queryParam02.setValue(aplicacion.getPtbId());
		queryParams.add(queryParam02);
		
		queryParam03.setIndex(3);
		queryParam03.setType("STRING");
		queryParam03.setValue(aplicacion.getDescripcionCorta());
		queryParams.add(queryParam03);
		
		queryParam04.setIndex(4);
		queryParam04.setType("STRING");
		queryParam04.setValue(aplicacion.getDescripcionLarga());
		queryParams.add(queryParam04);
		
		queryParam05.setIndex(5);
		String idL1 = aplicacion.getIdL1() != null ? aplicacion.getIdL1().toString() : null;  
		queryParam05.setType((idL1 != null)?"INT":"OBJECT");
		queryParam05.setValue(idL1);
		queryParams.add(queryParam05);
		
		queryParam06.setIndex(6);
		String idL2 = aplicacion.getIdL2() != null ? aplicacion.getIdL2().toString() : null;  
		queryParam06.setType((aplicacion.getIdL2() != null)?"INT":"OBJECT");
		queryParam06.setValue(idL2);
		queryParams.add(queryParam06);
		
		queryParam07.setIndex(7);
		String idL3 = aplicacion.getIdL3() != null ? aplicacion.getIdL3().toString() : null;  
		queryParam07.setType((idL3 != null)?"INT":"OBJECT");
		queryParam07.setValue(idL3);
		queryParams.add(queryParam07);
		
		queryParam08.setIndex(8);
		String idPlat1 = (aplicacion.getIdPlat1() != null)? aplicacion.getIdPlat1().toString():null;
		queryParam08.setType((idPlat1 != null)?"INT":"OBJECT");
		queryParam08.setValue(idPlat1);
		queryParams.add(queryParam08);
		
		queryParam09.setIndex(9);
		String idPlat2 = (aplicacion.getIdPlat2() != null)? aplicacion.getIdPlat2().toString():null;
		queryParam09.setType((idPlat2 != null)?"INT":"OBJECT");
		queryParam09.setValue(idPlat2);
		queryParams.add(queryParam09);
		
		queryParam10.setIndex(10);
		String idPlat3 = (aplicacion.getIdPlat3() != null)? aplicacion.getIdPlat3().toString():null;
		queryParam10.setType((idPlat3 != null)?"INT":"OBJECT");
		queryParam10.setValue(idPlat3);
		queryParams.add(queryParam10);
		
		queryParam11.setIndex(11);
		queryParam11.setType("STRING");
		queryParam11.setValue(aplicacion.getComentarios());
		queryParams.add(queryParam11);
		
		queryParam12.setIndex(12);
		queryParam12.setType("INT");
		queryParam12.setValue(aplicacion.getContactosProveedorProveedor().toString());
		queryParams.add(queryParam12);
		
		queryParam13.setIndex(13);
		String aplicacionCol = (aplicacion.getAplicacionCol() != null)?aplicacion.getAplicacionCol():null;
		queryParam13.setType((aplicacionCol != null)?"STRING" : "OBJECT");
		queryParam13.setValue(aplicacionCol);
		queryParams.add(queryParam13);
		
		queryParam14.setIndex(14);
		queryParam14.setType("INT");
		queryParam14.setValue(id.toString());
		queryParams.add(queryParam14);
		
		requestParams.put("tipoQuery", 1);
		requestParams.put("sql", "UPDATE "+Constants.APLICACION+" SET Id_Dominio = ? , PTB_ID = ? , DescripcionCorta = ? , DescripcionLarga = ? , Id_L1 = ? , Id_L2 = ? , Id_L3 = ? , Id_Plat1 = ? , Id_Plat2 = ? , Id_Plat3 = ? , Comentarios = ? , `Contactos Proveedor_Proveedor` = ? , APLICACIONcol = ? WHERE CSI_ID = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = databaseClientService.callBase(requestParams);
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public Object removeCity(@PathVariable Integer id) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(id.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", 1);
		requestParams.put("sql", "DELETE FROM "+Constants.APLICACION+" WHERE CSI_ID = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = databaseClientService.callBase(requestParams);
		
		return resultBase;
	}
	
	@FeignClient(name = "mcTDCdbMain")
	public interface DatabaseMicroServiceClient {
		
		@RequestMapping(value = "/getResultBD", method = RequestMethod.POST, produces = "application/json")
		public ResultBase getResultQuery(@RequestBody HashMap<String, Object> datos);
		
	}

}
