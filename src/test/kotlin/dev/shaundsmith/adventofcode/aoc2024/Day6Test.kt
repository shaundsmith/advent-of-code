package dev.shaundsmith.adventofcode.aoc2024

import dev.shaundsmith.adventofcode.core.FileLoader
import dev.shaundsmith.adventofcode.aoc2024.day6.Day6
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day6Test {

    private val puzzleSolution = Day6()
    private val fileLoader = FileLoader()

    @Test fun day6Part1() {

        val contents = fileLoader.loadFile("aoc2024/day6/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("41", result)
    }

    @Test fun day6Part2() {

        val contents = fileLoader.loadFile("aoc2024/day6/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("6", result)
    }

}