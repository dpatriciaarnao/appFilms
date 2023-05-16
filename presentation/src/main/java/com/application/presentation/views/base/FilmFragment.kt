package com.application.presentation.views.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.application.presentation.views.viewmodels.FilmsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class FilmFragment : Fragment() {
    private val rootViewModel by viewModels<FilmsViewModel>()

    fun getFilmActivity(): FilmActivity = requireActivity() as FilmActivity

}
