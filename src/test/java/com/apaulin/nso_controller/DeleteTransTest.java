package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class DeleteTransTest {
	
	@Test
	public void DeleteTrans() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "delete_trans";
		String expectedRequest = "{}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		DeleteTrans deleteTrans = new DeleteTrans(transaction);
		
		assertEquals(deleteTrans.getRequest(), expectedRequest);
		assertEquals(deleteTrans.getId(), 0);
		assertEquals(deleteTrans.getMethod(), method);
		assertEquals(IOUtils.toString(deleteTrans.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
