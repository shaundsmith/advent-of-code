package dev.shaundsmith.adventofcode.aoc2024.day5

import dev.shaundsmith.adventofcode.core.PuzzleSolution
import io.github.oshai.kotlinlogging.KotlinLogging

class Day5 : PuzzleSolution {

    private val logger = KotlinLogging.logger {}

    override fun part1(input: List<String>): String {

        val parsedData = parse(input)
        val rules = parsedData.first
        val lists = parsedData.second

        return lists.filter { rules.isValid(it) }
            .sumOf { it.getMiddleValue() }
            .toString()
    }

    override fun part2(input: List<String>): String {

        val parsedData = parse(input)
        val rules = parsedData.first
        val lists = parsedData.second

        return lists.filter { !rules.isValid(it) }
            .map { rules.makeValid(it) }
            .sumOf { it.getMiddleValue() }
            .toString()
    }

    private fun parse(input: List<String>): Pair<PageOrderingRules, List<List<Int>>> {

        val rulesBuilder = PageOrderingRules.Builder()
        var line = 0;
        while (line < input.size) {
            if (input[line].isEmpty()) {
                break
            }
            rulesBuilder.addRule(parseRule(input[line]))
            line++
        }
        logger.debug { "Loaded rules ${rulesBuilder.build()}" }

        line++
        val lists = mutableListOf<List<Int>>()
        while (line < input.size) {
            lists.add(parseList(input[line]))
            line++
        }

        return Pair(rulesBuilder.build(), lists)
    }

    private fun parseRule(line: String): Pair<Int, Int> {

        val parts = line.split("|")

        return Pair(parts[0].toInt(), parts[1].toInt())
    }

    private fun parseList(line: String): List<Int> {
        val parts = line.split(",")

        return parts.map { it.toInt() }
    }

    fun List<Int>.getMiddleValue(): Int {

        val middle = this.lastIndex / 2

        return this[middle]
    }

}