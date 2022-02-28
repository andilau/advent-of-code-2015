package days


@AdventOfCodePuzzle(
    name = "Let It Snow",
    url = "https://adventofcode.com/2015/day/25",
    date = Date(day = 25, year = 2015)
)
class Day25(prompt: String) : Puzzle {
    val row: Int
    val col: Int

    init {
        Regex("""\d+""").findAll(prompt).map(MatchResult::value).map(String::toInt).take(2).let {
            row = it.first()
            col = it.last()
        }
    }

    override fun partOne() = codes().drop(index(row, col) - 1).first()

    override fun partTwo() = Unit

    companion object {
        // To continue, please consult the code grid in the manual.  Enter the code at row 2947, column 3029.
        const val seed = 20151125L

        internal fun index(row: Int, col: Int): Int = (row + col - 2) * (row + col - 1) / 2 + col

        internal fun codes() = generateSequence(seed) {
            (it * 252533) % 33554393
        }
    }
}