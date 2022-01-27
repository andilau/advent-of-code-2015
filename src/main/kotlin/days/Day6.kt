package days

@AdventOfCodePuzzle(
    name = "Probably a Fire Hazard",
    url = "https://adventofcode.com/2015/day/6",
    date = Date(day = 6, year = 2015)
)
class Day6(private val commands: List<String>) : Puzzle {
    private val size = 1000

    override fun partOne() =
        lights(BooleanGrid(size))

    override fun partTwo() =
        lights(IntGrid(size))

    private fun lights(init: Grid) =
        commands
            .map { Command.from(it) }
            .fold(init) { grid, command ->
                when (command.type) {
                    "turn on" -> for (x in command.from.first..command.to.first)
                        for (y in command.from.second..command.to.second) grid.turnOn(x, y)
                    "turn off" -> for (x in command.from.first..command.to.first)
                        for (y in command.from.second..command.to.second) grid.turnOff(x, y)
                    "toggle" -> for (x in command.from.first..command.to.first)
                        for (y in command.from.second..command.to.second) grid.toggle(x, y)
                }
                grid
            }
            .lights()

    interface Grid {
        fun turnOn(x: Int, y: Int)
        fun turnOff(x: Int, y: Int)
        fun toggle(x: Int, y: Int)
        fun lights(): Int
    }

    class BooleanGrid(private val size: Int) : Grid {
        private val cells = BooleanArray(size * size)
        override fun turnOn(x: Int, y: Int) { cells[at(y, x)] = true }
        override fun turnOff(x: Int, y: Int) { cells[at(y, x)] = false }
        override fun toggle(x: Int, y: Int) { cells[at(y, x)] = !cells[at(y, x)] }
        override fun lights() = cells.count { it }

        private fun at(y: Int, x: Int) = y * size + x
    }

    class IntGrid(private val size: Int) : Grid {
        private val cells = IntArray(size * size)
        override fun turnOn(x: Int, y: Int) { cells[at(y, x)]++ }
        override fun turnOff(x: Int, y: Int) { cells[at(y, x)] -= if (cells[at(y, x)] > 0) 1 else 0 }
        override fun toggle(x: Int, y: Int) { cells[at(y, x)] += 2 }
        override fun lights() = cells.sum()

        private fun at(y: Int, x: Int) = y * size + x
    }

    data class Command(val type: String, val from: Point, val to: Point) {
        companion object {
            fun from(line: String): Command {
                val regex = Regex("""(turn on|turn off|toggle)\s+(\d+),(\d+)\s+through\s+(\d+),(\d+)""")
                val (command, x1, y1, x2, y2) = regex.find(line)!!.destructured
                return Command(command, Point(x1.toInt(), y1.toInt()), Point(x2.toInt(), y2.toInt()))
            }
        }
    }
}