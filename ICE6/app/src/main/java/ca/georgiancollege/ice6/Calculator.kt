package ca.georgiancollege.ice6

import ca.georgiancollege.ice6.databinding.ActivityMainBinding


class Calculator(dataBinding: ActivityMainBinding) {
    private var binding: ActivityMainBinding = dataBinding
    private var result: String
    private var currentOperand: String
    private var currentOperator: String

    init {
        result = ""
        currentOperand = ""
        currentOperator = ""
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
            binding.percentButton, binding.clearButton, binding.equalsButton
        )

        operandButtons.forEach { it.setOnClickListener { operandHandler(it.tag as String) } }

        operatorButtons.forEach { it.setOnClickListener { operatorHandler(it.tag as String) } }
    }

    private fun operandHandler(tag: String) {
        when (tag) {
            "." -> {
                if (!binding.resultTextView.text.contains(".")) {
                    result += if (result.isEmpty()) "0." else "."

                    binding.resultTextView.text = result
                }
            }

            "delete" -> {
                result = result.dropLast(1) // dropLast: 뒤에서부터 n개의 문자 제거

                binding.resultTextView.text = if (result.isEmpty() || result == "-") "0" else result
            }

            "plus_minus" -> {
                if (result.startsWith("-")) {
                    result = result.substring(1)
                } else {
                    if (result.isNotEmpty()) {
                        result = "-".plus(result)
                    }
                }
                binding.resultTextView.text = result
            }

            else -> {

                if (binding.resultTextView.text == "0") {
                    result = tag
                } else {
                    result += tag
                }
                binding.resultTextView.text = result
            }
        }
    }

    /**
     * This function performs all the computation for the Calculator
     *
     * @param {tag} [String]
     */
    private fun operatorHandler(tag: String) {
        if (tag != "clear") {
            if (currentOperand.isNotEmpty()) {


                when (currentOperator) {
                    "plus" -> add()
                    "minus" -> subtract()
                    "divide" -> divide()
                    "multiply" -> multiply()
                    "percent" -> percentage()
                    "equals" -> equals()
                }
            }
                currentOperand = binding.resultTextView.text.toString()
                result = ""
                //binding.resultTextView.text = ""

            currentOperator = tag
        } else {// when the clear button is clicked
            clear()
        }

    }

    private fun equals() {
       binding.resultTextView.text = result
    }

    private fun add() {
        if (currentOperand.contains(".") || result.contains(".")) {
            result = ((currentOperand.toFloat()) + (result.toFloat())).toString()
        } else {
            result = ((currentOperand.toInt()) + (result.toInt())).toString()
        }

        // remove decimal if the result is int
        if (result.endsWith(".0")) {
            result = result.substring(0, result.length - 2)
        }
        binding.resultTextView.text = result
    }

    private fun subtract() {
        if (currentOperand.contains(".") || result.contains(".")) {
            // currentOperand = old, result= new
            result = ((currentOperand.toFloat()) - (result.toFloat())).toString()
        } else {
            result = ((currentOperand.toInt()) - (result.toInt())).toString()
        }

        if (result.endsWith(".0")) {
            result = result.substring(0, result.length - 2)
        }
        binding.resultTextView.text = result
    }

    private fun divide()
    {
        // currentOperand = old, result= new
        result = ((currentOperand.toFloat()) / (result.toFloat())).toString()

        if (result.endsWith(".0")) {
            result = result.substring(0, result.length - 2)
        }

        binding.resultTextView.text = result
    }

    private fun multiply()
    {
        if (currentOperand.contains(".") || result.contains(".")) {
            // currentOperand = old, result= new
            result = ((currentOperand.toFloat()) * (result.toFloat())).toString()
        } else {
            result = ((currentOperand.toInt()) * (result.toInt())).toString()
        }

        if (result.endsWith(".0")) {
            result = result.substring(0, result.length - 2)
        }
        binding.resultTextView.text = result
        currentOperand = ""
    }

    private fun percentage()
    {
        var result = binding.resultTextView.toString().toFloat() * 0.01

    }

    private fun clear() {
        result = ""
        binding.resultTextView.text = "0"
        currentOperand = ""
        currentOperator = ""
    }
}


/*
    2 stacks..
    trees
    string manipulation """ """"
    collections
    Lists..

* */