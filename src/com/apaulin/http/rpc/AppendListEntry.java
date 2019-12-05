/**
 * 
 */
package com.apaulin.http.rpc;

/**
 * Append a list entry to a leaf-list.
 * @author Anthony Paulin
 * @since  30/07/2018
 * @version 0.1
 * The path is a keypath pointing to a leaf-list
 */
public class AppendListEntry extends GetSchema {
	private String value;
	
	public AppendListEntry(int th, String path,String value) {
		super("append_list_entry");
		super.setTh(th);
		super.setPath(path);
		this.value = value;
		this.setRequest();
	} 
	
	/**
	 * Build the request
	 */
	public void setRequest() {
		super.setRequest("{\"th\": "+getTh()+",\"path\":\""+getPath()+"\",\"value\": \""+value+"\"}"); 
	}
}
