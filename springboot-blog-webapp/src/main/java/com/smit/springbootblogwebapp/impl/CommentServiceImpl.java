package com.smit.springbootblogwebapp.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.smit.springbootblogwebapp.dto.CommentDto;
import com.smit.springbootblogwebapp.entity.Comment;
import com.smit.springbootblogwebapp.entity.Post;
import com.smit.springbootblogwebapp.entity.User;
import com.smit.springbootblogwebapp.mapper.CommentMapper;
import com.smit.springbootblogwebapp.repository.CommentRepository;
import com.smit.springbootblogwebapp.repository.PostRepository;
import com.smit.springbootblogwebapp.repository.UserRepository;
import com.smit.springbootblogwebapp.service.CommentService;
import com.smit.springbootblogwebapp.util.SecurityUtils;


@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private UserRepository userRepository;
	
	
	// constructor based dependency
	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}


	@Override
	public void createComment(String postUrl, CommentDto commentdto) {
		// we are retrieving this post entity because whenever we save
		// the comment object we need to also this post object to the
		// comment jpa entity because this is one to many relationship
		Post post = postRepository.findByUrl(postUrl).get();
		Comment comment = CommentMapper.mapToComment(commentdto);
		comment.setPost(post);
		commentRepository.save(comment);
		
		
		
	}


	@Override
	public List<CommentDto> findAllComments() {
		List<Comment> comments = commentRepository.findAll();
		return comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
	}


	@Override
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}


	@Override
	public List<CommentDto> findCommentsByPost() {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Long userId = createdBy.getId();
		List<Comment> comments = commentRepository.findCommentsByPost(userId);
		return comments.stream().map((comment) -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList()); 
	}

}
