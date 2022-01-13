package days

@AdventOfCodePuzzle(
    name = "Matchsticks",
    url = "https://adventofcode.com/2015/day/8",
    date = Date(day = 8, year = 2015)
)
class Day8(private val strings: List<String>) : Puzzle {

    override fun partOne(): Int = strings.sumOf { it.codeLength() - it.decodedLength() }

    override fun partTwo(): Int = strings.sumOf { it.encodedLength() - it.codeLength() }

    private fun String.codeLength() = length

    private fun String.decodedLength(): Int {
        var characterCount = 0
        var index = 0
        while (index in indices) {
            when (this[index++]) {
                '\\' -> index += when (this[index]) {
                    '\\' -> 1
                    '\"' -> 1
                    'x' -> 3
                    else -> error("Undefined escape in $this at $index")
                }
            }
            characterCount++
        }
        return characterCount - doubleQuoteCount
    }

    private fun String.encodedLength(): Int =
        fold(doubleQuoteCount) { length, char ->
            length + when (char) {
                '\\' -> 2
                '\"' -> 2
                else -> 1
            }
        }

    companion object {
        private const val doubleQuoteCount = 2
    }
}