package com.example.tastycreations.ui.starter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tastycreations.data.starter.StarterModel
import com.example.tastycreations.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarterViewModel @Inject constructor(
        val repository:Repository): ViewModel() {

   val starters=MutableLiveData<StarterModel>()

    fun getStartersData(){

        viewModelScope.launch{

            val result=repository.getStarter()
            starters.postValue(result)
        }
    }
}