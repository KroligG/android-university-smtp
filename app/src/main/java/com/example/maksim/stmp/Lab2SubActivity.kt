package com.example.maksim.stmp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText


class Lab2SubActivity : AppCompatActivity() {
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lab2_subactivity)

        val message = intent.getStringExtra(LAB2_TEXT_ATTRIBUTE)

        editText = findViewById(R.id.lab2_text_edit) as EditText
        editText.setText(message)
    }

    fun onSavePress(button: View) {
        val result = Intent(LAB2_RESULT_INTENT)

        val message = editText.text.toString()
        result.putExtra(LAB2_TEXT_ATTRIBUTE, message)

        setResult(Activity.RESULT_OK, result)
        finish()
    }
}
