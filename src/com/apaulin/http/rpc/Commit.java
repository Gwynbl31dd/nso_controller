/**
 * 
 */
package com.apaulin.http.rpc;

import com.apaulin.http.StringArray;
import com.apaulin.http.rpc.options.CommitOptions;

/**
 * Copy the configuration into the running datastore.
 * @author Anthony Paulin
 * @since 19/07/2018
 * @version 0.3
 * The timeout param represents the confirmed commit timeout, i.e. set it to zero (0) to commit without
 * timeout.
 */
public class Commit extends ValidateTrans{

	private StringArray flags = new StringArray();
	private int timeout = 30;//Set to zero by default now
	private boolean releaseLock = true;
	
	/**
	 * Build the request
	 * @param th
	 */
	public Commit(int th) {
		super(th,"commit");
		this.setRequest();
	}
	
	/**
	 * Built the request
	 * @param th
	 * @param timeout
	 */
	public Commit(int th,int timeout) {
		super(th,"commit");
		this.setTimeout(timeout);
	}
	
	/**
	 * Build the request
	 * @param th
	 * @param flags
	 */
	public Commit(int th,CommitOptions flags) {
		super(th,"commit");
	    this.setFlags(flags);
	    this.setRequest();
	}
	
	/**
	 * Build the request
	 * @param th
	 * @param flags
	 * @param timeout
	 */
	public Commit(int th,CommitOptions flags, int timeout) {
		super(th,"commit");
	    this.setFlags(flags);
	    this.setTimeout(timeout);
	    this.setRequest();
	}
	
	/**
	 * Build the request
	 * @param th
	 * @param flags
	 * @param timeout
	 * @param releaseLock
	 */
	public Commit(int th,CommitOptions flags, int timeout, boolean releaseLock) {
		super(th,"commit");
	    this.setFlags(flags);
	    this.setTimeout(timeout);
	    this.setReleaseLock(releaseLock);
	    this.setRequest();
	}

	/**
	 * Build the request
	 */
	public void setRequest() {
		String request = "{\"th\": "+getTh()+",\"timeout\": "+getTimeout()+",\"release_locks\" : "+isReleaseLock()+",\"flags\": "+getFlags()+"}";
		super.setRequest(request); 
	}
	
	/**
	 * @return the flags
	 */
	public StringArray getFlags() {
		return flags;
	}

	/**
	 * @param flags the flags to set
	 */
	public void setFlags(StringArray flags) {
		this.flags = flags;
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the releaseLock
	 */
	public boolean isReleaseLock() {
		return releaseLock;
	}

	/**
	 * @param releaseLock the releaseLock to set
	 */
	public void setReleaseLock(boolean releaseLock) {
		this.releaseLock = releaseLock;
	}
	
}
