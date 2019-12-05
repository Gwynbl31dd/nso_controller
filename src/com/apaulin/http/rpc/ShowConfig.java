/**
 * 
 */
package com.apaulin.http.rpc;

/**
 * Retrieves a compact string representation of the configuration
 * @author Anthony Paulin
 * @since 13/07/2018
 * @version 0.1
 * The path param is a keypath to the configuration to be returned. 
 * result_as controls the output format, string for a compact string format and json for json. 
 * max_size sets the maximum size of the data field in kb, set to 0 to disable the limit.
 * 
 */
public class ShowConfig extends GetSchema {	
	private final static String[] RESULT_AS_VALUES = {"string","json"};
	private String resultAs;
	private boolean withOper;
	private int maxSize;
	
	/**
	 * Craft a config request
	 * @param th
	 * @param path
	 * @param id
	 */
	public ShowConfig(int th,String path) {
		super("show_config");
		super.setTh(th);
		super.setPath(path); 
		super.setRequest();
	}
	
	/**
	 * Craft a config request
	 * @param th 
	 * @param path
	 * @param resultAs
	 * @param withOper
	 * @param maxSize
	 * @throws RCPparameterException 
	 */
	public ShowConfig(int th,String path,String resultAs, boolean withOper,int maxSize) throws RCPparameterException {
		super("show_config");
		super.setTh(th);
		super.setPath(path); 
		this.setResultAs(resultAs);
		this.setWithOper(withOper);
		this.setMaxSize(maxSize);
		this.setRequest(false);
	}

	/**
	 * Craft a config request
	 * @param th 
	 * @param path
	 * @param withOper
	 * @param maxSize
	 * @throws RCPparameterException 
	 */
	public ShowConfig(int th,String path, boolean withOper,int maxSize) throws RCPparameterException {
		super("show_config");
		super.setTh(th);
		super.setPath(path); 
		this.setWithOper(withOper);
		this.setMaxSize(maxSize);
		this.setRequest(true);
	}
	/**
	 * Get the max size
	 * @return
	 */
	private int getMaxSize() {
		return maxSize;
	}

	/**
	 * Set Max size
	 * @param maxSize
	 * @throws RCPparameterException
	 */
	private void setMaxSize(int maxSize) throws RCPparameterException {
		if(maxSize >= 0) {
			this.maxSize = maxSize;
		}
		else {
			new String();
			throw new RCPparameterException(String.valueOf(maxSize));
		}
	}

	/**
	 * Check if with oper
	 * @return
	 */
	private boolean isWithOper() {
		return withOper;
	}

	/**
	 * set with oper
	 * @param withOper
	 */
	private void setWithOper(boolean withOper) {
		this.withOper = withOper;
	}
	
	/**
	 * get the result as
	 * @return
	 */
	private String getResultAs() {
		return resultAs;
	}

	/**
	 * Set result type
	 * @param resultAs
	 * @throws RCPparameterException
	 */
	private void setResultAs(String resultAs) throws RCPparameterException {
		valueListExist(resultAs,RESULT_AS_VALUES);
		this.resultAs = resultAs;
	}
	
	/**
	 * Build the request
	 */
	public void setRequest(boolean noResultAs) {
		String request = new String();
		if(noResultAs == false) {
			//TODO Check with version 4.6... 4.5 cannot use max size
			request = "{\"th\": "+getTh()+",\"path\":\""+getPath()+"\",\"result_as\": \""+getResultAs()+"\",\"with_oper\": "+isWithOper()+"}";
		}
		else{
			request = "{\"th\": "+getTh()+",\"path\":\""+getPath()+"\",\"with_oper\": "+isWithOper()+",\"max_size\": "+getMaxSize()+"}";
		}	
		super.setRequest(request); 
	}
}
