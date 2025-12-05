package dev.shaundsmith.adventofcode.core

class Grid<T>(val contents: Array<Array<T>>) {

    companion object {

        inline fun <reified T> clone(original: Grid<T>): Grid<T> {
            return Grid(original.contents.map { a -> a.copyOf() }.toTypedArray())
        }

        inline fun <reified T> ofSize(width: Int, height: Int, default: T): Grid<T> {

            return Grid(Array(width) { _ -> Array(height) { _ -> default } })
        }
    }

    operator fun get(position: Coordinate): T {
        return contents[position.x][position.y]
    }

    operator fun set(position: Coordinate, value: T) {
        contents[position.x][position.y] = value
    }

    fun width(): Int {
        return contents.size
    }

    fun height(): Int {
        return contents[0].size
    }

    fun column(x: Int): List<T> {
        return IntRange(0, height() - 1)
            .map { contents[x][it] }
    }

    fun row(y: Int): List<T> {
        return IntRange(0, width() - 1)
            .map { contents[it][y] }
    }

    operator fun iterator(): Iterator<Array<T>> {

        return contents.iterator()
    }

    fun findCoordinate(predicate: (T) -> Boolean): Coordinate? {

        for (x in 0..<width()) {
            for (y in 0..<height()) {
                if (predicate.invoke(get(Coordinate(x, y)))) {
                    return Coordinate(x, y)
                }
            }
        }

        return null
    }

    fun findCoordinates(predicate: (T) -> Boolean): List<Coordinate> {

        val coordinates = ArrayList<Coordinate>()
        for (x in 0..<width()) {
            for (y in 0..<height()) {
                if (predicate.invoke(get(Coordinate(x, y)))) {
                    coordinates.add(Coordinate(x, y))
                }
            }
        }

        return coordinates
    }

    fun findCoordinates(predicate: (T, Coordinate) -> Boolean): List<Coordinate> {
        val coordinates = ArrayList<Coordinate>()
        for (x in 0..<width()) {
            for (y in 0..<height()) {
                val coordinate = Coordinate(x, y)
                if (predicate.invoke(get(coordinate), coordinate)) {
                    coordinates.add(Coordinate(x, y))
                }
            }
        }

        return coordinates
    }

    fun isValid(coordinate: Coordinate): Boolean {

        val isNegative = coordinate.x < 0 || coordinate.y < 0
        val isLarger = coordinate.x >= width() || coordinate.y >= height()

        return !isNegative && !isLarger
    }

    override fun toString(): String {

        val sb = StringBuilder()
        for (y in 0 until contents[0].size) {
            for (element in contents) {
                sb.append(element[y])
            }
            sb.append("\n")
        }

        return sb.toString()
    }
}