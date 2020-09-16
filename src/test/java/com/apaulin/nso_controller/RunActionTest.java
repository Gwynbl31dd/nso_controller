package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class RunActionTest {
	
	@Test
	public void RunAction() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String path = "/test"; 
		String method = "run_action";
		String expectedRequest = "{\"th\": 0,\"path\":\"/test\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\"run_action\",\"params\":"+expectedRequest+"}";
		
		RunAction runAction = new RunAction(transaction, path);
		
		assertEquals(runAction.getRequest(), expectedRequest);
		assertEquals(runAction.getId(), 0);
		assertEquals(runAction.getMethod(), method);
		assertEquals(runAction.getPath(), path);
		assertEquals(IOUtils.toString(runAction.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test
	public void RunActionWithFormat() throws UnsupportedEncodingException, IOException, RCPparameterException {
		int transaction = 0;
		String path = "/test"; 
		String method = "run_action";
		String expectedRequest = "{\"th\": 0,\"path\":\"/test\",\"format\":\"json\"}";
		String format = "json";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\"run_action\",\"params\":"+expectedRequest+"}";
		
		RunAction runAction = null;
		
		runAction = new RunAction(transaction, path,format);

		assertEquals(runAction.getRequest(), expectedRequest);
		assertEquals(runAction.getId(), 0);
		assertEquals(runAction.getMethod(), method);
		assertEquals(runAction.getPath(), path);
		assertEquals(IOUtils.toString(runAction.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test(expected = RCPparameterException.class)
	public void RunActionWithWrongFormat() throws RCPparameterException {
		int transaction = 0;
		String path = "/test"; 
		String format = "test";
		new RunAction(transaction, path,format);
	}
	
	@Test
	public void RunActionWithParams() throws RCPparameterException, UnsupportedEncodingException, IOException {
		int transaction = 0;
		String path = "/test"; 
		String method = "run_action";
		String params = "{\"args\":\"test\"}";
		String expectedRequest = "{\"th\": 0,\"path\":\"/test\",\"params\": "+params+",\"format\":\"json\"}";
		String format = "json";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\"run_action\",\"params\":"+expectedRequest+"}";
		
		RunAction runAction = null;
		runAction = new RunAction(transaction, path,format,params);

		assertEquals(runAction.getRequest(), expectedRequest);
		assertEquals(runAction.getId(), 0);
		assertEquals(runAction.getMethod(), method);
		assertEquals(runAction.getPath(), path);
		assertEquals(IOUtils.toString(runAction.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
