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
 * @since 22/10/2020
 *
 */
public class PingExample {
	private static String url = "http://ipdm-dev.in.telstra.com.au:10430";
	private static String username = "admin";
	private static String password = "admin";
	
	public PingExample() {
		NSOController nso = null;
		try {
			nso = new NSOController(url,username,password);
			nso.startTransaction("running", "read_write", "private", "test", "reuse");
			System.out.println("****************************************************");
			System.out.println(nso.runAction("/ncs:devices/device{mcpmodel}/ping","json"));
			System.out.println("****************************************************");
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
