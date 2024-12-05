package dev.shaundsmith.adventofcode.aoc2024

import dev.shaundsmith.adventofcode.core.FileLoader
import dev.shaundsmith.adventofcode.aoc2024.day4.Day4
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day4Test {

    private val puzzleSolution = Day4()
    private val fileLoader = FileLoader()

    @Test fun day4Part1() {

        val contents = fileLoader.loadFile("aoc2024/day4/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("18", result)
    }

    @Test fun day4Part2() {

        val contents = fileLoader.loadFile("aoc2024/day4/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("9", result)
    }

}