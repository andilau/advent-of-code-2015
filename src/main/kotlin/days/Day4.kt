package days

@AdventOfCodePuzzle(
    name = "The Ideal Stocking Stuffer",
    url = "https://adventofcode.com/2015/day/4",
    date = Date(day = 4, year = 2015)
)
class Day4(private val secret: String) : Puzzle {
    override fun partOne() =
        generateSequence(0) { it.plus(1) }
            .first { it.findMD5WithPrefix("00000") }

    override fun partTwo() =
        generateSequence(0) { it.plus(1) }
            .first { it.findMD5WithPrefix("000000") }

    private fun Int.findMD5WithPrefix(prefix: String) = (secret + this).toMD5().startsWith(prefix)
}