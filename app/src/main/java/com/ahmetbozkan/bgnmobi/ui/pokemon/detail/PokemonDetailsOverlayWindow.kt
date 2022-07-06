package com.ahmetbozkan.bgnmobi.ui.pokemon.detail

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.databinding.DialogPokemonOverlayDetailsBinding
import com.ahmetbozkan.bgnmobi.domain.model.PokemonDetailEntity
import javax.inject.Inject

class PokemonDetailsOverlayWindow @Inject constructor(
    private val context: Context,
    private val click: () -> Unit
) {

    private val windowManager: WindowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    private lateinit var layoutParams: WindowManager.LayoutParams

    private val binding: DialogPokemonOverlayDetailsBinding by lazy {
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_pokemon_overlay_details,
            null,
            false
        )
    }

    private var isShowing = false

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            ).apply {
                gravity = Gravity.CENTER
            }
        }
    }

    fun setPokemon(pokemon: PokemonDetailEntity) = with(binding) {
        this.pokemon = pokemon

        btnCloseWindow.setOnClickListener {
            click.invoke()
        }
    }

    fun show() {
        dismiss()
        isShowing = true
        windowManager.addView(binding.root, layoutParams)
    }

    fun dismiss() {
        if (isShowing) {
            windowManager.removeView(binding.root)
            isShowing = false
        }
    }

}