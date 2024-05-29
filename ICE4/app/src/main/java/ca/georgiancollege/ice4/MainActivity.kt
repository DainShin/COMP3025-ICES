package ca.georgiancollege.ice4

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.ice4.databinding.ActivityMainBinding
import java.util.Stack

/*
 * Follow along in class
 * Create your ICE4 project from your ICE3 project. Follow the copy / rename process.
 * Add a CalculatorButton Style to reduce attribute duplication for each Button.
 * Remove any superfluous attributes after the Style has been applied
 *
 * Create the layout-land folder and copy the activity_main layout into the new folder to support the landscape orientation
 *
 * Adjust your layout so that it works on a "Small" phone then test on your regular-sized phone
 *  => explore
 *
 * Create functions that allow the number buttons (and decimal) to create an appropriate "Operand"
 *  => numbers only. do not have to do it for operator
 *
 * Enable functionality for the Clear, Backspace and Plus / Minus buttons so that they match the functionality you see in other calculators
 *  => when tag == delete

 */





class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var isResultValue = false
    var isDecimalClicked = false
    var isPlus = true

    var stack = Stack<String>()

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
            binding.plusMinusButton, binding.deleteButton, binding.clearButton
        )

        val numberButtons = arrayOf(
            binding.oneButton, binding.twoButton, binding.threeButton, binding.fourButton, binding.fiveButton,
            binding.sixButton, binding.sevenButton, binding.eightButton, binding.nineButton, binding.zeroButton, binding.decimalButton
        )


        operandButtons.forEach { it.setOnClickListener { operandHandler(it.tag.toString()) } }
        numberButtons.forEach { it.setOnClickListener {numberHandler(it.tag.toString()) }}

    }

    private fun numberHandler(num: String) {

        if (stack.isEmpty()) {
            if (num == ".") {
                stack.push("0.")
                isDecimalClicked = true
            } else {
                stack.push(num)
            }
        }
        else if(stack.size == 1 && stack.peek() == "0" && num != "0" && num != ".") {
            stack.pop()
            stack.push(num)
        }
        else {
            if (num == "." && !isDecimalClicked) {
                stack.push(num)
                isDecimalClicked = true
            } else if (num != "." || (num == "." && !stack.peek().contains("."))) {
                stack.push(num)
            }
        }
        updateResultView()
    }

    private fun operandHandler(tag: String) {
        when (tag) {
            "delete" -> {
                if(binding.resultTextView.text == "0") {
                    isPlus = true
                }
               stack.pop()
                updateResultView()
            }
            "clear" -> {
                stack.clear()
                isDecimalClicked = false
                isPlus = true
                updateResultView()
            }
            "plus_minus" -> {
                if(binding.resultTextView.text != "0") {
                    isPlus = !isPlus
                }
               else {
                   isPlus = true
                }
                updateResultView()
            }
        }
    }

    // update result view screen
    private fun updateResultView() {
        if (stack.isEmpty()) {
            binding.resultTextView.text = "0"
        } else {
            val resultText = stack.joinToString("")
            binding.resultTextView.text =
                if (!isPlus) "-$resultText" else resultText
        }
    }
}

/*
Add a CalculatorButton Style to reduce attribute duplication for each Button. --> ok
Add an inherited sytle for each type of button (light_grey_button, dark_grey_button, operator_button) --> ok

Remove any superfluous attributes after the Style has been applied --> ok
Create the layout-land folder and copy the activity_main layout into the new folder to support the landscape orientation --> ok

Adjust your layout so that it works on a "Small" phone then test on your regular-sized phone

Create functions that allow the number buttons (and decimal) to create an appropriate "Operand" --> ok
Enable functionality for the Clear, Backspace and Plus / Minus buttons so that they match the functionality you see in other calculators --> ok
*
*/