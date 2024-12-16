package dev.shaundsmith.adventofcode.aoc2024

import dev.shaundsmith.adventofcode.core.FileLoader
import dev.shaundsmith.adventofcode.aoc2024.day12.Day12
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12Test {

    private val puzzleSolution = Day12()
    private val fileLoader = FileLoader()

    @Test fun day12Part1() {

        val contents = fileLoader.loadFile("aoc2024/day12/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("1930", result)
    }

    @Test fun day12Part2() {

        val contents = fileLoader.loadFile("aoc2024/day12/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("1206", result)
    }

}