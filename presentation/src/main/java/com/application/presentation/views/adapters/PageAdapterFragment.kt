package com.application.presentation.views.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.application.presentation.views.ui.AllFilmsFragment
import com.application.presentation.views.ui.MoviesFragment
import com.application.presentation.views.ui.SeriesFragment

class PageAdapterFragment(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllFilmsFragment.newInstance(0)
            1 -> AllFilmsFragment.newInstance(1)
            2 -> AllFilmsFragment.newInstance(2)
            else -> AllFilmsFragment.newInstance(0)
        }
    }
}