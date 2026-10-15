package com.omrbranch.pojo.searchproduct;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Searchproduct_Output_Pojo {
	public int status;
    public String message;
    public ArrayList<Datum> data;
    public String currency;

}
