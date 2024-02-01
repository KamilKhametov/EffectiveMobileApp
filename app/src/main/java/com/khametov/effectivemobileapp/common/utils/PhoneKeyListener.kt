package com.khametov.effectivemobileapp.common.utils

import android.text.method.DialerKeyListener

class PhoneKeyListener : DialerKeyListener() {
    companion object {
        private val CHARACTERS = charArrayOf('0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9' , '#' , '*' , '+' , '-' , '(' , ')' , ',' , '/' , 'N' , '.' , ' ' , ';' , '_')
    }

    override fun getAcceptedChars() : CharArray {
        return CHARACTERS
    }
}