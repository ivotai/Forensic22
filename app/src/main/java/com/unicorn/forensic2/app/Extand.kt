package com.unicorn.forensic2.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ToastUtils
import com.jakewharton.rxbinding3.view.clicks
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.iconics.utils.colorRes
import com.unicorn.forensic2.R
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import florent37.github.com.rxlifecycle.RxLifecycle.disposeOnDestroy
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime
import java.util.concurrent.TimeUnit

fun View.safeClicks(): Observable<Unit> = this.clicks()
    .throttleFirst(2000, TimeUnit.MILLISECONDS)

fun TextView.trimText() = this.text.toString().trim()

fun <T> Single<T>.observeOnMain(lifecycleOwner: LifecycleOwner): Single<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(disposeOnDestroy(lifecycleOwner))

fun <T> Observable<T>.observeOnMain(lifecycleOwner: LifecycleOwner): Observable<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(disposeOnDestroy(lifecycleOwner))

fun RecyclerView.addDefaultItemDecoration(size: Int) {
    HorizontalDividerItemDecoration.Builder(context)
        .colorResId(R.color.md_grey_300)
        .size(size)
        .build().let { this.addItemDecoration(it) }
}

fun Context.startAct(actClass: Class<*>) {
    this.startActivity(Intent(this, actClass))
}

fun Fragment.startAct(actClass: Class<*>) {
    this.context?.startAct(actClass)
}

fun Context.toActAndFinish(actClass: Class<*>) {
    this.startActivity(Intent(this, actClass))
    this as Activity
    this.finish()
}

fun TextView.isEmpty(): Boolean {
    return this.trimText().isEmpty()
}

fun TextView.isNotBlack(): Boolean {
    return this.trimText().isNotBlank()
}

fun String.toast() {
    ToastUtils.showShort(this)
}

fun Long.toDateTime(): DateTime = DateTime(this)

fun Long.toDisplayFormat2(default:String=""): String {
    if (this == 0L) return default
    return this.toDateTime().toString(displayDateFormat2)
}
fun Long.toDisplayFormat(): String {
    if (this == 0L) return ""
    return this.toDateTime().toString(displayDateFormat)
}

fun ImageView.setIIcon(context: Context, iIcon: IIcon, colorResId: Int) {
    IconicsDrawable(
        context,
        iIcon
    ).apply {
        colorRes = colorResId
    }.let { this.setImageDrawable(it) }
}

