package ca.georgiancollege.ice2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.ice2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create a reference to the ActivityMainBinding Class object
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun sharedButtonHandler(view: View) {
        if (view.id == R.id.clickMeButton) {
            Log.i("onCreate", "Click Me Button clicked!")
            binding.helloWorldTextView.text = getString(R.string.good_bye_dain)
        } else if (view.id == R.id.anotherButton) {
            Log.i("onCreate", "Another Button Clicked")
            binding.helloWorldTextView.text = getString(R.string.hello_to_dain)
        }
    }
}
