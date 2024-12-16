package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private var cloths: MutableList<Cloth>) :
    RecyclerView.Adapter<CustomAdapter.ArticleViewHolder>() {

    private var onArticleClickListener: OnArticleClickListener? = null

    interface OnArticleClickListener {
        fun onArticleClick(cloth: Cloth, position: Int)
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameTV: TextView = itemView.findViewById(R.id.itemNameTextViewTV)
        val descriptionTV: TextView = itemView.findViewById(R.id.itemDescriptionTextViewTV)
        val imageTV: ImageView = itemView.findViewById(R.id.itemImageViewIV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cloths.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = cloths[position]
        holder.nameTV.text = article.name
        holder.descriptionTV.text = article.description
        holder.imageTV.setImageResource(article.image)
        holder.itemView.setOnClickListener {
            if (onArticleClickListener != null) {
                onArticleClickListener!!.onArticleClick(article, position)
            }
        }
    }

    fun updateAdapter(newList: MutableList<Cloth>) {
        cloths = newList
    }

    fun setOnArticleClickListener(onArticleClickListener: OnArticleClickListener) {
        this.onArticleClickListener = onArticleClickListener
    }
}