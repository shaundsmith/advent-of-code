package dev.shaundsmith.adventofcode.aoc2024

import dev.shaundsmith.adventofcode.core.FileLoader
import dev.shaundsmith.adventofcode.aoc2024.day7.Day7
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day7Test {

    private val puzzleSolution = Day7()
    private val fileLoader = FileLoader()

    @Test fun day7Part1() {

        val contents = fileLoader.loadFile("aoc2024/day7/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("3749", result)
    }

    @Test fun day7Part2() {

        val contents = fileLoader.loadFile("aoc2024/day7/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("11387", result)
    }

}