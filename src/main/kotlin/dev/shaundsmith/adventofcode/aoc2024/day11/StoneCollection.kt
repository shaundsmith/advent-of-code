package dev.shaundsmith.adventofcode.aoc2024.day11

class StoneCollection(private var initStones: List<Long>) {

    val numberOfStones get() = stones.map { it.value }.sum()
    private var stones = mutableMapOf<Long, Long>()

    init {
        initStones.forEach { if (stones.containsKey(it)) stones[it] = stones[it]!! + 1 else stones[it] = 1 }
    }

    fun step(times: Int) {
        repeat(times) { step() }
    }

    private fun step() {

        val newStones = mutableMapOf<Long, Long>()

        for (stone in stones) {
            if (stone.key == 0L) {
                newStones.increment(1L, stone.value)
            } else if (stone.key.toString().length % 2 == 0) {
                val string = stone.key.toString()
                val midpoint = string.length / 2
                val split = string.take(midpoint) to string.substring(midpoint)

                newStones.increment(split.first.toLong(), stone.value)
                newStones.increment(split.second.toLong(), stone.value)
            } else {
                val result = stone.key * 2024
                newStones.increment(result, stone.value)
            }
        }

        stones = newStones
    }

    private fun MutableMap<Long, Long>.increment(value: Long, qty: Long) {

        if (this.containsKey(value)) {
            this[value] = this[value]!! + qty
        } else {
            this[value] = qty
        }
    }

}