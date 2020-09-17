package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class ValidateTransTest {
	
	@Test
	public void ValidateTrans() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "validate_trans";
		String expectedRequest = "{\"th\": "+transaction+"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		ValidateTrans validateCommit = new ValidateTrans(transaction);
		
		assertEquals(validateCommit.getRequest(), expectedRequest);
		assertEquals(validateCommit.getId(), 0);
		assertEquals(validateCommit.getMethod(), method);
		assertEquals(IOUtils.toString(validateCommit.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
