package ca.georgiancollege.ice5

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.ice5.databinding.ActivityMainBinding
import java.util.Stack


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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


       buttonsReferences()

    }

    // buttons
    private fun buttonsReferences() {
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
        if(num == "." && isDecimalClicked) {
            return
        }

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
                if(stack.peek() == ".") {
                    isDecimalClicked = false
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
            isPlus = true
            isDecimalClicked = false
        } else {
            val resultText = stack.joinToString("")
            binding.resultTextView.text =
                if (!isPlus) "-$resultText" else resultText
            isDecimalClicked = stack.contains(".")
        }
    }
}
