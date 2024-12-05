package dev.shaundsmith.adventofcode.aoc2024.day4.part1

class VerticalLineScanner(override val data: List<String>) : LineScanner {

    override fun toLines(): List<String> {

        val lines = mutableMapOf<Int, MutableList<Char>>()

        for (i in 0..<data[0].length) {
            for (j in data.indices) {
                if (!lines.containsKey(i)) {
                    lines[i] = mutableListOf()
                }
                lines[i]?.add(data[j][i])
            }
        }

        return lines.map { (_, value) -> value.joinToString("") }.toList()
    }
}