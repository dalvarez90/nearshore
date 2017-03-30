package com.banamex.nearshore.client;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.banamex.nearshore.catalogsms.controller.DominiosController.DatabaseMicroServiceClient;
import com.banamex.nearshore.util.ResultBase;

@Service
public class DatabaseClientService {
	
	@Autowired
	private DatabaseMicroServiceClient dbMicroServiceClient;

	public ResultBase callBase(@RequestBody HashMap<String,Object> datos) {  
		return dbMicroServiceClient.getResultQuery(datos);  
	}
	
}