package ca.georgiancollege.ice5

import android.util.Log
import ca.georgiancollege.ice5.databinding.ActivityMainBinding


class Calculator(dataBinding: ActivityMainBinding)
{
    private var binding: ActivityMainBinding = dataBinding
    private var result: String

    init {
        result = ""
        createButtonsReferences()
    }

    //
    private fun createButtonsReferences() {
        val operandButtons = arrayOf(
            binding.oneButton, binding.twoButton, binding.threeButton, binding.fourButton,
            binding.fiveButton, binding.sixButton, binding.sevenButton, binding.eightButton,
            binding.nineButton, binding.zeroButton, binding.plusMinusButton, binding.decimalButton,
            binding.deleteButton
        )

        val operatorButtons = arrayOf(
            binding.minusButton, binding.plusButton, binding.multiplyButton, binding.divideButton,
            binding.percentButton, binding.clearButton
        )

        operandButtons.forEach { it.setOnClickListener { operandHandler(it.tag as String) } }

        operatorButtons.forEach { it.setOnClickListener { operatorHandler(it.tag as String) } }
    }

    private fun operandHandler(tag: String) {
        when(tag)
        {
            "." -> {
                if(!binding.resultTextView.text.contains("."))
                {
                    result += if(result.isEmpty()) "0." else "."

                    binding.resultTextView.text = result
                }
            }
            "delete" -> {
                result = result.dropLast(1)

                binding.resultTextView.text = if(result.isEmpty() || result=="-") "0" else result
            }
            "plus_minus" -> {
                if(result.startsWith("-"))
                {
                    result = result.substring(1)
                }
                else
                {
                    if(result.isNotEmpty())
                    {
                        result = "-".plus(result)
                    }
                }
                binding.resultTextView.text = result
            }
            else -> {

                if(binding.resultTextView.text == "0")
                {
                    result = tag
                }
                else
                {
                    result += tag
                }
                binding.resultTextView.text = result
            }
        }
    }

    private fun operatorHandler(tag: String)
    {
        when (tag)
        {
            "clear" -> clear()
        }
    }

    private fun clear()
    {
        result = ""
        binding.resultTextView.text = "0"
    }
}