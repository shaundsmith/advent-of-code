package dev.shaundsmith.adventofcode.aoc2024.day4.part1

interface LineScanner {

    val data: List<String>

    fun toLines(): List<String>

}