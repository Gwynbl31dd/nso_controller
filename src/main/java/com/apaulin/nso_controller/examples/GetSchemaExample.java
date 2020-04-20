/**
 * 
 */
package com.apaulin.nso_controller.examples;

import com.apaulin.nso_controller.NSOController;
import com.apaulin.nso_controller.exception.NSOException;
import com.apaulin.nso_controller.http.rpc.RCPparameterException;
import com.apaulin.nso_controller.http.rpc.RPCException;

/**
 * @author Anthony Paulin
 * @since 20/04/2020
 * @version 1.0
 *
 */
public class GetSchemaExample {
	
	private final static String url = "http://127.0.0.1:8080";
	private final static String username = "admin";
	private final static String password = "cisco123";
	
	public GetSchemaExample() {
		
		NSOController nso = null;
		try {
			nso = new NSOController(url,username,password);
			System.out.println(nso.getModel("/aaa/authentication/users/user"));
		} catch (NSOException | RPCException e) {
			e.printStackTrace();
		}
		finally{
			nso.logout();
		}
	}
}
