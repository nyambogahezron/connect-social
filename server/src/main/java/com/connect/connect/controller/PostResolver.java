package com.connect.connect.controller;

import com.connect.connect.model.Post;
import com.connect.connect.model.User;
import com.connect.connect.repository.PostRepository;
import com.connect.connect.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PostResolver {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResolver(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @QueryMapping
    public List<Post> posts() {
        return postRepository.findAllByOrderByTimestampDesc();
    }

    @QueryMapping
    public Post post(@Argument String id) {
        return postRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Post createPost(@Argument String content, @Argument String imageUrl) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post(content, imageUrl, user);
        return postRepository.save(post);
    }
}
