package dev.shaundsmith.adventofcode.aoc2025.day4

import dev.shaundsmith.adventofcode.core.Coordinate
import dev.shaundsmith.adventofcode.core.Grid
import io.github.oshai.kotlinlogging.KotlinLogging

class ForkliftMap(val grid: Grid<Char>) {

    private val logger = KotlinLogging.logger {}

    fun countRemovablePaper(): Int {
        var accessiblePaper: List<Coordinate>
        var count = 0
        do {
            accessiblePaper = findAccessiblePaper()
            count += accessiblePaper.size
            accessiblePaper.forEach { grid[it] = '.' }
        } while (accessiblePaper.isNotEmpty())
        return count
    }

    fun findAccessiblePaper(): List<Coordinate> {
        val accessiblePaper = grid.findCoordinates { value, coordinate -> value == '@' && isAccessible(coordinate) }
        logger.debug { "Found accessible paper: $accessiblePaper" }

        return accessiblePaper
    }

    private fun isAccessible(coordinate: Coordinate): Boolean {
        return coordinate.adjacentWithDiagonals()
            .filter { grid.isValid(it) }
            .count { grid[it] == '@'} < 4
    }

}