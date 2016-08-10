package com.nag;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.CharEncoding;

public class ServletUtil {

	public static void writeJSONResponse(HttpServletResponse response, Object jsonObject) throws IOException {
		String toJson = JSONUtil.convertToJSON(jsonObject);
		response.setContentType("application/json; charset=" + CharEncoding.UTF_8);
		response.getWriter().print(toJson);
		response.flushBuffer();
	}
}
