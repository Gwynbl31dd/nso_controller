/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

/**
 * Kills a user session, i.e. kicking out the user
 * @author Anthony Paulin
 * @since 13/07/2018
 * @version 0.1
 * The user param is either the username of a logged in user or session id.
 */
public class KickUser extends GetSchema {	
	private Object user;
	
	/**
	 * Craft a config request
	 * @param th transaction id
	 * @param user username
	 */
	public KickUser(int th,String user) {
		super("kick_user");
		super.setTh(th);
		setUser(user);
		super.setRequest();
	}

	/**
	 * Build the request
	 */
	@Override
	public void setRequest() {
		String request = new String();
		if(user instanceof Integer) {
			request = "{\"th\": "+getTh()+",\"user\": "+getUser()+"}";
		}
		else {
			request = "{\"th\": "+getTh()+",\"user\": \""+getUser()+"\"}";
		}
		super.setRequest(request); 
	}

	/**
	 * @return the user
	 */
	public Object getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Object user) {
		this.user = user;
	}
}
