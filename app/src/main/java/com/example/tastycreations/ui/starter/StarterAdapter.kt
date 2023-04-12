package com.example.tastycreations.ui.starter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastycreations.R
import com.example.tastycreations.data.starter.MealModel
import com.example.tastycreations.databinding.StarterItemsBinding

class StarterAdapter(val starter:List<MealModel?>):
RecyclerView.Adapter<com.example.tastycreations.ui.starter.StarterAdapter.ViewHolder>() {

    var onItemClick: ((MealModel) -> Unit)? = null

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = StarterItemsBinding.bind(view)

        fun displayData(item: MealModel?) {

            binding.textView1.text = item?.strMeal
            Glide.with(view).load(item?.strMealThumb).centerCrop().into(binding.imgView)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): com.example.tastycreations.ui.starter.StarterAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.starter_items, parent, false)

        return ViewHolder(view)
    }


    // Size of the list
    override fun getItemCount(): Int = starter?.size ?: 0


    // Handle the CURRENT item you are on
    override fun onBindViewHolder(
        holder: com.example.tastycreations.ui.starter.StarterAdapter.ViewHolder,
        position: Int
    ) {
        holder.displayData(starter[position])
        holder.itemView.setOnClickListener {
            starter?.get(position)?.let {

               onItemClick?.invoke(it)
            }
        }
    }
}




