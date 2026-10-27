package com.example.bitpevent

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvDetailName = findViewById<TextView>(R.id.tvDetailName)
        val tvDetailEmail = findViewById<TextView>(R.id.tvDetailEmail)
        val tvDetailGender = findViewById<TextView>(R.id.tvDetailGender)
        val tvDetailSocial = findViewById<TextView>(R.id.tvDetailSocial)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val name = intent.getStringExtra("EXTRA_NAME") ?: ""
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        val gender = intent.getStringExtra("EXTRA_GENDER") ?: ""
        val social = intent.getStringExtra("EXTRA_SOCIAL") ?: ""

        tvDetailName.text = name
        tvDetailEmail.text = email
        tvDetailGender.text = gender
        tvDetailSocial.text = social

        btnBack.setOnClickListener {
            finish()
        }
    }
}
