package dev.shaundsmith.adventofcode.aoc2024.day4

import dev.shaundsmith.adventofcode.aoc2024.day4.part1.DiagonalLineScanner
import dev.shaundsmith.adventofcode.aoc2024.day4.part1.HorizontalLineScanner
import dev.shaundsmith.adventofcode.aoc2024.day4.part1.VerticalLineScanner
import dev.shaundsmith.adventofcode.aoc2024.day4.part1.WordFinder
import dev.shaundsmith.adventofcode.aoc2024.day4.part2.WindowMaker
import dev.shaundsmith.adventofcode.aoc2024.day4.part2.XMasDetector
import dev.shaundsmith.adventofcode.core.PuzzleSolution

class Day4 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        return listOf(
            WordFinder("horizontal", "XMAS", HorizontalLineScanner(input)),
            WordFinder("vertical", "XMAS", VerticalLineScanner(input)),
            WordFinder("diagonal1", "XMAS", DiagonalLineScanner(input, false)),
            WordFinder("diagonal2", "XMAS", DiagonalLineScanner(input, true))
        )
            .sumOf { it.find() }
            .toString()
    }

    override fun part2(input: List<String>): String {

        val detector = XMasDetector()
        val windowMaker = WindowMaker(3, 3)

        return windowMaker.make(input)
            .count { detector.containsXMas(it) }
            .toString()
    }

}