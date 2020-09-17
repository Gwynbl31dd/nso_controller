package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class GetValueTest {
	
	@Test
	public void GetValue() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		boolean checkDefault = false;
		String method = "get_value";
		String path = "/test";
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\",\"check_default\": "+checkDefault+"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		GetValue getValue = new GetValue(transaction, path);
		
		assertEquals(getValue.getRequest(), expectedRequest);
		assertEquals(getValue.getId(), 0);
		assertEquals(getValue.getMethod(), method);
		assertEquals(IOUtils.toString(getValue.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test
	public void GetValueCheckDefault() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		boolean checkDefault = true;
		String method = "get_value";
		String path = "/test";
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\",\"check_default\": "+checkDefault+"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		GetValue getValue = new GetValue(transaction, path,checkDefault);
		
		assertEquals(getValue.getRequest(), expectedRequest);
		assertEquals(getValue.getId(), 0);
		assertEquals(getValue.getMethod(), method);
		assertEquals(IOUtils.toString(getValue.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
