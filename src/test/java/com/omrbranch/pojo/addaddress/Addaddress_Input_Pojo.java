package com.omrbranch.pojo.addaddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Addaddress_Input_Pojo {
	
	public String first_name;
    public String last_name;
    public String mobile;
    public String apartment;
    public String state;
    public String city;
    public String country;
    public String zipcode;
    public String address;
    public String address_type;

}
