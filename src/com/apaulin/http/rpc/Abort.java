/**
 * 
 */
package com.apaulin.http.rpc;

/**
 * Abort a JSON-RPC method by its associated id.
 * @author Anthony Paulin
 * @since  02/08/2018
 * @version 0.1
 *
 */
public class Abort extends RPCRequest {
	private int id;

	public Abort(int id) {
		super("abort");
		this.id = id;
		setRequest();
	}
	
	/**
	 * Build the request
	 */
	public void setRequest() {
		String request = "{\"id\": "+getID()+"}";
		super.setRequest(request); 
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int th) {
		this.id = th;
	}

}
