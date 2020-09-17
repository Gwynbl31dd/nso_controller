package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class GetListKeysTest {
	
	@Test
	public void GetListKeys() throws UnsupportedEncodingException, IOException {
		int transaction = 0;

		String method = "get_list_keys";
		String path = "/test";
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		GetListKeys getListKeys = new GetListKeys(transaction, path);
		
		assertEquals(getListKeys.getRequest(), expectedRequest);
		assertEquals(getListKeys.getId(), 0);
		assertEquals(getListKeys.getMethod(), method);
		assertEquals(IOUtils.toString(getListKeys.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
