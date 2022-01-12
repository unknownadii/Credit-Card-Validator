package com.example.spot.creditcardinputexercise

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_design.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit.setOnClickListener {
            if (checkCvv() == true && checkValidation() == true
                && checkDate()== true) {
                customDialogForBackButton()
            }
        }
    }

    private fun checkDate():Boolean {
        val date = creditCardDate.text.toString()
        val valid = date[2]
        if (valid.equals("/")) {
                if (date[1].toInt() == 0 && date[0].toInt() == 0)
                {
                    return false
                    Toast.makeText(this, "Incorrect Date Format", Toast.LENGTH_SHORT).show()
                }
            return true
        }
        else{
            Toast.makeText(this, "Incorrect Date Format", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    private fun checkCvv(): Boolean {
        val cvv = creditCardNCvv.text.toString()
        if (cvv.length >= 3 && cvv.length <= 4) {
            return true
        } else {
            Toast.makeText(this, "Invalid CVV", Toast.LENGTH_SHORT).show()
            return false
        }
        return false
    }

    private fun checkValidation(): Boolean {
        val cardNumber = creditCardNumber.text.toString()
        if (CreditValidator.isValid(cardNumber.toLong())) {
            return true
        } else {
            Toast.makeText(
                this, "Invalid Credit CardNumber",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return false
    }

    private fun customDialogForBackButton() {
        val customDialog = Dialog(this)
        /*Set the screen content from a layout resource.
         The resource will be inflated, adding all top-level views to the screen.*/
        customDialog.setContentView(R.layout.dialog_design)
        customDialog.ok.setOnClickListener {
            creditCardNumber.text?.clear()
            creditCardNCvv.text?.clear()
            creditCardDate.text?.clear()
            creditCardFirstName.text?.clear()
            creditCardLastName.text?.clear()
            customDialog.dismiss() // Dialog will be dismissed
        }

        customDialog.show()
    }
}