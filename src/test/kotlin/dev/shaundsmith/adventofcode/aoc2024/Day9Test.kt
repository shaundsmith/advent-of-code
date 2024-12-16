package dev.shaundsmith.adventofcode.aoc2024

import dev.shaundsmith.adventofcode.core.FileLoader
import dev.shaundsmith.adventofcode.aoc2024.day9.Day9
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day9Test {

    private val puzzleSolution = Day9()
    private val fileLoader = FileLoader()

    @Test fun day9Part1() {

        val contents = fileLoader.loadFile("aoc2024/day9/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("1928", result)
    }

    @Test fun day9Part2() {

        val contents = fileLoader.loadFile("aoc2024/day9/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("2858", result)
    }

}