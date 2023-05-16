package com.application.presentation.views.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.viewpager2.widget.ViewPager2
import com.application.appmovies.presentation.R
import com.application.appmovies.presentation.databinding.ActivityFilmBinding
import com.application.entities.Film
import com.application.presentation.views.adapters.FilmsAdapterList
import com.application.presentation.views.adapters.PageAdapterFragment
import com.application.presentation.views.base.FilmActivity
import com.application.presentation.views.viewmodels.FilmViewState
import com.application.presentation.views.viewmodels.FilmsViewModel
import com.google.android.material.tabs.TabLayout

//La aplicación está realizada con Clean arquitecture en capas por módulos:
// Tenemos el módulo de app que inicializa inyección de dependencias, la app y singleton.
// Tenemos el módulo de entities que son los data class del proyecto, las entidades con sus atributos.
// Tenemos el módulo presentation donde se encuentran los adapters, vistas, view models, layouts, etc.
// Tenemos el módulo repository con lo relacionado a BD, API services, DAO, Interfaces, DataSources, API responses, etc.
// Tenemos el módulo usecases donde se encuentran los casos de uso del proyecto.
// Todos estos módulos van interactuando entre sí, es importante asegurarse que se llamen desde los gradles de cada módulo para asegurar que se puedan comunicar entre ellos y el settings.gradle.
// El patrón de arquitectura utilizado es MVVM (Model-View-ViewModel)
// El patrón de diseño utilizado es Singleton.

class ListFilmActivity : FilmActivity() {

    private lateinit var binding: ActivityFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_film)
        binding.lifecycleOwner = this
        initializeTabs()
    }


    fun initializeTabs() {

        // Se inicializan los tabs
        binding.tabTypesFilms.addTab(binding.tabTypesFilms.newTab().setText("All"))
        binding.tabTypesFilms.addTab(binding.tabTypesFilms.newTab().setText("Movies"))
        binding.tabTypesFilms.addTab(binding.tabTypesFilms.newTab().setText("Series"))

        val adapterViewPager = PageAdapterFragment(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapterViewPager

        binding.tabTypesFilms.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabTypesFilms.selectTab(binding.tabTypesFilms.getTabAt(position))
            }
        })

    }

    override fun onBackPressed() {
        //Método para manejar onBackPressed
        val fragment = this.supportFragmentManager.findFragmentById(R.id.main_container)
        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let { isCanceled: Boolean ->
            if (!isCanceled) super.onBackPressed()
        }
    }

    interface IOnBackPressed {
        fun onBackPressed(): Boolean
    }
}
