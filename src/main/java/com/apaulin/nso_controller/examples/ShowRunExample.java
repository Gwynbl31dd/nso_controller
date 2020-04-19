package com.apaulin.nso_controller.examples;

import com.apaulin.nso_controller.http.rpc.RPCException;
import com.apaulin.nso_controller.NSOController;
import com.apaulin.nso_controller.exception.NSOException;

/**
 * Example of how to do a simple show run on all devices on NSO using this API
 * @author Anthony Paulin
 * @version 0.1
 * @since 13/01/2020
 *
 */

public class ShowRunExample {
	private static String url = "http://127.0.0.1:8080";
	private static String username = "admin";
	private static String password = "cisco123";
	
	public ShowRunExample() {
		try {
			NSOController nso = new NSOController(url,username,password);
			System.out.println(nso.showConfig("/devices"));
			nso.logout();
		} catch (NSOException | RPCException e) {
			e.printStackTrace();
		}

	}
}
