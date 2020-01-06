/**
 * 
 */
package com.apaulin.http.rpc;

import com.apaulin.http.StringArray;

/**
 * Get node attributes
 * @author Anthony Paulin
 * @since 20/07/2018
 * @version 0.1
 * The path param is a keypath pointing to the node and the names 
 * param is a list of attribute names that you want to retrieve.
 * 
 */
public class GetAttrs extends GetSchema{
	private StringArray names;
	
	public GetAttrs(int th, String path,StringArray names) {
	 	super("get_attrs");
		super.setTh(th);
		super.setPath(path);
		this.setNames(names);
		this.setRequest();
	}
	
	/**
	 * @return the names
	 */
	private StringArray getNames() {
		return names;
	}

	/**
	 * @param names the names to set
	 */
	private void setNames(StringArray names) {
		this.names = names;
	}
	
	@Override
	public void setRequest() {
		String request = "{\"th\": "+getTh()+",\"path\":\""+getPath()+"\",\"names\": "+getNames()+"}";
		super.setRequest(request); 
	}
	
}
