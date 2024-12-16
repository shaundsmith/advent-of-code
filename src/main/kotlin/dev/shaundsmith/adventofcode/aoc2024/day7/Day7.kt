package dev.shaundsmith.adventofcode.aoc2024.day7

import dev.shaundsmith.adventofcode.core.PuzzleSolution
import io.github.oshai.kotlinlogging.KotlinLogging

class Day7 : PuzzleSolution {

    private val logger = KotlinLogging.logger {}

    override fun part1(input: List<String>): String {

        val calibrationEquationSolver = CalibrationEquationSolver()

        return input.map { parse(it) }
            .filter { calibrationEquationSolver.canSmartSolve(it.first, it.second) }
            .map { logAndReturn(it) }
            .sumOf { it.first }
            .toString()
    }

    override fun part2(input: List<String>): String {

        val calibrationEquationSolver = CalibrationEquationSolver(true)

        return input.map { parse(it) }
            .filter { calibrationEquationSolver.canSmartSolve(it.first, it.second) }
            .map { logAndReturn(it) }
            .sumOf { it.first }
            .toString()
    }

    private fun parse(input: String): Pair<Long, List<Long>> {

        val splitInput = input.split(": ")

        val numbersList = splitInput[1].split(" ")
            .map { it.toLong() }

        return Pair(splitInput[0].toLong(), numbersList)
    }

    private fun logAndReturn(solution: Pair<Long, List<Long>>): Pair<Long, List<Long>> {

        logger.debug { "Calibration equation ${solution.first}: ${solution.second} can be solved" }

        return solution
    }

}