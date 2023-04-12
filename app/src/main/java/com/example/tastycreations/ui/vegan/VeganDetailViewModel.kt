package com.example.tastycreations.ui.vegan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tastycreations.data.detail.DetailsModel
import com.example.tastycreations.data.detail.MealModel
import com.example.tastycreations.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VeganDetailViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val details= MutableLiveData<DetailsModel>()



    fun getDetailsData(mealId:String){

        viewModelScope.launch{

            val result=repository.getDetails(mealId)

            details.postValue(result)

        }
    }
}
