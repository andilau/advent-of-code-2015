package days

@AdventOfCodePuzzle(
    name = "No Such Thing as Too Much",
    url = "https://adventofcode.com/2015/day/17",
    date = Date(day = 17, year = 2015)
)
class Day17(input: List<String>) : Puzzle {
    private val containers = input.map(String::toInt)
    internal var storage = 150
    private val lists by lazy {
        containers
            .combinations()
            .filter { it.sum() == storage }
    }

    private val fast by lazy { containers.combinationsSum(storage) }

    override fun partOne() = fast
        //.onEach { println(it) }
        .count()

    override fun partTwo() = fast
        .groupingBy { it.size }.eachCount()
        .toSortedMap()
        .let { map -> map[map.firstKey()]!! }
}

 fun List<Int>.combinationsSum(target: Int): Sequence<List<Int>> = sequence {
    if (target == 0)
        yield(emptyList())
    else {
        val options = this@combinationsSum
        options.withIndex().forEach { (index, container) ->
            if (container <= target) {
                subList(index + 1, options.size).combinationsSum(target - container).forEach {
                    yield(listOf(container) + it)
                }
            }
        }
    }
}