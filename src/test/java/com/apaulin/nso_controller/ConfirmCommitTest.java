package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class ConfirmCommitTest {
	
	@Test
	public void ConfirmCommit() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "confirm_commit";
		String expectedRequest = "{}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		ConfirmCommit confirmCommit = new ConfirmCommit(transaction);
		
		assertEquals(confirmCommit.getRequest(), expectedRequest);
		assertEquals(confirmCommit.getId(), 0);
		assertEquals(confirmCommit.getMethod(), method);
		assertEquals(IOUtils.toString(confirmCommit.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
