package com.visumit.businessboost.utils

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText

class Mask {
    companion object {
        private fun replaceChars(cpfFull : String) : String{
            return cpfFull.replace(".", "").replace("-", "")
                .replace("(", "").replace(")", "")
                .replace("/", "").replace(" ", "")
                .replace("*", "")
        }


        fun mask(mask : String, etCpf : TextInputEditText) : TextWatcher {

            val textWatcher : TextWatcher = object : TextWatcher {
                var isUpdating : Boolean = false
                var oldString : String = ""
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val str = replaceChars(s.toString())
                    var cpfWithMask = ""

                    if (count == 0)//is deleting
                        isUpdating = true

                    if (isUpdating){
                        oldString = str
                        isUpdating = false
                        return
                    }

                    var i = 0
                    for (m : Char in mask.toCharArray()){
                        if (m != '#' && str.length > oldString.length){
                            cpfWithMask += m
                            continue
                        }
                        try {
                            cpfWithMask += str.get(i)
                        }catch (e : Exception){
                            break
                        }
                        i++
                    }

                    isUpdating = true
                    etCpf.setText(cpfWithMask)
                    etCpf.setSelection(cpfWithMask.length)

                }

                override fun afterTextChanged(editable: Editable) {

                }
            }

            return textWatcher
        }
    }
}