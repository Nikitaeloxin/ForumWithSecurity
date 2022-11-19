package telran.java2022.forum.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Post {
	@Id
	String id;
	@Setter
	String title;
	String content;
	@Setter
	String author;
	String dateCreated = LocalDateTime.now().toString();
	List<String> tags;
	Integer likes = 0;
	List<Comment> comments = new ArrayList<>();
	
	public Post(String id, String title, String content, String author, List<String> tags,
			Integer likes) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author; 
		this.tags = tags;
		this.likes = likes;
	}
	
	public Post(String title,String content,String author,List<String> tags) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.tags = tags;
	}
	
	public Boolean addComent(String user,String message) {
		return comments.add(new Comment(user, message));
	}
	
	public void addTags(List<String> newTags) {
		newTags.forEach(s->tags.add(s));
	}
	
	public void addLike() {
		likes++;
	}
	
	
}
