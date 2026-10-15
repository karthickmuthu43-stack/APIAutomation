package com.omrbranch.pojo.getUserAddress;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetUserAddress_Output_Pojo {
	
	public int status;
    public String message;
    public ArrayList<Datum> data;

}
