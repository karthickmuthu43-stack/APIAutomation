package com.omrbranch.pojo.getCartItems;




import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Address {
	
	public int id;
    public String address_code;
    public int user_id;
    public String first_name;
    public String last_name;
    public String mobile;
    public String apartment;
    public String address;
    public int country_id;
    public int state_id;
    public int city_id;
    public String zipcode;
    public Object lat;
    public Object lng;
    @JsonProperty("default") 
    public int mydefault;
    public String status;
    public String address_type;
    public String created_at;
    public String updated_at;
    public String city;
    public String state;
    public String country;
	


}
