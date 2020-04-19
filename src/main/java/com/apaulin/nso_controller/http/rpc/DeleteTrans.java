/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

/**
 * Deletes a transaction created by new_trans or new_webui_trans
 * @author Anthony Paulin
 * @since 24/08/2018
 * @version 0.1
 */
public class DeleteTrans extends ValidateTrans{
	
	/**
	 * Build the request
	 * @param th
	 */
	public DeleteTrans(int th) {
		super(th,"delete_trans");
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
