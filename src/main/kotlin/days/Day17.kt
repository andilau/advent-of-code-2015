package days

@AdventOfCodePuzzle(
    name = "No Such Thing as Too Much",
    url = "https://adventofcode.com/2015/day/17",
    date = Date(day = 17, year = 2015)
)
class Day17(input: List<String>) : Puzzle {
    private val containers = input.map(String::toInt)
    internal var capacity = 150

    private val combinationsOfContainers by lazy { containers.combinationsFit(capacity) }

    override fun partOne() = combinationsOfContainers
        //.onEach { println(it) }
        .count()

    override fun partTwo() = combinationsOfContainers
        .groupingBy { it.size }.eachCount()
        .toSortedMap()
        .let { map -> map[map.firstKey()]!! }
}


