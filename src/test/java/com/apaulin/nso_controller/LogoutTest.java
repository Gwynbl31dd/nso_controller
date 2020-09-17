package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class LogoutTest {
	
	@Test
	public void Logout() throws UnsupportedEncodingException, IOException {
		String method = "logout";
		String expectedRequest = "{}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		Logout logout = new Logout();
		
		assertEquals(logout.getRequest(), expectedRequest);
		assertEquals(logout.getId(), 0);
		assertEquals(logout.getMethod(), method);
		assertEquals(IOUtils.toString(logout.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
