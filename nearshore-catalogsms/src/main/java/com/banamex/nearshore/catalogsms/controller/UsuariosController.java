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
import com.banamex.nearshore.catalogsms.exception.NearshoreDatabaseMicroserviceException;
import com.banamex.nearshore.databasems.Data;
import com.banamex.nearshore.databasems.DatabaseMicroserviceClientService;
import com.banamex.nearshore.databasems.ResultBase;
import com.banamex.nearshore.util.Constants;


@RestController
@RequestMapping("usuarios")
public class UsuariosController {
	
	@Autowired
	private DatabaseMicroserviceClientService databaseMicroserviceClientService;
	
	/*
	 * GET USUARIOS
	 * Endpoint para obtiene una lista de Usuario que tienen acceso al sistema.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveAllDomains() {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT u.Id_usuarios, u.Email, u.Primer_Nombre, u.Segundo_Nombre,"
				+ "u.Apellido_Paterno, u.ApellidoMaterno, u.Clave,u.Activo,u.Dominios,u.Proveedores,"
				+ "p.Id_Perfil,p.Descripcion FROM "+Constants.USUARIO+" u join "+Constants.CAT_PERFIL+" p on u.Id_Perfil=p.Id_Perfil");
		
		Object resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}
		
		return resultBase;
	}
	
	/*
	 * GET USUARIOS
	 * Endpoint para obtiene un usuario que tienen acceso al sistema por idUsuario.
	 */
	@RequestMapping(value = "/{idUsuario}", method = RequestMethod.GET, produces = "application/json")
	public Object retrieveDomainById(@PathVariable Integer idUsuario) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idUsuario.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "SELECT u.Id_usuarios, u.Email, u.Primer_Nombre, u.Segundo_Nombre,"
				+ "u.Apellido_Paterno, u.ApellidoMaterno, u.Clave,u.Activo,u.Dominios,u.Proveedores,"
				+ "p.Id_Perfil,p.Descripcion FROM "+Constants.USUARIO+" u join "+Constants.CAT_PERFIL+" p on u.Id_Perfil=p.Id_Perfil"
				+ " where u.Id_Usuarios = ?");
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
	 * POST USUARIOS
	 * Endpoint para insertar un usuario que tienen acceso al sistema.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public Object newDomain(@RequestBody Usuario usuario) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("STRING");
		queryParam01.setValue(usuario.getEmail());
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("STRING");
		queryParam02.setValue(usuario.getPrimerNombre());
		queryParams.add(queryParam02);
		
		Data queryParam03 = new Data();
		queryParam03.setIndex(3);
		queryParam03.setType("STRING");
		queryParam03.setValue(usuario.getSegundoNombre());
		queryParams.add(queryParam03);
		
		Data queryParam04 = new Data();
		queryParam04.setIndex(4);
		queryParam04.setType("STRING");
		queryParam04.setValue(usuario.getApellidoPaterno());
		queryParams.add(queryParam04);
		
		Data queryParam05 = new Data();
		queryParam05.setIndex(5);
		queryParam05.setType("STRING");
		queryParam05.setValue(usuario.getApellidoMaterno());
		queryParams.add(queryParam05);
		
		Data queryParam06 = new Data();
		queryParam06.setIndex(6);
		queryParam06.setType("STRING");
		queryParam06.setValue(usuario.getClave());
		queryParams.add(queryParam06);
		
		Data queryParam07 = new Data();
		queryParam07.setIndex(7);
		queryParam07.setType("INT");
		queryParam07.setValue(usuario.getPerfil().getId().toString());
		queryParams.add(queryParam07);
		
		Data queryParam08 = new Data();
		queryParam08.setIndex(8);
		queryParam08.setType("STRING");
		queryParam08.setValue(usuario.isActivo().toString());
		queryParams.add(queryParam08);
		
		Data queryParam09 = new Data();
		queryParam09.setIndex(9);
		queryParam09.setType("STRING");
		queryParam09.setValue(usuario.getDominios());
		queryParams.add(queryParam09);
		
		Data queryParam10 = new Data();
		queryParam10.setIndex(10);
		queryParam10.setType("STRING");
		queryParam10.setValue(usuario.getProveedores());
		queryParams.add(queryParam10);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "INSERT INTO "+Constants.USUARIO+" (email,"
				+ "Primer_Nombre,Segundo_Nombre,Apellido_Paterno,ApellidoMaterno,"
				+ "Clave,Id_perfil,Activo,Dominios,Proveedores) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
	 * PUT USUARIOS
	 * Endpoint para la edición de la información de un usuario del sistema.
	 */
	@RequestMapping(value = "/{idUsuario}", method = RequestMethod.PUT, produces = "application/json")
	public Object editDomain(@PathVariable Integer idUsuario, @RequestBody Usuario usuario) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("STRING");
		queryParam01.setValue(usuario.getEmail());
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("STRING");
		queryParam02.setValue(usuario.getPrimerNombre());
		queryParams.add(queryParam02);
		
		Data queryParam03 = new Data();
		queryParam03.setIndex(3);
		queryParam03.setType("STRING");
		queryParam03.setValue(usuario.getSegundoNombre());
		queryParams.add(queryParam03);
		
		Data queryParam04 = new Data();
		queryParam04.setIndex(4);
		queryParam04.setType("STRING");
		queryParam04.setValue(usuario.getApellidoPaterno());
		queryParams.add(queryParam04);
		
		Data queryParam05 = new Data();
		queryParam05.setIndex(5);
		queryParam05.setType("STRING");
		queryParam05.setValue(usuario.getApellidoMaterno());
		queryParams.add(queryParam05);
		
		Data queryParam06 = new Data();
		queryParam06.setIndex(6);
		queryParam06.setType("STRING");
		queryParam06.setValue(usuario.getClave());
		queryParams.add(queryParam06);
		
		Data queryParam07 = new Data();
		queryParam07.setIndex(7);
		queryParam07.setType("INT");
		queryParam07.setValue(usuario.getPerfil().getId().toString());
		queryParams.add(queryParam07);
		
		Data queryParam08 = new Data();
		queryParam08.setIndex(8);
		queryParam08.setType("INT");
		queryParam08.setValue(usuario.isActivo().toString());
		queryParams.add(queryParam08);
		
		Data queryParam09 = new Data();
		queryParam09.setIndex(9);
		queryParam09.setType("STRING");
		queryParam09.setValue(usuario.getDominios());
		queryParams.add(queryParam09);
		
		Data queryParam10 = new Data();
		queryParam10.setIndex(10);
		queryParam10.setType("STRING");
		queryParam10.setValue(usuario.getProveedores());
		queryParams.add(queryParam10);
		
		Data queryParam11 = new Data();
		queryParam11.setIndex(11);
		queryParam11.setType("INT");
		queryParam11.setValue(idUsuario.toString());
		queryParams.add(queryParam11);

		
		requestParams.put("tipoQuery", 1);
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "UPDATE "+Constants.USUARIO+" SET Email = ?,Primer_Nombre = ?,"
				+ "Segundo_Nombre = ?, Apellido_Paterno = ?,ApellidoMaterno = ?,"
				+ "Clave = ?, Id_Perfil = ?, Activo= ?, Dominios = ?, Proveedores= ?"
				+ " WHERE Id_Usuarios = ?");
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
	 * DELETE USUARIOS
	 * Endpoint para eliminar la cuenta de un usuario del sistema.
	 */
	@RequestMapping(value = "/{idUsuario}", method = RequestMethod.DELETE, produces = "application/json")
	public Object removeDomain(@PathVariable Integer idUsuario) {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("INT");
		queryParam01.setValue(idUsuario.toString());
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", Constants.UPDATE_STATEMENT_TYPE);
		requestParams.put("sql", "DELETE FROM "+Constants.USUARIO+" WHERE Id_Usuarios = ?");
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
