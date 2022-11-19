package telran.java2022.forum.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.dto.AddCommentDto;
import telran.java2022.forum.dto.FindPostsByPeriodDto;
import telran.java2022.forum.dto.PostCreateDto;
import telran.java2022.forum.dto.PostDto;
import telran.java2022.forum.dto.UpdatePostDto;
import telran.java2022.forum.service.ForumService;

@RestController
@RequiredArgsConstructor
public class ForumController {
	final ForumService forumService;
	
	@PostMapping("/forum/post/{author}")
	PostDto addPost(@PathVariable String author,@RequestBody PostCreateDto PostCreateDto) {
		return forumService.addPost(author, PostCreateDto);
	}
	
	@GetMapping("/forum/post/{id}")
	PostDto findPostById(@PathVariable String id) {
		return forumService.findPostById(id);
	}
	
	@PutMapping("/forum/post/{id}/like")
	Integer addLike(@PathVariable String id) {
		return forumService.addLike(id);
	}
	
	@GetMapping("/forum/posts/author/{author}")
	List<PostDto> findPostsByAuthor(@PathVariable String author){
		return forumService.findPostsByAuthor(author);
	}
	
	@PutMapping("/forum/post/{id}/comment/{author}")
	PostDto addComment(@PathVariable String id,@PathVariable String author,@RequestBody AddCommentDto addCommentDto) {
		return forumService.addComment(id, author, addCommentDto);
	}
	
	@DeleteMapping("/forum/post/{id}")
	PostDto deletePost(@PathVariable String id) {
		return forumService.deletePost(id);
	}
	
	@PostMapping("/forum/posts/tags")
	List<PostDto> findPostByTags(@RequestBody List<String> tags){
		return forumService.findPostByTags(tags);
	}
	
	@PostMapping("/forum/posts/period")
	List<PostDto> findPostByPeriod(@RequestBody FindPostsByPeriodDto findPostsByPeriodDto){
		return forumService.findPostByPeriod(findPostsByPeriodDto);
	}
	
	
	@PutMapping("/forum/post/{id}")
	PostDto updatePost(@PathVariable String id,@RequestBody UpdatePostDto updatePostDto) {
		return forumService.updatePost(id, updatePostDto);
	}
}
