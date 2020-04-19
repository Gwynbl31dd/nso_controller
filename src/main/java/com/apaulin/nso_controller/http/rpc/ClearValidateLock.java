/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;
/**
 * Releases validate lock taken by validate_commit
* @author Anthony Paulin
* @since 19/07/2018
* @version 0.1
*/
public class ClearValidateLock extends ValidateTrans{

	/**
	 * 
	 * @param th the transaction id
	 */
	public ClearValidateLock(int th) {
		super(th,"clear_validate_lock");
	}
}
