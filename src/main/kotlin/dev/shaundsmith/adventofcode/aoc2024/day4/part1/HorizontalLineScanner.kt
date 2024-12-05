package dev.shaundsmith.adventofcode.aoc2024.day4.part1

class HorizontalLineScanner(override val data: List<String>) : LineScanner {

    override fun toLines(): List<String> {

        return data.toList()
    }
}