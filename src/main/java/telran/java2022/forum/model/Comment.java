package telran.java2022.forum.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Comment {
	String user;
	String message;
	String dateCreated = LocalDateTime.now().toString();
	Integer likes = 0;
	
	public Comment(String user, String message) {
		super();
		this.user = user;
		this.message = message;
		this.likes = likes;
	}
	
	
}
