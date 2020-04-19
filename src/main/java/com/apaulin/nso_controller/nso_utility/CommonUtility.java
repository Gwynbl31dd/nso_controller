/**
 * 
 */
package com.apaulin.nso_controller.nso_utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Common utility
 * @author Anthony Paulin
 * @version  0.1
 * @since    24/07/2018
 *
 */
public class CommonUtility {
	
	/**
	 * Read a file
	 * @param path path to use
	 * @return the file 
	 * @throws IOException Cannot read the data
	 */
	public static String readFile(String path) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while (line != null) {
			sb.append(line.trim());
			line = br.readLine()+"\n";
		}
		return sb.toString();
	}
}
