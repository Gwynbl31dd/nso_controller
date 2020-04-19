/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

/**
 * Deletes an existing list entry, a presence container, or an optional leaf and all its children (if any)
 * @author Anthony Paulin
 * @since 19/07/2018
 * @version 0.1
 *
 */
public class Delete extends GetSchema{

	/**
	 * 
	 * @param th
	 * @param path
	 */
	public Delete(int th, String path) {
		super("delete");
		super.setTh(th);
		super.setPath(path);
		super.setRequest();
	}

}
