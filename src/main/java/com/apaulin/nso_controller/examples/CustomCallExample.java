package com.apaulin.nso_controller.examples;

import com.apaulin.nso_controller.http.rpc.RCPparameterException;
import com.apaulin.nso_controller.http.rpc.RPCException;
import com.apaulin.nso_controller.http.rpc.RpcData;
import com.apaulin.nso_controller.NSOController;
import com.apaulin.nso_controller.exception.NSOException;

/**
 * Example of a custom call for NSO
 * 
 * This demonstrate how to create a simple custom RPC call from scratch
 * 
 * @author Anthony Paulin
 * @version 0.1
 * @since 16/09/2020
 *
 */

public class CustomCallExample {
	private static String url = "http://127.0.0.1:8080";
	private static String username = "admin";
	private static String password = "cisco123";
	
	public CustomCallExample() {
		NSOController nso = null;
		try {
			// Build the NSO controller object and start the authentication
			nso = new NSOController(url,username,password);
			
			//Create a transaction
			int th = nso.startTransaction("running", "read", "private", "test", "reuse");
			
			// Get the generated ID
			int id = nso.getSessionManager().getCurrentId();
			
			// We will create a show config with oper state to true
			MyRPCCustomCall call = new MyRPCCustomCall("show_config");
			// Set the generate ID
			call.setId(id);		
			// Build the request string, do not forget the transaction
			call.setRequest("{\"th\": " + th + ",\"path\":\"/devices\",\"result_as\": \"json\",\"with_oper\": true}");
			// Just push that object to the session manager and ask for a send.
			String result = nso.getSessionManager().getCurrentReq().send(call);
			System.out.println(result);
			
		} catch (NSOException | RPCException | RCPparameterException e) {
			e.printStackTrace();
		} 
		finally {
			nso.logout();
		}
	}
	
}

// Create a class that will extend the abstract class RPCData.
class MyRPCCustomCall extends RpcData {
	/* 
	 * Just specify the method. Example "show_config"
	 * This is defined from the web_ui documentation in NSO
	 */
	public MyRPCCustomCall(String method) {
		super(method);
	}
}


