package ca.georgiancollege.ice4

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.ice4.databinding.ActivityMainBinding

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




    }

    fun numbersHandler(button:Button) {
        binding.resultTextView.text = button.tag.toString()
    }

    fun operatorsHandler(button:Button) {
        binding.resultTextView.text = button.tag.toString()
    }

}
