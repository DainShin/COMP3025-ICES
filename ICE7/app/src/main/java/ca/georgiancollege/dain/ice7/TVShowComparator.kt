package ca.georgiancollege.dain.ice7

import androidx.recyclerview.widget.DiffUtil

class TVShowComparator: DiffUtil.ItemCallback<TVShow>()
{
    // This method checks if two items represent the same entity by comparing their IDs.
    override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean
    {
        return oldItem.id == newItem.id
    }

    // This method checks if the contents fo two items are the same by comparing their properties
    override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean
    {
        return oldItem == newItem
    }

}