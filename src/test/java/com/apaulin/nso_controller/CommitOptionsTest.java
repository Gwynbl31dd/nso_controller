package com.apaulin.nso_controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.apaulin.nso_controller.http.rpc.*;

public class CommitOptionsTest {

	@Test
	public void CommitOptions() throws UnsupportedEncodingException, IOException, RCPparameterException {

		String options = "[\"commit-queue=async\",\"commit-queue-atomic=true\",\"commitQueueBlockOthers\",\"commit-queue-lock\",\"commit-queue-tag=test\",\"commit-queue-timeout=10\",\"dry-run=native\",\"dry-run-reverse\",\"no-deploy\",\"no-lsa\",\"no-networking\",\"no-out-of-sync-check\",\"no-overwrite\",\"no-revision-drop\"]";

		CommitOptions commitOptions = new CommitOptions();

		commitOptions.addCommitQueue("async");
		commitOptions.addCommitQueueAtomic(true);
		commitOptions.addCommitQueueBlockOthers();
		commitOptions.addCommitQueueLock();
		commitOptions.addCommitQueueTag("test");
		commitOptions.addCommitQueueTimeout(10);
		commitOptions.addDryRun("native");
		commitOptions.addDryRunReverse();
		commitOptions.addNoDeploy();
		commitOptions.addNoLSA();
		commitOptions.addNoNetworking();
		commitOptions.addNoOutOfSyncCheck();
		commitOptions.addNoOverwrite();
		commitOptions.addNoRevisionDrop();

		assertEquals(commitOptions.toString(), options);
	}
	
	@Test(expected = RCPparameterException.class)
	public void CommitOptionsFailCommitQueue() throws UnsupportedEncodingException, IOException, RCPparameterException {
		CommitOptions commitOptions = new CommitOptions();
		commitOptions.addCommitQueue("test");
	}
	
	@Test(expected = RCPparameterException.class)
	public void CommitOptionsFailDryRun() throws UnsupportedEncodingException, IOException, RCPparameterException {
		CommitOptions commitOptions = new CommitOptions();
		commitOptions.addDryRun("test");
	}

}
