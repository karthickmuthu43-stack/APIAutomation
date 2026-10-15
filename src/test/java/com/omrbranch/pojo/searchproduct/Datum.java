package com.omrbranch.pojo.searchproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Datum {

	public String image;
    public String type;
    public String text;
    public int id;
    public int category_id;
}
