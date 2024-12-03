package dev.shaundsmith.adventofcode.aoc2024.day2

import io.github.oshai.kotlinlogging.KotlinLogging


class Level(private val contents: List<Int>) {

    private val logger = KotlinLogging.logger {}

    fun isSafe(): Boolean {

        return if (contents[1] > contents[0]) isIncreasingSafe() else isDecreasingSafe()
    }

    fun isSafeWithDampening(): Boolean {

        if (isSafe()) {
            return true
        }

        return contents.indices
            .map { Level(contents.withoutItemAt(it)) }
            .any { it.isSafe() }
    }

    private fun isIncreasingSafe(): Boolean {

        for (i in 1..<contents.size) {
            if (contents[i] <= contents[i - 1] || contents[i] - contents[i - 1] > 3) {
                logger.debug { "Line $contents is not safe between position ${i-1} (${contents[i-1]}) and $i (${contents[i]})"}
                return false
            }
        }

        logger.debug { "Line $contents is safe"}
        return true
    }

    private fun isDecreasingSafe(): Boolean {

        for (i in 1..<contents.size) {
            if (contents[i] >= contents[i - 1] || contents[i - 1] - contents[i] > 3) {
                logger.debug { "Line $contents is not safe between position ${i-1} (${contents[i-1]}) and $i (${contents[i]})"}
                return false
            }
        }

        logger.debug { "Line $contents is safe"}
        return true
    }

    private fun <T> Iterable<T>.withoutItemAt(index: Int): List<T> =
        filterIndexed { i, _ -> i != index }

}