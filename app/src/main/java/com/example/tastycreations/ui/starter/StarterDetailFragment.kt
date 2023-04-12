package com.example.tastycreations.ui.starter

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
import com.example.tastycreations.databinding.FragmentStarterDetailBinding
import com.example.tastycreations.ui.breakfast.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarterDetailFragment : Fragment() {

    private var _binding: FragmentStarterDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel by viewModels<StarterDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStarterDetailBinding.inflate(inflater, container, false)
        val st = arguments?.getSerializable("StarterItem") as com.example.tastycreations.data.starter.MealModel
        detailViewModel.details.observe(viewLifecycleOwner){
            it?.let{
                displayMeal(it)
            }
        }
        detailViewModel.getDetailsData(st.idMeal)
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