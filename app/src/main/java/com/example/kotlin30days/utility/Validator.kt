package com.example.kotlin30days.utility

object Validator {

    fun getStringValidate(string: String): String {
        if (string.isNullOrEmpty()){
            return "Value can not be null or Empty"
        }
        if (string.isBlank()){
            return "Value can not be blank"
        }
        return SUCCESS
    }


    fun validateLogin(userName:String,password:String): String {
        if (userName.isEmpty())
            return "User Name can not be empty"
        if (userName.isBlank())
            return  "User Name can not ne blank"
        if (password.isEmpty())
            return "User Name can not be empty"
        if (password.isBlank())
            return  "User Name can not ne blank"
        if (password.length<6)
            return "Password must have 6 or more digits"
        return SUCCESS
    }
}