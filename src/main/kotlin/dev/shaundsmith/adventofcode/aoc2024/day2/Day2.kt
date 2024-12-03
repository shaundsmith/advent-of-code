package dev.shaundsmith.adventofcode.aoc2024.day2

import dev.shaundsmith.adventofcode.core.PuzzleSolution

class Day2 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        return parse(input)
            .count { it.isSafe() }
            .toString()
    }

    override fun part2(input: List<String>): String {

        return parse(input)
            .count { it.isSafeWithDampening() }
            .toString()
    }

    private fun parse(input: List<String>): List<Level> {

        return input.map { it.split(" ").map { part -> part.toInt() } }
            .map { Level(it) }

    }

}