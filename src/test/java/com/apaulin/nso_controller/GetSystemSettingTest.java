package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class GetSystemSettingTest {
	
	@Test
	public void GetSystemSetting() throws UnsupportedEncodingException, IOException, RCPparameterException {
		int transaction = 0;

		String method = "get_system_setting";
		String operation = "all";
		String expectedRequest = "{\"operation\": \""+operation+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		GetSystemSetting getSystemSetting = new GetSystemSetting(transaction, operation);
		
		assertEquals(getSystemSetting.getRequest(), expectedRequest);
		assertEquals(getSystemSetting.getId(), 0);
		assertEquals(getSystemSetting.getMethod(), method);
		assertEquals(IOUtils.toString(getSystemSetting.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test(expected = RCPparameterException.class)
	public void GetSystemSettingFail() throws UnsupportedEncodingException, IOException, RCPparameterException {
		int transaction = 0;
		String operation = "test";
		new GetSystemSetting(transaction, operation);
	}
	
}
