package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class CommitTest {
	
	@Test
	public void Commit() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "commit";
		String expectedRequest = "{\"th\": "+transaction+",\"timeout\": "+30+",\"release_locks\" : true,\"flags\": []}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		Commit commit = new Commit(transaction);
		
		assertEquals(commit.getRequest(), expectedRequest);
		assertEquals(commit.getId(), 0);
		assertEquals(commit.getMethod(), method);
		assertEquals(IOUtils.toString(commit.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test
	public void CommitWithTimeout() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "commit";
		int timeout = 0;
		String expectedRequest = "{\"th\": "+transaction+",\"timeout\": "+timeout+",\"release_locks\" : true,\"flags\": []}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		Commit commit = new Commit(transaction,timeout);
		
		assertEquals(commit.getRequest(), expectedRequest);
		assertEquals(commit.getId(), 0);
		assertEquals(commit.getMethod(), method);
		assertEquals(IOUtils.toString(commit.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test
	public void CommitWithOptions() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "commit";
		int timeout = 30;
		String expectedRequest = "{\"th\": "+transaction+",\"timeout\": "+timeout+",\"release_locks\" : true,\"flags\": [\"no-networking\"]}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		CommitOptions options = new CommitOptions();
		options.addNoNetworking();
		
		Commit commit = new Commit(transaction,options);
		
		assertEquals(commit.getRequest(), expectedRequest);
		assertEquals(commit.getId(), 0);
		assertEquals(commit.getMethod(), method);
		assertEquals(IOUtils.toString(commit.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test
	public void CommitWithOptionsAndTimeout() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "commit";
		int timeout = 0;
		String expectedRequest = "{\"th\": "+transaction+",\"timeout\": "+timeout+",\"release_locks\" : true,\"flags\": [\"no-networking\"]}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		CommitOptions options = new CommitOptions();
		options.addNoNetworking();
		
		Commit commit = new Commit(transaction,options,timeout);
		
		assertEquals(commit.getRequest(), expectedRequest);
		assertEquals(commit.getId(), 0);
		assertEquals(commit.getMethod(), method);
		assertEquals(IOUtils.toString(commit.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
	@Test
	public void CommitWithReleaseLock() throws UnsupportedEncodingException, IOException {
		int transaction = 0;
		String method = "commit";
		int timeout = 0;
		boolean releaseLock = false;
		String expectedRequest = "{\"th\": "+transaction+",\"timeout\": "+timeout+",\"release_locks\" : "+releaseLock+",\"flags\": [\"no-networking\"]}";
		String fullRequest = "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\""+method+"\",\"params\":"+expectedRequest+"}";
		
		CommitOptions options = new CommitOptions();
		options.addNoNetworking();
		
		Commit commit = new Commit(transaction,options,timeout,releaseLock);
		
		assertEquals(commit.getRequest(), expectedRequest);
		assertEquals(commit.getId(), 0);
		assertEquals(commit.getMethod(), method);
		assertEquals(IOUtils.toString(commit.getRequestEntity().getContent(), Charset.defaultCharset()), fullRequest);
	}
	
}
