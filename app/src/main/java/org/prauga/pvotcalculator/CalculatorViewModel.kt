package org.prauga.pvotcalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var display by mutableStateOf("0")
        private set

    var expression by mutableStateOf("")
        private set

    private var operand1: Double? = null
    private var operator: String? = null
    private var resetOnNext = false

    fun onDigit(digit: String) {
        if (resetOnNext) {
            display = digit
            resetOnNext = false
        } else {
            display = if (display == "0") digit else display + digit
        }
    }

    fun onDecimal() {
        if (resetOnNext) {
            display = "0."
            resetOnNext = false
            return
        }
        if ("." !in display) {
            display += "."
        }
    }

    fun onOperator(op: String) {
        val current = display.toDoubleOrNull() ?: return

        if (operand1 != null && operator != null && !resetOnNext) {
            val result = calculate(operand1!!, current, operator!!)
            operand1 = result
            display = formatResult(result)
        } else {
            operand1 = current
        }

        operator = op
        expression = "${formatResult(operand1!!)} $op"
        resetOnNext = true
    }

    fun onEquals() {
        val current = display.toDoubleOrNull() ?: return
        val op = operator ?: return
        val first = operand1 ?: return

        expression = "${formatResult(first)} $op $display ="
        val result = calculate(first, current, op)
        display = formatResult(result)
        operand1 = null
        operator = null
        resetOnNext = true
    }

    fun onClear() {
        display = "0"
        expression = ""
        operand1 = null
        operator = null
        resetOnNext = false
    }

    fun onBackspace() {
        if (resetOnNext) return
        display = if (display.length <= 1) "0" else display.dropLast(1)
    }

    fun onToggleSign() {
        val value = display.toDoubleOrNull() ?: return
        display = formatResult(-value)
    }

    fun onPercent() {
        val value = display.toDoubleOrNull() ?: return
        display = formatResult(value / 100.0)
        resetOnNext = true
    }

    private fun calculate(a: Double, b: Double, op: String): Double {
        return when (op) {
            "+" -> a + b
            "−" -> a - b
            "×" -> a * b
            "÷" -> if (b != 0.0) a / b else Double.NaN
            else -> b
        }
    }

    private fun formatResult(value: Double): String {
        if (value.isNaN()) return "Error"
        if (value.isInfinite()) return "Error"
        return if (value == value.toLong().toDouble()) {
            value.toLong().toString()
        } else {
            value.toString()
        }
    }
}
