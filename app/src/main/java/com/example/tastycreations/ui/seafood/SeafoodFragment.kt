package com.example.tastycreations.ui.seafood

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
import com.example.tastycreations.data.seafood.SeafoodModel
import com.example.tastycreations.data.seafood.MealModel
import com.example.tastycreations.databinding.FragmentSeafoodBinding
import com.example.tastycreations.ui.seafood.SeafoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeafoodFragment : Fragment() {

    private var _binding: FragmentSeafoodBinding? = null
    private val binding get() = _binding!!
    private val seafoodViewModel by viewModels<SeafoodViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeafoodBinding.inflate(inflater, container, false)

        seafoodViewModel.seafoods.observe(viewLifecycleOwner) {
            it?.let{
                setupUI(it)
            }
        }
        seafoodViewModel.getSeafoodsData()
        return binding.root
    }

    private fun setupUI(seafood: SeafoodModel) {

        val seafoodAdapter= SeafoodAdapter(seafood.meals as List<MealModel?>)
        binding.rvSeafood.apply{

            layoutManager = LinearLayoutManager(context)
            adapter = seafoodAdapter
        }

        seafoodAdapter.onItemClick = {

            val bundle = Bundle().apply {
                putSerializable("SeafoodItem", it)
            }

            findNavController().navigate(R.id.action_nav_seafood_to_seafoodDetailFragment, bundle)
        }
        binding.searchview.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //TODO("Not yet implemented")
                return false

            }


            override fun onQueryTextChange(newText: String): Boolean {

                // to filter the data start with lastname given in a search bar
                val filteredList = seafood.meals.filter {
                    it?.strMeal?.startsWith(newText, ignoreCase = true) ?: false
                }
                //changing from list of people item model to arraylist of people item model
                if (filteredList?.isNotEmpty() == true) {
                    val filteredItems = ArrayList<com.example.tastycreations.data.seafood.MealModel?>()
                    filteredList?.let {
                        filteredItems.addAll(it)
                    }

                    seafoodAdapter.updateData(filteredItems)
                    binding.rvSeafood.visibility=View.VISIBLE
                    binding.text1.visibility=View.GONE

                }
                else
                {
                    binding.rvSeafood.visibility=View.GONE
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