package com.example.tastycreations.ui.seafood

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastycreations.R
import com.example.tastycreations.data.seafood.MealModel
import com.example.tastycreations.databinding.SeafoodItemsBinding

class SeafoodAdapter(var seafood:List<MealModel?>):
    RecyclerView.Adapter<com.example.tastycreations.ui.seafood.SeafoodAdapter.ViewHolder>(){

    var onItemClick:((MealModel)->Unit)?=null
    class ViewHolder(val view:View):RecyclerView.ViewHolder(view) {

        private val binding = SeafoodItemsBinding.bind(view)

        fun displayData(item: MealModel?) {

            binding.textView1.text = item?.strMeal
            Glide.with(view).load(item?.strMealThumb).centerCrop().into(binding.imgView)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.tastycreations.ui.seafood.SeafoodAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.seafood_items, parent, false)

        return ViewHolder(view)
    }


    // Size of the list
    override fun getItemCount(): Int = seafood?.size ?: 0


    // Handle the CURRENT item you are on
    override fun onBindViewHolder(holder: com.example.tastycreations.ui.seafood.SeafoodAdapter.ViewHolder, position: Int) {
        holder.displayData(seafood[position])
        holder.itemView.setOnClickListener {
            seafood?.get(position)?.let {
                onItemClick?.invoke(it)

            }
        }
    }
    fun updateData(filteredItems: ArrayList<com.example.tastycreations.data.seafood.MealModel?>) {

        seafood=filteredItems
        notifyDataSetChanged()
    }
}




