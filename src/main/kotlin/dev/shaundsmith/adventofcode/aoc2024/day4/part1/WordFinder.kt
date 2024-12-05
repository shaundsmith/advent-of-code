package dev.shaundsmith.adventofcode.aoc2024.day4.part1

import io.github.oshai.kotlinlogging.KotlinLogging

class WordFinder(private val label: String, private val word: String, private val lineScanner: LineScanner) {

    private val logger = KotlinLogging.logger {}

    fun find(): Int {

        return lineScanner.toLines()
            .sumOf { find(it) }
    }

    private fun find(string: String): Int {

        val count = search(string, word) + search(string.reversed(), word)

        logger.debug { "Found $count occurrences of $word in $label"}

        return count
    }

    private fun search(searchTarget: String, word: String): Int {

        val windows = searchTarget.windowed(word.length, 1, false)

        return windows.count { word == it }
    }

}