package com.example.newsproject.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.newsproject.R
import com.example.newsproject.databinding.ItemNewsBinding
import com.example.newsproject.domain.model.NewsModel

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var list = arrayListOf<NewsModel>()
    private var onItemClickListener: ((news: NewsModel) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: (news: NewsModel) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemBinding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = list[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listItem: List<NewsModel>) {
        this.list = listItem as ArrayList<NewsModel>
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsModel) {
            with(news) {
                renderThumbnail(urlToImage)
                renderTextView(this)

            }
            binding.llItemNews.setOnClickListener {
                onItemClickListener?.invoke(news)
            }
        }

        private fun renderThumbnail(thumbnailUrl: String) {
            with(binding) {
                imgCover.load(thumbnailUrl) {
                    crossfade(true)
                    crossfade(500)
                    transformations(RoundedCornersTransformation(10f))
                    placeholder(R.drawable.file)
                    error(R.drawable.ic_fail)
                }
            }
        }

        private fun renderTextView(news: NewsModel) {
            with(binding) {
                itemNewsAuthor.text = news.author
                itemNewsTitle.text = news.title
                itemNewsPublished.text = news.published
            }
        }
    }
}