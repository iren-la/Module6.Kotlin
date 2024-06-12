package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likesCount.text = countToString(post.likes)
                repostsCount.text = countToString(post.repostCount)
                viewsCount.text = countToString(post.viewCount)
                likes.setImageResource(
                    if (post.likedByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
                )
            }
        }
        binding.likes.setOnClickListener {
            viewModel.like()
        }
        binding.repost.setOnClickListener {
            viewModel.repost()
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