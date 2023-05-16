package com.application.presentation.views.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.application.presentation.views.viewmodels.FilmsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class FilmActivity(
) : AppCompatActivity() {

    private val viewModel: FilmsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Este método sirve para manejar el volumen del dispositivo
        when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
            }
            KeyEvent.KEYCODE_VOLUME_UP -> {
            }
            else -> return super.onKeyDown(keyCode, event)
        }
        return true
    }
}
