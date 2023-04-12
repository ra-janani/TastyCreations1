package com.example.tastycreations.ui.vegetarian

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastycreations.R
import com.example.tastycreations.data.vegetarian.VegetarianModel
import com.example.tastycreations.data.vegetarian.MealModel
import com.example.tastycreations.databinding.FragmentVegetarianBinding
import com.example.tastycreations.ui.seafood.SeafoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VegetarianFragment : Fragment() {

    private var _binding: FragmentVegetarianBinding? = null
    private val binding get() = _binding!!
    private val vegetarianViewModel by viewModels<VegetarianViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVegetarianBinding.inflate(inflater, container, false)

        vegetarianViewModel.vegetarians.observe(viewLifecycleOwner) {
            it?.let{
                setupUI(it)
            }
        }
        vegetarianViewModel.getVegetariansData()
        return binding.root
    }

    private fun setupUI(vegetarian: VegetarianModel) {

        val vegetarianAdapter= VegetarianAdapter(vegetarian.meals as List<MealModel?>)
        binding.rvVegetarian.apply{

            layoutManager = LinearLayoutManager(context)
            adapter = vegetarianAdapter
        }

        vegetarianAdapter.onItemClick = {

            val bundle = Bundle().apply {
                putSerializable("VegItem", it)
            }

            findNavController().navigate(R.id.action_nav_vegetarian_to_vegDetailFragment, bundle)
        }
        binding.searchview.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //TODO("Not yet implemented")
                return false

            }


            override fun onQueryTextChange(newText: String): Boolean {

                // to filter the data start with lastname given in a search bar
                val filteredList = vegetarian.meals.filter {
                    it?.strMeal?.startsWith(newText, ignoreCase = true) ?: false
                }
                //changing from list of people item model to arraylist of people item model
                if (filteredList?.isNotEmpty() == true) {
                    val filteredItems = ArrayList<MealModel?>()
                    filteredList?.let {
                        filteredItems.addAll(it)
                    }

                    vegetarianAdapter.updateData(filteredItems)
                    binding.rvVegetarian.visibility=View.VISIBLE
                    binding.text1.visibility=View.GONE

                }
                else
                {
                    binding.rvVegetarian.visibility=View.GONE
                    binding.text1.visibility=View.VISIBLE

                }
                return true
            }
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
