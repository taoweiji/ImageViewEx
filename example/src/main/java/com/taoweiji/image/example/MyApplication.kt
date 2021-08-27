package com.taoweiji.image.example

import android.app.Application
import com.facebook.drawee.view.SimpleDraweeView

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SimpleDraweeView.setLoadHandler { imageView, url ->
            // 实现图片显示
        }
    }
}