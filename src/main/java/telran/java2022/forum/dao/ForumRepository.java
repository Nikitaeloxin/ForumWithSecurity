package telran.java2022.forum.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import telran.java2022.forum.model.Post;


public interface ForumRepository extends CrudRepository<Post, String> {
	
	Stream<Post> findByAuthorIgnoreCase(String author);
	
	@Query("{tags: {$in: ?0}}")
	Stream<Post> findPostsByTags(List<String> author);
	
	@Query("{ dateCreated : { $gte : ?0, $lt : ?1 } }")
	Stream<Post> findPostsByPeriod(String dateFrom,String dateTo);
}
