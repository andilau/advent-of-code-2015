package days

@AdventOfCodePuzzle(
    name = "Not Quite Lisp",
    url = "https://adventofcode.com/2015/day/1",
    date = Date(day = 1, year = 2015)
)
class Day1(private val parentheses: String) : Puzzle {

    override fun partOne() = parentheses
        .fold(0, ::upDownByChar)

    override fun partTwo() = parentheses
        .filter { it in "()" }
        .runningFold(0, ::upDownByChar)
        .indexOfFirst { it < 0 }

    companion object {
        private fun upDownByChar(floor: Int, c: Char) =
            floor + when (c) {
                '(' -> +1
                ')' -> -1
                else -> 0
            }
    }
}