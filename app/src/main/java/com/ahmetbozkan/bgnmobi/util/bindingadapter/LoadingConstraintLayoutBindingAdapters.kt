package com.ahmetbozkan.bgnmobi.util.bindingadapter

import androidx.databinding.BindingAdapter
import com.ahmetbozkan.bgnmobi.core.Failure
import com.ahmetbozkan.bgnmobi.cv.LoadingConstraintLayout
import com.ahmetbozkan.bgnmobi.util.extensions.hideView
import com.ahmetbozkan.bgnmobi.util.extensions.showView

@BindingAdapter("isVisible")
fun setProgressVisibility(view: LoadingConstraintLayout, isVisible: Boolean) {
    if (isVisible)
        view.progressBar.showView()
    else
        view.progressBar.hideView()
}

@BindingAdapter("error")
fun setErrorFields(view: LoadingConstraintLayout, error: Failure?) {
    error?.let {

    }
}