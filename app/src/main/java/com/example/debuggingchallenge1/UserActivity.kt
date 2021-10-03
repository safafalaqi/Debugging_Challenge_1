package com.example.debuggingchallenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.view.isVisible

class UserActivity : AppCompatActivity() {
    private lateinit var activeUsers : TextView
    override fun onCreate ( savedInstanceState : Bundle ?) {
        super.onCreate( savedInstanceState )
        setContentView(R.layout.activity_user)
        val bundle = intent.extras
        var valueReceived = bundle!!.getStringArrayList("users")
        activeUsers = findViewById(R.id.tvActiveUsers)
        var allUsers = "Active Users:\n\n"

        val iter: Iterator<String> = valueReceived!!.iterator()
        while (iter.hasNext()) {
            allUsers+= iter.next()+"\n"
        }
        activeUsers.text=allUsers

    }
}