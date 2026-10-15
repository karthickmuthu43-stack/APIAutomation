package com.omrbranch.pojo.postmanbasicauthlogin;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {
	public int id;
	public String user_code;
	public String first_name;
	public String last_name;
	public String email;
	public String mobile_number;
	public String password;
	public String api_key;
	public int days;
	public String month;
	public String year;
	public String gender;
	public String hobbies;
	public String forgot_key;
	public String profile_picture;
	public String login_cookie;
	public int google_signin;
	public String google_auth_id;
	public int facebook_signin;
	public String facebook_id;
	public String referral_code;
	public int wallet_amount;
	public Object device_id;
	public Object device_token;
	public int is_verified;
	public int admin_verify;
	public int admin_verified;
	public String flight_access;
	public Object device_type;
	public String status;
	public String travel_access;
	public String grocery_front_access;
	public String travel_with_api_access;
	public String sample_api_access;
	public String database_access;
	public String admin_panel_access;
	public String mobile_app_access;
	public String faq_set1_access;
	public String faq_set2_access;
	public String faq_set3_access;
	public String faq_set4_access;
	public String created_at;
	public String updated_at;
	public String refer_screen;
	public String logtoken;
	public ArrayList<UserRole> user_role;

}
