package com.mycom.myapp.phone.dto;

import java.util.List;

import lombok.Data;

@Data
public class PhoneResultDto {
	private String result;
	private PhoneDto phoneDto;
	private List<PhoneDto> phoneList;
}
