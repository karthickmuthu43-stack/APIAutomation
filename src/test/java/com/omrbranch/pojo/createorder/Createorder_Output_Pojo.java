package com.omrbranch.pojo.createorder;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor


public class Createorder_Output_Pojo {
	
	 public int status;
	    public String message;
	    public String currency;
	    public Data data;
	    public int order_id;

}
