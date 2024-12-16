package dev.shaundsmith.adventofcode.aoc2024.day10

import dev.shaundsmith.adventofcode.core.Coordinate
import dev.shaundsmith.adventofcode.core.Grid
import dev.shaundsmith.adventofcode.core.Path
import io.github.oshai.kotlinlogging.KotlinLogging

class TrailMapper(private val map: Grid<Int>) {

    private val logger = KotlinLogging.logger {}

    fun findTrails(): List<Path> {

        return findStarts().flatMap { mapPath(it) }
    }

    fun findUniqueTrailheads(): Set<Pair<Coordinate, Coordinate>> {

        val paths = findStarts().flatMap { mapPath(it) }
        val uniqueTrailheads = paths
            .map { Pair(it.getInitialPosition(), it.getCurrentPosition()) }
            .toSet()

        logger.debug { "Found ${uniqueTrailheads.size} unique trailheads: \n" +
                "\t${uniqueTrailheads.joinToString("\n\t")}" }

        return uniqueTrailheads
    }

    private fun findStarts(): List<Coordinate> {

        val starts = map.findCoordinates { it == 0 }

        logger.debug { "Found ${starts.size} starting coordinates: $starts" }

        return starts
    }

    private fun mapPath(start: Coordinate): List<Path> {

        val queue = ArrayDeque<Path>()
        val finished = mutableListOf<Path>()
        queue.add(Path(start))

        do {
            val currentPath = queue.removeFirst()
            val nextSteps = mapNextSteps(currentPath)
            for (nextStep in nextSteps) {
                if (map[nextStep.getCurrentPosition()] == 9) {
                    finished.add(nextStep)
                } else {
                    queue.add(nextStep)
                }
            }
        } while (queue.isNotEmpty())

        logger.debug { "Found ${finished.size} paths: \n\t${finished.joinToString( "\n\t")}" }

        return finished
    }

    private fun mapNextSteps(path: Path): List<Path> {

        val currentPosition = path.getCurrentPosition()
        val currentValue = map[currentPosition]

        val toTry = listOf(
            currentPosition.transpose(0, -1),
            currentPosition.transpose(1, 0),
            currentPosition.transpose(0, 1),
            currentPosition.transpose(-1, 0))

        return toTry.filter { map.isValid(it) }
            .filter { currentValue + 1 == map[it] }
            .map { path.append(it) }
    }


}