package com.unicorn.forensic2.data

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.baseUrl
import kotlinx.android.synthetic.main.activity_simple_player.*

class SimplePlayer : AppCompatActivity() {
    var orientationUtils: OrientationUtils? = null

    private val mp4Id by lazy { intent.getSerializableExtra(Param) as String }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_player)
        init()
    }

    private fun init() {
//        var source1 = file.absolutePath
        val source1 = "${baseUrl}sysFileinfo/download/$mp4Id"
        videoPlayer!!.setUp(source1, true, "摇号回放")

        //增加封面
//        ImageView imageView = new ImageView(this);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(R.mipmap.xxx1);
//        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer!!.titleTextView.visibility = View.VISIBLE
        //设置返回键
        videoPlayer!!.backButton.visibility = View.VISIBLE
        //设置旋转
        orientationUtils = OrientationUtils(this, videoPlayer)
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer!!.fullscreenButton
            .setOnClickListener { orientationUtils!!.resolveByClick() }
        //是否可以滑动调整
        videoPlayer!!.setIsTouchWiget(true)
        //设置返回按键功能
        videoPlayer!!.backButton.setOnClickListener { onBackPressed() }
        videoPlayer!!.startPlayLogic()
    }

    override fun onPause() {
        super.onPause()
        videoPlayer!!.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        videoPlayer!!.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        if (orientationUtils != null) orientationUtils!!.releaseListener()
    }

    override fun onBackPressed() {
        //先返回正常状态
        if (orientationUtils!!.screenType == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer!!.fullscreenButton.performClick()
            return
        }
        //释放所有
        videoPlayer!!.setVideoAllCallBack(null)
        super.onBackPressed()
    }
}