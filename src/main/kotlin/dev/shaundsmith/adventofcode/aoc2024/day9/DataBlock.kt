package dev.shaundsmith.adventofcode.aoc2024.day9

data class DataBlock(var size: Int, var id: Int?) {

    fun isFree(): Boolean {

        return id == null
    }

    override fun toString(): String {

        return if (id == null) ".".repeat(size) else id.toString().repeat(size)
    }

}
