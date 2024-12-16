package dev.shaundsmith.adventofcode.aoc2024.day10

import dev.shaundsmith.adventofcode.core.PuzzleSolution
import dev.shaundsmith.adventofcode.core.toGrid

class Day10 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val grid = input.toGrid(({ i -> if (i == '.') -1 else i.toString().toInt()}), 0)

        val trailMapper = TrailMapper(grid)
        return trailMapper.findUniqueTrailheads().size.toString()
    }

    override fun part2(input: List<String>): String {

        val grid = input.toGrid(({ i -> if (i == '.') -1 else i.toString().toInt()}), 0)

        val trailMapper = TrailMapper(grid)
        return trailMapper.findTrails().size.toString()
    }

}