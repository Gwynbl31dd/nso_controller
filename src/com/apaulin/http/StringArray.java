/**
 * 
 */
package com.apaulin.http;

import java.util.ArrayList;

/**
 * String array returning a format compatible with HTTPrequest
 * @author Anthony Paulin
 * @since  20/07/2018
 * @version 0.1
 *
 */
public class StringArray extends ArrayList<String> {
	private static final long serialVersionUID = 4079847527595200210L;

    public String toString() {
    	String arrayBuilt = new String("[");
    	for(int i=0;i<this.size();i++) {
    		if(i < this.size()-1) {
    			arrayBuilt = arrayBuilt + "\"" + this.get(i)  + "\"" + ",";
    		}
    		else {
    			arrayBuilt = arrayBuilt + "\"" +this.get(i)+ "\"" ;
    		}
    	}
    	arrayBuilt = arrayBuilt+"]";
		return arrayBuilt;
    }
}
