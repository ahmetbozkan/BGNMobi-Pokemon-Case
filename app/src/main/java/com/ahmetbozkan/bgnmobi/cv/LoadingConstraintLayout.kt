package com.ahmetbozkan.bgnmobi.cv

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.ahmetbozkan.bgnmobi.R

class LoadingConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var progressBar: ProgressBar

    init {
        inflate(context, R.layout.custom_loading_constraint_layout, this)

        progressBar = findViewById(R.id.progress_bar)
    }
}