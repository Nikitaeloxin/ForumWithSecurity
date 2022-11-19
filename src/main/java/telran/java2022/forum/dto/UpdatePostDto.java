package telran.java2022.forum.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class UpdatePostDto {
	String title;
	List<String> tags;
}
