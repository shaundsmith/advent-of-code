package dev.shaundsmith.adventofcode.aoc2024.day4.part1

import dev.shaundsmith.adventofcode.core.Coordinate

class DiagonalLineScanner(override val data: List<String>, private val reverse: Boolean) : LineScanner {

    override fun toLines(): List<String> {

        return if (reverse) getDiagonalsReversed() else getDiagonals()
    }

    private fun getDiagonals(): List<String> {

        val list = mutableListOf<String>()
        for (i in data.indices) {
            list.add(getDiagonalLine(Coordinate(if (reverse) data[0].length else 0, i)))
        }
        for (i in 1..<data[0].length) {
            list.add(getDiagonalLine(Coordinate(i, 0)))
        }

        return list
    }

    private fun getDiagonalsReversed(): List<String> {

        val list = mutableListOf<String>()
        for (i in data.indices) {
            list.add(getDiagonalLine(Coordinate(data[0].length - 1, i)))
        }
        for (i in 0..<data[0].length - 1) {
            list.add(getDiagonalLine(Coordinate(i, 0)))
        }

        return list
    }

    private fun getDiagonalLine(start: Coordinate): String {

        val lineCharacters = mutableListOf<Char>()
        var nextCoordinate = start
        val movement = if (reverse) -1 else 1

        while (nextCoordinate.isValid()) {

            lineCharacters.add(data[nextCoordinate.y][nextCoordinate.x])
            nextCoordinate = nextCoordinate.transpose(movement, 1)
        }

        return lineCharacters.joinToString("")
    }

    private fun Coordinate.isValid(): Boolean {

        return this.x < data[0].length && this.y < data.size &&
                this.x >= 0 && this.y >= 0
    }

}