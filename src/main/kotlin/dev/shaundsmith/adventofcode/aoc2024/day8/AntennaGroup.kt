package dev.shaundsmith.adventofcode.aoc2024.day8

import dev.shaundsmith.adventofcode.core.Coordinate
import io.github.oshai.kotlinlogging.KotlinLogging

class AntennaGroup(private val frequency: Char, private val locations: List<Coordinate>) {

    private val logger = KotlinLogging.logger {}

    fun calculateAntinodes(gridMax: Coordinate): Set<Coordinate> {

        val antinodes = mutableSetOf<Coordinate>()
        for (i in 0..<locations.size - 1) {
            for (j in (i + 1)..<locations.size) {
                val nodes = calculateAntinodes(gridMax, locations[i], locations[j])
                antinodes.addAll(nodes)
            }
        }

        return antinodes
    }

    fun calculateRepeatingAntinodes(gridMax: Coordinate): Set<Coordinate> {

        val antinodes = mutableSetOf<Coordinate>()
        for (i in 0..<locations.size - 1) {
            for (j in (i + 1)..<locations.size) {
                var nodes: List<Coordinate>
                var multiplier = 0
                do {
                    nodes = calculateAntinodes(gridMax, locations[i], locations[j], multiplier)
                    antinodes.addAll(nodes)
                    multiplier++
                } while (nodes.isNotEmpty())
            }
        }

        return antinodes
    }

    private fun calculateAntinodes(gridMax: Coordinate, a: Coordinate, b: Coordinate, multiplier: Int = 1): List<Coordinate> {

        val distance = calculateDistance(a, b)

        val firstAntinode = Coordinate(a.x - distance.first * multiplier, a.y - distance.second * multiplier)
        val secondAntinode = Coordinate(b.x + distance.first * multiplier, b.y + distance.second * multiplier)

        val antinodes = mutableListOf<Coordinate>()
        logger.debug { "Found antinodes for $a and $b -> [$firstAntinode, $secondAntinode]" }
        if (firstAntinode.isValid(gridMax)) {
            antinodes.add(firstAntinode)
        }
        if (secondAntinode.isValid(gridMax)) {
            antinodes.add(secondAntinode)
        }

        logger.debug { "Returning valid antinodes for $a and $b -> [$antinodes]" }

        return antinodes
    }

    private fun calculateDistance(a: Coordinate, b: Coordinate): Pair<Int, Int> {

        return Pair(b.x - a.x, b.y - a.y)
    }

    private fun Coordinate.isValid(gridMax: Coordinate): Boolean {

        return this.x >= 0 && this.y >= 0 && this.x <= gridMax.x && this.y <= gridMax.y
    }

}