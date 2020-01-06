/**
 * 
 */
package com.apaulin.http.rpc;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Schema Request
 * @author Anthony Paulin
 * @since 13/07/2018
 * @version 0.2
 */
public class GetSchema extends RpcData {
	private int th;
	private String path;
	
	/**
	 * Craft a request to get the schema
	 * @param th
	 * @param path
	 * @param id
	 */
	public GetSchema(int th,String path) {
		this("get_schema");
		this.setTh(th);
		this.path = path;
		this.setRequest();
	}
	
	/**
	 * Call super constructor
	 * @param id
	 * @param method
	 */
	protected GetSchema(String method) {
		super(method);
	}
	
	/**
	 * Build the request
	 */
	public void setRequest() {
		String request = "{\"th\": "+getTh()+",\"path\":\""+getPath()+"\"}";
		super.setRequest(request); 
	}
	
	/**
	 * Return the path
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Set the path
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Get the transaction number
	 * @return
	 */
	public int getTh() {
		return th;
	}

	/**
	 * Set the transaction number
	 * @param th
	 */
	public void setTh(int th) {
		this.th = th;
	}
	
	protected static void valueListExist(String value,String[] list) throws RCPparameterException {
		ArrayList<String> test = new ArrayList<String>(Arrays.asList(list));
		if(!test.contains(value)) {
			throw new RCPparameterException(value);
		}
	}

}
