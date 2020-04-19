package com.apaulin.nso_controller.json;

import com.apaulin.nso_controller.http.rpc.RPCException;
import com.apaulin.nso_controller.http.rpc.RpcData;
import com.apaulin.nso_controller.exception.NSOException;
import com.apaulin.nso_controller.http.RpcRequest;
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
	 * @param request RPC request
	 * @param req RPC request
	 * @return the parsed result
	 * @throws RPCException rpc related exception
	 * @throws NSOException  nso related exception
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
	 * @param jsonResult json to parse
	 * @param parser parser to use
	 * @return json object
	 */
	public static JSONObject parseResult(String jsonResult, String parser) {
		JSONObject jO = JsonPath.parse(jsonResult).read(parser, JSONObject.class);
		return jO;
	}
	
	/**
	 * Parse the result as a String
	 * @param jsonResult json to parse
	 * @param parser parser to use
	 * @return json object
	 */
	public static String parseStringResult(String jsonResult, String parser) {
		String jO = JsonPath.parse(jsonResult).read(parser, String.class);
		return jO;
	}
	
	/**
	 * Parse as a boolean
	 * @param jsonResult json to parse
	 * @param parser parser to use
	 * @return json object
	 */
	public static Boolean parseBool(String jsonResult, String parser) {
		Boolean jO = JsonPath.parse(jsonResult).read(parser, Boolean.class);
		return jO;
	}
	
	/**
	 * Parse the result
	 * @param jsonResult json to parse
	 * @return json object
	 */
	private static JSONObject parseResult(String jsonResult) {
		return parseResult(jsonResult, "$.result");
	}

	/**
	 * Parse the error
	 * @param jsonResult json to parse
	 * @return json object
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
