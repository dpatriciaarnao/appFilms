package com.application.presentation.views.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.application.appmovies.presentation.R
import com.application.appmovies.presentation.databinding.ActivitySplashBinding
import com.application.presentation.views.base.FilmActivity
import com.application.presentation.views.ui.ListFilmActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : FilmActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.lifecycleOwner = this

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ListFilmActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
