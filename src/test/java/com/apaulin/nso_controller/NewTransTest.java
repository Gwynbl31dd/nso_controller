package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class NewTransTest {

	@Test
	public void NewTransDefault() throws UnsupportedEncodingException, IOException, RCPparameterException {
		String method = "new_trans";
		String db = "running";
		String tag = "test";
		String mode = "read";
		String confMode = "private";
		String onPendingChange = "reuse";
		
		String expectedRequest = "{\"db\": \"" + db + "\",\"mode\": \"" + mode + "\" ,\"conf_mode\": \"" + confMode
				+ "\",\"tag\": \"" + tag + "\",\"on_pending_changes\": \""+onPendingChange+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\"" + method + "\",\"params\":" + expectedRequest
				+ "}";

		NewTrans newTrans = new NewTrans(tag);
		
		assertEquals(newTrans.getRequest(), expectedRequest);
		assertEquals(newTrans.getId(), 0);
		assertEquals(newTrans.getMethod(), method);
		assertEquals(IOUtils.toString(newTrans.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test
	public void NewTransFull() throws UnsupportedEncodingException, IOException, RCPparameterException {
		String method = "new_trans";
		String db = "running";
		String tag = "test";
		String mode = "read";
		String confMode = "private";
		String onPendingChange = "reuse";

		String expectedRequest = "{\"db\": \"" + db + "\",\"mode\": \"" + mode + "\" ,\"conf_mode\": \"" + confMode
				+ "\",\"tag\": \"" + tag + "\",\"on_pending_changes\": \""+onPendingChange+"\"}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\"" + method + "\",\"params\":" + expectedRequest
				+ "}";

		NewTrans newTrans = new NewTrans(db, mode, confMode,tag,onPendingChange);
		assertEquals(newTrans.getRequest(), expectedRequest);
		assertEquals(newTrans.getId(), 0);
		assertEquals(newTrans.getMethod(), method);
		assertEquals(IOUtils.toString(newTrans.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}

	@Test(expected = RCPparameterException.class)
	public void NewTransFailDb() throws UnsupportedEncodingException, IOException, RCPparameterException {
		String db = "test";
		String tag = "test";
		String mode = "read";
		String confMode = "private";
		String onPendingChange = "reuse";
		
		new NewTrans(db, mode, confMode,tag,onPendingChange);
	}
	
	@Test(expected = RCPparameterException.class)
	public void NewTransFailMode() throws UnsupportedEncodingException, IOException, RCPparameterException {
		String db = "running";
		String tag = "test";
		String mode = "test";
		String confMode = "private";
		String onPendingChange = "reuse";
		
		new NewTrans(db, mode, confMode,tag,onPendingChange);
	}
	
	@Test(expected = RCPparameterException.class)
	public void NewTransFailConfMode() throws UnsupportedEncodingException, IOException, RCPparameterException {
		String db = "running";
		String tag = "test";
		String mode = "read";
		String confMode = "test";
		String onPendingChange = "reuse";
		
		new NewTrans(db, mode, confMode,tag,onPendingChange);
	}
	
	@Test(expected = RCPparameterException.class)
	public void NewTransFailOnPendingChange() throws UnsupportedEncodingException, IOException, RCPparameterException {
		String db = "running";
		String tag = "test";
		String mode = "read";
		String confMode = "private";
		String onPendingChange = "test";
		
		new NewTrans(db, mode, confMode,tag,onPendingChange);
	}

}
