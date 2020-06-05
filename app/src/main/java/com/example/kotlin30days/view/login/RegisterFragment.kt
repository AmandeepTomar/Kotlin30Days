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
import com.example.kotlin30days.databinding.FragmentRegisterBinding
import com.example.kotlin30days.utility.Logger
import com.example.kotlin30days.utility.Resource
import com.example.kotlin30days.utility.showSnackBar
import com.example.kotlin30days.view.login.model.LoginResponse
import com.example.kotlin30days.view.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.register



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentRegisterBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register.setOnClickListener { view ->
            hideKeyboard()
            viewModel.register().observe(viewLifecycleOwner, Observer {
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
                        view.findNavController()
                            .navigate(R.id.action_registerFragment_to_homeFragment)
                    }
                    Resource.ERROR -> {
                        viewModel.isLoading = false
                        resetEditText()
                        it.message?.let { it1 -> registerContainer.showSnackBar(it1) }
                    }
                }
            })
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
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
        registerusername.setText("")
        registerusername.setText("")
        viewModel.reset()
    }

}