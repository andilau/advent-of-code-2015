package days

@AdventOfCodePuzzle(
    name = "Perfectly Spherical Houses in a Vacuum",
    url = "https://adventofcode.com/2015/day/3",
    date = Date(day = 3, year = 2015)
)
class Day3(private val instructions: String) : Puzzle {

    override fun partOne() = instructions
        .asSequence()
        .deliverHouses()
        .distinct()
        .count()

    override fun partTwo() =
        with(instructions.asSequence()) {
            val deliveredBySanta = filterIndexed { index, _ -> index.isEven() }.deliverHouses().toSet()
            val deliveredByRoboSanta = filterIndexed { index, _ -> index.isOdd() }.deliverHouses().toSet()
            (deliveredBySanta union deliveredByRoboSanta).count()
        }

    private fun Sequence<Char>.deliverHouses(): Sequence<House> =
        runningFold(House(0, 0)) { house, direction ->
            when (direction) {
                '<' -> house.copy(x = house.x - 1)
                '>' -> house.copy(x = house.x + 1)
                '^' -> house.copy(y = house.y - 1)
                'v' -> house.copy(y = house.y + 1)
                else -> error("Unknown direction: $direction")
            }
        }

    private data class House(val x: Int, val y: Int)

    private fun Int.isEven() = this % 2 == 0
    private fun Int.isOdd() = !isEven()
}