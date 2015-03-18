package com.x2.gsm.client.util;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.internal.Primitives;

public class JSonUtil {
	final static String dateformat = "yyyy-MM-dd'T'hh:ss:mm";
//	final static String dateformat = "yyyyMMddhhssmm";	
	public static <T> T jsonToObject(String jsonString, Class<T> classOfT) {
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder()
	     .registerTypeHierarchyAdapter(Date.class,  
	             new JsonSerializer<Date>() {
	    	 		@Override
	                 public JsonElement serialize(Date src,  
	                         Type typeOfSrc,  
	                         JsonSerializationContext context) {  
	                     SimpleDateFormat format = new SimpleDateFormat(  
	                             dateformat);  
	                     return new JsonPrimitive(format.format(src));  
	                 }
	             }).setDateFormat(dateformat).create(); 
		
		Object object = gson.fromJson(jsonString, (Type) classOfT);
		return Primitives.wrap(classOfT).cast(object);
	}
}
