package com.example.tastycreations.ui.vegan

import android.app.AlertDialog
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
import com.example.tastycreations.data.vegan.VeganModel
import com.example.tastycreations.data.vegan.MealModel
import com.example.tastycreations.databinding.FragmentVeganBinding
import com.example.tastycreations.ui.seafood.SeafoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VeganFragment : Fragment() {

    private var _binding: FragmentVeganBinding? = null
    private val binding get() = _binding!!
    private val veganViewModel by viewModels<VeganViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVeganBinding.inflate(inflater, container, false)

        veganViewModel.vegans.observe(viewLifecycleOwner) {


            it?.let{
                setupUI(it)
            }
        }
        veganViewModel.getVegansData()
        return binding.root
    }


    private fun setupUI(vegan: VeganModel) {

        val veganAdapter= VeganAdapter(vegan.meals as List<MealModel?>)
        binding.rvVegan.apply{

            layoutManager = LinearLayoutManager(context)
            adapter = veganAdapter
        }
        veganAdapter.onItemClick = {

            val bundle = Bundle().apply {
                putSerializable("VeganItem", it)
            }

            findNavController().navigate(R.id.action_nav_vegan_to_veganDetailFragment, bundle)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}