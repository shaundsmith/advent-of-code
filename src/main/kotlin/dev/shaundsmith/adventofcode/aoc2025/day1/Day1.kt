package dev.shaundsmith.adventofcode.aoc2025.day1

import dev.shaundsmith.adventofcode.core.PuzzleSolution

class Day1 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val dial = SafeDial()
        input.forEach { dial.rotate(it) }
        return dial.zeroCount.toString()
    }

    override fun part2(input: List<String>): String {

        val dial = SafeDial()
        input.forEach { dial.rotate(it) }
        return (dial.zeroPasses + dial.zeroCount).toString()
    }

}