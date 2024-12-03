package dev.shaundsmith.adventofcode

import dev.shaundsmith.adventofcode.core.FileLoader
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Paths
import kotlin.io.path.listDirectoryEntries

val fileLoader = FileLoader()

fun main() {

    val year = getLatestYear()
    val nextDay = getLatestDay(year) + 1

    writeTemplate(
        fileLoader.loadFile("templates/Day.template"),
        "src/main/kotlin/dev/shaundsmith/adventofcode/aoc$year/day$nextDay",
        "Day$nextDay.kt",
        year,
        nextDay
    )

    writeTemplate(
        fileLoader.loadFile("templates/Test.template"),
        "src/test/kotlin/dev/shaundsmith/adventofcode/aoc$year",
        "Day${nextDay}Test.kt",
        year,
        nextDay
    )

    writeTemplate(
        arrayListOf(),
        "src/main/resources/aoc$year/day$nextDay",
        "input.txt",
        year,
        nextDay
    )

    writeTemplate(
        arrayListOf(),
        "src/test/resources/aoc$year/day$nextDay",
        "test-input-1.txt",
        year,
        nextDay
    )

    writeTemplate(
        arrayListOf(),
        "src/test/resources/aoc$year/day$nextDay",
        "test-input-2.txt",
        year,
        nextDay
    )
}

fun getLatestYear(): Int {

    val yearPath = Paths.get("src/main/kotlin/dev/shaundsmith/adventofcode")
        .listDirectoryEntries("aoc*")
        .last()
    val yearDirectoryName = yearPath.fileName.toString()

    return yearDirectoryName.replace("aoc", "").toInt()
}

fun getLatestDay(year: Int): Int {

    return Paths.get("src/main/kotlin/dev/shaundsmith/adventofcode/aoc$year")
        .listDirectoryEntries("day*")
        .count()
}

fun writeTemplate(template: List<String>, destination: String, destinationName: String, year: Int, day: Int) {

    val directory = File(destination)

    directory.mkdirs()
    val file = File("$destination/$destinationName")
    check(!file.exists()) {
        "File $file already exists"
    }
    file.createNewFile()

    val fileContents = template.joinToString(System.lineSeparator()) {
        it.replace("\$year\$", year.toString()).replace("\$day\$", day.toString())
    }

    file.writeText(fileContents, StandardCharsets.UTF_8)
}