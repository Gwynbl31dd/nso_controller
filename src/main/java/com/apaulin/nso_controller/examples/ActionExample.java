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
 * @version 1.0
 * @since 20/04/2020
 *
 */
public class ActionExample {
	private static String url = "http://127.0.0.1:8080";
	private static String username = "admin";
	private static String password = "cisco123";
	
	public ActionExample() {
		NSOController nso = null;
		try {
			nso = new NSOController(url,username,password);
			nso.startTransaction("running", "read_write", "private", "test", "reuse");
			System.out.println(nso.syncFrom());//Sync from all device
			System.out.println(nso.commit());
		} catch (NSOException | RPCException | RCPparameterException e) {
			e.printStackTrace();
		}
		finally{
			nso.logout();
		}
	}
}
