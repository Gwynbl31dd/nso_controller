package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class GetLeafRefValuesTest {
	
	@Test
	public void GetLeafRefValues() throws UnsupportedEncodingException, IOException {
		int transaction = 0;

		String method = "get_leafref_values";
		String path = "/test";
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		GetLeafRefValues getLeafRefValues = new GetLeafRefValues(transaction, path);
		
		assertEquals(getLeafRefValues.getRequest(), expectedRequest);
		assertEquals(getLeafRefValues.getId(), 0);
		assertEquals(getLeafRefValues.getMethod(), method);
		assertEquals(IOUtils.toString(getLeafRefValues.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
