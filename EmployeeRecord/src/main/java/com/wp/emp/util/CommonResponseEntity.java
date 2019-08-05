package com.wp.emp.util;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service("commonResponseEntity")
public class CommonResponseEntity {

    public LinkedHashMap<String,Object> responseEntity(Object status,Object msg,Object code) {
		LinkedHashMap<String, Object> resultMap = new LinkedHashMap<String, Object>();
		resultMap.put(ConstantVariables.REQUEST_STATUS,status);
		resultMap.put(ConstantVariables.RESPONSE_MSG,msg);
		resultMap.put(ConstantVariables.RESPONSE_CODE,code);
		return resultMap;
	}
}
