package com.mizu.eyecare

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // オーバーレイ権限（画面上に表示する権限）の確認
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            startActivityForResult(intent, 123)
        }

        // 1時間おきに実行されるWorkerを登録
        val workRequest = PeriodicWorkRequestBuilder<AlertWorker>(1, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "EyeCareWork",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
        
        // 登録が終わったらアプリ画面を閉じる
        finish()
    }
}
