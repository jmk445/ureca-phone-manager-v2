package com.mycom.myapp.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
	
	private final UserService userService;	
	
	@PostMapping("/users")	
	@Operation(summary = "회원가입", description = "신규 사용자를 등록합니다.")
	public UserResultDto register(@RequestBody User user){
		return this.userService.register(user);		
	}
	
	
	@GetMapping("/users")
    @Operation(summary = "사용자 목록 조회", description = "전체 사용자 목록을 조회합니다.")
    public ResponseEntity<UserResultDto> listUser() {
        UserResultDto result = userService.listUsers();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "사용자 상세 조회", description = "사용자 ID를 통해 상세 정보를 조회합니다.")
    public ResponseEntity<UserResultDto> detailUser(@PathVariable("id") Integer id) {
        UserResultDto result = userService.detailUser(id);
        switch (result.getResult()) {
            case "success": return ResponseEntity.ok(result);
            case "notfound": return ResponseEntity.notFound().build();
            default: return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/users/needs-to-update")    
    @Operation(summary = "사용자 중 등업이 필요한 사람", description = "사용자 중 등업이 필요한 사람 목록을 조회합니다.")
    public ResponseEntity<UserResultDto> listUserUpgrade() {
        UserResultDto result = userService.listUsersUpgrade();
        return ResponseEntity.ok(result);
    }
    
    
    @PutMapping("/users/{id}")
    @Operation(summary = "사용자 수정", description = "기존 사용자 정보를 수정합니다.")
    public ResponseEntity<UserResultDto> updateUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto) {
        userDto.setUserId(id);
        UserResultDto result = userService.updateUser(userDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "사용자 삭제", description = "사용자를 삭제합니다.")
    public ResponseEntity<UserResultDto> deleteUser(@PathVariable("id") Integer id) {
        UserResultDto result = new UserResultDto();
        try {
            userService.deleteUser(id);
            result.setResult("success");
        } catch (Exception e) {
            result.setResult("fail");
        }
        return ResponseEntity.ok(result);
    }
}
