package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class GetTemplateVariablesTest {
	
	@Test
	public void GetTemplateVariables() throws UnsupportedEncodingException, IOException {
		int transaction = 0;

		String method = "get_template_variables";
		String name = "test";
		String expectedRequest = "{\"th\": 0,\"name\": \""+name+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		GetTemplateVariables getTemplateVariables = new GetTemplateVariables(transaction, name);
		
		assertEquals(getTemplateVariables.getRequest(), expectedRequest);
		assertEquals(getTemplateVariables.getId(), 0);
		assertEquals(getTemplateVariables.getMethod(), method);
		assertEquals(IOUtils.toString(getTemplateVariables.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
