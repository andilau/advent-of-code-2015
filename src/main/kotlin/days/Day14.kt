package days

@AdventOfCodePuzzle(
    name = "Reindeer Olympics",
    url = "https://adventofcode.com/2015/day/14",
    date = Date(day = 14, year = 2015)
)
class Day14(input: List<String>) : Puzzle {
    private val reindeer = input.map(Reindeer::from)
    internal var duration = 2503

    override fun partOne(): Int = reindeer.winningDistanceBy(duration)

    override fun partTwo(): Int = reindeer.winningPointsBy(duration)

    private fun List<Reindeer>.winningDistanceBy(seconds: Int): Int = maxOf { it.distanceMovedOn(seconds) }

    private fun List<Reindeer>.winningPointsBy(seconds: Int): Int =
        (1..seconds)
            .fold(this.associate { it.distances.iterator() to 0 }) { distancesPoints, _ ->
                val associateWithDistance: Map<Iterator<Int>, Int> = distancesPoints.keys.associateWith { it.next() }
                val maxDistance = associateWithDistance.values.maxOf { it }
                val winners = associateWithDistance.filterValues { it == maxDistance }.keys
                distancesPoints.mapValues { if (it.key in winners) it.value + 1 else it.value }
            }
            .values.maxOf { it }

    data class Reindeer(val name: String, val speed: Int, val endure: Int, val rest: Int) {
        fun distanceMovedOn(seconds: Int) = distances.drop(seconds - 1).first()

        val distances
            get() = sequence {
                var distance = 0
                while (true) {
                    var endureFor = endure
                    while (--endureFor >= 0) {
                        distance += speed
                        yield(distance)
                    }
                    var restFor = rest
                    while (--restFor >= 0) yield(distance)
                }
            }

        companion object {
            private val PATTERN =
                Regex("""^(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.${'$'}""")

            fun from(line: String): Reindeer {
                return PATTERN.matchEntire(line)?.destructured?.let { (name, speed, endure, rest) ->
                    Reindeer(name, speed.toInt(), endure.toInt(), rest.toInt())
                }
                    ?: error("Invalid format: $line")
            }
        }
    }
}