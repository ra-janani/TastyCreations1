package com.example.tastycreations.ui.seafood

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tastycreations.data.seafood.SeafoodModel
import com.example.tastycreations.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeafoodViewModel @Inject constructor(
    val repository:Repository): ViewModel() {

    val seafoods=MutableLiveData<SeafoodModel>()

    fun getSeafoodsData(){

        viewModelScope.launch{

            val result=repository.getSeafood()
            seafoods.postValue(result)
        }
    }
}