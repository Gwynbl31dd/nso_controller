/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

/**
 * @author Anthony Paulin
 * @version 0.1
 * @since 22/07/2018
 *
 */
public class RPCException extends Exception {
	private static final long serialVersionUID = -2584059054138890327L;

	public RPCException(String error) {
		super(error);
	}
}
