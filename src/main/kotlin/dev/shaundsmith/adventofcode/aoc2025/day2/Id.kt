package dev.shaundsmith.adventofcode.aoc2025.day2

import io.github.oshai.kotlinlogging.KotlinLogging

class Id(val range: LongRange) {

    private val logger = KotlinLogging.logger {}

    val invalidIds: List<Long>
        get() = calculateInvalidIds()

    val complexInvalidIds: List<Long>
        get() = calculateComplexInvalidIds()

    companion object {
        fun of(value: String): Id {
            val splitValue = value.split("-")
            return Id(splitValue[0].toLong()..(splitValue[1].toLong()))
        }
    }

    private fun calculateInvalidIds(): List<Long> {
        val invalidIds = range.filter { it.isInvalidId(2) }
        logger.debug { "Range $range contains ${invalidIds.size} invalid ids." }
        return invalidIds
    }

    private fun calculateComplexInvalidIds(): List<Long> {
        val invalidIds = range.filter { it.isInvalidId() }
        logger.debug { "Range $range contains ${invalidIds.size} invalid ids." }
        return invalidIds
    }

    private fun Long.isInvalidId(): Boolean {
        val string = this.toString()
        return IntRange(2, string.length).any { isInvalidId(it) }
    }

    private fun Long.isInvalidId(patternSize: Int): Boolean {
        val string = this.toString()

        if (string.length % patternSize != 0) {
            return false
        }

        val parts = mutableListOf<String>()
        for (i in 0 until string.length step string.length / patternSize) {
            parts.add(string.substring(i, i + string.length / patternSize))
        }

        val isInvalid = parts.toSet().size == 1
        logger.debug { "Checking $this for repeats of size $patternSize: ${parts.joinToString(",")} ==== $isInvalid" }
        return isInvalid
    }

}