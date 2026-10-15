package com.omrbranch.pojo.cityList;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CityList_Output_Pojo {
	
	public int status;
    public String message;
    public ArrayList<Datum> data;

}
