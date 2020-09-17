package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class DeleteTest {
	
	@Test
	public void Delete() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "delete";
		String path = "/test";
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		Delete delete = new Delete(transaction, path);
		
		assertEquals(delete.getRequest(), expectedRequest);
		assertEquals(delete.getId(), 0);
		assertEquals(delete.getMethod(), method);
		assertEquals(IOUtils.toString(delete.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
