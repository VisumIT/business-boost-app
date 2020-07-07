package com.visumit.businessboost.model

class Login {

    var email: String = ""
    var password : String = ""

    override fun toString(): String {
        return "Login(email='$email', password='$password')"
    }
}