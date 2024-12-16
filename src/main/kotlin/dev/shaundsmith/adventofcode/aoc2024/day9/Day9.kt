package dev.shaundsmith.adventofcode.aoc2024.day9

import dev.shaundsmith.adventofcode.core.PuzzleSolution

class Day9 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val line = input[0]
        val builder = BitDataArray.Builder()

        var freeSpace = false
        for (i in line) {
            if (freeSpace) {
                builder.addFreeSpace(i.toString().toInt())
            } else {
                builder.addData(i.toString().toInt())
            }
            freeSpace = !freeSpace
        }
        val dataArray = builder.build()

        dataArray.defrag()

        return dataArray.getChecksum().toString()
    }

    override fun part2(input: List<String>): String {

        val line = input[0]
        val builder = BlockDataArray.Builder()

        var freeSpace = false
        for (i in line) {
            if (freeSpace) {
                builder.addFreeSpace(i.toString().toInt())
            } else {
                builder.addData(i.toString().toInt())
            }
            freeSpace = !freeSpace
        }
        val dataArray = builder.build()

        dataArray.defrag()

        return dataArray.getChecksum().toString()
    }

}