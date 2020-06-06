package com.example.kotlin30days.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin30days.R
import com.example.kotlin30days.data.local.model.UserRepo
import com.example.kotlin30days.databinding.RvUserRepoUrlBinding
import java.util.logging.Logger

class RvAdapterRepos : RecyclerView.Adapter<RvAdapterRepos.ReposVH>() {

    private var dataList = mutableListOf<UserRepo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposVH {
        val binding: RvUserRepoUrlBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.rv_user_repo_url, parent, false
        )
        return ReposVH(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ReposVH, position: Int) {
        holder.onBindData(dataList[position])
    }


    inner class ReposVH(val binding: RvUserRepoUrlBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBindData(
            item: UserRepo
        ) {
            binding.item = item
            binding.executePendingBindings()

        }

    }


    fun addDataList(list: List<UserRepo>) {
        com.example.kotlin30days.utility.Logger.setLog(message = "${list.size}  yep list added ")
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }



}