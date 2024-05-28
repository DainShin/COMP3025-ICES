package ca.georgiancollege.ice4

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.ice4.databinding.ActivityMainBinding

/*
 * Follow along in class
 * Create your ICE4 project from your ICE3 project. Follow the copy / rename process.
 * Add a CalculatorButton Style to reduce attribute duplication for each Button.
 * Remove any superfluous attributes after the Style has been applied
 * Create the layout-land folder and copy the activity_main layout into the new folder to support the landscape orientation
 * Adjust your layout so that it works on a "Small" phone then test on your regular-sized phone
 * Create functions that allow the number buttons (and decimal) to create an appropriate "Operand"
 * Enable functionality for the Clear, Backspace and Plus / Minus buttons so that they match the functionality you see in other calculators
 * Deploy to an emulator of your choice
 * Use GitHub for version control
 */

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

        // list : no index
        // array : index
        val operandButtons = arrayOf(
            binding.oneButton, binding.twoButton, binding.threeButton, binding.fourButton, binding.fiveButton,
            binding.sixButton, binding.sevenButton, binding.eightButton, binding.nineButton, binding.zeroButton,
            binding.plusMinusButton, binding.decimalButton, binding.deleteButton
        )

        var operatorButtons = arrayOf(
            binding.divideButton, binding.plusButton, binding.minusButton, binding.multiplyButton,
            binding.percentButton, binding.percentButton, binding.clearButton, binding.equalsButton
        )

        // looping
        operandButtons.forEach { it.setOnClickListener { operandHandler(it.tag as String) } }
        operatorButtons.forEach { it.setOnClickListener { operatorHandler (it.tag as String) } }
    }

    private fun operandHandler(tag:String) {
        binding.resultTextView.text = tag
    }

    private fun operatorHandler(tag:String) {
        binding.resultTextView.text = tag
    }

}
