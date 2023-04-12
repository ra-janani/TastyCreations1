package com.example.tastycreations.ui.vegetarian

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tastycreations.data.vegetarian.VegetarianModel
import com.example.tastycreations.data.starter.StarterModel
import com.example.tastycreations.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VegetarianViewModel @Inject constructor(
    val repository:Repository): ViewModel() {

    val vegetarians=MutableLiveData<VegetarianModel>()

    fun getVegetariansData(){

        viewModelScope.launch{

            val result=repository.getVegetarian()
            vegetarians.postValue(result)
        }
    }
}