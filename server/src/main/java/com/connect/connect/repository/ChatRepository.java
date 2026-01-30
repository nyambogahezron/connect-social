package com.connect.connect.repository;

import com.connect.connect.model.Chat;
import com.connect.connect.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {
    
    // Find all chats where the user is a participant
    @Query("SELECT c FROM Chat c JOIN c.participants p WHERE p = :user")
    List<Chat> findByParticipant(@Param("user") User user);
}
