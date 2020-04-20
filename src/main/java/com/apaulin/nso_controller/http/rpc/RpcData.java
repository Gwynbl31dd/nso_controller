package com.apaulin.nso_controller.http.rpc;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.entity.StringEntity;

/**
 * Define the main RPC request body
 * @author Anthony Paulin
 * @since 13/07/2018
 * @version 0.1
 *
 */
abstract public class RpcData implements RPC {
	private String method;//Method of the request
	private int id = 0;//ID by default is zero
	private String request = "{}";//Request by default empty
	private static final String RPC_VERSION = "2.0";
    
	/**
	 * Build base for NCPRequest
	 * @param method method to use
	 */
	public RpcData(String method) {
		this.setMethod(method);
	}
	
	/**
	 * Get the method
	 * @return returns the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Set the method
	 * @param method The method
	 */
	public void setMethod(String method) {
		this.method = method;
	}
    
	/**
	 * Get the ID
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the ID
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return the built request
	 * @return the request
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * Build the request
	 * @param request The string parameters
	 */
	public void setRequest(String request){
		this.request = request;
	}
	
	@Override
	public StringEntity getRequestEntity() throws UnsupportedEncodingException {
		//System.out.println("[->]{\"jsonrpc\":\""+RPC_VERSION+"\",\"id\":"+id+",\"method\":\""+method+"\",\"params\":"+request+"}");
		return new StringEntity("{\"jsonrpc\":\""+RPC_VERSION+"\",\"id\":"+id+",\"method\":\""+method+"\",\"params\":"+request+"}");
	}
	
	protected static void valueListExist(String value,String[] list) throws RCPparameterException {
		ArrayList<String> test = new ArrayList<String>(Arrays.asList(list));
		if(!test.contains(value)) {
			throw new RCPparameterException(value);
		}
	}
	
}
