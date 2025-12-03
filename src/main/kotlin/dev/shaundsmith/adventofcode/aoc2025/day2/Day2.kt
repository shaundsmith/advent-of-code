package dev.shaundsmith.adventofcode.aoc2025.day2

import dev.shaundsmith.adventofcode.core.PuzzleSolution

class Day2 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val invalidIdSum = input[0].split(",")
            .map { Id.of(it) }
            .map { it.invalidIds }
            .sumOf { it.sum() }

        return invalidIdSum.toString()
    }

    override fun part2(input: List<String>): String {

        val invalidIdSum = input[0].split(",")
            .map { Id.of(it) }
            .map { it.complexInvalidIds }
            .sumOf { it.sum() }

        return invalidIdSum.toString()
    }

}