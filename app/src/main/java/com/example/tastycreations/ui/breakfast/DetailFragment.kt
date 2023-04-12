package com.example.tastycreations.ui.breakfast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tastycreations.data.detail.DetailsModel
import com.example.tastycreations.data.detail.MealModel
import com.example.tastycreations.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val bf = arguments?.getSerializable("BreakfastItem") as com.example.tastycreations.data.breakfast.MealModel
        detailViewModel.details.observe(viewLifecycleOwner){
            it?.let{
                displayMeal(it)
            }
        }
        detailViewModel.getDetailsData(bf.idMeal)
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

