package dev.shaundsmith.adventofcode.aoc2024.day3

import dev.shaundsmith.adventofcode.core.PuzzleSolution
import io.github.oshai.kotlinlogging.KotlinLogging

class Day3 : PuzzleSolution {

    private val logger = KotlinLogging.logger {}

    override fun part1(input: List<String>): String {

        val instructions = parseMultiplicationInstructions(input.joinToString(""))

        return instructions.sumOf { it.calculate() }.toString()
    }

    override fun part2(input: List<String>): String {

        val instructions = parseValidInstructions(input.joinToString(""))

        return instructions.sumOf { it.calculate() }.toString()
    }

    fun parseValidInstructions(input: String): List<MultiplicationInstruction> {

        val instructions = mutableListOf<Instruction>()
        instructions.addAll(parseMultiplicationInstructions(input))
        instructions.addAll(parseDoAndDontInstructions(input))
        instructions.sortBy { it.index }

        var enabled = true
        val validInstructions = mutableListOf<MultiplicationInstruction>()
        for (instruction in instructions) {
            if (instruction is DoDontInstruction) {
                logger.debug { "Found do/don't instruction $instruction"}
                enabled = instruction.isDo()
            } else if (enabled) {
                logger.debug { "Adding instruction $instruction" }
                validInstructions.add(instruction as MultiplicationInstruction)
            } else {
                logger.debug { "Ignoring instruction $instruction" }
            }
        }

        return validInstructions
    }

    fun parseMultiplicationInstructions(input: String): List<MultiplicationInstruction> {

        val regex = Regex("mul\\(\\d+,\\d+\\)")

        return regex.findAll(input)
            .map { MultiplicationInstruction(it.value, it.range.first) }
            .toList()
    }

    fun parseDoAndDontInstructions(input: String): List<DoDontInstruction> {

        val regex = Regex("(do\\(\\))|(don't\\(\\))")

        return regex.findAll(input)
            .map { DoDontInstruction(it.value, it.range.first) }
            .toList()
    }

}