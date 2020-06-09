package com.apaulin.nso_controller.http.rpc;

/**
 * Load XML/JSON configuration into current transaction
 * 
 * The data param is the data to be loaded into the transaction.
 * mode controls how the data is loaded into the transaction, analogous
 * with the CLI command load.format informs load about which format
 * data is in. If format is xml the data must be an XML document
 * encoded as a string. If format is json data can either be a JSON
 * document encoded as a string or the JSON data itself.
 * 
 * This mode uses a String instead of a file path
 * 
 * @author Anthony Paulin
 * @since 09/06/2020
 * @version 0.1 		
 */
public class LoadString extends Load {

	
	/**
	 * Load the string payload
	 * 
	 * @param th transaction id
	 * @param path keypath
	 * @param data data String payload 
	 * @throws RCPparameterException RPC Error
	 */
	public LoadString(int th, String path, String data)  throws RCPparameterException {
		super("load");
		super.setData(data);
		super.setPath(path);
		super.setTh(th);
		setRequest();
	}
	
	/**
	 * Load the String payload
	 * 
	 * @param th transaction id
	 * @param path keypath
	 * @param data data to load
	 * @param mode mode used
	 * @param format format of the data
	 * @throws RCPparameterException RPC related exception
	 */
	public LoadString(int th, String path, String data, String mode, String format) throws RCPparameterException{
		super("load");
		this.setPath(path);
		this.setData(data);
		this.setMode(mode);
		this.setFormat(format);
		this.setTh(th);
		setRequest();
	}

	/**
	 * Build the request
	 */
	@Override
	public void setRequest() {
		String request = null;
		request = "{\"th\": " + getTh() + ",\"data\": \"" + data + "\",\"path\":\"" + getPath()
				+ "\",\"format\": \"" + getFormat() + "\"," + "\"mode\": \"" + getMode() + "\"}";
		super.setRequest(request);
	}
}
