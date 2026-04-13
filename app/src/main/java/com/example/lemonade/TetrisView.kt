package com.example.lemonade

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

class TetrisView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    private val rows = 20
    private val cols = 10
    private var board = Array(rows) { IntArray(cols) { 0 } }
    
    // 1%ブランドを意識した水色と黒の配色にしてみたよ！
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val blockSize = width / cols.toFloat()

        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (board[r][c] == 1) {
                    paint.color = Color.parseColor("#0288D1") // 瑞希ブルー
                } else {
                    paint.color = Color.DKGRAY
                }
                canvas.drawRect(c * blockSize + 1, r * blockSize + 1, 
                               (c + 1) * blockSize - 1, (r + 1) * blockSize - 1, paint)
            }
        }
    }
}
