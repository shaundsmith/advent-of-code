package dev.shaundsmith.adventofcode.aoc2024.day3

data class DoDontInstruction(private val value: String, override val index: Int) : Instruction {

    fun isDo(): Boolean = value == "do()"

}