/**
 * 
 */
package com.apaulin.http.rpc;

/**
 * Request the transaction number
 * @author Anthony Paulin
 * @since  13/07/2018
 * @version 0.1
 */
public class GetTrans extends RPCRequest {	
	/**
	 * Craft a transaction number request
	 * @param id Transaction id
	 */
	public GetTrans() {
		super("get_trans");
	}
	
}
