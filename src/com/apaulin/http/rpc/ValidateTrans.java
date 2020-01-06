/**
 * 
 */
package com.apaulin.http.rpc;

/**
 * Validates a transaction
 * @author Anthony Paulin
 * @since  19/07/2018
 * @version 0.1
 *
 */
public class ValidateTrans extends RpcData {
	private int th;
	/**
	 * Validate a transaction
	 * @param th Transaction to validate
	 * @param method method to validate
	 */
	public ValidateTrans(int th) {
		this(th,"validate_trans");
	}
	
	/**
	 * Validate a transaction
	 * @param th
	 * @param method
	 */
	protected ValidateTrans(int th,String method) {
		super(method);
		this.th = th;
		setRequest();
	}
	
	/**
	 * Build the request
	 */
	public void setRequest() {
		String request = "{\"th\": "+getTh()+"}";
		super.setRequest(request); 
	}
	
	public int getTh() {
		return th;
	}
	
	public void setTh(int th) {
		this.th = th;
	}
}
