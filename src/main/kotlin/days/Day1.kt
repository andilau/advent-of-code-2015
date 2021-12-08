package days

@AdventOfCodePuzzle(
    name = "Not Quite Lisp",
    url = "https://adventofcode.com/2015/day/1",
    date = Date(day = 1, year = 2015)
)
class Day1(private val parentheses: String) : Puzzle {
    override fun partOne() =
        parentheses.fold(0, Int::upDownByChar)

    override fun partTwo() =
        parentheses.runningFold(0, Int::upDownByChar)
            .indexOfFirst { floor -> floor == -1 }

}

private fun Int.upDownByChar(c: Char) =
    this + when (c) {
        '(' -> +1
        ')' -> -1
        else -> 0
    }
