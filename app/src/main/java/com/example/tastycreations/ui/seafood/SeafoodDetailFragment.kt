package com.example.tastycreations.ui.seafood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tastycreations.data.detail.DetailsModel
import com.example.tastycreations.data.detail.MealModel
import com.example.tastycreations.databinding.FragmentDetailBinding
import com.example.tastycreations.databinding.FragmentSeafoodDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeafoodDetailFragment : Fragment() {

    private var _binding: FragmentSeafoodDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel by viewModels<SeafoodDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeafoodDetailBinding.inflate(inflater, container, false)
        val sf = arguments?.getSerializable("SeaFoodItem") as? com.example.tastycreations.data.seafood.MealModel
        detailViewModel.details.observe(viewLifecycleOwner){
            it?.let{
                displayMeal(it)
            }
        }
        detailViewModel.getDetailsData(sf?.idMeal?:"-")
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