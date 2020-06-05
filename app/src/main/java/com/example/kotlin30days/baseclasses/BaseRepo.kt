package com.example.kotlin30days.baseclasses

import com.example.kotlin30days.data.network.api.ApiInterface
import com.example.kotlin30days.utility.AppPref

open class BaseRepo(open val apiInterface:ApiInterface,open  val  pref:AppPref)