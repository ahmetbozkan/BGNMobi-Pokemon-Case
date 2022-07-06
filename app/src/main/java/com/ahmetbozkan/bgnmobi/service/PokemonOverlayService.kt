package com.ahmetbozkan.bgnmobi.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.ahmetbozkan.bgnmobi.domain.model.PokemonDetailEntity
import com.ahmetbozkan.bgnmobi.ui.pokemon.detail.PokemonDetailsOverlayWindow
import com.ahmetbozkan.bgnmobi.util.Constants

private const val OVERLAY_NOTIFICATION_CHANNEL_ID = "com.ahmetbozkan.bgnmobi_overlay_channel_id"
private const val OVERLAY_NOTIFICATION_CHANNEL_NAME = "com.ahmetbozkan.bgnmobi_overlay_channel_name"

class PokemonOverlayService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    private lateinit var dialog: PokemonDetailsOverlayWindow

    override fun onCreate() {
        super.onCreate()
        dialog = PokemonDetailsOverlayWindow(this) {
            dialog.dismiss()
            stopSelf()
        }

        val foreground = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) getCustomForeground()
        else Notification()

        startForeground(1, foreground)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val pokemon: PokemonDetailEntity? = intent?.getParcelableExtra(Constants.POKEMON)

        pokemon?.let {
            dialog.run {
                dialog.setPokemon(pokemon)
                dialog.show()
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCustomForeground(): Notification {
        val channel = NotificationChannel(
            OVERLAY_NOTIFICATION_CHANNEL_ID,
            OVERLAY_NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_MIN
        )

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

        return Notification.Builder(this, OVERLAY_NOTIFICATION_CHANNEL_ID).run {
            setOngoing(true)
            build()
        }
    }
}