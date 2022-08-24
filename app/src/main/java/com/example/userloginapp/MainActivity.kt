package com.example.userloginapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.userloginapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.system.exitProcess
import android.view.LayoutInflater as LayoutInflater1

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var attempts: Int = 1
    private val username = arrayOf<String>("JanM","JoseT","SharT","test4")
    private val password = arrayOf<String>("12345","1234","123456","1236")
    private var userfound: Boolean = false
    private var passfound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnLogin.setOnClickListener{
            val user = binding.UserName.text
            val pass = binding.Password.text

            if(attempts <= 3) {
                if(user.toString().equals(username[0], true) && pass.toString().equals(password[0],true) ||
                    user.toString().equals(username[1], true) && pass.toString().equals(password[1],true) ||
                    user.toString().equals(username[2], true) && pass.toString().equals(password[2],true) ||
                    user.toString().equals(username[3], true) && pass.toString().equals(password[3],true)){
                    userfound = true
                    passfound = true
                }
            }
            else{
                Toast.makeText(this, "You have reached a maximum of three (3) invalid login attempts!", Toast.LENGTH_SHORT).show()
                exitProcess(-1)

            }


            if(userfound){
                if(passfound){
                    val snack = Snackbar.make(it, "Hi, $user, Welcome to Android-Kotlin", Snackbar.LENGTH_LONG)
                    snack.setAction("Show Details...") { displayToast() }
                        .show()
                    attempts = 1
                    passfound = false
                }
                else{
                    addTextView("Invalid Attempt number $attempts: ${Calendar.getInstance().time}")
                    attempts++
                }
                userfound = false
            }
            else{
                addTextView("Invalid Attempt number $attempts: ${Calendar.getInstance().time}")
                attempts++
            }
        }
    }

    private fun displayToast() {
        Toast.makeText(this, "Login Successful: ${Calendar.getInstance().time}", Toast.LENGTH_SHORT).show()
    }
    private fun addTextView(text: String){
        val textview1 = TextView(this)
        textview1.text = text
        textview1.textSize = 16f
        textview1.textAlignment = View.TEXT_ALIGNMENT_CENTER
        binding.layout1.addView(textview1)
    }
}