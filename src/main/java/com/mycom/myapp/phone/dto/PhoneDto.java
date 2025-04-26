package com.mycom.myapp.phone.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneDto {
	int id;
	String name;
	int price;
	String maker;
	int remain;
		

}
