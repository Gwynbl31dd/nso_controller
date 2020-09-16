package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class ClearValidateLockTest {
	
	@Test
	public void ClearValidateLock() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "clear_validate_lock";
		String expectedRequest = "{\"th\": "+transaction+"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		ClearValidateLock clearValidateLock = new ClearValidateLock(transaction);
		
		assertEquals(clearValidateLock.getRequest(), expectedRequest);
		assertEquals(clearValidateLock.getId(), 0);
		assertEquals(clearValidateLock.getMethod(), method);
		assertEquals(IOUtils.toString(clearValidateLock.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
}
