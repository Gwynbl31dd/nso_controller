/**
 * 
 */
package com.apaulin.nso_controller.examples;

import com.apaulin.nso_controller.NSOController;
import com.apaulin.nso_controller.exception.NSOException;
import com.apaulin.nso_controller.http.rpc.RCPparameterException;
import com.apaulin.nso_controller.http.rpc.RPCException;

/**
 * Example how to create a user
 * @author Anthony Paulin
 * @since 20/04/2020
 * @version 1.0
 *
 */
public class CreateExample {
	
	private final static String url = "http://127.0.0.1:8080";
	private final static String username = "admin";
	private final static String password = "cisco123";
	
	public CreateExample() {
		NSOController nso = null;
		try {
			nso = new NSOController(url,username,password);
			nso.startTransaction("running", "read_write", "private", "test", "reuse");
			//Create the user
			System.out.println(nso.create("/aaa/authentication/users/user{test2}"));
			//Add a value
			nso.setValue("/aaa/authentication/users/user{test2}/uid", 5);
			nso.setValue("/aaa/authentication/users/user{test2}/gid", 5);
			nso.setValue("/aaa/authentication/users/user{test2}/password", "cisco");
			nso.setValue("/aaa/authentication/users/user{test2}/ssh_keydir", "/home/test2/.ssh");
			nso.setValue("/aaa/authentication/users/user{test2}/homedir", "/home/test2");
			System.out.println(nso.dryRun());
			//nso.commit();
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
