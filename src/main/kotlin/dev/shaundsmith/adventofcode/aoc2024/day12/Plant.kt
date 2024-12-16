package dev.shaundsmith.adventofcode.aoc2024.day12

import dev.shaundsmith.adventofcode.core.Coordinate

data class Plant(val character: Char,
                 val coordinate: Coordinate,
                 val adjacentSamePatches: List<Coordinate>,
                 val adjacentNonSamePatches: List<Coordinate>) {

    val numberOfNonSamePatches get() = adjacentNonSamePatches.size

}
