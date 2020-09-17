/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

/**
 * Get node attributes
 * @author Anthony Paulin
 * @since 23/08/2018
 * @version 0.1
 * Extracts all variables from a NSO service/device template
 * 
 */
public class GetTemplateVariables extends GetSchema{
	private String name;
	
	/**
	 * Build the template variable request
	 * @param th transaction id
	 * @param name template name
	 */
	public GetTemplateVariables(int th,String name) {
	 	super("get_template_variables");
		super.setTh(th);
		this.setName(name);
		this.setRequest();
	}
	
	/**
	 * @return the names
	 */
	private String getName() {
		return name;
	}

	/**
	 * @param name the names to set
	 */
	private void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Build the request
	 */
	@Override
	public void setRequest() {
		String request = "{\"th\": "+getTh()+",\"name\": \""+getName()+"\"}";
		super.setRequest(request); 
	}
	
}
