package dev.shaundsmith.adventofcode.aoc2024.day4.part2

import dev.shaundsmith.adventofcode.core.Coordinate
import io.github.oshai.kotlinlogging.KotlinLogging

class XMasDetector() {

    private val logger = KotlinLogging.logger {}

    fun containsXMas(window: Window): Boolean {

        val isMas = isMas(listOf(window[Coordinate(0, 0)], window[Coordinate(1, 1)], window[Coordinate(2, 2)])) &&
                isMas(listOf(window[Coordinate(0, 2)], window[Coordinate(1, 1)], window[Coordinate(2, 0)]))

        logger.debug { "Window ${window.start} ${if (isMas) "is" else "is not"} Mas" }

        return isMas
    }

    private fun isMas(characters: List<Char>): Boolean {

        val joined = characters.joinToString("")
        return joined == "MAS" || joined == "SAM"
    }

}