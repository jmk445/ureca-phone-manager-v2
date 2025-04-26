package com.mycom.myapp.phone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.phone.dto.PhoneResultDto;
import com.mycom.myapp.phone.entity.Phone;
import com.mycom.myapp.phone.service.PhoneService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/phones")
 
public class PhoneController {
	private final PhoneService phoneService;
			
	@GetMapping("/list")
	public PhoneResultDto listPhone() {
		PhoneResultDto phoneResultDto = phoneService.listPhone();		
		System.out.println(phoneResultDto.getPhoneList().get(0).getRemain());
		if(phoneResultDto.getPhoneList() != null) {
			phoneResultDto.setResult("success");			
		}else {
			phoneResultDto.setResult("fail");
		}
		
		return phoneResultDto;		
	}
	
	@GetMapping("/detail/{phoneId}")
	public PhoneResultDto detailPhone(@PathVariable("phoneId") Integer phoneId) {
		PhoneResultDto phoneResultDto = phoneService.detailPhone(phoneId);		
		System.out.println(phoneResultDto.getPhoneDto().getRemain());
		if(phoneResultDto.getPhoneDto() != null) {
			phoneResultDto.setResult("success");			
		}else {
			phoneResultDto.setResult("fail");
		}
		
		return phoneResultDto;			
	}
	
	@PostMapping("/insert")	
	public PhoneResultDto insertPhone(Phone phone) {

		PhoneResultDto phoneResultDto = phoneService.insertPhone(phone);
		       
        return phoneResultDto;	
	}

	
	@PostMapping(value="/update")	
	public PhoneResultDto updatePhone(Phone phone) {
		PhoneResultDto phoneResultDto = phoneService.updatePhone(phone);

		 if (phoneResultDto.getPhoneDto() != null) {
	        	phoneResultDto.setResult("success");
	        } else {
	        	phoneResultDto.setResult("fail");
	        }
        
		 return phoneResultDto;		
	}

	@GetMapping(value="/delete/{phoneId}")	
	public PhoneResultDto deletePhone(@PathVariable("phoneId") int phoneId) {
		PhoneResultDto phoneResultDto = new PhoneResultDto();
		try {
			phoneService.deletePhone(phoneId);
		}catch(RuntimeException e) {
			phoneResultDto.setResult("fail");
		}
        phoneResultDto.setResult("true");
        
        return phoneResultDto;	
	}
	
}
