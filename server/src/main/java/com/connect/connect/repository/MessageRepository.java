package com.connect.connect.repository;

import com.connect.connect.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, String> {
    List<Message> findByChatIdOrderByTimestampAsc(String chatId);
    Message findTopByChatIdOrderByTimestampDesc(String chatId);
}
