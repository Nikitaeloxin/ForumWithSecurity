package telran.java2022.user.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class RoleDto {
	String login;
	List<String> roles;
}
