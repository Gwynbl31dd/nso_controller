package com.apaulin.nso_controller.http.rpc;

/**
 * Removes a user session and invalidates the browser cookie
 * @author Anthony Paulin
 * @since 23/07/2018
 * @version 0.1
 * The HTTP cookie identifies the user session so no input parameters are needed.
 */

public class Logout extends RpcData{
	
	/**
	 * Generate logout request
	 */
	public Logout() {
		super("logout");
	}

}
