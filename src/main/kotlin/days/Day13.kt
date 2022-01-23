package days

@AdventOfCodePuzzle(
    name = "Knights of the Dinner Table",
    url = "https://adventofcode.com/2015/day/13",
    date = Date(day = 13, year = 2015)
)
class Day13(happinessStatements: List<String>) : Puzzle {
    private val happinessMap = happinessStatements.parseStatements()

    override fun partOne(): Int =
        happinessMap
            .guests()
            .arrangements()
            .maxOf { arrangement -> arrangement.totalHappiness(happinessMap) }

    override fun partTwo(): Int =
        happinessMap.toMutableMap()
            .apply {
                happinessMap.guests().forEach { guest ->
                    this[Guest("Me") to guest] = 0
                    this[guest to Guest("Me")] = 0
                }
            }.let {
                it
                    .guests()
                    .arrangements()
                    .maxOf { arrangement -> arrangement.totalHappiness(it) }
            }

    private fun Map<Pair<Guest, Guest>, Int>.guests() =
        keys.map { it.first }.toSet()

    private fun List<Guest>.totalHappiness(mutableMap: Map<Pair<Guest, Guest>, Int>): Int {
        return this.sumOf { person ->
            val leftNeighbor = this[(indexOf(person) - 1 + size) % size]
            val rightNeighbor = this[(indexOf(person) + 1) % size]
            mutableMap[person to leftNeighbor]!! + mutableMap[person to rightNeighbor]!!
        }
    }

    private fun List<String>.parseStatements() =
        associate { line ->
            val (guest, gainLose, happiness, neighbor) = STATEMENT_PATTERN.matchEntire(line)!!.destructured
            Pair(Guest(guest), Guest(neighbor)) to if (gainLose == "gain") happiness.toInt() else -happiness.toInt()
        }.toMap()

    companion object {
        private val STATEMENT_PATTERN =
            Regex("""^(\w+) would (gain|lose) (\d+) happiness units by sitting next to (\w+).$""")
    }

    data class Guest(val name: String)
}