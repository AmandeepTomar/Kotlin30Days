package com.example.kotlin30days.view.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.kotlin30days.R
import com.example.kotlin30days.databinding.FragmentLoginBinding
import com.example.kotlin30days.di.Injectable
import com.example.kotlin30days.utility.AppPref
import com.example.kotlin30days.utility.Logger
import com.example.kotlin30days.utility.Resource
import com.example.kotlin30days.utility.showSnackBar
import com.example.kotlin30days.view.login.model.LoginResponse
import com.example.kotlin30days.view.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(),Injectable {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    @Inject
    lateinit var appPref: AppPref

    private lateinit var bindingFragmentlogin: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingFragmentlogin =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        bindingFragmentlogin.viewModel = viewModel
        return bindingFragmentlogin.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login.setOnClickListener {view->
            hideKeyboard()
            viewModel.login().observe(viewLifecycleOwner, Observer {
                when (it?.status) {
                    Resource.LOADING -> {
                        Logger.setLog("loading", "start loading")
                        viewModel.isLoading = true
                    }
                    Resource.SUCCESS -> {
                        viewModel.isLoading = false
                        val response = it.data as LoginResponse
                        Logger.setLog(message = response.toString())
                      resetEditText()
                        appPref.setLogin(true)
                        view.findNavController().navigate(R.id.action_loginFragment2_to_homeFragment)

                    }
                    Resource.ERROR -> {
                        viewModel.isLoading = false
                       resetEditText()
                        it.message?.let { it1 -> container.showSnackBar(it1) }
                    }
                }
            })
        }

        register.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment2_to_registerFragment)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



    fun hideKeyboard() {
        val view = requireActivity().currentFocus
        if (view != null) {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun resetEditText() {
        username.setText("")
        password.setText("")
        viewModel.reset()
    }



}