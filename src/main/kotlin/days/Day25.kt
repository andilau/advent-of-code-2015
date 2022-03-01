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
        Regex("""\d+""").findAll(prompt).map(MatchResult::value).map(String::toInt).toList().take(2)
            .let { numbers ->
                if (numbers.size < 2) error("Unable to extract row and column from prompt: $prompt")
                row = numbers[0].also { number -> check(number > 0) }
                col = numbers[1].also { number -> check(number > 0) }
            }
    }

    override fun partOne() = codes().drop(index(row, col) - 1).first()

    override fun partTwo() = Unit

    companion object {
        private const val seed = 20151125L

        internal fun codes() = generateSequence(seed) { (it * 252533) % 33554393 }

        /*
        - side n = row + col - 2 (1-based)
        - area t = n * (n + 1) / 2 (area of triangle)
        - total index = t(#) + col(&)
        -- n --
        # # # #
        # # #
        # # &
        # &
        &
        */

        internal fun index(row: Int, col: Int): Int = triangular(row + col - 2) + col

        /**
         * https://en.wikipedia.org/wiki/Triangular_number
         */

        private fun triangular(n: Int) = n * (n + 1) / 2
    }
}