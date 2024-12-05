package dev.shaundsmith.adventofcode.aoc2024.day4.part2

import dev.shaundsmith.adventofcode.core.Coordinate
import io.github.oshai.kotlinlogging.KotlinLogging

class WindowMaker(private val height: Int, private val width: Int) {

    private val logger = KotlinLogging.logger {}

    fun make(input: List<String>): List<Window> {

        val windows = mutableListOf<Window>()
        for (i in 0..(input[0].length - width)) {
            for (j in 0..(input.size - height)) {
                val window = mutableListOf<String>()
                for (x in 0..<height) {
                    window.add(input[j + x].substring(i, i + width))
                }
                windows.add(Window(Coordinate(i, j), window))
            }
        }

        logger.debug { "Found ${windows.size} windows" }

        return windows
    }

}