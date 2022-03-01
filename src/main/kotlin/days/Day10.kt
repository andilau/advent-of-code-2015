package days

@AdventOfCodePuzzle(
    name = "Elves Look, Elves Say",
    url = "https://adventofcode.com/2015/day/10",
    date = Date(day = 10, year = 2015)
)
class Day10(private val numbers: String) : Puzzle {

    override fun partOne(): Int =
        (1..40)
            .fold(numbers) { acc, _ -> lookAndSay(acc).joinToString("") }
            .length

    override fun partTwo(): Int =
        (1..50)
            .fold(numbers) { acc, _ -> lookAndSay(acc).joinToString("") }
            .length

    companion object {
        fun lookAndSay(look: String) = sequence {
            var begin = 0
            var end = 0
            while (end in look.indices) {
                while (end in look.indices && look[begin] == look[end]) end++
                yield("${end - begin}${look[begin]}")
                begin = end
            }
        }
    }
}