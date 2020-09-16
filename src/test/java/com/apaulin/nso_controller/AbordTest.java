package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class AbordTest {
	
	@Test
	public void Abord() throws UnsupportedEncodingException, IOException {
		int transaction = 0;

		String method = "abort";
		String expectedRequest = "{\"id\": 0}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		Abort abord = new Abort(transaction);
		
		assertEquals(abord.getRequest(), expectedRequest);
		assertEquals(abord.getId(), 0);
		assertEquals(abord.getMethod(), method);

		assertEquals(IOUtils.toString(abord.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
