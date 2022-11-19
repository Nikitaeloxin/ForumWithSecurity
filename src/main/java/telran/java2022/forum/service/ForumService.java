package telran.java2022.forum.service;

import java.util.List;

import telran.java2022.forum.dto.AddCommentDto;
import telran.java2022.forum.dto.FindPostsByPeriodDto;
import telran.java2022.forum.dto.PostCreateDto;
import telran.java2022.forum.dto.PostDto;
import telran.java2022.forum.dto.UpdatePostDto;

public interface ForumService {
	
	PostDto addPost(String author,PostCreateDto postCreateDto);
	
	PostDto findPostById(String id);
	
	Integer addLike(String id);
	
	List<PostDto> findPostsByAuthor(String author);
	
	PostDto addComment(String id, String author,AddCommentDto addCommentDto);
	
	PostDto deletePost(String id);
	
	List<PostDto> findPostByTags(List<String> tags);
	
	List<PostDto> findPostByPeriod(FindPostsByPeriodDto findPostsByPeriodDto);
	
	PostDto updatePost(String id, UpdatePostDto updatePostDto);

}
