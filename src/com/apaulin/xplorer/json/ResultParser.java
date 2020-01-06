package com.apaulin.xplorer.json;

import com.apaulin.http.RpcRequest;
import com.apaulin.http.rpc.RPCException;
import com.apaulin.http.rpc.RpcData;
import com.apaulin.xplorer.exception.NSOException;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import net.minidev.json.JSONObject;

/**
 * @author  Anthony Paulin
 * @version 0.1
 * @since   24/08/2018
 */
public class ResultParser {
	/**
	 * Process the raw data to extract the result
	 * This validate JSON
	 * 
	 * @param request
	 * @return
	 * @throws RPCException
	 * @throws NSOException 
	 */
	public static String processRawData(RpcData request,RpcRequest req) throws RPCException, NSOException {
		String raw = null;
		raw = req.send(request);
		// Get the raw data
		JSONObject error = parseError(raw);
		// If no error
		if (error != null) {
			throw new RPCException(error.toString());
		}
		return parseResult(raw).toString();
	}
	
	/**
	 * Parse the result
	 * @param jsonResult
	 * @param parser
	 * @return
	 */
	public static JSONObject parseResult(String jsonResult, String parser) {
		JSONObject jO = JsonPath.parse(jsonResult).read(parser, JSONObject.class);
		return jO;
	}
	
	/**
	 * Parse the result as a String
	 * @param jsonResult
	 * @param parser
	 * @return
	 */
	public static String parseStringResult(String jsonResult, String parser) {
		String jO = JsonPath.parse(jsonResult).read(parser, String.class);
		return jO;
	}
	
	/**
	 * Parse as a boolean
	 * @param jsonResult
	 * @param parser
	 * @return
	 */
	public static Boolean parseBool(String jsonResult, String parser) {
		Boolean jO = JsonPath.parse(jsonResult).read(parser, Boolean.class);
		return jO;
	}
	
	/**
	 * Parse the result
	 * @param jsonResult
	 * @return
	 */
	private static JSONObject parseResult(String jsonResult) {
		return parseResult(jsonResult, "$.result");
	}

	/**
	 * Parse the error
	 * @param jsonResult
	 * @return
	 */
	public static JSONObject parseError(String jsonResult) {
		try {
			JSONObject jO = JsonPath.parse(jsonResult).read("$.error", JSONObject.class);
			return jO;
		} catch (PathNotFoundException e) {
			return null;
		}
	}
}
