package dev.shaundsmith.adventofcode.aoc2025.day5

import dev.shaundsmith.adventofcode.core.PuzzleSolution
import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigInteger

class Day5 : PuzzleSolution {

    private val logger = KotlinLogging.logger {}

    override fun part1(input: List<String>): String {

        val data = parse(input)

        return data.second.count { data.first.any { value -> value.contains(it) } }.toString()
    }

    override fun part2(input: List<String>): String {

        val data = parse(input)
        val ranges = data.first.sortedWith(compareBy({ it.first }, { it.last }))

        val reduced = reduce(ranges)
        val count = reduced.sumOf { sum(it) }

        return count.toString()
    }

    private fun sum(range: LongRange): BigInteger {
        val value = BigInteger.valueOf(range.last()).minus(BigInteger.valueOf(range.first())).plus(BigInteger.ONE)
        logger.info { "Count of $range is $value" }
        return value
    }

    private fun parse(input: List<String>): Pair<List<LongRange>, List<Long>> {

        var i = 0
        val ranges = mutableListOf<LongRange>()
        while (input[i].trim().isNotEmpty()) {
            val line = input[i].split("-")
            ranges.add(LongRange(line[0].toLong(), line[1].toLong()))
            i++
        }
        i++
        val values = mutableListOf<Long>()
        while (i != input.size) {
            values.add(input[i].toLong())
            i++
        }

        return Pair(ranges, values)
    }

    private fun reduce(ranges: List<LongRange>): List<LongRange> {
        var currentRange = ranges.first()
        val reduced = mutableListOf<LongRange>()

        for (i in 1..<ranges.size) {
            if (currentRange.last >= ranges[i].first) {
                currentRange = merge(currentRange, ranges[i])
            } else {
                reduced.add(currentRange)
                currentRange = ranges[i]
            }
        }
        reduced.add(currentRange)

        logger.info { "Original range set (${ranges.size}) = $ranges \n Reduced range set (${reduced.size}) = $reduced" }
        return reduced
    }

    private fun merge(range1: LongRange, range2: LongRange): LongRange {
        val min = minOf(range1.first, range2.first)
        val max = maxOf(range1.last, range2.last)
        return LongRange(min, max)
    }

}