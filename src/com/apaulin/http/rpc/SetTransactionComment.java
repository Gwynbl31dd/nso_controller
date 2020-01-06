/**
 * 
 */
package com.apaulin.http.rpc;

/**
 * Set a transaction comment to the current transaction
 * @author Anthony Paulin
 * @since 19/06/2019
 * @version 0.1
 */
public class SetTransactionComment extends RpcData {
	private int th;
	private String comment;
	
	/**
	 * Craft a request to get the schema
	 * @param th
	 * @param path
	 * @param id
	 */
	public SetTransactionComment(int th,String comment) {
		this("set_trans_comment");//Set the action name
		this.setTh(th);//Set the transaction ID
		this.comment = comment;//Comment to add to the transaction
		this.setRequest();//Build the request
	}
	
	/**
	 * Call super constructor
	 * @param id
	 * @param method
	 */
	protected SetTransactionComment(String method) {
		super(method);//Set the method/action to use with JRPC
	}
	
	/**
	 * Build the request
	 */
	public void setRequest() {
		String request = "{\"th\": "+getTh()+",\"comment\":\""+getComment()+"\"}";
		super.setRequest(request); 
	}
	
	/**
	 * Return the comment
	 * @return
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Get the transaction number
	 * @return
	 */
	public int getTh() {
		return th;
	}

	/**
	 * Set the transaction number
	 * @param th
	 */
	public void setTh(int th) {
		this.th = th;
	}

}
