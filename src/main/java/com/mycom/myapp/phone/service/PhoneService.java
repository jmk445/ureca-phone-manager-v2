package com.mycom.myapp.phone.service;

import java.util.Optional;

import com.mycom.myapp.phone.dto.PhoneResultDto;
import com.mycom.myapp.phone.entity.Phone;

public interface PhoneService {
		
	public PhoneResultDto insertPhone(Phone phone);
    public PhoneResultDto detailPhone(int id);
    public PhoneResultDto listPhone();
    public PhoneResultDto updatePhone(Phone phone);
    public void deletePhone(int phoneId);
    
	
}
