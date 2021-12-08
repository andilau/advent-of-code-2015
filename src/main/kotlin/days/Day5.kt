package days

@AdventOfCodePuzzle(
    name = "Doesn't He Have Intern-Elves For This?",
    url = "https://adventofcode.com/2015/day/5",
    date = Date(day = 5, year = 2015)
)
class Day5(private val words: List<String>) : Puzzle {

    override fun partOne() = words.count { word ->
        word.containsAtLeastThreeVowels()
                && word.containsLetterTwiceInRow()
                && word.containsNotAbCdPqXy()
    }

    override fun partTwo() = words.count { word ->
        word.containsPairThatAppearsAtLeastTwice()
                && word.containsLetterRepeatsWithOneLetterInBetween()
    }

    private fun String.containsAtLeastThreeVowels() =
        count { it in "aeiou" } >= 3

    private fun String.containsLetterTwiceInRow() =
        windowed(2).any { it.first() == it.last() }

    private fun String.containsNotAbCdPqXy() =
        setOf("ab", "cd", "pq", "xy")
            .none { this.contains(it) }

    private fun String.containsPairThatAppearsAtLeastTwice() =
        windowed(2)
            .any { indexOf(it) < lastIndexOf(it) - 1 }

    private fun String.containsLetterRepeatsWithOneLetterInBetween() =
        windowed(3)
            .any { it.first() == it.last() }
}