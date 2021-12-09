package days

@AdventOfCodePuzzle(
    name = "Probably a Fire Hazard",
    url = "https://adventofcode.com/2000/day/6",
    date = Date(day = 6, year = 2000)
)
class Day6(private val commands: List<String>) : Puzzle {

    override fun partOne() =
        lights(BooleanGrid(1000))

    override fun partTwo() =
        lights(IntGrid(1000))

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

    class BooleanGrid(size: Int) : Grid {
        private val on = BooleanArray(size * size)
        override fun turnOn(x: Int, y: Int) { on[y * 1000 + x] = true }
        override fun turnOff(x: Int, y: Int) { on[y * 1000 + x] = false }
        override fun toggle(x: Int, y: Int) { on[y * 1000 + x] = !on[y * 1000 + x] }
        override fun lights() = on.count { it }
    }

    class IntGrid(size: Int) : Grid {
        private val on = IntArray(size * size)
        override fun turnOn(x: Int, y: Int) { on[y * 1000 + x]++ }
        override fun turnOff(x: Int, y: Int) { on[y * 1000 + x] -= if (on[y * 1000 + x] > 0) 1 else 0 }
        override fun toggle(x: Int, y: Int) { on[y * 1000 + x] += 2 }
        override fun lights() = on.sum()
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