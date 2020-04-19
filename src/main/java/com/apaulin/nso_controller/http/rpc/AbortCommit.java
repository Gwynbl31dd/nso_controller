/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

/**
 * Aborts the active read-write transaction
 * @author Anthony Paulin
 * @since 24/08/2018
 * @version 0.1
 */
public class AbortCommit extends ValidateTrans{
	
	/**
	 * Build the request
	 * @param th
	 */
	public AbortCommit(int th) {
		super(th,"abort_commit");
		this.setRequest();
	}
	
	
	/**
	 * Build the request
	 */
	@Override
	public void setRequest() {
		String request = "{}";
		super.setRequest(request); 
	}
	
	
}
