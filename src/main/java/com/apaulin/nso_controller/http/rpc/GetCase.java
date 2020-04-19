/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

/**
 * Get the case of a choice leaf
 * @author Anthony Paulin
 * @since 19/07/2018
 * @version 0.1
 * 
 */
public class GetCase extends GetSchema {	
	private String choice;

	/**
	 * Build a request
	 * @param th   Transaction number
	 * @param path   The path param is a keypath pointing to data that contains the choice leaf given by the choice param.
	 * @param choice  the choice param
	 */
	public GetCase(int th,String path,String choice) {
		super("get_case");
		super.setTh(th);
		super.setPath(path); 
		this.setChoice(choice);
		this.setRequest();
	}
	
	/**
	 * Build the request
	 */
	@Override
	public void setRequest() {
		String request = "{\"th\": "+getTh()+",\"path\":\""+getPath()+"\",\"choice\": \""+getChoice()+"\"}";
		super.setRequest(request); 
	}
	
	/**
	 * Get the choice
	 * @return the choice
	 */
	private String getChoice() {
		return choice;
	}
	
	/**
	 * Set the choice
	 * @param choice <String>
	 */
	private void setChoice(String choice) {
		this.choice = choice;
	}
}
