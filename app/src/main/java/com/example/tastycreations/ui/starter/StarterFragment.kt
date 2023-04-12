package com.example.tastycreations.ui.starter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastycreations.R
import com.example.tastycreations.data.starter.MealModel
import com.example.tastycreations.data.starter.StarterModel
import com.example.tastycreations.databinding.FragmentStarterBinding
import com.example.tastycreations.ui.breakfast.BreakfastViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarterFragment : Fragment() {

    private var _binding: FragmentStarterBinding? = null
    private val binding get() = _binding!!
    private val starterViewModel by viewModels<StarterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStarterBinding.inflate(inflater, container, false)

        starterViewModel.starters.observe(viewLifecycleOwner){
            it?.let{
                setupUI(it)
            }
        }

        starterViewModel.getStartersData()
        return binding.root
    }

    private fun setupUI(starter: StarterModel) {

        val starterAdapter=StarterAdapter(starter.meals as List<MealModel?>)
        binding.rvStarter.apply{

            layoutManager = LinearLayoutManager(context)
            adapter = starterAdapter
        }
        starterAdapter.onItemClick = {

            val bundle = Bundle().apply {
                putSerializable("StarterItem", it)
            }

            findNavController().navigate(R.id.action_nav_starter_to_starterDetailFragment, bundle)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}