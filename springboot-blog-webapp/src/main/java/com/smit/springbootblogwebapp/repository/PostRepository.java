package com.smit.springbootblogwebapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smit.springbootblogwebapp.entity.Post;



public interface PostRepository extends JpaRepository<Post, Long>{
	
	Optional<Post> findByUrl(String url);
	
	@Query("SELECT p from Post p WHERE " + 
			"p.title LIKE CONCAT('%', :query, '%') OR " +
			"p.shortDescription LIKE CONCAT('%', :query, '%')")
	List<Post> searchPosts(String query);
	
	@Query(value = "select * from posts p where p.created_by =:userId", nativeQuery = true)
	List<Post> findPostByUser(Long userId);
}
