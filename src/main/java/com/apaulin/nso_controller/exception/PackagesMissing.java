/**
 * 
 */
package com.apaulin.nso_controller.exception;

/**
 * Exception raised by the packages
 * @author Anthony Paulin
 * @since 24/07/2018
 * @version 0.1
 *
 */
public class PackagesMissing extends Exception {
	private static final long serialVersionUID = -3241362301646139696L;

	public PackagesMissing(String error) {
		super("Package(s) missing "+error);
	}
	
}
