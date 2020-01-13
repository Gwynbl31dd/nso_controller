/**
 * 
 */
package com.apaulin.xplorer;

import com.apaulin.examples.ShowRunExample;
import com.apaulin.examples.ShowRunMultiSessionExample;

/**
 * @author Anthony Paulin
 * @since  10/07/2018
 * @version 1.0
 * Generate robot doc 
 * jython -m robot.libdoc eclipse-workspace/Xplorer/src/com/apaulin/xplorer/NSOController.java nso_controller.html
 */
public class Main {
	/**
	 * @param args
	 * @category test
	 */
	public static void main(String[] args) {
		new ShowRunMultiSessionExample();
	}
}
