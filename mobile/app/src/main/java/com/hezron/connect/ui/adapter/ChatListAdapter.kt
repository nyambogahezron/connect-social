package com.hezron.connect.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hezron.connect.databinding.ItemChatListBinding
import com.hezron.connect.model.Chat

class ChatListAdapter(private val onChatClick: (Chat) -> Unit) : 
    ListAdapter<Chat, ChatListAdapter.ChatViewHolder>(ChatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ChatViewHolder(private val binding: ItemChatListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: Chat) {
            binding.tvUsername.text = chat.user.name
            binding.tvLastMessage.text = chat.lastMessage
            binding.tvTimestamp.text = "10:30 AM" // Placeholder

            Glide.with(binding.root)
                .load(chat.user.avatarUrl)
                .circleCrop()
                .into(binding.ivAvatar)
                
            binding.root.setOnClickListener { onChatClick(chat) }
        }
    }

    class ChatDiffCallback : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean = oldItem == newItem
    }
}
