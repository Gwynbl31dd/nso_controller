package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class LoadTest {

	@Test
	public void Load() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "load";
		String path = "/test";
		String data = "{\"test\":\"test\"}";

		Load load = new Load(transaction, path, data);

		String expectedRequest = "{\"th\": 0,\"data\": \"" + load.stringify(data) + "\",\"path\":\"" + path
				+ "\",\"format\": \"xml\",\"mode\": \"merge\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\"" + method + "\",\"params\":" + expectedRequest
				+ "}";

		assertEquals(load.getRequest(), expectedRequest);
		assertEquals(load.getId(), 0);
		assertEquals(load.getMethod(), method);
		assertEquals(IOUtils.toString(load.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}

	@Test
	public void LoadModeFormat() throws UnsupportedEncodingException, IOException, RCPparameterException {
		int transaction = 0;
		String method = "load";
		String path = "/test";
		String data = "{\"test\":\"test\"}";
		String mode = "merge";
		String format = "json";

		Load load = new Load(transaction, path, data,mode,format);

		String expectedRequest = "{\"th\": 0,\"data\": \"" + load.stringify(data) + "\",\"path\":\"" + path
				+ "\",\"format\": \"" + format + "\",\"mode\": \"" + mode + "\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\"" + method + "\",\"params\":" + expectedRequest
				+ "}";

		assertEquals(load.getRequest(), expectedRequest);
		assertEquals(load.getId(), 0);
		assertEquals(load.getMethod(), method);
		assertEquals(IOUtils.toString(load.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test(expected = RCPparameterException.class)
	public void LoadModeFail() throws UnsupportedEncodingException, IOException, RCPparameterException {
		int transaction = 0;
		String path = "/test";
		String data = "{\"test\":\"test\"}";
		String mode = "test";
		String format = "json";

		new Load(transaction, path, data,mode,format);
	}
	
	@Test(expected = RCPparameterException.class)
	public void LoadFormatFail() throws UnsupportedEncodingException, IOException, RCPparameterException {
		int transaction = 0;
		String path = "/test";
		String data = "{\"test\":\"test\"}";
		String mode = "merge";
		String format = "test";

		new Load(transaction, path, data,mode,format);
	}


}
