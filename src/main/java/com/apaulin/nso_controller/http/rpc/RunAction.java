package com.apaulin.nso_controller.http.rpc;

/**
 * 
 * Actions are as specified in th YANG module, i.e. having a specific name and a
 * well defined set of parameters and result. the path param is a keypath
 * pointing to an action or rpc in and the params param is a JSON object with
 * action parameters. The format param defines if the result should be an array
 * of key values or a pre-formatted string on bracket format as seen in the CLI.
 * The result is also as specified by the YANG module. The JSON-RPC API Method
 * run_action Both a comet_id and handle need to be provided in order to receive
 * notifications.
 * 
 * NOTE This method is often used to call an action that uploads binary data
 * (e.g. images) and retrieving them at a later time. While retrieval is not a
 * problem, uploading is a problem, because JSON-RPC request payloads have a
 * size limitation (e.g. 64 kB). The limitation is needed for performance
 * concerns because the payload is first buffered, before the JSON string is
 * parsed and the request is evaluated. When you have scenarios that need binary
 * uploads, please use the CGI functionality instead which has a size limitation
 * that can be configured, and which is not limited to JSON payloads, so one can
 * use streaming techniques.
 * 
 * @author Anthony Paulin
 * @since 24/07/2018
 * @version 1.0
 *
 */
public class RunAction extends GetSchema {
	
	private String param = "";
	private String format = "normal";
	private final static String[] FORMAT_VALUES = { "normal", "bracket", "json" };

	/**
	 * Run an action
	 * 
	 * @param th
	 *            transaction id
	 * @param path
	 *            keypath
	 */
	public RunAction(int th, String path) {
		super("run_action");
		super.setTh(th);
		super.setPath(path);
		super.setRequest();
	}

	/**
	 * Run an action
	 * 
	 * @param th
	 *            transaction id
	 * @param path
	 *            keypath
	 * @param format
	 *            format used for the output "normal", "bracket", "json" (default
	 *            normal)
	 * @throws RCPparameterException RPC related exception
	 */
	public RunAction(int th, String path, String format) throws RCPparameterException {
		super("run_action");
		super.setTh(th);
		super.setPath(path);
		setFormat(format);
		this.setRequest();
	}

	/**
	 * Run an action
	 * 
	 * @param th
	 *            transaction id
	 * @param path
	 *            keypath
	 * @param param
	 *            The format param defines if the result should be an array of key
	 *            values or a pre-formatted string on bracket format as seen in the
	 *            CLI. Eg : {"clockSettings": "2014-02-11T14:20:53.460%2B01:00"}
	 * @param format
	 *            format used for the output "normal", "bracket", "json" (default
	 *            normal)
	 * @throws RCPparameterException RPC related exception
	 */
	public RunAction(int th, String path, String format,String param) throws RCPparameterException {
		super("run_action");
		super.setTh(th);
		super.setPath(path);
		setFormat(format);
		this.param = param;
		this.setRequest();
	}

	/**
	 * Set format
	 * 
	 * @param format
	 *            output format
	 * @throws RCPparameterException
	 *             rpc related exception
	 */
	protected void setFormat(String format) throws RCPparameterException {
		ValueCheck.valueListExist(format, FORMAT_VALUES);
		this.format = format;
	}

	/**
	 * Get the format
	 * 
	 * @return file format
	 */
	protected String getFormat() {
		return format;
	}

	/**
	 * Build the request
	 */
	@Override
	public void setRequest() {
		String request = "";
		if(param.compareTo("") == 0) {
			request = "{\"th\": " + getTh() + ",\"path\":\"" + getPath() + "\",\"format\":\"" + getFormat() + "\"}";
		}
		else {
			request = "{\"th\": " + getTh() + ",\"path\":\"" + getPath() + "\",\"params\": "+param+",\"format\":\"" + getFormat() + "\"}";
		}
		super.setRequest(request);
	}

}
