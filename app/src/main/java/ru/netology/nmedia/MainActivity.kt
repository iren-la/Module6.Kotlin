package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post (
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likes = 999,
            repostCount = 987,
            viewCount = 103293
        )

        with(binding){
            author.text = post.author
            published.text= post.published
            content.text = post.content
            likesCount.text = countToString(post.likes)
            repostsCount.text = countToString(post.repostCount)
            viewsCount.text = countToString(post.viewCount)
            if (post.likedByMe) {
                likes.setImageResource(R.drawable.baseline_favorite_24)
            }

            likes.setOnClickListener{
                post.likedByMe = !post.likedByMe
                if (post.likedByMe) {
                    post.likes += 1
                    likesCount.text = countToString(post.likes)
                    likes.setImageResource(R.drawable.baseline_favorite_24)
                } else {
                    post.likes -= 1
                    likesCount.text = countToString(post.likes)
                    likes.setImageResource(R.drawable.baseline_favorite_border_24)
                }
            }

            repost.setOnClickListener {
                post.repostCount += 1
                repostsCount.text = countToString(post.repostCount)
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