package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class ExistTest {
	
	@Test
	public void Exists() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "exists";
		String path = "/test";
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		Exists exists = new Exists(transaction, path);
		
		assertEquals(exists.getRequest(), expectedRequest);
		assertEquals(exists.getId(), 0);
		assertEquals(exists.getMethod(), method);
		assertEquals(IOUtils.toString(exists.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
