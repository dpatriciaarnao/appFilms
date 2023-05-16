package com.application.presentation.views.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.application.appmovies.presentation.R
import com.application.appmovies.presentation.databinding.FragmentFilmsBinding
import com.application.entities.Film
import com.application.presentation.views.adapters.FilmsAdapterList
import com.application.presentation.views.base.FilmFragment
import com.application.presentation.views.viewmodels.FilmViewState
import com.application.presentation.views.viewmodels.FilmsViewModel

class MoviesFragment : FilmFragment() {
    private lateinit var binding: FragmentFilmsBinding
    private val rootViewModel: FilmsViewModel by viewModels()
    private lateinit var filmsAdapter: FilmsAdapterList

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.label = "Movies"
        initialize()
        return binding.root
    }

    fun initialize() {

        initObserver()
        rootViewModel.loadDataMovies()

        filmsAdapter = FilmsAdapterList(
            object : FilmsAdapterList.FilmsAdapterListListener {
                override fun onClickDetail(film: Film) {
                    val filmSelected = film.name
                    val fragmentManager = getFilmActivity().supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(
                        R.id.container,
                        FilmDetailFragment.newInstance(filmSelected)
                    ).commit()
                }
            }
        )

        binding.recyclerViewFilm.adapter = filmsAdapter
        binding.recyclerViewFilm.addItemDecoration(
            DividerItemDecoration(
                getFilmActivity(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    fun initObserver() {
        rootViewModel.viewState.observe(this) {
            when (it) {
                is FilmViewState.LoadData.Failure -> {
                    //TODO
                }

                FilmViewState.LoadData.Processing -> {
                    //TODO
                }

                is FilmViewState.LoadData.Success -> {
                    filmsAdapter.submitList(it.films.filter { it.type == "MOVIE" })
                }
            }
        }
    }

    companion object {
        private const val POSITION = "position"
        val TAG = AllFilmsFragment::javaClass.name
        fun newInstance(position: Int) = AllFilmsFragment().also {
            it.arguments = Bundle().also { b -> b.putInt(POSITION, position) }
        }
    }
}