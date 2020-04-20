/**
 * 
 */
package com.apaulin.nso_controller.examples;

import com.apaulin.nso_controller.NSOController;
import com.apaulin.nso_controller.exception.NSOException;
import com.apaulin.nso_controller.http.rpc.RPCException;

/**
 * Example of how to read list of keys from NSO
 * @author Anthony Paulin
 * @since 20/04/2020
 * @version 1.0
 *
 */
public class GettingListKeys {
	private static String url = "http://127.0.0.1:8080";
	private static String username = "admin";
	private static String password = "cisco123";
	
	public GettingListKeys() {
		NSOController nso = null;
		try {
			nso = new NSOController(url,username,password);
			System.out.println(nso.getListKeys("/aaa/authentication/users/user"));
			
		} catch (NSOException | RPCException e) {
			e.printStackTrace();
		}
		finally {
			nso.logout();
		}
	}
}
