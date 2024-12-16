package dev.shaundsmith.adventofcode.aoc2024.day13

import dev.shaundsmith.adventofcode.core.PuzzleSolution

class Day13 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val craneMovements = parse(input)

        var cost = 0L
        for (craneMovement in craneMovements) {
            val solution = craneMovement.solve()
            if (solution == null) {
                continue
            }

            cost += (solution.first * 3L) + solution.second
        }

        return cost.toString()
    }

    override fun part2(input: List<String>): String {

        val craneMovements = parse(input, 10000000000000L)

        var cost = 0L
        for (craneMovement in craneMovements) {
            val solution = craneMovement.solve()
            if (solution == null) {
                continue
            }

            cost += (solution.first * 3L) + solution.second
        }

        return cost.toString()
    }

    private val buttonPattern = Regex("Button [AB]: X\\+(\\d+), Y\\+(\\d+)")
    private val prizePattern = Regex("Prize: X=(\\d+), Y=(\\d+)")
    private fun parse(input: List<String>, modifier: Long = 0L): List<CraneMovement> {

        val craneMovements = mutableListOf<CraneMovement>()
        for (i in 0..input.size step 4) {
            val (aX, aY) = buttonPattern.find(input[i])!!.destructured
            val (bX, bY) = buttonPattern.find(input[i + 1])!!.destructured
            val (pX, pY) = prizePattern.find(input[i + 2])!!.destructured

            craneMovements.add(
                CraneMovement((i/4).toString(),
                Pair(aX.toDouble(), aY.toDouble()),
                Pair(bX.toDouble(), bY.toDouble()),
                Pair(pX.toLong() + modifier, pY.toLong() + modifier))
            )
        }

        return craneMovements
    }


}