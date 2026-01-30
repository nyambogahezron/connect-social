package com.connect.connect.controller;

import com.connect.connect.model.User;
import com.connect.connect.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class ProfileResolver {

    private final UserRepository userRepository;

    private final com.connect.connect.repository.FollowRepository followRepository;

    public ProfileResolver(UserRepository userRepository, com.connect.connect.repository.FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    @QueryMapping
    public User user(@Argument String id) {
        return userRepository.findById(id).orElse(null);
    }
    
    @QueryMapping
    public User me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElse(null);
    }

    @MutationMapping
    public User updateProfile(@Argument String bio, @Argument String avatar) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (bio != null) user.setBio(bio);
        if (avatar != null) user.setAvatar(avatar);

        return userRepository.save(user);
    }

    @MutationMapping
    public Boolean follow(@Argument String userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        
        User follower = userRepository.findByEmail(email).orElseThrow();
        User following = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (followRepository.existsByFollowerAndFollowing(follower, following)) {
            return false;
        }

        com.connect.connect.model.Follow follow = new com.connect.connect.model.Follow(follower, following);
        followRepository.save(follow);
        return true;
    }

    @MutationMapping
    public Boolean unfollow(@Argument String userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        
        User follower = userRepository.findByEmail(email).orElseThrow();
        User following = userRepository.findById(userId).orElseThrow();

        var follow = followRepository.findByFollowerAndFollowing(follower, following);
        if (follow.isPresent()) {
            followRepository.delete(follow.get());
            return true;
        }
        return false;
    }
}
