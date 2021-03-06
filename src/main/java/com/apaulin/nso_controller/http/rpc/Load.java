package com.apaulin.nso_controller.http.rpc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Load XML/JSON configuration into current transaction
 * 
 * The data param is the data to be loaded into the transaction. mode controls
 * how the data is loaded into the transaction, analogous with the CLI command
 * load.format informs load about which format data is in. If format is xml the
 * data must be an XML document encoded as a string. If format is json data can
 * either be a JSON document encoded as a string or the JSON data itself.
 * 
 * @author Anthony Paulin
 * @since 19/07/2018
 * @version 0.2
 */
public class Load extends GetSchema {
	protected String data;
	private String format = "xml";
	private String mode = "merge";
	private final static String[] FORMAT_VALUES = { "json", "xml" };
	private final static String[] MODE_VALUES = { "create", "merge", "replace" };

	protected Load(String mode) {
		super(mode);
	}

	/**
	 * Load the file
	 * 
	 * @param th
	 *            transaction id
	 * @param path
	 *            keypath
	 * @param data
	 *            data path or data string
	 * @throws FileNotFoundException
	 *             Cannot find the file
	 * @throws IOException
	 *             Input output exception
	 */
	public Load(int th, String path, String data) throws FileNotFoundException, IOException {
		super("load");
		this.setData(data);
		this.setPath(path);
		this.setTh(th);
		setRequest();
	}

	/**
	 * Load the file
	 * 
	 * @param th
	 *            transaction id
	 * @param path
	 *            keypath
	 * @param data
	 *            data to load path or string
	 * @param mode
	 *            mode used
	 * @param format
	 *            format of the data
	 * @throws RCPparameterException
	 *             RPC related exception
	 */
	public Load(int th, String path, String data, String mode, String format) throws RCPparameterException {
		super("load");
		this.setPath(path);
		this.setData(data);
		this.setMode(mode);
		this.setFormat(format);
		this.setTh(th);
		setRequest();
	}

	/**
	 * Read the file
	 * 
	 * @return The data
	 * @throws FileNotFoundException
	 *             cannot find the file
	 * @throws IOException
	 *             cannot read the file
	 */
	private String getData() throws FileNotFoundException, IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(data));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while (line != null) {
			sb.append(line.trim());
			line = br.readLine();
		}
		return stringify(sb.toString());
	}

	/**
	 * Stringify the payload to send as data
	 * 
	 * @param payload
	 *            Base payload
	 * @return Stringified payload
	 */
	public String stringify(String payload) {
		String dataToReturn = payload;
		return dataToReturn.replaceAll("\"", "\\\\\"");
	}

	/**
	 * Set the data
	 * 
	 * @param data
	 *            data to use
	 */
	protected void setData(String data) {
		this.data = data;
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
	 * Set format
	 * 
	 * @param format
	 *            file format
	 * @throws RCPparameterException
	 *             rpc related exception
	 */
	protected void setFormat(String format) throws RCPparameterException {
		ValueCheck.valueListExist(format, FORMAT_VALUES);
		this.format = format;
	}

	/**
	 * Get the mode
	 * 
	 * @return the mode
	 */
	protected String getMode() {
		return mode;
	}

	/**
	 * Set the mode
	 * 
	 * @param mode
	 *            mode to use
	 * @throws RCPparameterException
	 *             RPC related exception
	 */
	protected void setMode(String mode) throws RCPparameterException {
		ValueCheck.valueListExist(mode, MODE_VALUES);
		this.mode = mode;
	}

	/**
	 * Build the request
	 */
	@Override
	public void setRequest() {
		String request = null;
		try {
			request = "{\"th\": " + getTh() + ",\"data\": \"" + getData() + "\",\"path\":\"" + getPath()
					+ "\",\"format\": \"" + getFormat() + "\"," + "\"mode\": \"" + getMode() + "\"}";
		} catch (IOException e) {// If cannot read, load the data
			request = "{\"th\": " + getTh() + ",\"data\": \"" + stringify(data) + "\",\"path\":\"" + getPath()
					+ "\",\"format\": \"" + getFormat() + "\"," + "\"mode\": \"" + getMode() + "\"}";
		}
		super.setRequest(request);
	}
}
