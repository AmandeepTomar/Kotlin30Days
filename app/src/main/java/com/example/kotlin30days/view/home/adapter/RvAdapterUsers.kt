package com.example.kotlin30days.view.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin30days.R
import com.example.kotlin30days.data.local.model.Users
import com.example.kotlin30days.databinding.RvItemUserBinding
import com.example.kotlin30days.utility.URL
import com.example.kotlin30days.utility.setImage
import com.example.kotlin30days.view.home.fragment.HomeFragmentDirections
import com.example.kotlin30days.view.webview.WebViewActivity

class RvAdapterUsers : RecyclerView.Adapter<RvAdapterUsers.UsersVH>() {
    private var dataList = mutableListOf<Users>()
    private var login= MutableLiveData<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersVH {
        val view = DataBindingUtil.inflate<RvItemUserBinding>(LayoutInflater.from(parent.context),R.layout.rv_item_user, parent, false)
        return UsersVH(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: UsersVH, position: Int) {
      holder.apply {
           onBindData(position)
          dataList[position].avatarUrl?.let { binding.profileImage.setImage(it) }
        binding.buttonRepos.setOnClickListener {
                // we open new fragment here
            val login=dataList[position].login
            val action=HomeFragmentDirections.actionHomeFragmentToListofReposFragment(login)
            it.findNavController().navigate(action)
            }

          binding.buttonDetails.setOnClickListener{
                // here new fragment with details
              it.findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)

          }

          binding.buttonHtmlView.setOnClickListener {
              val mIntent = Intent(it.context, WebViewActivity::class.java)
              mIntent.putExtra(URL, dataList[position].html_url)
              mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
              mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
              it.context.startActivity(mIntent)
          }
        }
    }




    inner class UsersVH(val binding: RvItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBindData(
            position: Int
        ) {
            com.example.kotlin30days.utility.Logger.setLog(message = dataList[position].toString())
            binding.item=dataList[position]
            binding.executePendingBindings()

        }
    }


    fun addDataList(list: List<Users>) {
        dataList.clear()
        dataList.addAll(list)
        com.example.kotlin30days.utility.Logger.setLog(message = "${dataList.size} after add new ")
        notifyDataSetChanged()
    }


    fun getSelectedLogin(): LiveData<String> {
        return login
    }



}