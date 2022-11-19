package telran.java2022.security.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import telran.java2022.forum.dao.ForumRepository;
import telran.java2022.forum.model.Post;
import telran.java2022.user.dao.UsersRepository;
import telran.java2022.user.model.User;

@Service
@AllArgsConstructor
public class CustomWebSecurity {
	
	final ForumRepository forumRepository;
	final UsersRepository usersRepository;
	
	public boolean checkPostAuthor(String postID,String userName) {
		Post post = forumRepository.findById(postID).orElse(null);
		return post != null && userName.equalsIgnoreCase(post.getAuthor());
	}
	
	public boolean checkPasswordRelevance(String userName) {
		int daysInWhichPassIsRelevance = 60;
		User user = usersRepository.findById(userName).orElse(null);
		if (user == null) {
			return false;
		}
		LocalDate localDate1 = LocalDate.parse(user.getChangePasswordDate().substring(0, 10));
		LocalDate localDate2 = LocalDate.now().minus(daysInWhichPassIsRelevance, ChronoUnit.DAYS);
		
		return localDate2.isBefore(localDate1);
	}

}
