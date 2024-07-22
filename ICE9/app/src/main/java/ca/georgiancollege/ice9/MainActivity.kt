package ca.georgiancollege.ice9

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.ice9.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TVShowViewModel by viewModels()
    private lateinit var auth: FirebaseAuth

    private lateinit var dataManager: DataManager

    private var pauseTime: Long = 0
    private val sessionTimer: Long = 10 * 1000   // session time (10 seconds)

    // Adapter for the RecyclerView, with a click listener to open the DetailsActivity
    private val adapter = TVShowListAdapter { tvShow: TVShow ->
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("tvShowId", tvShow.id)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize Firestore and DataManager
        FirebaseFirestore.setLoggingEnabled(true)
        dataManager = DataManager.instance()

        binding.firstRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.firstRecyclerView.adapter = adapter

        // Load all TV shows from the database when the user is logged in
        // if the user is not logged in show the empty list
        viewModel.tvShows.observe(this) { tvShows ->
            if(auth.currentUser != null)
            {
                adapter.submitList(tvShows)
            }
            else
            {
                adapter.submitList(emptyList())
                binding.addButton.visibility = View.GONE
            }
        }

        // button events
        binding.addButton.setOnClickListener {
            if(auth.currentUser != null ){
                val intent = Intent(this, DetailsActivity::class.java)
                startActivity(intent)
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }

        binding.logoutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        pauseTime = System.currentTimeMillis()
    }

    override fun onResume()
    {
        super.onResume()

        val elapsedTime = System.currentTimeMillis() - pauseTime

        // Check if the user is logged in
        if (auth.currentUser != null) {
            if (elapsedTime >= sessionTimer) {
                // If the user was logged in and the app was in the background for more than 10 seconds
                auth.signOut()
                Log.i("Access", "Session was expired")
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                // If the user is logged in and the app was not in the background for more than 10 seconds
                Log.i("Access", "Login session is valid")
                viewModel.loadAllTVShows()
            }
        } else {
            // User is not logged in
            Log.i("Access", "User is not logged in")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        pauseTime = System.currentTimeMillis()
        Log.i("Access", "OnPause called")
    }

}