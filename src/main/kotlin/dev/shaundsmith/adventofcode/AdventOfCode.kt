package dev.shaundsmith.adventofcode

import dev.shaundsmith.adventofcode.core.FileLoader
import dev.shaundsmith.adventofcode.core.PuzzleSolution
import kotlin.system.exitProcess

fun main() {

    println("Please enter year to run:")
    val year = readln()
    if (!year.matches(Regex("\\d+"))) {
        quitWithInvalidYear(year)
        return
    }

    println("Please enter day to run:")
    val day = readln()
    if (!day.matches(Regex("\\d+"))) {
        quitWithInvalidDay(day)
        return
    }

    val solutionClass: Class<*>
    try {
        solutionClass = Class.forName("dev.shaundsmith.adventofcode.aoc$year.day$day.Day$day")
    } catch (e: ClassNotFoundException) {
        quitWithInvalidDay(day)
        return
    }

    val solutionInstance: PuzzleSolution = solutionClass.getDeclaredConstructor().newInstance() as PuzzleSolution

    val fileLoader = FileLoader()

    val part1Solution = solutionInstance.part1(fileLoader.loadFile("aoc$year/day$day/input.txt"))
    println("Day $day, Part 1 Solution: `$part1Solution`")

    val part2Solution = solutionInstance.part2(fileLoader.loadFile("aoc$year/day$day/input.txt"))
    println("Day $day, Part 2 Solution: `$part2Solution`")

}

fun quitWithInvalidYear(year: String) {
    println("Invalid day $year")
    exitProcess(1)
}

fun quitWithInvalidDay(day: String) {
    println("Invalid day $day")
    exitProcess(1)
}

