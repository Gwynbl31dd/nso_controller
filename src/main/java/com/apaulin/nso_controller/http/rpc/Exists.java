/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

/**
 * @author Anthony Paulin
 * @since 19/07/2018
 * @version 0.1
 *
 */
public class Exists extends GetSchema {	

	/**
	 * Checks if optional data exists
	 * @param th Transaction
	 * @param path Path to check
	 * @param id transaction ID
	 */
	public Exists(int th,String path) {
		super("exists");
		super.setTh(th);
		super.setPath(path); 
		super.setRequest();
	}
}

