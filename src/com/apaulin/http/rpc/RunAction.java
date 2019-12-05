/**
 * 
 */
package com.apaulin.http.rpc;

/**
 * @author Anthony Paulin
 * @since 24/07/2018
 * @version 0.1
 *
 */
public class RunAction extends GetSchema {
	
	private String param;
	
	/**
	 * Run an action
	 * @param th
	 * @param path
	 */
	public RunAction(int th,String path) {
			super("run_action");
			super.setTh(th);
			super.setPath(path); 
			super.setRequest();
	}
	
	public RunAction(int th,String path,String param) {
		super("run_action");
		super.setTh(th);
		super.setPath(path); 
		this.param = param;
		this.setRequest();
	}
	
	/**
	 * Build the request
	 */
	public void setRequest() {
		String request = "{\"th\": "+getTh()+",\"path\":\""+getPath()+"\",\"params\": {\"args\": \""+param+"\"}}";
		super.setRequest(request); 
	}

}
