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

        // 1. loop over the value
        // 2. if the value is number then call the numbersHandler
        // 3. if the value is operator then call the operatorHandler
        // 4. each function need to print out the value in the resultView section

        // call the function based on each values
        binding.clearButton.setOnClickListener {
            operatorsHandler(it as Button)
        }

        binding.percentButton.setOnClickListener {
            operatorsHandler(it as Button)
        }

        binding.deleteButton.setOnClickListener {
            operatorsHandler(it as Button)
        }

        binding.divideButton.setOnClickListener {
            operatorsHandler(it as Button)
        }

        binding.sevenButton.setOnClickListener {
            numbersHandler(it as Button)
        }

        binding.eightButton.setOnClickListener {
            numbersHandler(it as Button)
        }

        binding.nineButton.setOnClickListener {
            numbersHandler(it as Button)
        }

        binding.fourButton.setOnClickListener {
            numbersHandler(it as Button)
        }

        binding.fiveButton.setOnClickListener {
            numbersHandler(it as Button)
        }

        binding.sixButton.setOnClickListener {
            numbersHandler(it as Button)
        }

        binding.oneButton.setOnClickListener {
            numbersHandler(it as Button)
        }

        binding.twoButton.setOnClickListener {
            numbersHandler(it as Button)
        }

        binding.threeButton.setOnClickListener {
            numbersHandler(it as Button)
        }

        binding.zeroButton.setOnClickListener {
            numbersHandler(it as Button)
        }

        binding.decimalButton.setOnClickListener {
            numbersHandler(it as Button)
        }

        binding.plusMinusButton.setOnClickListener {
            operatorsHandler(it as Button)
        }

        binding.equalsButton.setOnClickListener {
            operatorsHandler(it as Button)
        }

        binding.plusButton.setOnClickListener {
            operatorsHandler(it as Button)
        }

        binding.minusButton.setOnClickListener {
            operatorsHandler(it as Button)
        }

        binding.multiplyButton.setOnClickListener {
            operatorsHandler(it as Button)
        }

        binding.divideButton.setOnClickListener {
            operatorsHandler(it as Button)
        }

    }



    fun numbersHandler(button:Button) {
        binding.resultTextView.text = button.tag.toString()
    }

    fun operatorsHandler(button:Button) {
        binding.resultTextView.text = button.tag.toString()
    }

    /*
    * Complete the Calculator Layout
    * Select a colour for the Number Buttons and the background --> ok
    *
    * Create two shared handler functions - one for operator buttons and one for number buttons
    * Add Listeners for each button that point to the correct handler function
    * Have the handler functions output the tag of the button clicked to the resultTextView
    * */


}
