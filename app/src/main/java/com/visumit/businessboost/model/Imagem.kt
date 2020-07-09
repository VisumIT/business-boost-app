package com.visumit.businessboost.model

class Imagem {

    var fileName: String = ""
    var mimetype: String = ""
    var base64 : String = ""

    override fun toString(): String {
        return "Image(fileName='$fileName', mimeType='$mimetype', base64='$base64')"
    }

}