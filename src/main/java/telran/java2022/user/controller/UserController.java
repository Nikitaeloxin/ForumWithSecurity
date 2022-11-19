package telran.java2022.user.controller;

import java.security.Principal;
import java.util.Base64;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.user.dto.LoginDto;
import telran.java2022.user.dto.RoleDto;
import telran.java2022.user.dto.UserDto;
import telran.java2022.user.dto.UserRegisterDto;
import telran.java2022.user.dto.UserUpdate;
import telran.java2022.user.service.UserService;

@RequestMapping("/account")
@RestController
@RequiredArgsConstructor
public class UserController {
	
	final UserService userService;
	
	@PostMapping("/register")
	UserDto userRegister(@RequestBody UserRegisterDto userRegisterDto) {
		return userService.userRegister(userRegisterDto);
	}
	
	@PostMapping("/login")
	UserDto userLogin(Principal principal) {
		return userService.getUser(principal.getName());
	}	
	
	@DeleteMapping("/user/{login}")
//	@PreAuthorize("#login.equals(principal.getUsername()) or hasRole(\"ADMINISTRATOR\")")
	UserDto userDelete(@PathVariable String login) {
		return userService.userDelete(login);
	}
	
	@PutMapping("/user/{login}")
//	@PreAuthorize("#login == authentication.name")
	UserDto userUpdate(@PathVariable String login,@RequestBody UserUpdate userUpdate) {
		return userService.userUpdate(login, userUpdate);
	}
	
	@PutMapping("/user/{login}/role/{role}")
	RoleDto addRole(@PathVariable String login,@PathVariable String role) {
		return userService.addRole(login, role);
	}
	
	@DeleteMapping("user/{login}/role/{role}")
	RoleDto deleteRole(@PathVariable String login,@PathVariable String role) {
		return userService.deleteRole(login, role);
	}
	
	@PutMapping("/password")
	void changePassword(Principal principal, @RequestHeader("X-Password") String newPassword) {
		userService.changePassword(principal.getName(),newPassword);
	}

}
