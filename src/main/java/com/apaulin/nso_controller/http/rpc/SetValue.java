/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

/**
 * Sets a leaf value
 * @author Anthony Paulin
 * @since 19/07/2018
 * @version 0.1
 * The path param is the keypath to give a new value as specified with the value param.
 * value can be an array when the path is a leaf-list node.
 * When value is null, the set_value method acts like delete.
 * When dryrun is true, this function can be used to test if a value is valid or not.
 */
public class SetValue extends GetSchema {
	private boolean dryRun = false;
	private Object value = null;
	private boolean isString = false; //used only if the object is a String
	
	/**
	 * Build a request
	 * @param th
	 * @param path
	 */
	public SetValue(int th, String path,String value) {
		super("set_value");
		super.setTh(th);
		super.setPath(path);
		this.value = value;
		this.isString = true;
		this.setRequest();
	}
	
	/**
	 * Build a request
	 * @param th
	 * @param path
	 * @param value
	 */
	public SetValue(int th,String path,boolean value) {
		super("set_value");
		super.setTh(th);
		super.setPath(path);
		this.value = value;
		this.setRequest();
	}

	/**
	 * Build a request
	 * @param th
	 * @param path
	 * @param value
	 */
	public SetValue(int th,String path,int value) {
		super("set_value");
		super.setTh(th);
		super.setPath(path);
		this.value = value;
		this.setRequest();
	}
	
	/**
	 * @return the dryRun
	 */
	public boolean isDryRun() {
		return dryRun;
	}

	/**
	 * @param dryRun the dryRun to set
	 */
	public void setDryRun(boolean dryRun) {
		this.dryRun = dryRun;
	}
	
	
	/**
	 * Build the request
	 */
	@Override
	public void setRequest() {
		String request = new String();
		if(isString == true) {
			request = "{\"th\": "+getTh()+",\"path\":\""+getPath()+"\",\"value\": \""+value+"\",\"dryrun\": "+isDryRun()+"}";
		}
		else{
			request = "{\"th\": "+getTh()+",\"path\":\""+getPath()+"\",\"value\": "+value+",\"dryrun\": "+isDryRun()+"}";
		}	
		super.setRequest(request); 
	}

	
}
