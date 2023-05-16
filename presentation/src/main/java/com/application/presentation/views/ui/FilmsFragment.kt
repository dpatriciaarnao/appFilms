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

class FilmsFragment() : FilmFragment() {
    private lateinit var binding: FragmentFilmsBinding
    private val rootViewModel: FilmsViewModel by viewModels()
    private lateinit var filmsAdapter: FilmsAdapterList
    private var paramPosition: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initialize()
        return binding.root
    }


    fun initialize() {

        // Se trae el argumento desde el adapter con la position y dependiendo de ella se coloca en la variable en el xml el texto.
        arguments?.let {
            paramPosition = it.getInt(POSITION)
            when (paramPosition) {
                0 -> {
                    binding.label = "All movies and series"
                }

                1 -> {
                    binding.label = "Movies"
                }

                2 -> {
                    binding.label = "Series"
                }

                else -> {
                    binding.label = "All movies and series"
                }
            }
        }

        // Se cargan las peliculas y series desde el API
        initObserver()
        rootViewModel.loadDataMovies()

        //Se instancia el adapter y se coloca un ejemplo si el elemento es seleccionado en la lista para traer el detalle.
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

        // Se rellena el adapter
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
                    //Se valida la position para saber que tabs se invocará y la información que se filtrará.
                    when (paramPosition) {
                        0 -> {
                            filmsAdapter.submitList(it.films)
                        }

                        1 -> {
                            filmsAdapter.submitList(it.films.filter { it.type == "MOVIE" })
                        }

                        2 -> {
                            filmsAdapter.submitList(it.films.filter { it.type == "SERIES" })
                        }

                        else -> {
                            filmsAdapter.submitList(it.films)
                        }
                    }

                }
            }
        }
    }

    companion object {
        //Se trae el parámetro position
        private const val POSITION = "position"
        val TAG = FilmsFragment::javaClass.name
        fun newInstance(position: Int) = FilmsFragment().also {
            it.arguments = Bundle().also { b -> b.putInt(POSITION, position) }
        }
    }
}