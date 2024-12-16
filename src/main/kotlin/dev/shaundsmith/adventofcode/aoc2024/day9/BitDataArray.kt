package dev.shaundsmith.adventofcode.aoc2024.day9

import io.github.oshai.kotlinlogging.KotlinLogging

class BitDataArray(private val data: MutableList<Int?>) {

    private val logger = KotlinLogging.logger {}

    private var freeSpacePointer: Int = 0
    private var dataPointer: Int = data.size - 1

    fun defrag() {

        var nextFreeSpace = findNextFreeSpace()
        var nextDataBlock = findLastDataBlock()

        logger.debug { "Initial disk state: $data" }
        logger.debug { "Next data block: $nextDataBlock. Next free space: $nextFreeSpace" }

        while (nextFreeSpace != null && nextDataBlock > nextFreeSpace) {

            move(nextFreeSpace, nextDataBlock)

            nextFreeSpace = findNextFreeSpace()
            nextDataBlock = findLastDataBlock()
            logger.debug { "Data defrag progress: $data" }
        }

    }

    fun getChecksum(): Long {

        val checksum = data.mapIndexed { index, value -> if (value == null) 0L else (index.toLong() * value.toLong()) }
            .sum()
        logger.debug { "Checksum: $checksum" }
        return checksum
    }

    private fun move(to: Int, from: Int) {

        if (data[to] != null) {
            throw RuntimeException("Cannot move to position $to. $to already contains ${data[to]}")
        }

        data[to] = data[from]
        data[from] = null
    }

    private fun findNextFreeSpace(): Int? {

        for (i in freeSpacePointer..<data.size) {
            if (data[i] == null) {
                freeSpacePointer = i
                return i
            }
        }

        return null
    }

    private fun findLastDataBlock(): Int {

        for (i in dataPointer downTo 0) {
            if (data[i] != null) {
                dataPointer = i
                return i
            }
        }

        return -1
    }

    class Builder {

        private val data: MutableList<Int?> = mutableListOf()
        private var id = 0

        fun addFreeSpace(length: Int) {
            repeat(length) { data.add(null) }
        }

        fun addData(length: Int) {
            repeat(length) { data.add(id) }
            id++
        }

        fun addData(length: Int, customId: Int) {
            repeat(length) { data.add(customId) }
        }

        fun build(): BitDataArray {

            return BitDataArray(data.toMutableList())
        }

    }

}