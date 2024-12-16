package dev.shaundsmith.adventofcode.aoc2024.day9

import io.github.oshai.kotlinlogging.KotlinLogging

class BlockDataArray(private val data: MutableList<DataBlock>) {

    private val logger = KotlinLogging.logger {}

    private var idPointer: Int = data.findLast { !it.isFree() }?.id!!

    fun defrag() {

        var nextDataBlock = findBlock()
        var nextFreeSpace = findNextFreeSpaceOfSize(data[nextDataBlock].size)

        logger.debug { "Initial disk state: $data" }

        while (idPointer > 0) {

            logger.debug { "Next data block: $nextDataBlock. Next free space: $nextFreeSpace. ID: ${idPointer + 1}" }
            if (nextFreeSpace >= 0 && nextFreeSpace < nextDataBlock) {
                move(nextFreeSpace, nextDataBlock)
            }

            nextDataBlock = findBlock()
            nextFreeSpace = findNextFreeSpaceOfSize(data[nextDataBlock].size)
            logger.debug { "Data defrag progress: ${data.joinToString("")}" }
        }

    }

    fun getChecksum(): Long {

        val bitDataArrayBuilder = BitDataArray.Builder()

        data.forEach { if (it.isFree()) bitDataArrayBuilder.addFreeSpace(it.size) else bitDataArrayBuilder.addData(it.size, it.id!!) }

        return bitDataArrayBuilder.build().getChecksum()
    }

    private fun move(to: Int, from: Int) {

        if (!data[to].isFree()) {
            throw RuntimeException("Cannot move to position $to. $to already contains ${data[to]}")
        }

        val fromSize = data[from].size
        val fromId = data[from].id
        data[to].size -= fromSize
        data[from].id = null
        data.add(to, DataBlock(fromSize, fromId))
    }

    private fun findNextFreeSpaceOfSize(length: Int): Int {

        return data.indexOfFirst { it.isFree() && it.size >= length }
    }

    private fun findBlock(): Int {

        val blockPosition = data.indexOfLast { it.id == idPointer }
        idPointer--

        return blockPosition
    }

    class Builder {

        private val data: MutableList<DataBlock> = mutableListOf()
        private var id = 0

        fun addFreeSpace(length: Int) {
            data.add(DataBlock(length, null))
        }

        fun addData(length: Int) {
            data.add(DataBlock(length, id))
            id++
        }

        fun build(): BlockDataArray {

            return BlockDataArray(data.toMutableList())
        }

    }

}