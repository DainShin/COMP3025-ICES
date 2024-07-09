package ca.georgiancollege.dain.ice7

import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.dain.ice7.databinding.TextRowItemBinding

// This class decides on how it shows the TVShow in the app
class TVShowViewHolder(private val binding: TextRowItemBinding): RecyclerView.ViewHolder(binding.root)
{
    // Pass a TVShow object
    fun bind(tvShow: TVShow)
    {
        binding.title.text = tvShow.title
        binding.genre.text = tvShow.genre
        binding.rating.text = tvShow.rating.toString()
    }
}