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
	public PhoneResultDto insertPhone(PhoneDto phoneDto) {
		PhoneResultDto phoneResultDto = new PhoneResultDto();
		Phone phone = Phone.builder()
				.phoneName(phoneDto.getName())
				.phonePrice(phoneDto.getPrice())
				.phoneMaker(phoneDto.getMaker())
				.phoneRemain(phoneDto.getRemain())
				.build();
		
		Optional<Phone> phoneExisting = phoneRepository.findByPhoneName(phone.getPhoneName());
		
		if(!phoneExisting.isPresent()) {			
//			Phone phoneFromRepo = phoneRepository.save(phone);
//			PhoneDto phoneDto = PhoneDto.builder()
//					.id(phoneFromRepo.getPhoneId())
//					.name(phoneFromRepo.getPhoneName())
//					.maker(phoneFromRepo.getPhoneMaker())
//					.remain(phoneFromRepo.getPhoneRemain())
//					.price(phoneFromRepo.getPhonePrice())
//					.build();
//			
//			phoneResultDto.setPhoneDto(phoneDto);
			
			phoneRepository.save(phone);
			phoneResultDto.setResult("success");
		}else {			
			phoneResultDto.setResult("fail");
		}
		
		return phoneResultDto;		
    }

    public PhoneResultDto detailPhone(int id) {
    	PhoneResultDto phoneResultDto = new PhoneResultDto();
    	Optional<Phone> phoneFromRepo = phoneRepository.findById(id);
    	
    	try {
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
        		phoneResultDto.setResult("success");
        	}else {
        		phoneResultDto.setResult("fail");
        	}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
			phoneResultDto.setResult("fail");
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


    public PhoneResultDto updatePhone(PhoneDto phoneDto) {
    	Optional<Phone> existingPhone = phoneRepository.findById(phoneDto.getId());    	
    	PhoneResultDto phoneResultDto = new PhoneResultDto();
    	
    	Phone phone = Phone.builder()
    			.phoneId(phoneDto.getId())
				.phoneName(phoneDto.getName())
				.phonePrice(phoneDto.getPrice())
				.phoneMaker(phoneDto.getMaker())
				.phoneRemain(phoneDto.getRemain())
				.build();
    	
		if(existingPhone.isPresent()) {
			try {
				Optional<Phone> resultPhone = Optional.of(phoneRepository.save(phone));
				phoneResultDto.setResult("success");
			}catch(Exception e) {
				e.printStackTrace();
				phoneResultDto.setResult("fail");
			}
			
			
			
//			PhoneDto phoneDto = PhoneDto.builder()
//    				.id(resultPhone.get().getPhoneId())
//    				.name(resultPhone.get().getPhoneName())
//    				.maker(resultPhone.get().getPhoneMaker())
//    				.price(resultPhone.get().getPhonePrice())
//    				.remain(phone.getPhoneRemain())
//    				.build();
//			phoneResultDto.setPhoneDto(phoneDto);			
		}
		
		return phoneResultDto;
    }
	
    public void deletePhone(int Id) {
    	phoneRepository.deleteById(Id);
    }
    
	
}
