package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class CreateTest {
	
	@Test
	public void Create() throws UnsupportedEncodingException, IOException {
		int transaction = 0;

		String method = "create";
		String path = "/test";
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		Create create = new Create(transaction, path);
		
		assertEquals(create.getRequest(), expectedRequest);
		assertEquals(create.getId(), 0);
		assertEquals(create.getMethod(), method);
		assertEquals(IOUtils.toString(create.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
