/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Anthony Paulin
 * @version 0.1
 * @since 17/09/2020
 *
 */
public class ValueCheck {
	
	private ValueCheck() {
		super();
	}
	
	/**
	 * Check if a value exists
	 * @param value Value to check
	 * @param list List to check
	 * @throws RCPparameterException RPC related exception
	 */
	public static void valueListExist(String value,String[] list) throws RCPparameterException {
		ArrayList<String> test = new ArrayList<>(Arrays.asList(list));
		if(!test.contains(value)) {
			throw new RCPparameterException(value);
		}
	}
	
}
