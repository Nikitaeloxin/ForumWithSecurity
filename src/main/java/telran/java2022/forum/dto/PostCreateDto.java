package telran.java2022.forum.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class PostCreateDto {
	String title;
	String content;
	List<String> tags;
}
