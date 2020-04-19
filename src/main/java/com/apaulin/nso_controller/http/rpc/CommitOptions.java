/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

import com.apaulin.nso_controller.http.StringArray;

/**
 * Return a commit StringArray of commit option
 * @author Anthony Paulin
 * @since 22/07/2018
 * @version 0.1
 */
public class CommitOptions extends StringArray {
	
	private static final long serialVersionUID = -8789914606834976699L;
	
	public final static String[] MODE_VALUES = { "async", "sync", "bypass" };
	
	public final static String[] FORMAT_VALUES = { "xml", "cli", "native" };
	
	/**
	 * no-revision-drop - NSO will not run its data model revision algorithm, which requires all participating
	 * managed devices to have all parts of the data models for all data contained in this transaction. Thus,
     * this flag forces NSO to never silently drop any data set operations towards a device.
	 */
	public void addNoRevisionDrop() {
		this.add("no-revision-drop");
	}
	
	/**
	 * no-deploy - Commit without invoking the service create method, i.e, write the service instance data
     * without activating the service(s). The service(s) can later be re-deployed to write the changes of the
     * service(s) to the network.
	 */
	public void addNoDeploy() {
		this.add("no-deploy");
	}
	
	/**
	 * commit-queue=MODE - Where MODE is: async, sync or bypass. Commit the transaction data to the
     * commit queue. If the async value is set the operation returns successfully if the transaction data has
     * been successfully placed in the queue. The sync value will cause the operation to not return until the
     * transaction data has been sent to all devices, or a timeout occurs. The bypass value means that if /
     * devices/global-settings/commit-queue/enabled-by-default is true the data in
     * this transaction will bypass the commit queue. The data will be written directly to the devices.
	 */
	public void addCommitQueue(String mode) {
        this.add("commit-queue="+mode);
	}
	
	/**
	 * commit-queue-atomic=ATOMIC - Where ATOMIC is: true or false. Sets the atomic behaviour of
     * the resulting queue item. If ATOMIC is set to false, the devices contained in the resulting queue item
     * can start executing if the same devices in other non-atomic queue items ahead of it in the queue are
     * completed. If set to true, the atomic integrity of the queue item is preserved.
	 */
	public void addCommitQueueAtomic(boolean atomic) {
		this.add("commit-queue-atomic="+atomic);
	}
	
	/**
	 * commit-queue-block-others - The resulting queue item will block subsequent queue items, which use
     * any of the devices in this queue item, from being queued.
	 */
    public void addCommitQueueBlockOthers() {
    	this.add("commitQueueBlockOthers");
    }
    
    /**
     * commit-queue-lock - Place a lock on the resulting queue item. The queue item will not be processed
     * until it has been unlocked, see the actions unlock and lock in /devices/commit-queue/
     * queue-item. No following queue items, using the same devices, will be allowed to execute as long
     * as the lock is in place.
     */
    public void addCommitQueueLock() {
    	this.add("commit-queue-lock");
    }
    
    /**
     * commit-queue-tag=TAG - Where TAG is a user defined opaque tag. The tag is present in all
     * notifications and events sent referencing the specific queue item.
     */
    public void addCommitQueueTag(String tag) {
    	this.add("commit-queue-tag="+tag);
    }
    
    /**
     * commit-queue-timeout=TIMEOUT - Where TIMEOUT is infinity or a positive integer. Specifies a
     * maximum number of seconds to wait for the transaction to be committed. If the timer expires, the
     * transaction is kept in the commit-queue, and the operation returns successfully. If the timeout is not
     * set, the operation waits until the transaction is committed.
     */
    public void addCommitQueueTimeout(int timeout) {
    	this.add("commit-queue-timeout="+timeout);
    }
    
    /**
     * no-networking - Do not send data to the devices; this is a way to manipulate CDB in NSO without
     * generating any southbound traffic.
     */
    public void addNoNetworking() {
    	this.add("no-networking");
    }
    
    /**
     * dry-run=FORMAT - Where FORMAT is the desired output format: xml, cli or native. Validate and
     * display the configuration changes but do not perform the actual commit. Neither CDB nor the devices
     * are affected. Instead the effects that would have taken place is showed in the returned output.
     */
    public void addDryRun(String format) {
    	this.add("dry-run="+format);
    }
    
    /**
     * dry-run-reverse - Used with the dry-run=native flag this will display the device commands for getting
     * back to the current running state in the network if the commit is successfully executed. Beware that if
     * any changes are done later on the same data the reverse device commands returned are invalid.
     */
    public void addDryRunReverse() {
    	this.add("dry-run-reverse");
    }
    
    /**
     * no-out-of-sync-check - Continue with the transaction even if NSO detects that a device's
     * configuration is out of sync. Can't be used with no-overwrite.
     */
    public void addNoOutOfSyncCheck() {
    	this.add("no-out-of-sync-check");
    }
    
    /**
     * no-overwrite - NSO will check that the data that should be modified has not changed on the device
     * compared to NSO's view of the data. Can't be used with no-out-of-sync-check.
     */
    public void addNoOverwrite() {
    	this.add("no-overwrite");
    }
    
    /**
     * use-lsa - Force handling LSA nodes as such.
     */
    public void addUseLSA() {
    	this.add("use-lsa");
    }
    
    /**
     * no-lsa - Do not handle any of the LSA nodes as such. These nodes will be handled as any other
	 * device.
     */
    public void addNoLSA() {
    	this.add("no-lsa");
    }
}
