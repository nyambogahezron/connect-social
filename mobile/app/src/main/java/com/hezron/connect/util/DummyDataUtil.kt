package com.hezron.connect.util

import com.hezron.connect.model.Chat
import com.hezron.connect.model.Comment
import com.hezron.connect.model.Message
import com.hezron.connect.model.Post
import com.hezron.connect.model.User

object DummyDataUtil {

    private val users = listOf(
        User("1", "Alice Johnson", "https://i.pravatar.cc/150?u=1", "Bio needed"),
        User("2", "Bob Smith", "https://i.pravatar.cc/150?u=2", "Loves coding"),
        User("3", "Charlie Brown", "https://i.pravatar.cc/150?u=3", "Designer"),
        User("4", "Diana Prince", "https://i.pravatar.cc/150?u=4", "Wonder Woman")
    )

    fun getPosts(): List<Post> {
        return listOf(
            Post("1", users[0], "https://picsum.photos/id/237/400/300", "Just a cute dog!", 120, 12, System.currentTimeMillis()),
            Post("2", users[1], null, "Working on a new project. Native Android is fun!", 45, 5, System.currentTimeMillis()),
            Post("3", users[2], "https://picsum.photos/id/10/400/300", "Nature is beautiful.", 300, 40, System.currentTimeMillis()),
            Post("4", users[3], null, "Anyone up for a meetup?", 80, 2, System.currentTimeMillis())
        )
    }

    fun getComments(): List<Comment> {
        return listOf(
            Comment("1", users[1], "Amazing shot!", System.currentTimeMillis()),
            Comment("2", users[2], "Totally agree!", System.currentTimeMillis())
        )
    }

    fun getChats(): List<Chat> {
        return listOf(
            Chat("1", users[1], "Hey, how are you?", System.currentTimeMillis(), 2),
            Chat("2", users[2], "Project looks great!", System.currentTimeMillis(), 0),
            Chat("3", users[3], "Let's meet tomorrow.", System.currentTimeMillis(), 5)
        )
    }
    
    fun getMessages(): List<Message> {
        return listOf(
             Message("1", "2", "Hey there!", System.currentTimeMillis(), false),
             Message("2", "1", "Hi! How's it going?", System.currentTimeMillis(), true),
             Message("3", "2", "Pretty good, just coding.", System.currentTimeMillis(), false),
             Message("4", "1", "Same here. making progress.", System.currentTimeMillis(), true)
        )
    }
}
