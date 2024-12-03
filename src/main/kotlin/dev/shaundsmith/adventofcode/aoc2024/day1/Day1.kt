package dev.shaundsmith.adventofcode.aoc2024.day1

import dev.shaundsmith.adventofcode.core.PuzzleSolution
import kotlin.math.abs

class Day1 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val orderedLists = parseAndOrderList(input)
        val differences = mutableListOf<Long>()
        for (i in 0..<orderedLists.first.size) {
            differences.add(abs(orderedLists.first[i] - orderedLists.second[i]))
        }

        return differences.sum().toString()
    }

    override fun part2(input: List<String>): String {

        val orderedLists = parseAndOrderList(input)
        val similarity = mutableListOf<Long>()
        for (value in orderedLists.first) {
            similarity.add(value * orderedLists.second.count { it == value })
        }
        return similarity.sum().toString()
    }

    private val whitespace = "\\s+".toRegex()
    private fun parseAndOrderList(input: List<String>): Pair<List<Long>, List<Long>> {

        val list1 = mutableListOf<Long>()
        val list2 = mutableListOf<Long>()
        input.map { it.split(whitespace) }
            .forEach { list1.add(it[0].toLong()); list2.add(it[1].toLong()) }

        list1.sort()
        list2.sort()

        return Pair(list1, list2)
    }

}