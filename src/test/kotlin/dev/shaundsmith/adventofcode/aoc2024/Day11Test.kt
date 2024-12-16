package dev.shaundsmith.adventofcode.aoc2024

import dev.shaundsmith.adventofcode.core.FileLoader
import dev.shaundsmith.adventofcode.aoc2024.day11.Day11
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {

    private val puzzleSolution = Day11()
    private val fileLoader = FileLoader()

    @Test fun day11Part1() {

        val contents = fileLoader.loadFile("aoc2024/day11/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("55312", result)
    }

    @Test fun day11Part2() {

        val contents = fileLoader.loadFile("aoc2024/day11/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("", result)
    }

}