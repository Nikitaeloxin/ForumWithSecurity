package telran.java2022.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4366804073103624072L;
	
	public UserNotFoundException(String login) {
		super("User with login = " + login + " not found");
	}

}
