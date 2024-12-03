package dev.shaundsmith.adventofcode.aoc2024

import dev.shaundsmith.adventofcode.core.FileLoader
import dev.shaundsmith.adventofcode.aoc2024.day1.Day1
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {

    private val puzzleSolution = Day1()
    private val fileLoader = FileLoader()

    @Test fun day1Part1() {

        val contents = fileLoader.loadFile("aoc2024/day1/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("11", result)
    }

    @Test fun day1Part2() {

        val contents = fileLoader.loadFile("aoc2024/day1/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("31", result)
    }

}