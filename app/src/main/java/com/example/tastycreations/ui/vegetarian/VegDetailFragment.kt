package com.example.tastycreations.ui.vegetarian

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tastycreations.R
import com.example.tastycreations.data.detail.DetailsModel
import com.example.tastycreations.data.detail.MealModel
import com.example.tastycreations.databinding.FragmentDetailBinding
import com.example.tastycreations.databinding.FragmentVegDetailBinding
import com.example.tastycreations.ui.breakfast.DetailViewModel
import com.example.tastycreations.ui.vegan.VeganDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VegDetailFragment : Fragment() {

    private var _binding: FragmentVegDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel by viewModels<VeganDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVegDetailBinding.inflate(inflater, container, false)
        val veg = arguments?.getSerializable("VegItem") as com.example.tastycreations.data.vegetarian.MealModel
        detailViewModel.details.observe(viewLifecycleOwner){
            it?.let{
                displayMeal(it)
            }
        }
        detailViewModel.getDetailsData(veg.idMeal)
        return binding.root
    }
    private fun displayMeal(detailsModel: DetailsModel) {
        val meal=detailsModel.meals?.get(0)
        meal?.let {

            binding.text2.text=it.strMeal
            binding.text6.text=it.strCategory
            binding.text4.text=it.strArea
            binding.text7.text=it.strInstructions

        }
    }
}