package dev.shaundsmith.adventofcode.aoc2024.day7

import io.github.oshai.kotlinlogging.KotlinLogging

class CalibrationEquationSolver(private val concat: Boolean = false) {

    private val logger = KotlinLogging.logger {}

    fun canSmartSolve(result: Long, values: List<Long>): Boolean {

        logger.debug { "Calibration equation: $result: $values" }
        if (values.size == 1 && result == values[0]) {
            return true
        } else if (result <= 0 || values.isEmpty()) {
            return false
        }

        var canSolve = false
        if (concat && values.size > 1) {
            val deconcatanatedValue = deconcatanate(result, values.last())
            if (deconcatanatedValue != null) {
                logger.debug { "Can deconcatanate $result by ${values.last()}" }
                canSolve = canSmartSolve(deconcatanatedValue, values.subList(0, values.lastIndex))
            }
        }
        if (!canSolve && result % values.last() == 0L) {
            logger.debug { "Can divide $result by ${values.last()}" }
            canSolve = canSmartSolve(result / values.last(), values.subList(0, values.lastIndex))
        }

        if (!canSolve) {
            canSolve = canSmartSolve(result - values.last(), values.subList(0, values.lastIndex))
        }

        return canSolve
    }

    fun deconcatanate(result: Long, value: Long): Long? {

        val resultValue = result.toString()
        val stringValue = value.toString()

        var deconcatanatedValue: Long? = null
        if (resultValue.length != stringValue.length && resultValue.takeLast(stringValue.length) == stringValue) {
            deconcatanatedValue = resultValue.dropLast(stringValue.length).toLong()
        }

        return deconcatanatedValue
    }

}