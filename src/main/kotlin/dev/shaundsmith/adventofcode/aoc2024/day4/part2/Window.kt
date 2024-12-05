package dev.shaundsmith.adventofcode.aoc2024.day4.part2

import dev.shaundsmith.adventofcode.core.Coordinate

data class Window(val start: Coordinate, val data: List<String>) {

    operator fun get(position: Coordinate): Char {
        return data[position.y][position.x]
    }

}
