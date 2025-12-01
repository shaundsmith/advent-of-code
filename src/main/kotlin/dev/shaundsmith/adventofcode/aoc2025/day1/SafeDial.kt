package dev.shaundsmith.adventofcode.aoc2025.day1

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.math.floor

class SafeDial() {

    private val logger = KotlinLogging.logger {}
    private var position: Int = 50
    var zeroCount: Int = 0; private set
    var zeroPasses: Int = 0; private set

    fun rotate(value: String) {

        rotate(SafeDirection.of(value[0]), Integer.parseInt(value.substring(1)))
    }

    fun rotate(direction: SafeDirection, amount: Int) {

        val passesZeroCount = countZeroPasses(direction, amount)
        zeroPasses += passesZeroCount

        position = Math.floorMod(if (direction == SafeDirection.LEFT) position - amount else position + amount, 100)
        if (position == 0) {
            zeroCount++
        }
        logger.debug { "Moved to $position ($direction, $amount)" }
        logger.debug { "Passed zero $passesZeroCount times" }
    }

    private fun countZeroPasses(direction: SafeDirection, amount: Int): Int {

        var count = floor(amount / 100.0).toInt()
        if (position == 0) {
            return count
        }
        if (direction == SafeDirection.LEFT && (position - amount) < 0 && position - (amount % 100) < 0) {
            count++
        } else if (direction == SafeDirection.RIGHT && (position + amount) > 100 && position + (amount % 100) > 100) {
            count ++
        }

        return count
    }


}