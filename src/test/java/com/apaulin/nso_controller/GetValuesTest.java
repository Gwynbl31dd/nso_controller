package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.StringArray;
import com.apaulin.nso_controller.http.rpc.*;

public class GetValuesTest {
	
	@Test
	public void GetValues() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		StringArray stringArray = new StringArray();
		stringArray.add("test");
		String method = "get_values";
		String path = "/test";
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\",\"leafs\": "+stringArray+"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		GetValues getValue = new GetValues(transaction, path, stringArray);
		
		assertEquals(getValue.getRequest(), expectedRequest);
		assertEquals(getValue.getId(), 0);
		assertEquals(getValue.getMethod(), method);
		assertEquals(IOUtils.toString(getValue.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
