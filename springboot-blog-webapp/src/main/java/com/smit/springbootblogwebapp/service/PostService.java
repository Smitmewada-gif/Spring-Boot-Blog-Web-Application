package com.smit.springbootblogwebapp.service;

import java.util.List;

import com.smit.springbootblogwebapp.dto.*;

public interface PostService {
	List<PostDto> findallPosts();
	
	List<PostDto> findPostByUser();
	
	void createPost(PostDto postDto);
	
	PostDto findPostById(Long postId);
	
	void updatePost(PostDto postDto);
	
	void deletePost(Long postId);

	PostDto findPostByUrl(String postUrl);
	
	List<PostDto> searchPosts(String query);
}
