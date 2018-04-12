package com.springboot.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

public class JsonUtil {

	public static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	public static ObjectMapper objectmapper= new ObjectMapper();

	/**
	 * 返回json字符串
	 * @param obj
	 * @return
	 */
	public static  String  writeValueAsString(Object obj)
	{
		String json="";

		try {	
			objectmapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));  

			json = objectmapper.writeValueAsString(obj);
		}
		catch (Exception e)
		{
			logger.info("writeValueAsString",e);
		}

		return json;
	}
	
	
	/**
	 * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
	 * (1)转换为普通JavaBean：readValue(json,Student.class)
	 * (2)转换为List,如List<Student>,将第二个参数传递为Student
	 * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
	 * 
	 * @param jsonStr
	 * @param valueType
	 * @return
	 */
	public static <T> T jsonToBean(String jsonStr, Class<T> valueType) {

		try {
			
			objectmapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));  
			
			return objectmapper.readValue(jsonStr, valueType);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
