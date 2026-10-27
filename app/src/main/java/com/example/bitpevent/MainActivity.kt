package com.example.bitpevent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val cbFacebook = findViewById<CheckBox>(R.id.cbFacebook)
        val cbInstagram = findViewById<CheckBox>(R.id.cbInstagram)
        val cbTiktok = findViewById<CheckBox>(R.id.cbTiktok)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // Events for EditText (Thao tác / Focus)
        etFullName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Toast.makeText(this, "Đang thao tác ô Họ và tên", Toast.LENGTH_SHORT).show()
            }
        }
        etEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Toast.makeText(this, "Đang thao tác ô Email", Toast.LENGTH_SHORT).show()
            }
        }

        // Events for RadioGroup (Chọn giới tính)
        rgGender.setOnCheckedChangeListener { _, checkedId ->
            val rb = findViewById<RadioButton>(checkedId)
            rb?.let {
                Toast.makeText(this, "Đã chọn giới tính: ${it.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // Events for Checkboxes (Chọn mạng xã hội)
        cbFacebook.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "Facebook: ${if (isChecked) "Đã chọn" else "Bỏ chọn"}", Toast.LENGTH_SHORT).show()
        }
        cbInstagram.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "Instagram: ${if (isChecked) "Đã chọn" else "Bỏ chọn"}", Toast.LENGTH_SHORT).show()
        }
        cbTiktok.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "TikTok: ${if (isChecked) "Đã chọn" else "Bỏ chọn"}", Toast.LENGTH_SHORT).show()
        }

        // Register button click -> Navigate to DetailActivity with selected data
        btnRegister.setOnClickListener {
            val name = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập họ và tên!", Toast.LENGTH_SHORT).show()
                etFullName.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email!", Toast.LENGTH_SHORT).show()
                etEmail.requestFocus()
                return@setOnClickListener
            }

            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else {
                "Chưa chọn"
            }

            val socials = mutableListOf<String>()
            if (cbFacebook.isChecked) socials.add("Facebook")
            if (cbInstagram.isChecked) socials.add("Instagram")
            if (cbTiktok.isChecked) socials.add("TikTok")
            val socialStr = if (socials.isEmpty()) "Không có" else socials.joinToString(", ")

            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_NAME", name)
                putExtra("EXTRA_EMAIL", email)
                putExtra("EXTRA_GENDER", gender)
                putExtra("EXTRA_SOCIAL", socialStr)
            }
            startActivity(intent)
        }
    }
}
