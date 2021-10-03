package com.example.debuggingchallenge1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var llMain: LinearLayout
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var password2: EditText
    private lateinit var submitBtn: Button
    private lateinit var tvLength:TextView
    private lateinit var tvUpper:TextView
    private lateinit var tvNumber:TextView
    private lateinit var tvSpecialchar:TextView
    private lateinit var tvUserChar:TextView
    private lateinit var tvSpace:TextView
    private lateinit var tvNoNumber:TextView
    private lateinit var  passwordInput:EditText
    private lateinit var  repasswordInput:EditText
    private lateinit var  NameInput:EditText



    var users = arrayListOf(
        "Freddy",
        "Jason",
        "Ripley",
        "Poncho",
        "Saitama",
        "Archer",
        "Derek",
        "Pamela",
        "Simba",
        "Simon",
        "Retsy",
        "Peter",
        "Daria",
        "Smitty"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llMain = findViewById(R.id.llMain)
        userName = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)
        password2 = findViewById(R.id.etConfirmPassword)
        submitBtn = findViewById(R.id.btSubmit)
        submitBtn.setOnClickListener {
            if(usernameAccepted(userName.text.toString()) && passwordAccepted(password.text.toString())){
                Toast.makeText(this, "User created!", Toast.LENGTH_LONG).show()
                users.add(userName.text.toString().capitalize())
                displayUsers()
                resetall()
            }
        }

    }

    private fun resetall() {
        tvUserChar.setTextColor(Color.BLACK)
        tvNoNumber.setTextColor(Color.BLACK)
        tvSpace.setTextColor(Color.BLACK)
        tvLength.setTextColor(Color.BLACK)
        tvUpper.setTextColor(Color.BLACK)
        tvNumber.setTextColor(Color.BLACK)
        tvSpecialchar.setTextColor(Color.BLACK)
        passwordInput=findViewById(R.id.etUsername)
        passwordInput.text.clear()
        NameInput=findViewById(R.id.etPassword)
        NameInput.text.clear()
        repasswordInput=findViewById(R.id.etConfirmPassword)
        repasswordInput.text.clear()
    }

    private fun usernameAccepted(text: String): Boolean{
        if(text.lowercase().capitalize() !in users){
            if(text.length in 5..15){
                tvUserChar=findViewById(R.id.tvUserChar)
                tvUserChar.setTextColor(Color.GREEN)
                if(!hasNumber(text)){
                    tvNoNumber=findViewById(R.id.tvNoNumber)
                    tvNoNumber.setTextColor(Color.GREEN)
                    if(!hasSpecial(text) && !text.contains(" ")){
                        tvSpace=findViewById(R.id.tvSpace)
                        tvSpace.setTextColor(Color.GREEN)
                        return true
                    }else{
                        tvSpace=findViewById(R.id.tvlength)
                        tvSpace.setTextColor(Color.RED)
                    Toast.makeText(this, "The username cannot contain special characters or spaces", Toast.LENGTH_SHORT).show()}
                }else{
                    tvNoNumber=findViewById(R.id.tvNoNumber)
                    tvNoNumber.setTextColor(Color.RED)
                    Toast.makeText(this, "The username cannot contain numbers", Toast.LENGTH_SHORT).show()}
            }else{
                tvUserChar=findViewById(R.id.tvUserChar)
                tvUserChar.setTextColor(Color.RED)
            Toast.makeText(this, "The username must be between 5 and 15 characters long", Toast.LENGTH_SHORT).show()}
        }else{
        Toast.makeText(this, "The username is already taken", Toast.LENGTH_SHORT).show()}
        return false
    }

    private fun passwordAccepted(text: String): Boolean{
        if(text.length >= 8){
            tvLength=findViewById(R.id.tvlength)
            tvLength.setTextColor(Color.GREEN)
            if(hasUpper(text)){
                if(hasNumber(text)){
                    if(hasSpecial(text)){
                        return true
                    }else{
                   Toast.makeText(this, "The password must contain a special character", Toast.LENGTH_SHORT).show()}
                }else{
               Toast.makeText(this, "The password must contain a number", Toast.LENGTH_SHORT).show()}
            }else{
           Toast.makeText(this, "The password must contain an uppercase letter", Toast.LENGTH_SHORT).show()}
        }
        else{
            tvLength=findViewById(R.id.tvlength)
            tvLength.setTextColor(Color.RED)

        Toast.makeText(this, "The password must be at least 8 characters long", Toast.LENGTH_SHORT).show()
            }
        return false
    }

    private fun hasUpper(text: String): Boolean{
        var letter = 'A'
        while (letter <= 'Z') {
            if(text[0] == letter){
                tvUpper=findViewById(R.id.tvUpperCase)
                tvUpper.setTextColor(Color.GREEN)
                return true
            }
            ++letter
        }
        tvUpper=findViewById(R.id.tvUpperCase)
        tvUpper.setTextColor(Color.RED)
        return false
    }

    private fun hasNumber(text: String): Boolean{
        for(i in 0..9){
            if(text.contains(i.toString())){
                tvNumber=findViewById(R.id.tvNumber)
                tvNumber.setTextColor(Color.GREEN)
                return true
            }
        }
        tvNumber=findViewById(R.id.tvNumber)
        tvNumber.setTextColor(Color.RED)
        return false
    }

    private fun hasSpecial(text: String): Boolean{
        val specialCharacters = "!@#$%"
        for(special in specialCharacters){
            if(text.contains(special)){
                tvSpecialchar=findViewById(R.id.tvSpecialChar)
                tvSpecialchar.setTextColor(Color.GREEN)
                return true
            }
        }
        tvSpecialchar=findViewById(R.id.tvSpecialChar)
        tvSpecialchar.setTextColor(Color.RED)
        return false
    }

    private fun displayUsers(){
        //to display users in different activity
        val intent = Intent(this, UserActivity::class.java)
        intent.putStringArrayListExtra("users", users)
        startActivity(intent)
    }
}