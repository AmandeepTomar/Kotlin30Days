package com.example.kotlin30days.repo

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

object LoginRepo {

    fun login(
        username: String,
        password: String,
        auth: FirebaseAuth
    ): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(username,password)

    }

    fun register(username: String,password: String,auth: FirebaseAuth): Task<AuthResult> {
        return  auth.createUserWithEmailAndPassword(username, password)
    }

    fun logout(auth: FirebaseAuth){
        return auth.signOut()
    }

}