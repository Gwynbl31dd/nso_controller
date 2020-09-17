package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class KickUserTest {
	
	@Test
	public void KickUserString() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "kick_user";
		String user = "test";
		String expectedRequest = "{\"th\": 0,\"user\":\""+user+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		KickUser kickUser = new KickUser(transaction, user);
		
		assertEquals(kickUser.getRequest(), expectedRequest);
		assertEquals(kickUser.getId(), 0);
		assertEquals(kickUser.getMethod(), method);
		assertEquals(IOUtils.toString(kickUser.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test
	public void KickUserInt() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "kick_user";
		int user = 1;
		String expectedRequest = "{\"th\": 0,\"user\":"+user+"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		KickUser kickUser = new KickUser(transaction, user);
		
		assertEquals(kickUser.getRequest(), expectedRequest);
		assertEquals(kickUser.getId(), 0);
		assertEquals(kickUser.getMethod(), method);
		assertEquals(IOUtils.toString(kickUser.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
