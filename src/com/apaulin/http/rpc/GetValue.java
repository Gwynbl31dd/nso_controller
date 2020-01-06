/**
 * 
 */
package com.apaulin.http.rpc;

/**
 * Gets a leaf value
 * @author Anthony Paulin
 * @since 19/07/2018
 * @version 0.1
 * The path param is a keypath pointing to a value.
 * The check_default param adds is_default to the result if set to true.
 * is_default is set to true if the default value handling returned the value.
 *
 */
public class GetValue extends GetSchema {
	private boolean checkDefault = false;
	public GetValue(int th, String path,boolean checkDefault) {
		super("get_value");
		super.setTh(th);
		super.setPath(path);
		this.setCheck_default(checkDefault);
		this.setRequest();
	}
	
	public GetValue(int th,String path) {
		this(th,path,false);
	}
	
	private boolean isCheck_default() {
		return checkDefault;
	}
	private void setCheck_default(boolean checkDefault) {
		this.checkDefault = checkDefault;
	}

	/**
	 * Build the request
	 */
	@Override
	public void setRequest() {
		String request = "{\"th\": "+getTh()+",\"path\":\""+getPath()+"\",\"check_default\": "+isCheck_default()+"}";
		super.setRequest(request); 
	}
}
