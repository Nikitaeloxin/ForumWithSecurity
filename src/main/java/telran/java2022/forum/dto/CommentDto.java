package telran.java2022.forum.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class CommentDto {
	String user;
	String message;
	String dateCreated;
	Integer likes;
}
