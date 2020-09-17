/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;


/**
 * New JSON-RCP transaction
 * 
 * @author Anthony Paulin
 * @since 13/07/2018
 * @version 0.1
 *
 */
public class NewTrans extends RpcData {
	
	private final static String[] DB_VALUES = { "startup", "running", "candidate" };
	private final static String[] MODE_VALUES = { "read", "read_write" };
	private final static String[] CONF_MODE_VALUES = { "private", "shared", "exclusive" };
	private final static String[] ON_PENDING_CHANGES_VALUES = { "reuse", "reject", "discard" };
    
	private String db;
	private String mode;
	private String conf_mode;
	private String tag;
	private String on_pending_changes;

	/**
	 * Build a new transaction request
	 * 
	 * @param db
	 *    -        "startup" | "running" | "candidate", default: "running"
	 * @param mode
	 *    -       "read" | "read_write", default: "read"
	 * @param conf_mode
	 *    -       "private" | "shared" | "exclusive", default: "private"
	 * @param tag
	 *    -       String
	 * @param on_pending_changes
	 *    -       "reuse" | "reject" | "discard", default: "reuse"
	 * @throws RCPparameterException RPC related exception
	 */
	public NewTrans(String db, String mode, String conf_mode, String tag, String on_pending_changes)
			throws RCPparameterException {
		super("new_trans");
		this.setDb(db);
		this.setMode(mode);
		this.setConfMode(conf_mode);
		this.setTag(tag);
		this.setOnPendingChanges(on_pending_changes);
		setRequest();
	}

	/**
	 * Build the request
	 */
	public void setRequest() {
		String request = "{\"db\": \"" + getDb() + "\"," + "\"mode\": \"" + getMode() + "\" ," + "\"conf_mode\": \""
				+ getConfMode() + "\"," + "\"tag\": \"" + getTag() + "\"," + "\"on_pending_changes\": \""
				+ getOnPendingChanges() + "\"}";
		super.setRequest(request);
	}

	/**
	 * Build a new transaction request
	 * 
	 * @param tag tag used for the new transaction
	 * @throws RCPparameterException RPC related exception
	 */
	public NewTrans(String tag) throws RCPparameterException {
		this("running", "read", "private", tag, "reuse");
	}

	/**
	 * @return the db
	 */
	public String getDb() {
		return db;
	}

	/**
	 * @param db
	 *            the db to set
	 * @throws RCPparameterException RPC related exception
	 */
	public void setDb(String db) throws RCPparameterException {
		ValueCheck.valueListExist(db, DB_VALUES);
		this.db = db;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 * @throws RCPparameterException RPC related exception
	 */
	public void setMode(String mode) throws RCPparameterException {
		ValueCheck.valueListExist(mode, MODE_VALUES);
		this.mode = mode;
	}

	/**
	 * @return the conf_mode
	 */
	public String getConfMode() {
		return conf_mode;
	}

	/**
	 * @param conf_mode
	 *            the conf_mode to set
	 * @throws RCPparameterException RPC related exception
	 */
	public void setConfMode(String conf_mode) throws RCPparameterException {
		ValueCheck.valueListExist(conf_mode, CONF_MODE_VALUES);
		this.conf_mode = conf_mode;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag
	 *            the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return the on_pending_changes
	 */
	public String getOnPendingChanges() {
		return on_pending_changes;
	}

	/**
	 * @param on_pending_changes
	 *            the on_pending_changes to set
	 * @throws RCPparameterException RPC related exception
	 */
	public void setOnPendingChanges(String on_pending_changes) throws RCPparameterException {
		ValueCheck.valueListExist(on_pending_changes, ON_PENDING_CHANGES_VALUES);
		this.on_pending_changes = on_pending_changes;
	}
}