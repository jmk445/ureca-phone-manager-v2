package com.mycom.myapp.phone.service;

import com.mycom.myapp.phone.dto.PhoneDto;
import com.mycom.myapp.phone.dto.PhoneResultDto;

public interface PhoneService {
		
	public PhoneResultDto insertPhone(PhoneDto phoneDto);
    public PhoneResultDto detailPhone(int id);
    public PhoneResultDto listPhone();
    public PhoneResultDto updatePhone(PhoneDto phoneDto);
    public void deletePhone(int phoneId);
    
	
}
