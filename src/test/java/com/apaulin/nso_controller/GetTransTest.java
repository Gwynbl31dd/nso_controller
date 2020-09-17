package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class GetTransTest {
	
	@Test
	public void GetTrans() throws UnsupportedEncodingException, IOException {
		String method = "get_trans";
		String expectedRequest = "{}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		GetTrans getTrans = new GetTrans();
		
		assertEquals(getTrans.getRequest(), expectedRequest);
		assertEquals(getTrans.getId(), 0);
		assertEquals(getTrans.getMethod(), method);
		assertEquals(IOUtils.toString(getTrans.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
