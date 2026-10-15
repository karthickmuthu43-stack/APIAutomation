package com.omrbranch.pojo.postmanbasicauthlogin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
	public int id;
	public String role;
	public String name;
	public String status;
	public String created_at;
	public String updated_at;
	public Pivot pivot;

}
