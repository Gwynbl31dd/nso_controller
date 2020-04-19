/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

/**
 * Validates a transaction before calling commit. If this method succeeds (with or without warnings) then the
 * next operation must be all call to either commit or clear_validate_lock. The configuration will be locked
 * for access by other users until one of these methods are called.
 * @author Anthony Paulin
 * @since 19/07/2018
 * @version 0.1
 */

public class ValidateCommit extends ValidateTrans {
	public ValidateCommit(int th) {
		super(th,"validate_commit");
	}
}
