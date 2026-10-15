package com.omrbranch.pojo.createorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Createorder_Input_Pojo {

	public String payment_method;
    public String card_no;
    public String card_type;
    public String year;
    public String month;
    public String cvv;
}
