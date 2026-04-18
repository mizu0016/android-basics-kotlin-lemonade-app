package com.mizu.eyecare

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.TextView

class OverlayService : Service() {
    private lateinit var windowManager: WindowManager

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showOverlay()
        return START_NOT_STICKY
    }

    private fun showOverlay() {
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.BOTTOM
            y = 100 // 下から少し浮かせた位置
        }

        val textView = TextView(this).apply {
            text = "スマホを1時間連続でやっています　目を離しましょう"
            textSize = 20f
            setBackgroundColor(0x88000000.toInt()) // 半透明の黒背景
            setTextColor(0xFFFFFFFF.toInt()) // 白文字
            setPadding(20, 10, 20, 10)
            setSingleLine()
        }

        windowManager.addView(textView, params)

        // 右から左に流れるアニメーションの設定
        val anim = TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, 1.0f,  // 画面の右端から
            Animation.RELATIVE_TO_PARENT, -1.0f, // 画面の左端へ
            Animation.RELATIVE_TO_PARENT, 0f,
            Animation.RELATIVE_TO_PARENT, 0f
        ).apply {
            duration = 10000 // 10秒かけて流れる
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationEnd(animation: Animation?) {
                    // 流れ終わったらビューを消してサービスを止める
                    windowManager.removeView(textView)
                    stopSelf()
                }
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }
        textView.startAnimation(anim)
    }
}
