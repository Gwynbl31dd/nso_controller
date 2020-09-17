package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class AbordCommitTest {
	
	@Test
	public void AbordCommit() throws UnsupportedEncodingException, IOException {
		int transaction = 0;

		String method = "abort_commit";
		String expectedRequest = "{}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		AbortCommit abordCommit = new AbortCommit(transaction);
		
		assertEquals(abordCommit.getRequest(), expectedRequest);
		assertEquals(abordCommit.getId(), 0);
		assertEquals(abordCommit.getMethod(), method);

		assertEquals(IOUtils.toString(abordCommit.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
