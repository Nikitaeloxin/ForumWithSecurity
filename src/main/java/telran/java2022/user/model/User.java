package telran.java2022.user.model;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "login")
public class User {
	@Id
	String login;
	@Setter
	String password;
	@Setter
	String firstName;
	@Setter
	String lastName;
	List<String> roles= new ArrayList<>();
	
	public User(String login, String password, String firstName, String lastName) {
		super();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public boolean addRole(String role) {
		return roles.add(role);
	}
	
	public boolean deleteRole(String role) {
		return roles.remove(role);
	}
	
	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}

}
