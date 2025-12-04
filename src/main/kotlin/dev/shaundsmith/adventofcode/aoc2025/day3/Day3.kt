package dev.shaundsmith.adventofcode.aoc2025.day3

import dev.shaundsmith.adventofcode.core.PuzzleSolution

class Day3 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        return input.map { BatteryBank.of(it) }
            .sumOf { it.calculateLargestJolts(2) }
            .toString()
    }

    override fun part2(input: List<String>): String {

        return input.map { BatteryBank.of(it) }
            .sumOf { it.calculateLargestJolts(12) }
            .toString()
    }

}