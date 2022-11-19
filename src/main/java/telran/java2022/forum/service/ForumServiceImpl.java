package telran.java2022.forum.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.dao.ForumRepository;
import telran.java2022.forum.dto.AddCommentDto;
import telran.java2022.forum.dto.FindPostsByPeriodDto;
import telran.java2022.forum.dto.PostCreateDto;
import telran.java2022.forum.dto.PostDto;
import telran.java2022.forum.dto.UpdatePostDto;
import telran.java2022.forum.dto.exceptions.PostNotFoundException;
import telran.java2022.forum.model.Post;

@Service	
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {
	
	final ForumRepository forumRepository;
	final ModelMapper modelMapper;

	@Override
	public PostDto addPost(String author, PostCreateDto postCreateDto) {
		Post post = modelMapper.map(postCreateDto, Post.class);
		post.setAuthor(author);
		
		return modelMapper.map(forumRepository.save(post), PostDto.class);
		
	}

	@Override
	public PostDto findPostById(String id) {
		Post post = forumRepository.findById(id).orElseThrow(()-> new PostNotFoundException(id));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public Integer addLike(String id) {
		Post post = forumRepository.findById(id).orElseThrow(()-> new PostNotFoundException(id));
		post.addLike();
		forumRepository.save(post);
		return 1;
	}

	@Override
	public List<PostDto> findPostsByAuthor(String author) {
		
		return forumRepository.findByAuthorIgnoreCase(author)
					.map(s->modelMapper.map(s, PostDto.class))
					.collect(Collectors.toList());
	}

	@Override
	public PostDto addComment(String id, String author, AddCommentDto addCommentDto) {
		Post post = forumRepository.findById(id).orElseThrow(()-> new PostNotFoundException(id));
		post.addComent(author, addCommentDto.getMessage());
		PostDto postDto = modelMapper.map(forumRepository.save(post),PostDto.class);
		return postDto;
	}

	@Override
	public PostDto deletePost(String id) {
		Post post = forumRepository.findById(id).orElseThrow(()-> new PostNotFoundException(id));
		forumRepository.deleteById(id);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> findPostByTags(List<String> tags) {
		return forumRepository.findPostsByTags(tags)
				.map(s->modelMapper.map(s, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostByPeriod(FindPostsByPeriodDto findPostsByPeriodDto) {
		
		return forumRepository.findPostsByPeriod(findPostsByPeriodDto.getDateFrom(), findPostsByPeriodDto.getDateTo())
				.map(s->modelMapper.map(s, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PostDto updatePost(String id, UpdatePostDto updatePostDto) {
		Post post = forumRepository.findById(id).orElseThrow(()-> new PostNotFoundException(id));
		
		post.setTitle(updatePostDto.getTitle());
		post.addTags(updatePostDto.getTags());
		PostDto postDto = modelMapper.map(forumRepository.save(post),PostDto.class);
		return postDto;
	}

}
