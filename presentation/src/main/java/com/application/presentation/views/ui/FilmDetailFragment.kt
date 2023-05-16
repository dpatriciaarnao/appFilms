package com.application.presentation.views.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import com.application.appmovies.presentation.databinding.FragmentMovieDetailBinding
import com.application.presentation.views.base.FilmFragment
import com.application.presentation.views.viewmodels.FilmsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FilmDetailFragment : FilmFragment(), ListFilmActivity.IOnBackPressed {

    private lateinit var binding: FragmentMovieDetailBinding
    private val rootViewModel: FilmsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isInterceptBackPress: Boolean = true
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(isInterceptBackPress){
                    //  do your work here
                }else{
                    isEnabled = false
                    activity?.onBackPressed()
                }
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        initVariables()
        return binding.root
    }

    private fun initVariables() {
        //TODO

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*binding.inclCatDetail.ivBackbutton.setOnClickListener {
            activity?.onBackPressed()
        }*/
    }

    companion object {
        private const val FILM_SELECTED = "filmSelected"
        val TAG = FilmDetailFragment::javaClass.name
        fun newInstance(filmSelected: String): FilmDetailFragment =
            FilmDetailFragment().also {
                it.arguments = Bundle().also { b -> b.putString(FILM_SELECTED, filmSelected) }
            }
    }

    override fun onBackPressed(): Boolean {
        TODO("Not yet implemented")
    }
}
