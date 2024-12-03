package dev.shaundsmith.adventofcode.aoc2024

import dev.shaundsmith.adventofcode.core.FileLoader
import dev.shaundsmith.adventofcode.aoc2024.day3.Day3
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {

    private val puzzleSolution = Day3()
    private val fileLoader = FileLoader()

    @Test fun day3Part1() {

        val contents = fileLoader.loadFile("aoc2024/day3/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("161", result)
    }

    @Test fun day3Part2() {

        val contents = fileLoader.loadFile("aoc2024/day3/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("48", result)
    }

}