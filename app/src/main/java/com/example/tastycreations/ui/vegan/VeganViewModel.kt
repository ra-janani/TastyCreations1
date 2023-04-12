package com.example.tastycreations.ui.vegan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tastycreations.data.vegan.VeganModel
import com.example.tastycreations.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VeganViewModel @Inject constructor(
    val repository:Repository): ViewModel() {

    val vegans=MutableLiveData<VeganModel>()

    fun getVegansData(){

        viewModelScope.launch{

                val result=repository.getVegan()
                vegans.postValue(result)
        }
    }
}