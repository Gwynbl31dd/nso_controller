package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class SetTransactionCommentTest {
	
	@Test
	public void SetTransactionComment() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "set_trans_comment";
		String comment = "test";
		String expectedRequest = "{\"th\": 0,\"comment\":\""+comment+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		SetTransactionComment kickUser = new SetTransactionComment(transaction, comment);
		
		assertEquals(kickUser.getRequest(), expectedRequest);
		assertEquals(kickUser.getId(), 0);
		assertEquals(kickUser.getMethod(), method);
		assertEquals(IOUtils.toString(kickUser.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
