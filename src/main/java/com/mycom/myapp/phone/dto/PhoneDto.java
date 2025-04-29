package com.mycom.myapp.phone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {
	int id;
	String name;
	int price;
	String maker;
	int remain;
		

}
