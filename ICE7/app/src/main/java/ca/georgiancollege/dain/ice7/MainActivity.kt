package ca.georgiancollege.dain.ice7

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.dain.ice7.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    private lateinit var dataManager: DataManager

    // Adapter for the RecyclerView, with a click listener to open the DetailsActivity
    private val adapter = TVShowListAdapter { tvShow: TVShow ->
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("tvShowId", tvShow.id)
        }
        startActivity(intent) // startActivity: Using for starting from the current Activity to a new Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataManager = DataManager.instance(this)

        binding.firstRecyclerView.layoutManager = LinearLayoutManager(this)  // layoutManager: Managing the layout of the RecyclerView
                                                                                     // this: context(MainActivity)
        binding.firstRecyclerView.adapter = adapter  // adapter: TVShowListAdapter instance

        loadTVShows()

        binding.addButton.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume()
    {
        super.onResume() // onResume method in the AppCompatActivity Class
        loadTVShows()
    }

    private fun loadTVShows()
    {
        lifecycleScope.launch {
            val tvShows = dataManager.getAllTVShows()
            adapter.submitList(tvShows)
        }
    }
}