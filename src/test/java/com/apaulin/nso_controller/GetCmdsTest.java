package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class GetCmdsTest {
	
	@Test
	public void GetCmds() throws UnsupportedEncodingException, IOException {

		String method = "get_cmds";
		String expectedRequest = "{}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		GetCmds getCmds = new GetCmds();
		
		assertEquals(getCmds.getRequest(), expectedRequest);
		assertEquals(getCmds.getId(), 0);
		assertEquals(getCmds.getMethod(), method);
		assertEquals(IOUtils.toString(getCmds.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
