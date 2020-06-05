package com.example.kotlin30days.view.login.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.example.kotlin30days.BR
import com.example.kotlin30days.baseclasses.BaseViewModel
import com.example.kotlin30days.repo.LoginRepo
import com.example.kotlin30days.utility.Logger
import com.example.kotlin30days.utility.Resource
import com.example.kotlin30days.utility.SUCCESS
import com.example.kotlin30days.utility.Validator
import com.example.kotlin30days.view.login.model.LoginResponse
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel:BaseViewModel() {
    var job: Job? = null
     var userName:String="a@a.com"
     var password:String="123456"

    @get:Bindable
    public var isLoading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loading)

        }



    fun login(): MutableLiveData<Resource<Any>> {
        job = Job()
        val auth=Firebase.auth
        var result: MutableLiveData<Resource<Any>> = MutableLiveData()
        result.setValue(Resource.loading(null))
        job = GlobalScope.launch(Dispatchers.Main + job!!) {
            val validate=Validator.validateLogin(userName, password)
            if ( validate== SUCCESS) {
                LoginRepo.login(userName, password, auth).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = auth.currentUser
                        result.value = Resource.success(user?.email?.let { it1 ->
                            LoginResponse(
                                true,
                                it1
                            )
                        })
                    } else if (it.isCanceled) {
                        result.value = Resource.error(
                            "Login Failed",
                            it.exception?.localizedMessage?.let { it1 ->
                                LoginResponse(
                                    userName = it1
                                )
                            }
                        )
                    } else {
                        it?.exception?.localizedMessage?.let { it1 -> Logger.setLog(message = it1) }
                        result.value = Resource.error(
                            it.exception?.localizedMessage, 0
                        )
                    }
                }
            }else
                result.value = Resource.error(
                   validate, 0
                )
        }
        return result
    }


    fun register(): MutableLiveData<Resource<Any>> {
        job = Job()
        val auth=Firebase.auth
        var result: MutableLiveData<Resource<Any>> = MutableLiveData()
        result.setValue(Resource.loading(null))
        job = GlobalScope.launch(Dispatchers.Main + job!!) {
            val validate=Validator.validateLogin(userName, password)
            if ( validate== SUCCESS) {
                LoginRepo.register(userName, password, auth).addOnCompleteListener {
                    when {
                        it.isSuccessful -> {
                            val user = auth.currentUser
                            result.value = Resource.success(user?.email?.let { it1 ->
                                LoginResponse(
                                    true,
                                    it1
                                )
                            })
                        }
                        it.isCanceled -> {
                            result.value = Resource.error(
                                "Login Failed",
                                it.exception?.localizedMessage?.let { it1 ->
                                    LoginResponse(
                                        userName = it1
                                    )
                                }
                            )
                        }
                        else -> {
                            it?.exception?.localizedMessage?.let { it1 -> Logger.setLog(message = it1) }
                            result.value = Resource.error(
                                it.exception?.localizedMessage, 0
                            )
                        }
                    }
                }
            }else
                result.value = Resource.error(
                    validate, 0
                )
        }
        return result
    }

  fun reset(){
      userName=""
      password=""
  }
}