package com.apaulin.http.rpc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Load XML/JSON configuration into current transaction
 * 
 * @author Anthony Paulin
 * @since 19/07/2018
 * @version 0.1 
 * 			The data param is the data to be loaded into the transaction.
 *          mode controls how the data is loaded into the transaction, analogous
 *          with the CLI command load.format informs load about which format
 *          data is in. If format is xml the data must be an XML document
 *          encoded as a string. If format is json data can either be a JSON
 *          document encoded as a string or the JSON data itself.
 */
public class Load extends GetSchema {
	private String data;
	private String format = "xml";
	private String mode = "merge";
	private final static String[] FORMAT_VALUES = { "json", "xml" };
	private final static String[] MODE_VALUES = { "create", "merge", "replace" };

	/**
	 * Load the file
	 * 
	 * @param th
	 * @param path
	 * @param data
	 * @throws FileNotFoundException
	 * @throws IOException
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
	 * @param path
	 * @param data
	 * @param mode
	 * @param format
	 * @throws RCPparameterException
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
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
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
		String dataToReturn = sb.toString();
		dataToReturn = dataToReturn.replaceAll("\"", "\\\\\"");
		return dataToReturn;
	}

	/**
	 * Set the data
	 * 
	 * @param data
	 */
	private void setData(String data) {
		this.data = data;
	}

	/**
	 * Get the format
	 * 
	 * @return
	 */
	private String getFormat() {
		return format;
	}

	/**
	 * Set format
	 * 
	 * @param format
	 * @throws RCPparameterException
	 */
	private void setFormat(String format) throws RCPparameterException {
		valueListExist(format, FORMAT_VALUES);
		this.format = format;
	}

	/**
	 * Get the mode
	 * 
	 * @return
	 */
	private String getMode() {
		return mode;
	}

	/**
	 * Set the mode
	 * 
	 * @param mode
	 * @throws RCPparameterException
	 */
	private void setMode(String mode) throws RCPparameterException {
		valueListExist(mode, MODE_VALUES);
		this.mode = mode;
	}

	/**
	 * Build the request
	 */
	public void setRequest() {
		String request = null;
		try {
			request = "{\"th\": " + getTh() + ",\"data\": \"" + getData() + "\",\"path\":\"" + getPath()
					+ "\",\"format\": \"" + getFormat() + "\"," + "\"mode\": \"" + getMode() + "\"}";
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.setRequest(request);
	}
}
