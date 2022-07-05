package com.ahmetbozkan.bgnmobi.util.bindingadapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.ahmetbozkan.bgnmobi.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("loadFromUrl")
fun loadImageFromUrl(view: AppCompatImageView, url: String?) {
    val circularProgressDrawable = CircularProgressDrawable(view.context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }

    val options: RequestOptions = RequestOptions()
        .placeholder(circularProgressDrawable)
        .error(R.drawable.ic_warning)
        .priority(Priority.HIGH)

    if (url.isNullOrBlank().not()) {
        Glide.with(view.context)
            .load(url)
            .apply(options)
            .into(view)

    }
}