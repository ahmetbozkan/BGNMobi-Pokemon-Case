package com.ahmetbozkan.bgnmobi.util.bindingadapter

import androidx.databinding.BindingAdapter
import com.ahmetbozkan.bgnmobi.core.Failure
import com.ahmetbozkan.bgnmobi.cv.LoadingConstraintLayout
import com.ahmetbozkan.bgnmobi.util.extensions.invisible
import com.ahmetbozkan.bgnmobi.util.extensions.visible

@BindingAdapter("isVisible")
fun setProgressVisibility(view: LoadingConstraintLayout, isVisible: Boolean) {
    if (isVisible)
        view.progressBar.visible()
    else
        view.progressBar.invisible()
}

@BindingAdapter("error")
fun setErrorFields(view: LoadingConstraintLayout, error: Failure?) {
    error?.let {

    }
}