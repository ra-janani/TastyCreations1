package com.example.tastycreations.ui.breakfast

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastycreations.R
import com.example.tastycreations.data.breakfast.MealModel
import com.example.tastycreations.databinding.BreakfastItemsBinding

class BreakfastAdapter(val breakfast:List<MealModel?>):
    RecyclerView.Adapter<com.example.tastycreations.ui.breakfast.BreakfastAdapter.ViewHolder>() {

    var onItemClick:((MealModel)->Unit)?=null

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = BreakfastItemsBinding.bind(view)

        fun displayData(item: MealModel?) {

            binding.textView1.text = item?.strMeal
            Log.d("STRMEAL", item?.strMeal!!)
            Glide.with(view).load(item?.strMealThumb).centerCrop().into(binding.imgView)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): com.example.tastycreations.ui.breakfast.BreakfastAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.breakfast_items, parent, false)

        return ViewHolder(view)
    }


    // Size of the list
    override fun getItemCount(): Int = breakfast?.size ?: 0


    // Handle the CURRENT item you are on
    override fun onBindViewHolder(
        holder: com.example.tastycreations.ui.breakfast.BreakfastAdapter.ViewHolder,
        position: Int)
     {
        holder.displayData(breakfast?.get(position))
         holder.itemView.setOnClickListener {
            breakfast?.get(position)?.let {
                onItemClick?.invoke(it)

            }
        }
    }
}




