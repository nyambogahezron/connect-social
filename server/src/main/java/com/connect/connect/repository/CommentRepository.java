package com.connect.connect.repository;

import com.connect.connect.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByPostIdOrderByTimestampDesc(String postId);
}
