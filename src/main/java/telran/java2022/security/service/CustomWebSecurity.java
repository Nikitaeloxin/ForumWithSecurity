package telran.java2022.security.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import telran.java2022.forum.dao.ForumRepository;
import telran.java2022.forum.model.Post;
import telran.java2022.user.dao.UsersRepository;

@Service
@AllArgsConstructor
public class CustomWebSecurity {
	
	final ForumRepository forumRepository;
	
	public boolean checkPostAuthor(String postID,String userName) {
		Post post = forumRepository.findById(postID).orElse(null);
		return post != null && userName.equalsIgnoreCase(post.getAuthor());
	}
	

}
