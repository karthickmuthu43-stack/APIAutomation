package com.omrbranch.globaldata;

import lombok.Data;

@Data
public class GlobalData {
	private static GlobalData globalDataObj;
	private int statusCode;
	private String logtoken;
	private int stateID;
	
	private int addID;
	
	private int prodID;
	private int catID;
	private int variationId;
	private int cartID;
	

	

	private GlobalData() {

	}

	public static GlobalData getGlobalDataInstance() {
		if (globalDataObj == null) {
			globalDataObj = new GlobalData();
		}
		return globalDataObj;

	}

}
