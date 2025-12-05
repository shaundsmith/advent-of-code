package dev.shaundsmith.adventofcode.aoc2025.day4

import dev.shaundsmith.adventofcode.core.PuzzleSolution
import dev.shaundsmith.adventofcode.core.toGrid

class Day4 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val grid = input.toGrid({ i -> i }, '.')
        val forkliftMap = ForkliftMap(grid)
        return forkliftMap.findAccessiblePaper().count().toString()
    }

    override fun part2(input: List<String>): String {

        val grid = input.toGrid({ i -> i }, '.')
        val forkliftMap = ForkliftMap(grid)
        return forkliftMap.countRemovablePaper().toString()
    }

}