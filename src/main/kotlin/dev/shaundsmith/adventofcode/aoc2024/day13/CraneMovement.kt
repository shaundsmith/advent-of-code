package dev.shaundsmith.adventofcode.aoc2024.day13

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.math.abs
import kotlin.math.round
import kotlin.math.roundToLong

class CraneMovement(
    private val id: String,
    private val aMovement: Pair<Double, Double>,
    private val bMovement: Pair<Double, Double>,
    private val prize: Pair<Long, Long>) {

    private val logger = KotlinLogging.logger {}

    fun solve(): Pair<Long, Long>? {

        val a = aMovement.first
        val b = bMovement.first
        val c = aMovement.second
        val d = bMovement.second

        val result: Pair<Double, Double>
        if (abs(a) > abs(c)) {
            val f = (prize.first * c) / a
            val g = b * c / a
            val y = (prize.second - f) / (d - g)
            result = Pair((f - g * y) / c, y)
        } else {
            val f = prize.second * a / c
            val g = d * a / c
            val y = (prize.first - f) / (b - g)
            result = Pair((f - g * y) / a, y)
        }

        verify(result)

        if (result.first.isInteger() && result.second.isInteger()) {
            logger.debug { "Crane $id has solution: A=${result.first.roundToLong()}, B=${result.second.roundToLong()}" }
            return Pair(result.first.roundToLong(), result.second.roundToLong())
        } else {
            logger.debug { "Crane $id does not have solution" }
            return null
        }
    }

    private fun verify(result: Pair<Double, Double>) {

        val firstCalc = aMovement.first * result.first + bMovement.first * result.second
        val secondCalc = aMovement.second * result.first + bMovement.second * result.second
        val tick = if (firstCalc.roundToLong() == prize.first && secondCalc.roundToLong() == prize.second) "✅" else "❌"

        logger.warn { "Expecting destination $prize - got ($firstCalc, $secondCalc) $tick" }
    }

    private fun Double.isInteger(): Boolean {
        return this.roundToDecimals(3) == this.roundToLong().toDouble()
    }

    private fun Double.roundToDecimals(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

}