package dev.shaundsmith.adventofcode.aoc2024.day14

import dev.shaundsmith.adventofcode.core.Coordinate
import dev.shaundsmith.adventofcode.core.Grid
import dev.shaundsmith.adventofcode.core.PuzzleSolution
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.math.abs

class Day14(val height: Int = 103, val width: Int = 101) : PuzzleSolution {

    private val logger = KotlinLogging.logger {}

    override fun part1(input: List<String>): String {

        val robotCoordinates = input.map { toRobot(it) }
            .map { it.move(100) }
            .map { wrapInGrid(it) }

        val centreX = (width - 1) / 2
        val centreY = (height - 1) / 2

        val quadrants = mutableMapOf(Pair(1, 0), Pair(2, 0), Pair(3, 0), Pair(4, 0))
        for (robotCoordinate in robotCoordinates) {

            if (robotCoordinate.x < centreX && robotCoordinate.y < centreY) {
                quadrants[1] = quadrants[1]!!.plus(1)
            } else if (robotCoordinate.x < centreX && robotCoordinate.y > centreY) {
                quadrants[2] = quadrants[2]!!.plus(1)
            } else if (robotCoordinate.x > centreX && robotCoordinate.y < centreY) {
                quadrants[3] = quadrants[3]!!.plus(1)
            } else if (robotCoordinate.x > centreX && robotCoordinate.y > centreY) {
                quadrants[4] = quadrants[4]!!.plus(1)
            }
        }

        if (logger.isDebugEnabled()) {
            drawGrid(robotCoordinates, centreX, centreY)
        }

        return (quadrants[1]!!.times(quadrants[2]!!).times(quadrants[3]!!).times(quadrants[4]!!)).toString()
    }

    override fun part2(input: List<String>): String {

        var robots = input.map { toRobot(it) }

        var i = 1
        do {

            val robotCoordinates = robots.map { it.move(i) }.map { wrapInGrid(it) }

            if (robotCoordinates.count { isGrouped(it, robotCoordinates) } > 10) {
                logger.error { "Found at iteration $i" }
                drawGrid(robotCoordinates, 999, 999)
            }

            i++
        } while (i < 10000)

        return ""
    }

    private fun toRobot(value: String): Robot {

        val splitValue = value.split(" ")

        val start = splitValue[0].split("=")[1]
        val velocity = splitValue[1].split("=")[1]

        val startCoordinates = start.split(",")
        val velocityPair = velocity.split(",")

        return Robot(
            Coordinate(startCoordinates[0].toInt(), startCoordinates[1].toInt()),
            Pair(velocityPair[0].toInt(), velocityPair[1].toInt()))
    }

    private fun wrapInGrid(coordinate: Coordinate): Coordinate {

        val wrapped = Coordinate(wrap(coordinate.x, width), wrap(coordinate.y, height))

        logger.debug { "Wrapped $coordinate to $wrapped"}

        return wrapped
    }

    private fun wrap(value: Int, maxValue: Int): Int {

        val wrapped: Int
        if (value < 0) {
            wrapped = (maxValue - (abs(value) % maxValue)) % maxValue
        } else {
            wrapped = value % maxValue
        }

        return wrapped
    }

    private fun drawGrid(coordinates: List<Coordinate>, centreX: Int, centreY: Int) {
        val grid = Grid.ofSize(width, height, '.')

        for (x in 0..<grid.width()) {
            for (y in 0..<grid.height()) {
                if (x == centreX || y == centreY) {
                    grid[Coordinate(x, y)] = ' '
                } else if (coordinates.contains(Coordinate(x, y))) {
                    grid[Coordinate(x, y)] = coordinates.count { it.x == x && it.y == y }.toString()[0]
                }
            }
        }

        logger.info { "\n" + grid }
    }

    private fun isGrouped(coordinate: Coordinate, robots: List<Coordinate>): Boolean {

        return robots.containsAll(coordinate.adjacent())
    }

}