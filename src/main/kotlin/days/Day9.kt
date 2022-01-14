package days

@AdventOfCodePuzzle(
    name = "All in a Single Night",
    url = "https://adventofcode.com/2015/day/9",
    date = Date(day = 9, year = 2015)
)
class Day9(input: List<String>) : Puzzle {
    private val legDistances: Map<Pair<Location, Location>, Int> = input.parseDistances()

    internal val totals: List<Int> =
        legDistances
            .keys.flatMap { it.toList() }.toSet()
            .permutations()
            .map { route -> route.totalDistance(legDistances) }

    override fun partOne(): Int =
        totals.minOrNull() ?: error("No distances")

    override fun partTwo(): Int =
        totals.maxOrNull() ?: error("No distances")

    private fun List<String>.parseDistances() =
        map { line -> line.split(" = ", limit = 2) }
            .associate { (fromTo, distance) ->
                fromTo
                    .split(" to ")
                    .let { Location(it.first()) to Location(it.last()) } to distance.toInt()
            }

    private fun Iterable<Location>.totalDistance(legDistances: Map<Pair<Location, Location>, Int>) =
        zipWithNext().fold(0) { total, leg ->
            total + (legDistances[leg]
                ?: legDistances[leg.let { it.second to it.first }]
                ?: error("Distance not found: $leg"))
        }

    data class Location(val name: String)
}