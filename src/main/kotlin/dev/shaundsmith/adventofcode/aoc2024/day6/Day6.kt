package dev.shaundsmith.adventofcode.aoc2024.day6

import dev.shaundsmith.adventofcode.core.*

class Day6 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val route = calculateRoute(input)

        return route.getAllPositions()
            .toSet()
            .count()
            .toString()
    }

    override fun part2(input: List<String>): String {

        val route = calculateRoute(input)

        val positions = route.getAllPositions()
        val turns = positions.indices
            .filter { it >= 1 }
            .count { positions[it] == positions[it - 1] }

        return turns.toString()
    }

    private fun calculateRoute(input: List<String>): DirectionalPath {

        val grid = input.toGrid({ i -> if (i == '#') Tile.SOLID else Tile.SPACE}, Tile.SPACE)
        val startY = input.indexOfFirst { it.contains("^") }
        val startX = input[startY].indexOf("^")

        val routeWalker = RouteWalker(grid)
        return routeWalker.walk(Coordinate(startX, startY))
    }

}