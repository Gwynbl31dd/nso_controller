/**
 * 
 */
package com.apaulin.http.rpc;

/**
 * Extracts system settings such as capabilities, supported datastores, etc.
 * @author Anthony Paulin
 * @since 23/07/2018
 * @version 0.1
 * 
 */
public class GetSystemSetting extends GetSchema {
	private String operation;
	private final static String[] OPERATION_VALUES = { "capabilities", "customizations", "models", "user", "version", "all", "namespaces" };
	
	
	public GetSystemSetting(int th,String operation) throws RCPparameterException {
		super("get_system_setting");
		super.setTh(th);
		this.setOperation(operation);
		this.setRequest();
	}

	
	/**
	 * Build the request
	 */
	@Override
	public void setRequest() {
		String request = "{\"operation\": \""+getOperation()+"\"  }";
		super.setRequest(request);
	}


	/**
	 * @return the operation
	 */
	private String getOperation() {
		return operation;
	}


	/**
	 * @param operation the operation to set
	 * @throws RCPparameterException 
	 */
	private void setOperation(String operation) throws RCPparameterException {
		valueListExist(operation, OPERATION_VALUES);
		this.operation = operation;
	}
}
