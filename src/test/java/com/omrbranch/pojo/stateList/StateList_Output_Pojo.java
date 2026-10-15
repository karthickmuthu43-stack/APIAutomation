package com.omrbranch.pojo.stateList;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StateList_Output_Pojo {

	public int status;
    public String message;
    public ArrayList<Datum> data;
}
