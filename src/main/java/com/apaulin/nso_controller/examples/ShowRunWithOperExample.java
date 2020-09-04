package com.apaulin.nso_controller.examples;

import com.apaulin.nso_controller.http.rpc.RCPparameterException;
import com.apaulin.nso_controller.http.rpc.RPCException;
import com.apaulin.nso_controller.NSOController;
import com.apaulin.nso_controller.exception.NSOException;

/**
 * Example of how to do a show run with operational data on all devices on NSO using this API
 * @author Anthony Paulin
 * @version 0.2
 * @since 02/09/2020
 *
 */

public class ShowRunWithOperExample {
	private static String url = "http://127.0.0.1:8080";
	private static String username = "admin";
	private static String password = "cisco123";
	
	public ShowRunWithOperExample() {
		NSOController nso = null;
		try {
			System.out.println("Show run with operational data");
			nso = new NSOController(url,username,password);
			nso.startTransaction("running", "read_write", "private", "test", "reuse");
			System.out.println(nso.showConfig("/devices",true,"json"));
			System.out.println("Show run without operational data");
			System.out.println(nso.showConfig("/devices",false,"json"));
		} catch (NSOException | RPCException e) {
			e.printStackTrace();
		} catch (RCPparameterException e) {
			e.printStackTrace();
		}
		finally {
			nso.logout();
		}
	}
}
