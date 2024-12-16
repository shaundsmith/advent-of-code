package dev.shaundsmith.adventofcode.aoc2024.day8

import dev.shaundsmith.adventofcode.core.Coordinate
import dev.shaundsmith.adventofcode.core.Grid
import dev.shaundsmith.adventofcode.core.PuzzleSolution
import dev.shaundsmith.adventofcode.core.toGrid

class Day8 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val grid = input.toGrid({ c -> c }, '.')
        val gridMax = Coordinate(grid.width() - 1, grid.height() - 1)

        return buildAntennaGroups(grid)
            .flatMap { it.calculateAntinodes(gridMax) }
            .toSet()
            .count()
            .toString()
    }

    override fun part2(input: List<String>): String {

        val grid = input.toGrid({ c -> c }, '.')
        val gridMax = Coordinate(grid.width() - 1, grid.height() - 1)

        return buildAntennaGroups(grid)
            .flatMap { it.calculateRepeatingAntinodes(gridMax) }
            .toSet()
            .count()
            .toString()
    }

    private fun buildAntennaGroups(grid: Grid<Char>): List<AntennaGroup> {

        val antennas = mutableMapOf<Char, MutableList<Coordinate>>()

        for (row in 0..<grid.height()) {
            for (column in 0..<grid.width()) {
                val coordinate = Coordinate(column, row)
                val value = grid[coordinate]
                if (value != '.') {
                    if (!antennas.containsKey(value)) {
                        antennas[value] = mutableListOf()
                    }
                    antennas[value]?.add(coordinate)
                }
            }
        }

        return antennas.map { AntennaGroup(it.key, it.value) }
    }


}