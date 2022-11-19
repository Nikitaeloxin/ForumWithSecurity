package telran.java2022.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UserRegisterDto {
	String login;
	String password;
	@Setter
	String changePasswordDate;
	String firstName;
	String lastName;
	

}
