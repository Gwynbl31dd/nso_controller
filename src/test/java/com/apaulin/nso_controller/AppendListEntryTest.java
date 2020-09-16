package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class AppendListEntryTest {
	
	@Test
	public void AppendListEntry() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String path = "/test";
		String value = "value";
		String method = "append_list_entry";
		String expectedRequest = "{\"th\": 0,\"path\":\""+path+"\",\"value\": \""+value+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		AppendListEntry appendListEntry = new AppendListEntry(transaction, path, value);
		
		assertEquals(appendListEntry.getRequest(), expectedRequest);
		assertEquals(appendListEntry.getId(), 0);
		assertEquals(appendListEntry.getMethod(), method);

		assertEquals(IOUtils.toString(appendListEntry.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
