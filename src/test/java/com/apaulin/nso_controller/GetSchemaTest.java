package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class GetSchemaTest {
	
	@Test
	public void GetSchema() throws UnsupportedEncodingException, IOException {
		int transaction = 0;

		String method = "get_schema";
		String path = "/test";
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		GetSchema getSchema = new GetSchema(transaction, path);
		
		assertEquals(getSchema.getRequest(), expectedRequest);
		assertEquals(getSchema.getId(), 0);
		assertEquals(getSchema.getMethod(), method);
		assertEquals(IOUtils.toString(getSchema.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
