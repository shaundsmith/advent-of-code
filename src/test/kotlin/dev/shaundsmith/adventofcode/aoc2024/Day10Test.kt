package dev.shaundsmith.adventofcode.aoc2024

import dev.shaundsmith.adventofcode.core.FileLoader
import dev.shaundsmith.adventofcode.aoc2024.day10.Day10
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {

    private val puzzleSolution = Day10()
    private val fileLoader = FileLoader()

    @Test fun day10Part1() {

        val contents = fileLoader.loadFile("aoc2024/day10/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("36", result)
    }

    @Test fun day10Part2() {

        val contents = fileLoader.loadFile("aoc2024/day10/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("81", result)
    }

}