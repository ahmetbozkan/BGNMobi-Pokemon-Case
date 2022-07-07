package com.ahmetbozkan.bgnmobi.cv

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.ahmetbozkan.bgnmobi.databinding.CustomLoadingConstraintLayoutBinding

class LoadingConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val progressBar: ProgressBar

    private val binding: CustomLoadingConstraintLayoutBinding =
        CustomLoadingConstraintLayoutBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        progressBar = binding.progressBar
    }
}