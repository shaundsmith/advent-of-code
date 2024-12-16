package dev.shaundsmith.adventofcode.aoc2024.day12

import dev.shaundsmith.adventofcode.core.Coordinate
import dev.shaundsmith.adventofcode.core.Grid

class Field(grid: Grid<Char>) {

    private val plants = mutableMapOf<Coordinate, Plant>()
    private val mutablePlots = mutableListOf<Plot>()
    val plots get() = mutablePlots.toList()

    init {
        buildPlants(grid)
        buildPlots()
    }

    private fun buildPlants(grid: Grid<Char>) {

        for (x in 0..<grid.width()) {
            for (y in 0..<grid.height()) {
                val coordinate = Coordinate(x, y)
                val adjacent = coordinate.adjacent()
                val plantType = grid[coordinate]
                plants[coordinate] = Plant(plantType,
                    coordinate,
                    adjacent.filter { grid.isValid(it) && grid[it] == plantType },
                    adjacent.filter { !grid.isValid(it) || grid[it] != plantType })
            }
        }
    }

    private fun buildPlots() {

        val seenCoordinates = mutableSetOf<Coordinate>()

        for (plant in plants) {
            if (seenCoordinates.contains(plant.key)) {
                continue
            }

            val coordinates = getPlotCoordinates(plant.value)
            seenCoordinates.addAll(coordinates)
            mutablePlots.add(Plot(coordinates.map { plants[it]!! }))
        }
    }

    private fun getPlotCoordinates(plant: Plant): Set<Coordinate> {

        val coordinates = mutableSetOf<Coordinate>()
        val toCheck = ArrayDeque<Coordinate>()
        toCheck.add(plant.coordinate)

        while(toCheck.isNotEmpty()) {
            val target = toCheck.removeFirst()
            if (!coordinates.contains(target)) {
                coordinates.add(target)
                toCheck.addAll(plants[target]?.adjacentSamePatches!!)
            }
        }

        return coordinates
    }

    // Turn each cell into a plant
    // Build up plots
    // Merge plots if possible

    // Area = size of plot
    // Perimeter = number of non-same plants around the plants.

}