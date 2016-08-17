/**
 * 
 */
package com.nag.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.CharEncoding;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtil {

	public static void writeJSONResponse(HttpServletResponse response, Object jsonObject) throws IOException {
		String toJson = convertToJSON(jsonObject);
		response.setContentType("application/json; charset=" + CharEncoding.UTF_8);
		response.getWriter().print(toJson);
		response.flushBuffer();
	}

	public static String convertToJSON(Object jsonObject) {
		Gson gson = new GsonBuilder().create();
		String toJson = gson.toJson(jsonObject);
		return toJson;
	}	
}