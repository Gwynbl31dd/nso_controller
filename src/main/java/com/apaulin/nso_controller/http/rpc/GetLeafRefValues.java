package com.apaulin.nso_controller.http.rpc;

/**
 * Gets all possible values for a leaf with a leafref type
 * @author Anthony Paulin
 * @since 01/08/2018
 * @version 0.1
 *
 */
public class GetLeafRefValues extends GetSchema {

	public GetLeafRefValues(int th, String path) {
		super("get_leafref_values");
		super.setTh(th);
		super.setPath(path);
		super.setRequest();
	}

}
