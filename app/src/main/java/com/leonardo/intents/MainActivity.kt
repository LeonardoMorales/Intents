package com.leonardo.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSend.setOnClickListener {
            if(isValidInput()) {
                passToNextActivity()
            }
        }

    }

    private fun passToNextActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        val email: String = textInputEditTextEmail.text.toString()
        Log.d(TAG, "CORREO: $email")
        intent.putExtra(getString(R.string.key_email), email)
        startActivity(intent)
    }

    private fun isValidInput(): Boolean {
        if(textInputEditTextEmail.text.isNullOrBlank()){
            textInputLayoutEmail.isErrorEnabled = true
            textInputLayoutEmail.error = "Digite el correo destinatario"
            return false
        } else {
            if(isValidEmailFormat(textInputEditTextEmail.text.toString())) {
                textInputLayoutEmail.isErrorEnabled = false
                return true
            }
            else {
                textInputLayoutEmail.isErrorEnabled = true
                textInputLayoutEmail.error = "Correo electronico no válido"
                return false
            }
        }
    }

    private fun isValidEmailFormat(target: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}
