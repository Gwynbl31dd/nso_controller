/**
 * 
 */
package com.apaulin.http.rpc;

/**
 * Enumerates keys in a list.
 * @author Anthony Paulin
 * @since 11/09/2019
 * @version 0.1
 * The th parameter is the transaction handle.
 * The path parameter is a keypath pointing to a list. Required on first invocation - optional in following.
 * The chunk_size parameter is the number of requested keys in the result. Optional - default is unlimited.
 * The start_with parameter will be used to filter out all those keys that do not start with the provided strings.
 * The parameter supports multiple keys e.g. if the list has two keys, then start_with can hold two items.
 * The lh (list handle) parameter is optional (on the first invocation) but must be used in following
 * invocations.
 * 
 */
public class GetListKeys extends GetSchema {	
	
	/**
	 * Craft a config request
	 * @param th
	 * @param path
	 * @param id
	 */
	public GetListKeys(int th,String path) {
		super("get_list_keys");
		super.setTh(th);
		super.setPath(path); 
		super.setRequest();
	}

	
	/**
	 * Build the request
	 */
	public void setRequest(boolean noResultAs) {
		String request = "{\"th\": "+getTh()+",\"path\":\""+getPath()+"}";
		super.setRequest(request); 
	}
}
