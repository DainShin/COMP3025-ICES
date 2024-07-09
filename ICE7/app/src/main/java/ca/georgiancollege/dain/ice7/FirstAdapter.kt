package ca.georgiancollege.dain.ice7

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.dain.ice7.databinding.TextRowItemBinding

// This class is taking the TVShow Array and assign the data in the view
// This is a kind of testing class to test the RecyclerView. We are going to use TVShowListAdapter
// All the functions overrides the RecyclerView class

// A control class and a wrapper for the first RecyclerView
class FirstAdapter(private val dataSet: Array<TVShow>) :
    RecyclerView.Adapter<FirstAdapter.ViewHolder>()
{

    // inner class ViewHolder
    class ViewHolder(val binding: TextRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    // Inflates the text_row_item layout and returns the ViewHolder (custom table view cell)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder
    {
        // Inflate the layout with view binding
        val binding = TextRowItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    // Binds the data to the view
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int)
    {
        // Use view binding to set the text
        viewHolder.binding.title.text = dataSet[position].title
        viewHolder.binding.genre.text = dataSet[position].genre
    }

    // Returns the size of the data set
    override fun getItemCount() = dataSet.size
}