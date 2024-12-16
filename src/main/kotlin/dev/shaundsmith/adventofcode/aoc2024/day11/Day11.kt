package dev.shaundsmith.adventofcode.aoc2024.day11

import dev.shaundsmith.adventofcode.core.PuzzleSolution

class Day11 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        return iterate(input, 25)
    }

    override fun part2(input: List<String>): String {

        return iterate(input, 75)
    }

    private fun iterate(input: List<String>, times: Int): String {

        val stones = input.flatMap { it.split(" ") }
            .map { it.toLong() }

        val stoneCollection = StoneCollection(stones)
        stoneCollection.step(times)

        return stoneCollection.numberOfStones.toString()
    }

}