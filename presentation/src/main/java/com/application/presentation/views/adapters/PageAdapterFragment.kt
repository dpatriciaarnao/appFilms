package com.application.presentation.views.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.application.presentation.views.ui.FilmsFragment

class PageAdapterFragment(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            // Como reutilicé un solo fragment, solo le envío la position para saber en que tab está y filtrar la información del pager
            0 -> FilmsFragment.newInstance(position)
            1 -> FilmsFragment.newInstance(position)
            2 -> FilmsFragment.newInstance(position)
            else -> FilmsFragment.newInstance(position)
        }
    }
}