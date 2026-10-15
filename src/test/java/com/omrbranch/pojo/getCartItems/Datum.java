package com.omrbranch.pojo.getCartItems;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Datum {
	
	public int id;
    public int cart_id;
    public int product_id;
    public int product_variation_id;
    public int qty;
    public String price;
    public String special_price;
    public Object offer_id;
    public Object coupon_code_id;
    public Object coupon_discount;
    public int scheduled;
    public String start_date;
    public String end_date;
    public int delivery_slot_id;
    public String status;
    public String created_at;
    public String updated_at;
    public int is_available;
    public String product_name;
    public String product_image;
    public String product_medium_image;
    public int discount;
    public String delivery_slots;
    public String delivery_slot_type;
    public int is_favorite;
    public int max_qty;
	
	
	  
	
	

}
