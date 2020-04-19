/**
 * 
 */
package com.apaulin.nso_controller.json;

import com.jayway.jsonpath.JsonPathException;

/**
 * Display the JSON file
 * @author Anthony Paulin
 * @since 22/07/2018
 * @version 0.2
 * 
 */
public class JSONDisplay {
	private String jsonString = new String();//String representation of the JSON file
	private int spaceNumber = 0;// Number of spaces to pass.
	private int spaceInc = 4;//Number of spaces to add.
	private int lineNumber = 0;//line numbers
	
	/**
	 * Initiate JSONDispay
	 * @param jsonString json string 
	 * @throws JsonPathException JSOn related exception
	 */
	public JSONDisplay(String jsonString) throws JsonPathException {
		String tmp = spaceCalculator(splitComma(splitBracketRight(splitBracketleft(splitEscaped(jsonString)))));
		if(tmp.endsWith("{\n")) {
			tmp = tmp.substring(0,tmp.length()-2);
			tmp += "}";
		}
		this.jsonString = tmp;
	}
	
	/**
	 * @return the jsonString
	 */
	public String getJsonString() {
		return jsonString;
	}
    
	/**
	 * Incement Spaces
	 */
	private void incrementSpaces() {
		this.spaceNumber += this.spaceInc;
	}
	
	/**
	 * Decrement spaces
	 * @throws JsonPathException json related exception
	 */
	private void decrementSpaces() throws JsonPathException {
		this.spaceNumber -= this.spaceInc;
		if(this.spaceNumber < 0) {
			throw new JsonPathException(jsonString);
		}
	}
	
	/**
	 * Split the right bracket
	 * @param jsonString the json data
	 * @return the modified json
	 */
	private String splitBracketRight(String jsonString) {
		String jsonModified = new String();
		String[] strToArray = jsonString.trim().split("\\{");
		for(int i=0;i<strToArray.length;i++) {
			jsonModified = jsonModified + strToArray[i].trim() + "{\n";
		}
		return jsonModified;
	}
	
	/**
	 * Split the left bracket
	 * @param jsonString the json data
	 * @return the modified json
	 */
	private String splitBracketleft(String jsonString) {
		String jsonModified = new String();
		String[] strToArray = jsonString.trim().split("\\}");
		for(int i=0;i<strToArray.length;i++) {
			jsonModified = jsonModified + strToArray[i].trim() + "\n}\n";
		}
		return jsonModified;
	}
	
	/**
	 * Split spaced character
	 * @param jsonString the json data
	 * @return the modified json
	 */
	private String splitEscaped(String jsonString) {
		String jsonModified = jsonString.trim().replaceAll("\\\\n", "\n");
		return jsonModified;
	}
	
	/**
	 * Split comma
	 * @param jsonString the json data
	 * @return the modified json
	 */
	private String splitComma(String jsonString) {
		String jsonModified = jsonString.trim().replaceAll(",", ",\n");
		return jsonModified;
	}
	
	/**
	 * Calculate the number of spaces
	 * @param jsonString the json data
	 * @return the modified json
	 * @throws JsonPathException
	 */
	private String spaceCalculator(String jsonString) throws JsonPathException {
		String jsonModified = new String();
		String[] strToArray = jsonString.trim().split(System.lineSeparator());
		for(int i=0;i<strToArray.length;i++) {
			lineNumber++;
			if(strToArray[i].contains("{")) {
				incrementSpaces();
			}
			else if(strToArray[i].contains("}")) {
				decrementSpaces();
			}
			String spaces = new String();
			for(int i2=0;i2<spaceNumber;i2++) {
				spaces = spaces + " ";
			}
			jsonModified = jsonModified + spaces + strToArray[i].trim() + "\n";
		}
		return jsonModified;
	}
	
	/**
	 * Return the JSON file modified
	 * @return the modified json
	 */
	@Override
	public String toString() {
		return this.jsonString;
	}
}
