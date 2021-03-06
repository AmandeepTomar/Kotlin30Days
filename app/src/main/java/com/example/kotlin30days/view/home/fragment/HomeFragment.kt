package com.example.kotlin30days.view.home.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin30days.R
import com.example.kotlin30days.data.local.model.Users
import com.example.kotlin30days.databinding.FragmentHomeBinding
import com.example.kotlin30days.di.Injectable
import com.example.kotlin30days.utility.Logger
import com.example.kotlin30days.utility.Resource
import com.example.kotlin30days.utility.showSnackBar
import com.example.kotlin30days.view.home.adapter.RvAdapterUsers
import com.example.kotlin30days.view.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*

import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), Injectable {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentHomeBinding
    private lateinit var rvAdapter: RvAdapterUsers

    //
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
        rvAdapter= RvAdapterUsers()

        setHasOptionsMenu(true)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel=homeViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.setLog(message = "$homeViewModel  is Received")
        setUpRecyclerView()

        homeViewModel.getUsers().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.LOADING -> {
                    Logger.setLog("loading", "start loading")
                    homeViewModel.isLoading = true
                }
                Resource.SUCCESS -> {
                    // here we need to setup recyclerview
                    val list=it.data as List<Users>
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


    fun setUpRecyclerView(){
        binding.homeRecyclerView.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=rvAdapter
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
        val id: Int = item.itemId
        if (id == R.id.menusetting) {
            NavHostFragment.findNavController(fragment)
                .navigate(R.id.action_homeFragment_to_settingFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}