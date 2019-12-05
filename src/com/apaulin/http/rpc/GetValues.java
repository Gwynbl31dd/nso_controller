/**
 * 
 */
package com.apaulin.http.rpc;

import com.apaulin.http.StringArray;

/**
 * Get leaf values
 * @author Anthony Paulin
 * @since 20/07/2018
 * @version 0.1
 * The path param is a keypath pointing to a container. 
 * the leafs param is an array of children names residing under the parent container in the YANG module.
 */
public class GetValues extends GetSchema {
	
	private StringArray leafs;
	/**
	 * Build the request
	 * @param th
	 * @param path
	 * @param leafs
	 */
	public GetValues(int th, String path, StringArray leafs) {
		super("get_values");
		super.setTh(th);
		super.setPath(path);
		this.setLeafs(leafs);
		this.setRequest();
	}

	/**
	 * @return the leafs
	 */
	private StringArray getLeafs() {
		return leafs;
	}

	/**
	 * @param leafs the leafs to set
	 */
	private void setLeafs(StringArray leafs) {
		this.leafs = leafs;
	}

	/**
	 * Build the request
	 */
	public void setRequest() {
		String request = "{\"th\": "+getTh()+",\"path\":\""+getPath()+"\", \"leafs\": "+getLeafs()+"}";
		super.setRequest(request); 
	}
}
