package com.example.maksim.stmp

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class Lab2Fragment : Fragment() {

    private lateinit var editButton: Button
    private lateinit var text: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.lab2_fragment, container, false)

        editButton = view.findViewById(R.id.lab2_edit_button) as Button
        text = view.findViewById(R.id.lab2_text) as TextView

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editButton.setOnClickListener(this::onEditPress)
    }

    fun onEditPress(button: View) {
        val intent = Intent(context, Lab2SubActivity::class.java)
        val message = text.text.toString()
        intent.putExtra(LAB2_TEXT_ATTRIBUTE, message)
        startActivityForResult(intent, LAB2_EDIT_REQUEST)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == LAB2_EDIT_REQUEST) {
            if (resultCode == RESULT_OK && data != null) {
                val message = data.getStringExtra(LAB2_TEXT_ATTRIBUTE)
                text.setText(message)
            }
        }
    }
}