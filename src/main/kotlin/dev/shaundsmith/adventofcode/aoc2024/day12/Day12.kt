package dev.shaundsmith.adventofcode.aoc2024.day12

import dev.shaundsmith.adventofcode.core.PuzzleSolution
import dev.shaundsmith.adventofcode.core.toGrid
import io.github.oshai.kotlinlogging.KotlinLogging

class Day12 : PuzzleSolution {

    private val logger = KotlinLogging.logger {}

    override fun part1(input: List<String>): String {

        val grid = input.toGrid(({ c -> c}), '.')
        val field = Field(grid)

        var totalCost = 0
        for (plot in field.plots) {
            val cost = plot.area * plot.perimeter
            logger.debug { "A region of '${plot.plants[0].character}' plants with price ${plot.area} * ${plot.perimeter} = $cost"}
            totalCost += cost
        }

        return totalCost.toString()
    }

    override fun part2(input: List<String>): String {

        val grid = input.toGrid(({ c -> c}), '.')
        val field = Field(grid)

        var totalCost = 0
        for (plot in field.plots) {
            val cost = plot.area * plot.vertexCount
            logger.debug { "A region of '${plot.plants[0].character}' plants with price ${plot.area} * ${plot.vertexCount} = $cost"}
            totalCost += cost
        }

        return totalCost.toString()
    }

}