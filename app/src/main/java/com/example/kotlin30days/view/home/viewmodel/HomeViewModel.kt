package com.example.kotlin30days.view.home.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.example.kotlin30days.BR
import com.example.kotlin30days.baseclasses.BaseViewModel
import com.example.kotlin30days.repo.HomeRepo
import com.example.kotlin30days.utility.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) :BaseViewModel() {

    var job: Job? = null

    @get:Bindable
    public var isLoading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loading)

        }

    init {
        getUsers()
    }

    fun getUsers(): MutableLiveData<Resource<Any>> {
        job = Job()
        var result: MutableLiveData<Resource<Any>> = MutableLiveData()
        result.setValue(Resource.loading(null))
        job = GlobalScope.launch(Dispatchers.Main + job!!) {
                val list=homeRepo.getUsers()
            result.value= Resource.success(list)
        }
        return result
    }
}