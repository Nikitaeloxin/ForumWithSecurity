package telran.java2022.user.service;

import telran.java2022.user.dto.LoginDto;
import telran.java2022.user.dto.RoleDto;
import telran.java2022.user.dto.UserDto;
import telran.java2022.user.dto.UserRegisterDto;
import telran.java2022.user.dto.UserUpdate;

public interface UserService {
	
	UserDto userRegister(UserRegisterDto userRegisterDto);
	
	UserDto userLogin(LoginDto loginDto);
	
	UserDto userDelete(String login);
	
	UserDto userUpdate(String login, UserUpdate userUpdate);
	
	RoleDto addRole(String login, String role);
	
	RoleDto deleteRole(String login, String role);
	
	UserDto getUser(String login);
	
	void changePassword(String login,String password);	
}
