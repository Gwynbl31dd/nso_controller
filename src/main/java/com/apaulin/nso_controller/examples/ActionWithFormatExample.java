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
 * @since 08/09/2020
 *
 */
public class ActionWithFormatExample {
	private static String url = "http://127.0.0.1:8080";
	private static String username = "admin";
	private static String password = "cisco123";
	
	public ActionWithFormatExample() {
		NSOController nso = null;
		try {
			nso = new NSOController(url,username,password);
			nso.startTransaction("running", "read_write", "private", "test", "reuse");
			System.out.println(nso.runAction("/devices/sync-from", "json"));
			System.out.println(nso.runAction("/ncs:devices/connect", "json"));
		} catch (NSOException | RPCException e) {
			e.printStackTrace();
		} catch (RCPparameterException e) {
			e.printStackTrace();
		}
		finally{
			nso.logout();
		}
	}
}
