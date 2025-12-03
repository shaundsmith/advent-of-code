package dev.shaundsmith.adventofcode.aoc2025

import dev.shaundsmith.adventofcode.core.FileLoader
import dev.shaundsmith.adventofcode.aoc2025.day2.Day2
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {

    private val puzzleSolution = Day2()
    private val fileLoader = FileLoader()

    @Test fun day2Part1() {

        val contents = fileLoader.loadFile("aoc2025/day2/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("1227775554", result)
    }

    @Test fun day2Part2() {

        val contents = fileLoader.loadFile("aoc2025/day2/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("4174379265", result)
    }

}