package com.example.tastycreations.ui.vegetarian

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastycreations.R
import com.example.tastycreations.data.vegetarian.MealModel
import com.example.tastycreations.databinding.VegetarianItemsBinding

class VegetarianAdapter(var vegetarian:List<MealModel?>):
    RecyclerView.Adapter<com.example.tastycreations.ui.vegetarian.VegetarianAdapter.ViewHolder>() {

    var onItemClick: ((MealModel) -> Unit)? = null

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = VegetarianItemsBinding.bind(view)

        fun displayData(item: MealModel?) {

            binding.textView1.text = item?.strMeal
            Glide.with(view).load(item?.strMealThumb).centerCrop().into(binding.imgView)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): com.example.tastycreations.ui.vegetarian.VegetarianAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vegetarian_items, parent, false)

        return ViewHolder(view)
    }


    // Size of the list
    override fun getItemCount(): Int = vegetarian?.size ?: 0


    // Handle the CURRENT item you are on
    override fun onBindViewHolder(
        holder: com.example.tastycreations.ui.vegetarian.VegetarianAdapter.ViewHolder,
        position: Int
    ) {
        holder.displayData(vegetarian[position])
        holder.itemView.setOnClickListener {
            vegetarian?.get(position)?.let {

                onItemClick?.invoke(it)
            }
        }
    }
    fun updateData(filteredItems: ArrayList<MealModel?>) {

        vegetarian=filteredItems
        notifyDataSetChanged()
    }
}




