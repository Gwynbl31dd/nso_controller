package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class ShowConfigTest {
	
	@Test
	public void ShowConfig() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "show_config";
		String path = "/test";
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		ShowConfig setValue = new ShowConfig(transaction,path);
		
		assertEquals(setValue.getRequest(), expectedRequest);
		assertEquals(setValue.getId(), 0);
		assertEquals(setValue.getMethod(), method);
		assertEquals(IOUtils.toString(setValue.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test
	public void ShowConfigWithOper() throws UnsupportedEncodingException, IOException, RCPparameterException {
		int transaction = 0;
		String method = "show_config";
		String path = "/test";
		boolean withOper = true;
		String resultAs = "json";
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\",\"result_as\": \""+resultAs+"\",\"with_oper\": "+withOper+"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		ShowConfig setValue = new ShowConfig(transaction,path,withOper,resultAs);
		
		assertEquals(setValue.getRequest(), expectedRequest);
		assertEquals(setValue.getId(), 0);
		assertEquals(setValue.getMethod(), method);
		assertEquals(IOUtils.toString(setValue.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test
	public void ShowConfigWithoutResultAs() throws UnsupportedEncodingException, IOException, RCPparameterException {
		int transaction = 0;
		String method = "show_config";
		String path = "/test";
		int maxSize = 100;
		boolean withOper = true;
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\",\"with_oper\": "+withOper+",\"max_size\": "+maxSize+"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		ShowConfig setValue = new ShowConfig(transaction,path,withOper,maxSize);
		
		assertEquals(setValue.getRequest(), expectedRequest);
		assertEquals(setValue.getId(), 0);
		assertEquals(setValue.getMethod(), method);
		assertEquals(IOUtils.toString(setValue.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
