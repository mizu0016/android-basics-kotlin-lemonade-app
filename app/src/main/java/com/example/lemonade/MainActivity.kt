package com.example.lemonade

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // さっき作った activity_main.xml を画面に映すよ
        setContentView(R.layout.activity_main)
        
        // ここから先、ゲームを動かすタイマーとかを
        // 少しずつ追加して「完全なテトリス」にしていこう！
    }
}
