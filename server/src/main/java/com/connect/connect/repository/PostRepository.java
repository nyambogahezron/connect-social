package com.connect.connect.repository;

import com.connect.connect.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findAllByOrderByTimestampDesc();
}
