package com.apaulin.http.rest;

/**
 * Exception generated for the HTTP request
 * @author Anthony Paulin
 * @since 23/07/2018
 * @version 0.1
 *
 */

public class HTTPException extends Exception {
	private static final long serialVersionUID = 1149094808932483934L;
	public HTTPException(String error,int code,String url) {
		super("HTTP error due to code :"+code+"\n"+error+"\n @ "+url);
	}
}
