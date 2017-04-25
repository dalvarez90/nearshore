package com.banamex.nearshore.gateway.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.banamex.nearshore.gateway.databasems.Data;
import com.banamex.nearshore.gateway.databasems.DatabaseMicroserviceClientService;
import com.banamex.nearshore.gateway.databasems.ResultBase;

@Service
public class NearshoreUserDetailsService implements UserDetailsService {
	
	@Autowired
	private DatabaseMicroserviceClientService databaseMicroserviceClientService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		
		List<Data> queryParams = new ArrayList<>();
		
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("STRING");
		queryParam01.setValue(username);
		queryParams.add(queryParam01);
		
		requestParams.put("tipoQuery", 2);
		requestParams.put("sql", "SELECT Email, Clave, Descripcion FROM USUARIO, CAT_PERFIL WHERE USUARIO.Id_Perfil = CAT_PERFIL.Id_Perfil AND Email = ?");
		requestParams.put("data", queryParams);
		
		ResultBase resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(e.getMessage(), e);
		}
		
		if (resultBase == null) {
			throw new NullPointerException("No response from db microservice was obtained.");
		}
		
		if (!resultBase.isSuccess()) {
			throw new UsernameNotFoundException(resultBase.getMensaje());
		}
		
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> dataList = (List<HashMap<String, String>>) resultBase.getData();
		
		if (dataList.isEmpty()) {
			throw new UsernameNotFoundException("Username not found.");
		}
		
		HashMap<String, String> userDetailsMap = dataList.get(0);
		
		String email = userDetailsMap.get("Email");
		String password = userDetailsMap.get("Clave");
		String role = userDetailsMap.get("Descripcion");
		
		GrantedAuthority authority = new SimpleGrantedAuthority(role);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		
		UserDetails user = new User(email, password, authorities);
		
		return user;
	}
	
	@FeignClient(name = "mcTDCdbMain")
	public interface DatabaseMicroserviceClient {

		@RequestMapping(value = "/getResultBD", method = RequestMethod.POST, produces = "application/json")
		public ResultBase getResultQuery(@RequestBody HashMap<String, Object> datos);

	}

}
