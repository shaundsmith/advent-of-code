package dev.shaundsmith.adventofcode.aoc2024

import dev.shaundsmith.adventofcode.core.FileLoader
import dev.shaundsmith.adventofcode.aoc2024.day8.Day8
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8Test {

    private val puzzleSolution = Day8()
    private val fileLoader = FileLoader()

    @Test fun day8Part1() {

        val contents = fileLoader.loadFile("aoc2024/day8/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("14", result)
    }

    @Test fun day8Part2() {

        val contents = fileLoader.loadFile("aoc2024/day8/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("34", result)
    }

}