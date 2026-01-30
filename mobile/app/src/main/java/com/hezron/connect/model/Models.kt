package com.hezron.connect.model

data class User(
    val id: String,
    val name: String,
    val avatarUrl: String,
    val bio: String = ""
)

data class Post(
    val id: String,
    val user: User,
    val imageUrl: String?,
    val content: String,
    val likes: Int,
    val commentsCount: Int,
    val timestamp: Long,
    val isLiked: Boolean = false
)

data class Comment(
    val id: String,
    val user: User,
    val content: String,
    val timestamp: Long
)

data class Chat(
    val id: String,
    val user: User, // The other user in the chat
    val lastMessage: String,
    val timestamp: Long,
    val unreadCount: Int = 0
)

data class Message(
    val id: String,
    val senderId: String,
    val content: String,
    val timestamp: Long,
    val isMe: Boolean // Helper to identify sent vs received
)
