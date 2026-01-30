package com.hezron.connect.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hezron.connect.databinding.ItemPostBinding
import com.hezron.connect.model.Post

class FeedAdapter(private val onPostClick: (Post) -> Unit) : 
    ListAdapter<Post, FeedAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.tvUsername.text = post.user.name
            binding.tvContent.text = post.content
            binding.tvLikes.text = "${post.likes} Likes"
            binding.tvComments.text = "${post.commentsCount} Comments"
            
            // Avatar
            Glide.with(binding.root)
                .load(post.user.avatarUrl)
                .circleCrop()
                .into(binding.ivAvatar)

            // Post Image
            if (post.imageUrl != null) {
                binding.ivPostImage.visibility = android.view.View.VISIBLE
                Glide.with(binding.root)
                    .load(post.imageUrl)
                    .into(binding.ivPostImage)
            } else {
                binding.ivPostImage.visibility = android.view.View.GONE
            }

            binding.root.setOnClickListener { onPostClick(post) }
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
    }
}
