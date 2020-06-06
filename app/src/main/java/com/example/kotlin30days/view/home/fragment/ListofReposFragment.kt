package com.example.kotlin30days.view.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin30days.R
import com.example.kotlin30days.data.local.model.UserRepo
import com.example.kotlin30days.data.local.model.Users
import com.example.kotlin30days.databinding.FragmentListofReposBinding
import com.example.kotlin30days.di.Injectable
import com.example.kotlin30days.utility.Logger
import com.example.kotlin30days.utility.Resource
import com.example.kotlin30days.utility.showSnackBar
import com.example.kotlin30days.view.home.adapter.RvAdapterRepos
import com.example.kotlin30days.view.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListofReposFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListofReposFragment : Fragment(),Injectable {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentListofReposBinding
    private lateinit var rvAdapter:RvAdapterRepos
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val homeViewModel: HomeViewModel by viewModels {
        viewModelFactory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        binding=DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_listof_repos, container, false)
        rvAdapter= RvAdapterRepos()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpRecyclerView()

        homeViewModel.geRepos("mojombo").observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.LOADING -> {
                    Logger.setLog("loading", "start loading")
                    homeViewModel.isLoading = true
                }
                Resource.SUCCESS -> {
                    // here we need to setup recyclerview
                    val list=it.data as List<UserRepo>
                    Logger.setLog("here",list.toString())
                    rvAdapter.addDataList(list)
                    homeViewModel.isLoading = false


                }
                Resource.ERROR -> {
                    homeViewModel.isLoading = false
                    it.message?.let { it1 -> homeContainer.showSnackBar(it1) }
                }
            }
        })
    }

    private fun setUpRecyclerView() {
        binding.listOfReposRecyclerView.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=rvAdapter
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListofReposFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListofReposFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}