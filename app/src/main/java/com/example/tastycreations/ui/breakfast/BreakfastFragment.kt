package com.example.tastycreations.ui.breakfast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastycreations.R
import com.example.tastycreations.data.breakfast.BreakfastModel
import com.example.tastycreations.data.breakfast.MealModel
import com.example.tastycreations.databinding.FragmentBreakfastBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreakfastFragment : Fragment() {

    private var _binding: FragmentBreakfastBinding? = null
    private val binding get() = _binding!!
    private val breakfastViewModel by viewModels<BreakfastViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentBreakfastBinding.inflate(inflater, container, false)

        breakfastViewModel.breakfasts.observe(viewLifecycleOwner) {
            it?.let{
                setupUI(it)
            }
        }
        breakfastViewModel.getBreakfastsData()
        return binding.root
    }

    private fun setupUI(breakfast: BreakfastModel) {

        val breakfastAdapter= BreakfastAdapter(breakfast.meals as List<MealModel?>)
        binding.rvBreakfast.apply{

            layoutManager = LinearLayoutManager(context)
            adapter = breakfastAdapter
        }
        breakfastAdapter.onItemClick = {

            val bundle = Bundle().apply {
                putSerializable("BreakfastItem", it)
            }

            findNavController().navigate(R.id.action_nav_breakfast_to_detailFragment, bundle)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}