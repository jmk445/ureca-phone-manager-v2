package com.mycom.myapp.phone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mycom.myapp.phone.dto.PhoneDto;
import com.mycom.myapp.phone.dto.PhoneResultDto;
import com.mycom.myapp.phone.entity.Phone;
import com.mycom.myapp.phone.repository.PhoneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService{
	private final PhoneRepository phoneRepository;
	
	@Override
	public PhoneResultDto insertPhone(Phone phone) {
		PhoneResultDto phoneResultDto = new PhoneResultDto();
		Optional<Phone> phoneExisting = phoneRepository.findByPhoneName(phone.getPhoneName());
		if(!phoneExisting.isPresent()) {
			Phone phoneFromRepo = phoneRepository.save(phone);
			PhoneDto phoneDto = PhoneDto.builder()
					.id(phoneFromRepo.getPhoneId())
					.name(phoneFromRepo.getPhoneName())
					.maker(phoneFromRepo.getPhoneMaker())
					.remain(phoneFromRepo.getPhoneRemain())
					.price(phoneFromRepo.getPhonePrice())
					.build();
			
			phoneResultDto.setPhoneDto(phoneDto);
			phoneResultDto.setResult("success");
		}else {
			phoneResultDto.setResult("fail:already exisiting");
		}
		
		return phoneResultDto;		
    }

    public PhoneResultDto detailPhone(int id) {
    	PhoneResultDto phoneResultDto = new PhoneResultDto();
    	Optional<Phone> phoneFromRepo = phoneRepository.findById(id);
    	
    	if(phoneFromRepo.isPresent()) {
    		Phone phone = phoneFromRepo.get();
    		PhoneDto phoneDto = PhoneDto.builder()
    				.id(phone.getPhoneId())
    				.name(phone.getPhoneName())
    				.maker(phone.getPhoneMaker())
    				.price(phone.getPhonePrice())
    				.remain(phone.getPhoneRemain())
    				.build();
    		    		
    		phoneResultDto.setPhoneDto(phoneDto);    		
    	}
		
		return phoneResultDto;		    	
    }

    public PhoneResultDto listPhone() {
    	//todo : pagination
    	PhoneResultDto phoneResultDto = new PhoneResultDto();
    	List<Phone> phoneFromRepo = phoneRepository.findAll();
    	List<PhoneDto> phoneDtoList = new ArrayList<>();
    	
    	for(int i = 0; i < phoneFromRepo.size(); i++) {
    		Phone phone = phoneFromRepo.get(i);
    		PhoneDto phoneDto = PhoneDto.builder()
    				.id(phone.getPhoneId())
    				.name(phone.getPhoneName())
    				.maker(phone.getPhoneMaker())
    				.price(phone.getPhonePrice())
    				.remain(phone.getPhoneRemain())
    				.build();    		
    		phoneDtoList.add(phoneDto);
    	}
		
		
		phoneResultDto.setPhoneList(phoneDtoList);
		
		return phoneResultDto;		

    }


    public PhoneResultDto updatePhone(Phone phone) {
    	Optional<Phone> existingPhone = phoneRepository.findById(phone.getPhoneId());    	
    	PhoneResultDto phoneResultDto = new PhoneResultDto();
		if(existingPhone.isPresent()) {
			Optional<Phone> resultPhone = Optional.of(phoneRepository.save(phone));
			
			PhoneDto phoneDto = PhoneDto.builder()
    				.id(resultPhone.get().getPhoneId())
    				.name(resultPhone.get().getPhoneName())
    				.maker(resultPhone.get().getPhoneMaker())
    				.price(resultPhone.get().getPhonePrice())
    				.remain(phone.getPhoneRemain())
    				.build();
			phoneResultDto.setPhoneDto(phoneDto);
		}
		
		return phoneResultDto;
    }
	
    public void deletePhone(int Id) {
    	phoneRepository.deleteById(Id);
    }
    
	
}
