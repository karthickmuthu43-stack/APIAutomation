package com.omrbranch.pojo.postmanbasicauthlogin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class PostmanBasicAuthLogin_Output_Pojo {
	public int status;
	public String message;
	public Data data;
	public String refer_msg;
	public int cart_count;
	public String role;

}
