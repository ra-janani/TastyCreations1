package com.example.tastycreations.ui.breakfast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tastycreations.data.breakfast.BreakfastModel
import com.example.tastycreations.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreakfastViewModel @Inject constructor(
    val repository:Repository): ViewModel() {

    val breakfasts=MutableLiveData<BreakfastModel>()

    fun getBreakfastsData(){

        viewModelScope.launch{

            val result=repository.getBreakfast()
            breakfasts.postValue(result)
        }
    }
}