/**
 * 
 */
package com.apaulin.http.rpc;

/**
 * Get a list of the session's batch commands
 * @author Anthony Paulin
 * @since 19/07/2018
 * @version 0.1
 *
 */
public class GetCmds extends RPCRequest{

	public GetCmds() {
		super("get_cmds");
	}

}
