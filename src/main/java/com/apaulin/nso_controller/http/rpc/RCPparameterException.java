package com.apaulin.nso_controller.http.rpc;

/**
 * @author Anthony Paulin
 * @since 13/07/2018
 * @version 0.1
 *
 */
public class RCPparameterException extends Exception {

	private static final long serialVersionUID = -1908165149130005403L;

	public RCPparameterException(String rcpSource) {
		super("RCP error wrong parameter for "+rcpSource);
	}
}
