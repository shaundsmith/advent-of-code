package dev.shaundsmith.adventofcode.aoc2024.day3

data class MultiplicationInstruction(private val value: String, override val index: Int) : Instruction {

    companion object {
        val multiplicationPattern = Regex("mul\\((\\d+),(\\d+)\\)")
    }

    fun calculate(): Long {

        val multiplication = multiplicationPattern.matchEntire(value)
        val firstNumber = multiplication?.groups?.get(1)?.value?.toLong() ?: 0
        val secondNumber = multiplication?.groups?.get(2)?.value?.toLong() ?: 0

        return firstNumber * secondNumber
    }

}