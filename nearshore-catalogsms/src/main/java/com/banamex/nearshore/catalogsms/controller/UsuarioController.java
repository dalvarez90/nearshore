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

import com.banamex.nearshore.catalogsms.domain.Usuario;
import com.banamex.nearshore.databasems.Data;
import com.banamex.nearshore.databasems.DatabaseMicroserviceClientService;
import com.banamex.nearshore.databasems.ResultBase;


@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	
	@Autowired
	private DatabaseMicroserviceClientService databaseClientService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveAllDomains() {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		requestParams.put("tipoQuery", 2);
		requestParams.put("sql", "SELECT u.Id_usuarios, u.Email, u.PrimerNombre, u.SegundoNombre,"
				+ "u.ApellidoPaterno, u.ApellidoMaterno, u.Clave,u.Activo,u.Dominios,u.Proveedores,"
				+ "p.Id_Perfil,p.Descripcion FROM usuario u join cat_perfil p on u.Id_Perfil=p.Id_Perfil");
		
		Object resultBase = databaseClientService.callBase(requestParams);
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveDomainById(@PathVariable Integer id) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(id.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", 2);
		requestParams.put("sql", "SELECT u.Id_usuarios, u.Email, u.PrimerNombre, u.SegundoNombre,"
				+ "u.ApellidoPaterno, u.ApellidoMaterno, u.Clave,u.Activo,u.Dominios,u.Proveedores,"
				+ "p.Id_Perfil,p.Descripcion FROM usuario u join cat_perfil p on u.Id_Perfil=p.Id_Perfil"
				+ " where u.Id_Usuarios = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = databaseClientService.callBase(requestParams);
		
		return resultBase;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public Object newDomain(@RequestBody Usuario usuario) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(usuario.getIdUsuarios().toString());
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("STRING");
		queryParam02.setValue(usuario.getEmail());
		queryParams.add(queryParam02);
		
		Data queryParam03 = new Data();
		queryParam03.setIndex(2);
		queryParam03.setType("STRING");
		queryParam03.setValue(usuario.getPrimerNombre());
		queryParams.add(queryParam03);
		
		Data queryParam04 = new Data();
		queryParam04.setIndex(2);
		queryParam04.setType("STRING");
		queryParam04.setValue(usuario.getSegundoNombre());
		queryParams.add(queryParam04);
		
		Data queryParam05 = new Data();
		queryParam05.setIndex(2);
		queryParam05.setType("STRING");
		queryParam05.setValue(usuario.getApellidoPaterno());
		queryParams.add(queryParam05);
		
		Data queryParam06 = new Data();
		queryParam06.setIndex(2);
		queryParam06.setType("STRING");
		queryParam06.setValue(usuario.getApellidoPaterno());
		queryParams.add(queryParam06);
		
		Data queryParam07 = new Data();
		queryParam07.setIndex(2);
		queryParam07.setType("STRING");
		queryParam07.setValue(usuario.getClave());
		queryParams.add(queryParam07);
		
		Data queryParam08 = new Data();
		queryParam08.setIndex(2);
		queryParam08.setType("INT");
		queryParam08.setValue(usuario.getPerfil().getId().toString());
		queryParams.add(queryParam08);
		
		Data queryParam09 = new Data();
		queryParam09.setIndex(2);
		queryParam09.setType("STRING");
		queryParam09.setValue(usuario.isActivo().toString());
		queryParams.add(queryParam09);
		
		Data queryParam10 = new Data();
		queryParam10.setIndex(2);
		queryParam10.setType("STRING");
		queryParam10.setValue(usuario.getDominios());
		queryParams.add(queryParam10);
		
		Data queryParam11 = new Data();
		queryParam11.setIndex(2);
		queryParam11.setType("STRING");
		queryParam11.setValue(usuario.getProveedores());
		queryParams.add(queryParam11);
		
		requestParams.put("tipoQuery", 1);
		requestParams.put("sql", "INSERT INTO usuario VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		requestParams.put("data", queryParams);
		
		Object resultBase = databaseClientService.callBase(requestParams);
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public Object editDomain(@PathVariable Integer id, @RequestBody Usuario usuario) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(usuario.getIdUsuarios().toString());
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("STRING");
		queryParam02.setValue(usuario.getEmail());
		queryParams.add(queryParam02);
		
		Data queryParam03 = new Data();
		queryParam03.setIndex(2);
		queryParam03.setType("STRING");
		queryParam03.setValue(usuario.getPrimerNombre());
		queryParams.add(queryParam03);
		
		Data queryParam04 = new Data();
		queryParam04.setIndex(2);
		queryParam04.setType("STRING");
		queryParam04.setValue(usuario.getSegundoNombre());
		queryParams.add(queryParam04);
		
		Data queryParam05 = new Data();
		queryParam05.setIndex(2);
		queryParam05.setType("STRING");
		queryParam05.setValue(usuario.getApellidoPaterno());
		queryParams.add(queryParam05);
		
		Data queryParam06 = new Data();
		queryParam06.setIndex(2);
		queryParam06.setType("STRING");
		queryParam06.setValue(usuario.getApellidoPaterno());
		queryParams.add(queryParam06);
		
		Data queryParam07 = new Data();
		queryParam07.setIndex(2);
		queryParam07.setType("STRING");
		queryParam07.setValue(usuario.getClave());
		queryParams.add(queryParam07);
		
		Data queryParam08 = new Data();
		queryParam08.setIndex(2);
		queryParam08.setType("INT");
		queryParam08.setValue(usuario.getPerfil().getId().toString());
		queryParams.add(queryParam08);
		
		Data queryParam09 = new Data();
		queryParam09.setIndex(2);
		queryParam09.setType("STRING");
		queryParam09.setValue(usuario.isActivo().toString());
		queryParams.add(queryParam09);
		
		Data queryParam10 = new Data();
		queryParam10.setIndex(2);
		queryParam10.setType("STRING");
		queryParam10.setValue(usuario.getDominios());
		queryParams.add(queryParam10);
		
		Data queryParam11 = new Data();
		queryParam11.setIndex(2);
		queryParam11.setType("STRING");
		queryParam11.setValue(usuario.getProveedores());
		queryParams.add(queryParam11);
		
		requestParams.put("tipoQuery", 1);
		requestParams.put("sql", "UPDATE usuario SET email = ?,PrimerNombre = ?,"
				+ "SegundoNombre = ?, ApellidoPaterno = ?,ApellidoMaterno = ?,"
				+ "Clave = ?, id_Perfil = ?, Activo= ?, Dominios = ?, Proveedores= ?"
				+ " WHERE Id_Usuarios = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = databaseClientService.callBase(requestParams);
		
		return resultBase;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public Object removeDomain(@PathVariable Integer id) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(id.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", 1);
		requestParams.put("sql", "DELETE FROM usuario WHERE Id_Usuarios = ?");
		requestParams.put("data", queryParams);
		
		Object resultBase = databaseClientService.callBase(requestParams);
		
		return resultBase;
	}
	
	@FeignClient(name = "mcTDCdbMain")
	public interface DatabaseMicroserviceClient {
		
		@RequestMapping(value = "/getResultBD", method = RequestMethod.POST, produces = "application/json")
		public ResultBase getResultQuery(@RequestBody HashMap<String, Object> datos);
		
	}

}
