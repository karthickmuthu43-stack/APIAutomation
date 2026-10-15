package com.omrbranch.pojo.createorder;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor

public class Astate {
	
	public int id;
    public String name;
    public int country_id;
    public String status;
    public Country country;

}
