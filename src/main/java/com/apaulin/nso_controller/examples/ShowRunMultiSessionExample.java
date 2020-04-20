/**
 * 
 */
package com.apaulin.nso_controller.examples;

import com.apaulin.nso_controller.http.rpc.RPCException;
import com.apaulin.nso_controller.NSOController;
import com.apaulin.nso_controller.exception.NSOException;

/**
 * Example of how to do use show run with multiple sessions
 * @author Anthony Paulin
 * @version 0.1
 * @since 13/01/2020
 *
 */

public class ShowRunMultiSessionExample {
	
	private static String url = "http://127.0.0.1:8080";
	private static String username = "admin";
	private static String password = "cisco123";
	
	public ShowRunMultiSessionExample() {
		NSOController nso = null;
		try {
			//Start the first session, it is created automatically when you instantiate the controller.
			nso = new NSOController(url,username,password);
			System.out.println(nso.showConfig("/devices"));
			System.out.print("Current session index: "+nso.getSessionIndex());
			
			//Start a new session
			nso.addSession();
			System.out.println(nso.showConfig("/devices"));
			System.out.print("Current session index: "+nso.getSessionIndex());
			
			//Go back to the first session
			nso.useSession(1);
			System.out.println(nso.showConfig("/devices"));
			System.out.print("Current session index: "+nso.getSessionIndex());

		} catch (NSOException | RPCException e) {
			e.printStackTrace();
		}
		finally {
			nso.logout();
		}
	}
}
