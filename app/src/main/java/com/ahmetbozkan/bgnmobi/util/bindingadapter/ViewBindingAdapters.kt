package com.ahmetbozkan.bgnmobi.util.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.ahmetbozkan.bgnmobi.util.extensions.gone
import com.ahmetbozkan.bgnmobi.util.extensions.invisible
import com.ahmetbozkan.bgnmobi.util.extensions.visible

@BindingAdapter(value = ["isVisible", "gone"], requireAll = false)
fun isVisible(view: View, isVisible: Boolean, gone: Boolean = false) {
    if(isVisible) view.visible()
    else {
        if(gone) view.gone()
        else view.invisible()
    }
}