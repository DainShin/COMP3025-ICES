package ca.georgiancollege.ice3

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.ice3.databinding.ActivityMainBinding

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

        binding.helloWorldTextView.text =getString(R.string.hello_world_string)

        binding.clickMeButton.setOnClickListener {
           sharedButtonHandler(it as Button)
        }

       binding.anotherButton.setOnClickListener {
           sharedButtonHandler(it as Button)
          // sharedButtonHandler(binding.anotherButton)
       }
    }

    fun sharedButtonHandler(button: Button) =
        // cast the button as a Button object
        // val button = view as Button
        when(button) {
            binding.anotherButton -> {
                binding.helloWorldTextView.text = getString(R.string.something_else)
            }
            binding.clickMeButton -> {
                // toggle
//                if(binding.helloWorldTextView.text == "Clicked!")
//                {
//                    binding.helloWorldTextView.text = getString(R.string.not_clicked)
//                }
//                else
//                {
//                    binding.helloWorldTextView.text = getString(R.string.clicked)
//                }
                binding.helloWorldTextView.text =
                    if (binding.helloWorldTextView.text == "Clicked!") getString(R.string.not_clicked)
                    else getString(R.string.clicked)

                binding.helloWorldTextView.text =
                    if (binding.helloWorldTextView.text == "Clicked!") getString(R.string.not_clicked)
                    else getString(R.string.clicked)
            }
            else  -> {

            }
        }

}
