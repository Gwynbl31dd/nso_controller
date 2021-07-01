/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

/**
 * Abort a JSON-RPC method by its associated id.
 * @author Anthony Paulin
 * @since  02/08/2018
 * @version 0.1
 *
 */
public class Abort extends RpcData {

	/**
	 * Abord a session
	 * @param id The id to abord
	 */
	public Abort(int id) {
		super("abort");
		setId(id);
		setRequest();
	}
	
	/**
	 * Build the request
	 */
	public void setRequest() {
		String request = "{\"id\": "+getId()+"}";
		super.setRequest(request); 
	}

}
