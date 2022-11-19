package telran.java2022.forum.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

@Getter
public class PostDto {
	String id;
	String title;
	String content;
	String author;
	String dateCreated;
	List<String> tags;
	Integer likes;
	List<CommentDto> comments;
	
}
