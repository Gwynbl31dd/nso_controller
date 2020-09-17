package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class SetValueTest {
	
	@Test
	public void SetValueString() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "set_value";
		String path = "/test";
		String value = "test";
		boolean dryrun = false;
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\",\"value\": \""+value+"\",\"dryrun\": "+dryrun+"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		SetValue setValue = new SetValue(transaction,path, value);
		
		assertEquals(setValue.getRequest(), expectedRequest);
		assertEquals(setValue.getId(), 0);
		assertEquals(setValue.getMethod(), method);
		assertEquals(IOUtils.toString(setValue.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test
	public void SetValueInt() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "set_value";
		String path = "/test";
		int value = 123;
		boolean dryrun = false;
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\",\"value\": "+value+",\"dryrun\": "+dryrun+"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		SetValue setValue = new SetValue(transaction,path, value);
		
		assertEquals(setValue.getRequest(), expectedRequest);
		assertEquals(setValue.getId(), 0);
		assertEquals(setValue.getMethod(), method);
		assertEquals(IOUtils.toString(setValue.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test
	public void SetValueBool() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "set_value";
		String path = "/test";
		boolean value = true;
		boolean dryrun = false;
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\",\"value\": "+value+",\"dryrun\": "+dryrun+"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		SetValue setValue = new SetValue(transaction,path, value);
		
		assertEquals(setValue.getRequest(), expectedRequest);
		assertEquals(setValue.getId(), 0);
		assertEquals(setValue.getMethod(), method);
		assertEquals(IOUtils.toString(setValue.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
