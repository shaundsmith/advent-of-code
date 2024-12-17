package dev.shaundsmith.adventofcode.aoc2024.day14

import dev.shaundsmith.adventofcode.core.Coordinate
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.math.roundToInt

class Robot(start: Coordinate, velocity: Pair<Int, Int>) {

    private val logger = KotlinLogging.logger {}

    private val slope: Double = if (velocity.first.toDouble() == 0.0) 0.0 else velocity.second / velocity.first.toDouble()
    private val constant: Double = start.y - (slope * start.x)
    private val velocity: Int = velocity.first
    private val start: Int = start.x

    fun move(steps: Int): Coordinate {

        val changeInX = steps * velocity

        val endX = (changeInX + start)
        val endY = ((endX * slope) + constant).roundToInt()

        logger.debug { "Moved to ($endX, $endY)" }
        return Coordinate(endX, endY)
    }

}