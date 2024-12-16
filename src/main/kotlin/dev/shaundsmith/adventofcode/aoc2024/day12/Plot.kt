package dev.shaundsmith.adventofcode.aoc2024.day12

import dev.shaundsmith.adventofcode.core.Coordinate
import dev.shaundsmith.adventofcode.core.Direction
import dev.shaundsmith.adventofcode.core.DirectionalPath
import io.github.oshai.kotlinlogging.KotlinLogging

class Plot(val plants: List<Plant>) {

    private val logger = KotlinLogging.logger {}

    val perimeter get() = plants.sumOf { it.numberOfNonSamePatches }
    val area get() = plants.size
    val vertexCount get() = vertices

    private val plantCoordinates: List<Coordinate>
    private var vertices = 0
    private val alreadySeenPaths = mutableSetOf<Coordinate>()

    init {
        plantCoordinates = plants.map { it.coordinate }
        do {
            val startingPlant = plants
                .firstOrNull { !alreadySeenPaths.contains(it.coordinate.transpose(0, -1))
                        && it.adjacentNonSamePatches.contains(it.coordinate.transpose(0, -1)) }
            if (startingPlant == null) {
                break
            }
            calculateOutsidePath(startingPlant)
        } while (true)
    }

    fun contains(coordinate: Coordinate): Boolean {

        return plants.any { it.coordinate == coordinate }
    }

    private fun calculateOutsidePath(startingPlant: Plant) {

        val startingPosition = startingPlant.adjacentNonSamePatches.first { startingPlant.coordinate.transpose(0, -1) == it}

        val path = DirectionalPath(startingPosition, Direction.EAST)

        do {
            val currentPosition = path.getCurrentPosition()
            if (!currentPosition.hasPlantToRight()) {
                val newDirection = currentPosition.second.rotateRight()
                vertices++
                path.moveTo(currentPosition.first, newDirection)
                path.moveTo(currentPosition.first.transpose(newDirection.xModifier, newDirection.yModifier), newDirection)
            } else {
                val nextPosition =
                    currentPosition.first.transpose(currentPosition.second.xModifier, currentPosition.second.yModifier)
                if (plantCoordinates.contains(nextPosition)) {
                    val newDirection = currentPosition.second.rotateLeft()
                    vertices++
                    path.moveTo(
                        currentPosition.first,
                        newDirection
                    )
                } else {
                    path.moveTo(nextPosition, currentPosition.second)
                }
            }
        } while (path.getCurrentPosition() != Pair(startingPosition, Direction.EAST))
        logger.debug { "Found $vertices for plot ${plants[0].character}:\n\t${path.getAllPositions().joinToString("\n\t")}" }
        alreadySeenPaths.addAll(path.getAllPositions())
    }

    private fun Direction.rotateRight(): Direction {

        return when (this) {
            Direction.NORTH -> Direction.EAST
            Direction.EAST -> Direction.SOUTH
            Direction.SOUTH -> Direction.WEST
            Direction.WEST -> Direction.NORTH
        }
    }

    private fun Direction.rotateLeft(): Direction {

        return when (this) {
            Direction.NORTH -> Direction.WEST
            Direction.EAST -> Direction.NORTH
            Direction.SOUTH -> Direction.EAST
            Direction.WEST -> Direction.SOUTH
        }
    }

    private fun Pair<Coordinate, Direction>.hasPlantToRight(): Boolean {

        val right = this.second.rotateRight()
        val toRightCoordinate = this.first.transpose(right.xModifier, right.yModifier)

        return plantCoordinates.contains(toRightCoordinate)
    }

}