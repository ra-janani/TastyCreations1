package com.example.tastycreations.ui.vegan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastycreations.R
import com.example.tastycreations.data.vegan.MealModel
import com.example.tastycreations.databinding.VeganItemsBinding

class VeganAdapter(val vegan:List<MealModel?>):
    RecyclerView.Adapter<com.example.tastycreations.ui.vegan.VeganAdapter.ViewHolder>() {

    var onItemClick: ((MealModel) -> Unit)? = null

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = VeganItemsBinding.bind(view)

        fun displayData(item: MealModel?) {

            binding.textView1.text = item?.strMeal
            Glide.with(view).load(item?.strMealThumb).centerCrop().into(binding.imgView)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): com.example.tastycreations.ui.vegan.VeganAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vegan_items, parent, false)

        return ViewHolder(view)
    }


    // Size of the list
    override fun getItemCount(): Int = vegan?.size ?: 0


    // Handle the CURRENT item you are on
    override fun onBindViewHolder(
        holder: com.example.tastycreations.ui.vegan.VeganAdapter.ViewHolder,
        position: Int
    ) {
        holder.displayData(vegan[position])
        holder.itemView.setOnClickListener {
            vegan?.get(position)?.let {

                onItemClick?.invoke(it)
            }
        }
    }
}




