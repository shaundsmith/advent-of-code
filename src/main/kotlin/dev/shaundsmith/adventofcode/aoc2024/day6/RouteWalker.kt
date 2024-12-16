package dev.shaundsmith.adventofcode.aoc2024.day6

import dev.shaundsmith.adventofcode.core.Coordinate
import dev.shaundsmith.adventofcode.core.Direction
import dev.shaundsmith.adventofcode.core.DirectionalPath
import dev.shaundsmith.adventofcode.core.Grid
import io.github.oshai.kotlinlogging.KotlinLogging

class RouteWalker(val map: Grid<Tile>) {

    private val logger = KotlinLogging.logger {}

    fun walk(start: Coordinate): DirectionalPath {

        val path = DirectionalPath(start, Direction.NORTH)

        val currentPosition = path.getCurrentPosition()
        var stepped = true
        do {
            stepped = step(path)
        } while (stepped)

        return path
    }

    private fun step(path: DirectionalPath): Boolean {

        val currentPosition = path.getCurrentPosition()
        val nextSpace = currentPosition.first.transpose(currentPosition.second.xModifier, currentPosition.second.yModifier)
        if (!map.isValid(nextSpace)) {
            logger.debug { "Left the area" }
            return false
        } else if (map[nextSpace] == Tile.SOLID) {
            val newDirection = turn(currentPosition.second)
            logger.debug { "$nextSpace is solid. Turning right - new direction: $newDirection" }
            path.moveTo(currentPosition.first, newDirection)
        } else {
            logger.debug { "Moving forward to $nextSpace" }
            path.moveTo(nextSpace, currentPosition.second)
        }
        return true
    }

    private fun turn(currentDirection: Direction): Direction {

        var newDirection: Direction
        if (currentDirection == Direction.NORTH) {
            newDirection = Direction.EAST
        } else if (currentDirection == Direction.EAST) {
            newDirection = Direction.SOUTH
        } else if (currentDirection == Direction.SOUTH) {
            newDirection = Direction.WEST
        } else {
            newDirection = Direction.NORTH
        }

        return newDirection
    }

}