package com.apaulin.nso_controller.exception;

/**
 * NSO related exception
 * @author Anthony Paulin
 *
 */
public class NSOException extends Exception{

	private static final long serialVersionUID = 7467468408890470185L;

	public NSOException(Exception e) {
		super(e);
	}

}
