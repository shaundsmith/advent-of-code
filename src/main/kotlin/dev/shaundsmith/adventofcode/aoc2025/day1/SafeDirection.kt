package dev.shaundsmith.adventofcode.aoc2025.day1

enum class SafeDirection {
    LEFT,
    RIGHT;

    companion object {
        fun of(value: Char): SafeDirection {
            return if (value == 'L') LEFT else RIGHT
        }
    }
}