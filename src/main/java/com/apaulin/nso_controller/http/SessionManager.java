/**
 * 
 */
package com.apaulin.nso_controller.http;

import java.util.ArrayList;
import java.util.Random;

import com.apaulin.nso_controller.http.rpc.RPCException;
import com.apaulin.nso_controller.exception.NSOException;

/**
 * Manage the sessions to NSO
 * 
 * @author Anthony Paulin
 * @version 0.1
 * @since 13/01/2020
 *
 */
public class SessionManager {
	private ArrayList<RpcSession> sessionList = new ArrayList<RpcSession>();
	private int currentIndex = 0;

	public void add(RpcSession session) {
		sessionList.add(session);
		currentIndex++;
	}

	public RpcSession getCurrentSession() {
		return this.sessionList.get(getCurrentIndex());
	}

	/**
	 * @return the id of the session
	 */
	public int getCurrentId() {
		return getCurrentSession().getId();
	}

	/**
	 * 
	 * @return the req
	 * @throws RPCException
	 *             RPC related exception
	 * @throws NSOException
	 *             NSo related exception
	 */
	public RpcRequest getCurrentReq() throws RPCException, NSOException {
		// testTransaction();
		return getCurrentSession().getReq();
	}

	/**
	 * Modify the commit validation
	 * 
	 * @param commitValidated
	 *            the commitValidated to set
	 */
	public void setCommitValidated(boolean commitValidated) {
		getCurrentSession().setCommitValidated(commitValidated);
	}

	/**
	 * Return the transaction id (th) without a new request (local saved) This will
	 * not work if you managed your transaction yourself
	 * 
	 * @return the transaction id
	 */
	public int getTransactionId() {
		return getCurrentSession().getTransactionId();
	}

	/**
	 * Get the id of the session
	 * 
	 * @return the id of the session
	 */
	public int getId() {
		return getCurrentId();
	}

	/**
	 * Set a random id for the session (called by default)
	 * 
	 * @return the id
	 */
	public int setId() {
		Random rand = new Random();
		return rand.nextInt(100) + 1;
	}

	/**
	 * Return the HTTPRequest object
	 * 
	 * @param req
	 *            the request to set
	 */
	public void setReq(RpcRequest req) {
		this.getCurrentSession().setReq(req);
	}

	/**
	 * This is used to check if you validated the commit
	 * 
	 * @return the commitValidated
	 */
	public boolean isCommitValidated() {
		return getCurrentSession().isCommitValidated();
	}

	/**
	 * Get the current index
	 * 
	 * @return the currentIndex
	 */
	public int getCurrentIndex() {
		return currentIndex - 1;
	}

	/**
	 * Set the index number
	 * 
	 * @param index the session index
	 */
	public void setIndex(int index) {
		if (index < 0 || index > sessionList.size()) {
			throw new IndexOutOfBoundsException();
		}
		this.currentIndex = index;
	}

	/**
	 * Get the session list
	 * 
	 * @return the sessionList
	 */
	public ArrayList<RpcSession> getSessionList() {
		return sessionList;
	}

	/**
	 * Set the session list
	 * 
	 * @param sessionList
	 *            the sessionList to set
	 */
	public void setSessionList(ArrayList<RpcSession> sessionList) {
		this.sessionList = sessionList;
	}

}
