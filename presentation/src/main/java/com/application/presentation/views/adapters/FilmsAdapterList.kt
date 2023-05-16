package com.application.presentation.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.appmovies.presentation.R
import com.application.appmovies.presentation.databinding.FilmItemListBinding
import com.application.entities.Film

class FilmsAdapterList(
    private val listener: FilmsAdapterListListener
) :
    ListAdapter<Film, FilmsAdapterList.ViewHolder>(FilmsAdapterListDiffCallback()) {

    interface FilmsAdapterListListener {
        // Este método servirá para cuando se desee hacer click sobre el elemento de la lista y traer el detalle
        fun onClickDetail(film: Film)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_item_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.setUi(item, listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FilmItemListBinding.bind(itemView)

        fun setUi(
            filmsList: Film,
            listener: FilmsAdapterListListener
        ) {
            val context = binding.root.context
            binding.filmName.text = filmsList.name

            // Click sobre el elemento para traer el detalle
            binding.clCardFilm.setOnClickListener {
                listener.onClickDetail(filmsList)
            }
        }
    }
}

class FilmsAdapterListDiffCallback : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}
