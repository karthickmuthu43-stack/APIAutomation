package com.omrbranch.pojo.getSearchResult;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSearchResult_Output_Pojo {

	
	 public int status;
	    public String message;
	    public String currency;
	    public ArrayList<Datum> data;
	    public int cart_count;
}
