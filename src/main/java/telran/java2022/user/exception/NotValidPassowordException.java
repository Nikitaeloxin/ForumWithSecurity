package telran.java2022.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class NotValidPassowordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5632821075627478100L;
	
	public NotValidPassowordException() {
		super("Invalid password");
	}

}
