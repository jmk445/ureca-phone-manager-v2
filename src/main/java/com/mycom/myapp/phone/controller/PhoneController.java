package com.mycom.myapp.phone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.phone.dto.PhoneDto;
import com.mycom.myapp.phone.dto.PhoneResultDto;
import com.mycom.myapp.phone.service.PhoneService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name="기본 Student CRUD API", description="Student의 등록, 수정, 삭제, 목록 조회, 상세 조회 기능을 REST API로 제공합니다.") //보통 작성자, 언제 했었다 (ownership을 가진 사람, last update 날짜)를 추가해서 적는다.
public class PhoneController {
	private final PhoneService phoneService;
			
	@GetMapping("/phones")
	@Operation(summary="휴대폰 목록 조회",description="전체 휴대폰 목록을 조회합니다.")
	public ResponseEntity<PhoneResultDto> listPhone() {
		PhoneResultDto phoneResultDto = phoneService.listPhone();		
		return ResponseEntity
				.ok()
				.body(phoneResultDto);

//		if(phoneResultDto.getPhoneList() != null) {
//			phoneResultDto.setResult("success");			
//		}else {
//			phoneResultDto.setResult("fail");
//		}
//		return phoneResultDto;		
	}
	
	@GetMapping("/phones/{id}")
	@Operation(summary="휴대폰 상세 조회",description="개별 휴대폰을 조회합니다.")
	public ResponseEntity<PhoneResultDto> detailPhone(@PathVariable("id") Integer id) {
		PhoneResultDto phoneResultDto = phoneService.detailPhone(id);	

//		if(phoneResultDto.getPhoneDto() != null) {
//			phoneResultDto.setResult("success");			
//		}else {
//			phoneResultDto.setResult("fail");
//		}
		
		//2-2번사용, 즉, resultDto를 Client에게 전달하지만 사용하지 않고, 대신 controller에서 service의 작업 결과를 ResultDto를 통해서 처리
		switch(phoneResultDto.getResult()) {
			case "success" : return ResponseEntity.ok().body(phoneResultDto);
			case "notfound" : return ResponseEntity.notFound().build(); 
			case "fail" : return ResponseEntity.internalServerError().build();
			default : return ResponseEntity.internalServerError().build();
		}
		
//		return phoneResultDto;			
	}
	
//	@GetMapping("/detail/namelike")
//	public PhoneResultDto detailPhoneByName(@RequestParam("name") String name){
//	}
	@PostMapping("/phones")	
	@Operation(summary="휴대폰 등록",description="신규 휴대폰을 등록합니다.")
	public PhoneResultDto insertPhone(PhoneDto phoneDto) {

		PhoneResultDto phoneResultDto = phoneService.insertPhone(phoneDto);
		       
        return phoneResultDto;	
	}

	
	@PutMapping(value="/phones/{id}")	
	@Operation(summary="휴대폰 수정",description="기존 휴대폰을 수정합니다.")
	public PhoneResultDto updatePhone(@PathVariable("id") Integer id, PhoneDto phoneDto) {
		phoneDto.setId(id);
		PhoneResultDto phoneResultDto = phoneService.updatePhone(phoneDto);

		 if (phoneResultDto.getPhoneDto() != null) {
	        	phoneResultDto.setResult("success");
	        } else {
	        	phoneResultDto.setResult("fail");
	        }
        
		 return phoneResultDto;		
	}

	@DeleteMapping(value="/phones/{id}")	
	@Operation(summary="휴대폰 삭제",description="기존 휴대폰을 삭제합니다.")
	public PhoneResultDto deletePhone(@PathVariable("id") Integer id) {
		PhoneResultDto phoneResultDto = new PhoneResultDto();
		try {
			phoneService.deletePhone(id);
		}catch(RuntimeException e) {
			phoneResultDto.setResult("fail");
		}
        phoneResultDto.setResult("true");
        
        return phoneResultDto;	
	}
	
}
