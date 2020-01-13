/**
 * 
 */
package com.apaulin.examples;

import com.apaulin.http.rpc.RPCException;
import com.apaulin.xplorer.NSOController;
import com.apaulin.xplorer.exception.NSOException;

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
	private static String password = "admin";
	
	public ShowRunMultiSessionExample() {
		try {
			//Start the first session, it is created automatically when you instantiate the controller.
			NSOController nso = new NSOController(url,username,password);
			
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

			nso.logout();
		} catch (NSOException | RPCException e) {
			e.printStackTrace();
		}
	}
}
