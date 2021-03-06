package com.unicorn.forensic2.ui.other

import android.content.Context
import android.graphics.Bitmap
import android.graphics.PointF
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.ImageViewTarget
import com.luck.picture.lib.engine.ImageEngine
import com.luck.picture.lib.listener.ImageCompleteCallback
import com.luck.picture.lib.tools.MediaUtils
import com.luck.picture.lib.widget.longimage.ImageSource
import com.luck.picture.lib.widget.longimage.ImageViewState
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView
import com.unicorn.forensic2.R

class GlideEngine private constructor() : ImageEngine {

    override fun loadImage( context: Context,  url: String,  imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }

    /**
     * 加载网络图片适配长图方案
     * # 注意：此方法只有加载网络图片才会回调
     *
     * @param context
     * @param url
     * @param imageView
     * @param longImageView
     * @param callback      网络图片加载回调监听
     */
    override fun loadImage(
        context: Context, url: String,
        imageView: ImageView,
        longImageView: SubsamplingScaleImageView, callback: ImageCompleteCallback?
    ) {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .into(object : ImageViewTarget<Bitmap>(imageView) {
                override fun onLoadStarted(placeholder: Drawable?) {
                    super.onLoadStarted(placeholder)
                    if (callback != null) {
                        callback.onShowLoading()
                    }
                }

                override fun onLoadFailed( errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    if (callback != null) {
                        callback.onHideLoading()
                    }
                }

                override fun setResource( resource: Bitmap?) {
                    if (callback != null) {
                        callback.onHideLoading()
                    }
                    if (resource != null) {
                        val eqLongImage = MediaUtils.isLongImg(
                            resource.getWidth(),
                            resource.getHeight()
                        )
                        longImageView.setVisibility(if (eqLongImage) View.VISIBLE else View.GONE)
                        imageView.setVisibility(if (eqLongImage) View.GONE else View.VISIBLE)
                        if (eqLongImage) {
                            // 加载长图
                            longImageView.setQuickScaleEnabled(true)
                            longImageView.setZoomEnabled(true)
                            longImageView.setPanEnabled(true)
                            longImageView.setDoubleTapZoomDuration(100)
                            longImageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP)
                            longImageView.setDoubleTapZoomDpi(SubsamplingScaleImageView.ZOOM_FOCUS_CENTER)
                            longImageView.setImage(
                                ImageSource.bitmap(resource),
                                ImageViewState(0f, PointF(0f, 0f), 0)
                            )
                        } else {
                            // 普通图片
                            imageView.setImageBitmap(resource)
                        }
                    }
                }
            })
    }


    override fun loadImage(
         context: Context,  url: String,
         imageView: ImageView,
        longImageView: SubsamplingScaleImageView
    ) {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .into(object : ImageViewTarget<Bitmap>(imageView) {
                override fun setResource( resource: Bitmap?) {
                    if (resource != null) {
                        val eqLongImage = MediaUtils.isLongImg(
                            resource.getWidth(),
                            resource.getHeight()
                        )
                        longImageView.setVisibility(if (eqLongImage) View.VISIBLE else View.GONE)
                        imageView.setVisibility(if (eqLongImage) View.GONE else View.VISIBLE)
                        if (eqLongImage) {
                            // 加载长图
                            longImageView.setQuickScaleEnabled(true)
                            longImageView.setZoomEnabled(true)
                            longImageView.setPanEnabled(true)
                            longImageView.setDoubleTapZoomDuration(100)
                            longImageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP)
                            longImageView.setDoubleTapZoomDpi(SubsamplingScaleImageView.ZOOM_FOCUS_CENTER)
                            longImageView.setImage(
                                ImageSource.bitmap(resource),
                                ImageViewState(0f, PointF(0f, 0f), 0)
                            )
                        } else {
                            // 普通图片
                            imageView.setImageBitmap(resource)
                        }
                    }
                }
            })
    }


    override fun loadFolderImage( context: Context,  url: String,  imageView: ImageView) {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .override(180, 180)
            .centerCrop()
            .sizeMultiplier(0.5f)
            .apply(RequestOptions().placeholder(R.drawable.picture_image_placeholder))
            .into(object : BitmapImageViewTarget(imageView) {
//                fun setResource(resource: Bitmap) {
//                    val circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(context.getResources(), resource)
//                    circularBitmapDrawable.setCornerRadius(8)
//                    imageView.setImageDrawable(circularBitmapDrawable)
//                }
            })
    }

    override fun loadAsGifImage(
         context: Context, url: String,
         imageView: ImageView
    ) {
        Glide.with(context)
            .asGif()
            .load(url)
            .into(imageView)
    }


    override fun loadGridImage( context: Context,  url: String,  imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .override(200, 200)
            .centerCrop()
            .apply(RequestOptions().placeholder(R.drawable.picture_image_placeholder))
            .into(imageView)
    }

    companion object {

        private var instance: GlideEngine? = null

        fun createGlideEngine(): GlideEngine {
            if (null == instance) {
                synchronized(GlideEngine::class.java) {
                    if (null == instance) {
                        instance = GlideEngine()
                    }
                }
            }
            return instance!!
        }
    }
}