package ca.georgiancollege.dain.ice7

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ca.georgiancollege.dain.ice7.databinding.TextRowItemBinding

// This class manages the whole TVShow list and connects each items to ViewHolder

// Whenever we click the item, it's going to return an object (Handling the clicked rows)
class TVShowListAdapter(private val onItemClicked: (TVShow) -> Unit):
    ListAdapter<TVShow, TVShowViewHolder>(TVShowComparator())
{
     // Creating a new TVShowViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder
    {
       val binding = TextRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(binding)
    }

    // Connecting the TVShowViewHolder to data and showing it in the app
    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int)
    {
        val current = getItem(position)
        holder.bind(current)

        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
    }

}