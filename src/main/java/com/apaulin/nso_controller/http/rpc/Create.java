/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

/**
 * Create a list entry, a presence container, or a leaf of type empty
 * @author Anthony Paulin
 * @since 19/07/2018
 * @version 0.1
 *
 */
public class Create extends GetSchema {

	/**
	 * Build the request
	 * @param th transaction id
	 * @param path keypath
	 */
	public Create(int th, String path) {
		super("create");
		super.setTh(th);
		super.setPath(path);
		super.setRequest();
	}
}
