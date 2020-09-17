package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.StringArray;
import com.apaulin.nso_controller.http.rpc.*;

public class GetAttrsTest {
	
	@Test
	public void GetAttrs() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "get_attrs";
		String path = "/test";
		StringArray stringArray = new StringArray();
		stringArray.add("test");
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\",\"names\": [\"test\"]}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		GetAttrs getAttrs = new GetAttrs(transaction, path, stringArray);
		
		assertEquals(getAttrs.getRequest(), expectedRequest);
		assertEquals(getAttrs.getId(), 0);
		assertEquals(getAttrs.getMethod(), method);
		assertEquals(IOUtils.toString(getAttrs.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
