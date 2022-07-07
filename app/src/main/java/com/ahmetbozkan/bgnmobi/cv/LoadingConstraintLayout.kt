package com.ahmetbozkan.bgnmobi.cv

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.util.extensions.gone
import com.ahmetbozkan.bgnmobi.util.extensions.invisible
import com.ahmetbozkan.bgnmobi.util.extensions.visible

class LoadingConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val progressBar: ProgressBar

    private val errorRoot: ConstraintLayout
    private val tvError: AppCompatTextView
    private val imgWarning: AppCompatImageView
    private val btnRetry: AppCompatButton
    private val errorLoading: ProgressBar


    init {
        inflate(context, R.layout.custom_loading_constraint_layout, this)

        progressBar = findViewById(R.id.progress_bar)

        errorRoot = findViewById(R.id.layout_error_footer)
        tvError = errorRoot.findViewById(R.id.tv_error)
        imgWarning = errorRoot.findViewById(R.id.img_warning)
        btnRetry = errorRoot.findViewById(R.id.button_retry)
        errorLoading = errorRoot.findViewById(R.id.progress_bar)
    }

    fun onError(msg: String, retry: () -> Unit) {
        progressBar.gone()
        errorLoading.invisible()

        errorRoot.visible()
        tvError.text = msg

        btnRetry.setOnClickListener {
            retry.invoke()
        }

    }

    fun onLoading() {
        progressBar.visible()
        errorRoot.gone()
    }

    fun onSuccess() {
        progressBar.gone()
        errorRoot.gone()
    }
}