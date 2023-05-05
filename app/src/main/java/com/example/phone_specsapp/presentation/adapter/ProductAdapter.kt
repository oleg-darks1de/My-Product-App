package com.example.phone_specsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phone_specsapp.R
import com.example.phone_specsapp.data.local.Product

class ProductAdapter : ListAdapter<Product, ProductAdapter.Holder>(Comparator()) {

    class Holder(view: View) : RecyclerView.ViewHolder(view){
        private val title = view.findViewById<TextView>(R.id.title)
        private val description = view.findViewById<TextView>(R.id.description)
        private val brand = view.findViewById<TextView>(R.id.brand)
        private val price = view.findViewById<TextView>(R.id.price)
        private val rating = view.findViewById<TextView>(R.id.rating)
        private fun getStarsString(rating: Float): String {
            val stars = "⭐"
            val numStars = rating.toInt()
            var starsString = ""

            for (i in 0 until numStars) {
                starsString += stars
            }

            return starsString
        }
        private val thumbnail = view.findViewById<ImageView>(R.id.thumbnail)
        fun bind(product: Product)= with(itemView){
            title.text = product.title
            description.text = product.description
            brand.text = "Brand: ${product.brand}"
            price.text = "Price: $${product.price}"
            rating.text ="Rating: ${product.rating} ${getStarsString(product.rating)}"
            Glide.with(thumbnail.context)
                .load(product.thumbnail)
                .into(thumbnail)
        }
    }

    class Comparator : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.phone_list, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}
