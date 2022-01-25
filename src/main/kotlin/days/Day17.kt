package days

@AdventOfCodePuzzle(
    name = "No Such Thing as Too Much",
    url = "https://adventofcode.com/2015/day/17",
    date = Date(day = 17, year = 2015)
)
class Day17(input: List<String>) : Puzzle {
    private val containers = input.map(String::toInt)
    internal var storage = 150

    override fun partOne() = containers
        .combinations()
        .filter { it.sum() == storage }
        .onEach { println(it) }
        .count()

    override fun partTwo() = containers
        .combinations()
        .filter { it.sum() == storage }
        .groupingBy { it.size }.eachCount()
        .toSortedMap()
        .let { map -> map[map.firstKey()]!! }
}