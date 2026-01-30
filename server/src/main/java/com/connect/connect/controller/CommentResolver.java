package com.connect.connect.controller;

import com.connect.connect.model.Comment;
import com.connect.connect.model.Post;
import com.connect.connect.model.User;
import com.connect.connect.repository.CommentRepository;
import com.connect.connect.repository.PostRepository;
import com.connect.connect.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CommentResolver {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentResolver(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @MutationMapping
    public Comment addComment(@Argument String postId, @Argument String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment(content, post, user);
        return commentRepository.save(comment);
    }

    @SchemaMapping
    public List<Comment> comments(Post post) {
        return commentRepository.findByPostIdOrderByTimestampDesc(post.getId());
    }
}
