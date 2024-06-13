package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

typealias OnLikeListener = (post: Post) -> Unit
typealias OnRepostListener = (post: Post) -> Unit
class PostsAdapter(private val onLikeListener: OnLikeListener, private val onRepostListener: OnRepostListener) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onRepostListener)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onRepostListener: OnRepostListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likesCount.text = countToString(post.likes)
            repostsCount.text = countToString(post.repostCount)
            viewsCount.text = countToString(post.viewCount)
            likes.setImageResource(
                if (post.likedByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
            )
            likes.setOnClickListener{
                onLikeListener(post)
            }
            repost.setOnClickListener{
                onRepostListener(post)
            }
        }
    }

    private fun countToString (count: Int) : String {
        return when {
            count < 1000 -> count.toString()
            count in 1000..9999 -> {
                val thousand = count / 1000
                val hundreds = (count % 1000) / 100
                if (hundreds > 0) "${thousand}.${hundreds}K" else "${thousand}K"
            }
            count in 10000..999999 -> "${count/1000}K"
            else -> {
                val millions = count / 1_000_000
                val thousands = (count % 1_000_000) / 100_000
                if (thousands > 0) "${millions}.${thousands}M" else "${millions}M"
            }
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}