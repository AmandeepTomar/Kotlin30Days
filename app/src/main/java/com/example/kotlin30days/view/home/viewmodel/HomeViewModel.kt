package com.example.kotlin30days.view.home.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.example.kotlin30days.BR
import com.example.kotlin30days.baseclasses.BaseViewModel
import com.example.kotlin30days.repo.HomeRepo
import com.example.kotlin30days.utility.Logger
import com.example.kotlin30days.utility.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) :BaseViewModel() {

    var job: Job? = null

    @get:Bindable
    public var isLoading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loading)

        }



    fun getUsers(): MutableLiveData<Resource<Any>> {
        job = Job()
        var result: MutableLiveData<Resource<Any>> = MutableLiveData()
        result.setValue(Resource.loading(null))
        job = GlobalScope.launch(Dispatchers.Main + job!!) {
            try {
                val list = homeRepo.getUsers()
                Logger.setLog(message = list.toString())
                result.value = Resource.success(list)
            }catch (e:Exception){
                Logger.setLog(message = e.toString())
                result.value=Resource.error(e.localizedMessage,0)
            }
        }
        return result
    }


    fun geRepos(login:String): MutableLiveData<Resource<Any>> {
        job = Job()
        var result: MutableLiveData<Resource<Any>> = MutableLiveData()
        result.setValue(Resource.loading(null))
        job = GlobalScope.launch(Dispatchers.Main + job!!) {
            try {
                val list = homeRepo.getUsersRepo(login)
                Logger.setLog(message = list.toString())
                result.value = Resource.success(list)
            }catch (e:Exception){
                Logger.setLog(message = e.toString())
                result.value=Resource.error(e.localizedMessage,0)
            }
        }
        return result
    }
}