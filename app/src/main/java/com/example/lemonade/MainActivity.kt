package com.example.lemonade // ここは元のファイルの一番上と同じにしてね

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.messageInput)
        val button = findViewById<Button>(R.id.sendButton)
        val display = findViewById<TextView>(R.id.messageDisplay)

        button.setOnClickListener {
            val text = input.text.toString()
            if (text.isNotEmpty()) {
                display.append("\nみず: $text")
                input.text.clear()
            }
        }
    }
}
