package dev.shaundsmith.adventofcode.aoc2024

import dev.shaundsmith.adventofcode.aoc2024.day14.Day14
import dev.shaundsmith.adventofcode.core.FileLoader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day14Test {

    private val puzzleSolution = Day14(7, 11)
    private val fileLoader = FileLoader()

    @Test fun day14Part1() {

        val contents = fileLoader.loadFile("aoc2024/day14/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("12", result)
    }

    @Test fun day14Part2() {

        val contents = fileLoader.loadFile("aoc2024/day14/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("", result)
    }

}