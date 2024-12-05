package dev.shaundsmith.adventofcode.aoc2024

import dev.shaundsmith.adventofcode.core.FileLoader
import dev.shaundsmith.adventofcode.aoc2024.day5.Day5
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5Test {

    private val puzzleSolution = Day5()
    private val fileLoader = FileLoader()

    @Test fun day5Part1() {

        val contents = fileLoader.loadFile("aoc2024/day5/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("143", result)
    }

    @Test fun day5Part2() {

        val contents = fileLoader.loadFile("aoc2024/day5/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("123", result)
    }

}