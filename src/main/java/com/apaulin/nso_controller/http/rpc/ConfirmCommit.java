/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

/**
 * Confirms the currently pending confirmed commit
 * @author Anthony Paulin
 * @since 01/08/2018
 * @version 0.1
 */
public class ConfirmCommit extends ValidateTrans{
	
	/**
	 * Build the request
	 * @param th transaction id
	 */
	public ConfirmCommit(int th) {
		super(th,"confirm_commit");
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
