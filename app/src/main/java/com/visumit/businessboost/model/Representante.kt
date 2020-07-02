package com.visumit.businessboost.model

import java.util.ArrayList

class Representante {

    var name: String = ""
    var email: String = ""
    var password: String = ""
    var photograph: String = ""
    var phones = ArrayList<Number>()
    var cpf: String = ""
    var dateOfBirth: String = ""
    var gender: String = ""
    var description: String = ""

    override fun toString(): String {
        return "Representante(name='$name', email='$email', password='$password', photograph='$photograph', phones=$phones, cpf='$cpf', dateOfBirth='$dateOfBirth', gender='$gender', description='$description')"
    }
}

class Number {
    var number = ""
}