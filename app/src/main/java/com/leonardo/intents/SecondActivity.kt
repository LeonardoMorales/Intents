package com.leonardo.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_second.textInputEditTextEmail
import kotlinx.android.synthetic.main.activity_second.textInputLayoutEmail

class SecondActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val bundle: Bundle? = intent.extras
        val email = bundle?.getString(getString(R.string.key_email))

        textInputEditTextEmail.setText(email)

        btnSendExplicit.setOnClickListener {
            if (isValidInputs()) {
                Log.d(TAG, "Correo creado exitosamente...Enviando")
                sendEmail()
            }
        }

    }

    private fun sendEmail() {
        val recipients = textInputEditTextEmail.text.toString()
        val recipientsList: Array<String> = recipients.split(",").toTypedArray()

        val subject = textInputEditTextSubject.text.toString()
        val message = textInputEditTextBody.text.toString()

        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_EMAIL, recipientsList)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)

        intent.type = "message/rfc822"
        startActivity(Intent.createChooser(intent, "Selecciona un aplicación"))
    }

    private fun isValidInputs(): Boolean {
        return isValidEmailInput() && isValidSubjectInput() && isValidBodyInput()
    }

    private fun isValidEmailInput():Boolean {
        if(textInputEditTextEmail.text.isNullOrBlank()){
            textInputLayoutEmail.isErrorEnabled = true
            textInputLayoutEmail.error = "Digite el correo destinatario"
            return false
        } else {
            textInputLayoutEmail.isErrorEnabled = false
            return true
        }
    }

    private fun isValidSubjectInput():Boolean {
        if(textInputEditTextSubject.text.isNullOrBlank()){
            textInputLayoutSubject.isErrorEnabled = true
            textInputLayoutSubject.error = "Digite el asunto del mensaje"
            return false
        } else {
            textInputLayoutSubject.isErrorEnabled = false
            return true
        }
    }

    private fun isValidBodyInput():Boolean {
        if(textInputEditTextBody.text.isNullOrBlank()){
            textInputLayoutBody.isErrorEnabled = true
            textInputLayoutBody.error = "Digite el cuerpo del mensaje"
            return false
        } else {
            textInputLayoutBody.isErrorEnabled = false
            return true
        }
    }
}
