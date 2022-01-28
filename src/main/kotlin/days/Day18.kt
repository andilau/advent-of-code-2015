package days

@AdventOfCodePuzzle(
    name = "Like a GIF For Your Yard",
    url = "https://adventofcode.com/2015/day/18",
    date = Date(day = 18, year = 2015)
)
class Day18(input: List<String>) : Puzzle {
    private val grid = BooleanGrid.from(input)
    internal var steps = 100

    override fun partOne(): Int = (1..steps)
        .fold(grid) { current, _ -> current.next() }
        .lights()

    override fun partTwo(): Int = (1..steps)
        .fold(grid) { current, _ -> current.turnCornersOn().next().turnCornersOn() }
        .lights()

    interface Grid {
        fun turnOn(x: Int, y: Int)
        fun turnOff(x: Int, y: Int)
        fun toggle(x: Int, y: Int)
        fun lights(): Int
        fun on(x: Int, y: Int): Boolean
    }

    fun BooleanGrid.next(): BooleanGrid {
        val new = BooleanGrid(size)
        for (x in 0 until size) for (y in 0 until size)
            if (on(x, y))
                when (neighbors(x, y)) {
                    2, 3 -> new.turnOn(x, y)
                }
            else
                when (neighbors(x, y)) {
                    3 -> new.turnOn(x, y)
                }
        return new
    }

    private fun BooleanGrid.neighbors(x: Int, y: Int): Int {
        var on = if (on(x, y)) -1 else 0
        for (dy in -1..1) for (dx in -1..1) {
            if (on(x + dx, y + dy)) on++
        }
        return on
    }

    private fun BooleanGrid.turnCornersOn(): BooleanGrid {
        turnOn(0, 0)
        turnOn(size - 1, 0)
        turnOn(0, size - 1)
        turnOn(size - 1, size - 1)
        return this
    }

    class BooleanGrid(val size: Int) : Grid {
        private val lights = mutableSetOf<Point>()

        override fun turnOn(x: Int, y: Int) {
            lights += Point(x, y)
        }

        override fun turnOff(x: Int, y: Int) {
            lights -= Point(x, y)
        }

        override fun toggle(x: Int, y: Int) =
            Point(x, y).let { if (it in lights) lights -= it else lights += it }

        override fun on(x: Int, y: Int) = Point(x, y) in lights

        override fun lights() = lights.size

        companion object {
            fun from(lines: Collection<String>): BooleanGrid {
                val grid = BooleanGrid(lines.size)
                lines.forEachIndexed { y, row -> row.forEachIndexed { x, c -> if (c == '#') grid.turnOn(x, y) } }
                return grid
            }
        }
    }
}