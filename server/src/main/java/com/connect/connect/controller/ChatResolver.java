package com.connect.connect.controller;

import com.connect.connect.model.Chat;
import com.connect.connect.model.Message;
import com.connect.connect.model.User;
import com.connect.connect.repository.ChatRepository;
import com.connect.connect.repository.MessageRepository;
import com.connect.connect.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ChatResolver {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public ChatResolver(ChatRepository chatRepository, MessageRepository messageRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @QueryMapping
    public List<Chat> myChats() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User currentUser = userRepository.findByEmail(email).orElseThrow();
        
        return chatRepository.findByParticipant(currentUser);
    }
    
    @QueryMapping
    public Chat chat(@Argument String id) {
        return chatRepository.findById(id).orElseThrow(() -> new RuntimeException("Chat not found"));
    }

    @MutationMapping
    public Chat startChat(@Argument String participantUserId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User currentUser = userRepository.findByEmail(email).orElseThrow();
        User participant = userRepository.findById(participantUserId).orElseThrow(() -> new RuntimeException("User not found"));

        // Check if chat already exists (simplified: creating new one for now or check manually)
        // For simplicity, create new chat
        Chat chat = new Chat();
        Set<User> participants = new HashSet<>();
        participants.add(currentUser);
        participants.add(participant);
        chat.setParticipants(participants);
        
        return chatRepository.save(chat);
    }

    @MutationMapping
    public Message sendMessage(@Argument String chatId, @Argument String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User sender = userRepository.findByEmail(email).orElseThrow();
        
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found"));
        
        // Verify user is participant
        boolean isParticipant = chat.getParticipants().stream()
                .anyMatch(u -> u.getId().equals(sender.getId()));
        
        if (!isParticipant) {
             throw new RuntimeException("Not a participant");
        }

        Message message = new Message(content, chat, sender);
        return messageRepository.save(message);
    }

    @SchemaMapping
    public List<Message> messages(Chat chat) {
        return messageRepository.findByChatIdOrderByTimestampAsc(chat.getId());
    }
    
    @SchemaMapping
    public Message lastMessage(Chat chat) {
        return messageRepository.findTopByChatIdOrderByTimestampDesc(chat.getId());
    }
}
