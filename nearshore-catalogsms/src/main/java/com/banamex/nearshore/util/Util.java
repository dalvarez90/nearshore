package com.banamex.nearshore.util;

import com.banamex.nearshore.databasems.Data;

public class Util {
	
	public static Data createDataObj( Object value, String type, int index){
		String valorparseado = null;
		if(value == null){
			type = "OBJECT";
		}
		else{
			if(!value.getClass().getName().equals("java.lang.String")){
				valorparseado = value.toString();
			}else{
				valorparseado = (String) value;
			}
		}
		return new Data(valorparseado, type, index);
	}
	
}