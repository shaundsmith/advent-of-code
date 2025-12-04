package dev.shaundsmith.adventofcode.aoc2025.day3

import io.github.oshai.kotlinlogging.KotlinLogging

class BatteryBank(val batteries: List<Int>) {

    private val logger = KotlinLogging.logger {}

    companion object {
        fun of(string: String): BatteryBank {
            return BatteryBank(string.toCharArray().map { Integer.parseInt(it.toString()) })
        }
    }

    fun calculateLargestJolts(size: Int): Long {
        val largestJolts = calculateLargestJolts(size, batteries)
        logger.debug { "Found largest in ${batteries} - $largestJolts" }
        return largestJolts.toLong()
    }

    private fun calculateLargestJolts(size: Int, remaining: List<Int>): String {
        val indexOfLargest = remaining.subList(0, remaining.size - (size - 1)).indexOfLargest()
        if (size == 1) {
            return remaining[indexOfLargest].toString()
        }
        val nextLargestStart = indexOfLargest + 1

        return remaining[indexOfLargest].toString() + calculateLargestJolts(size - 1, remaining.subList(nextLargestStart, remaining.size))
    }

    private fun List<Int>.indexOfLargest(): Int {
        return this.indexOfFirst { this.max() == it }
    }



}