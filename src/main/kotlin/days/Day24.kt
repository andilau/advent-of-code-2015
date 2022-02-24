package days

@AdventOfCodePuzzle(
    name = "It Hangs in the Balance",
    url = "https://adventofcode.com/2015/day/24",
    date = Date(day = 24, year = 2015)
)
class Day24(private val weights: List<Int>) : Puzzle {

    override fun partOne() = findBest(weights, 3)?.quantumEntanglement() ?: error("Not found")

    override fun partTwo() = findBest(weights, 4)?.quantumEntanglement() ?: error("Not found")

    private fun findBest(weights: List<Int>, size: Int): Set<Int>? {
        if (size < 1) return null
        if (size == 1) return weights.toSet()

        val targetSum = weights.sum() / size
        val groups = weights.combinationsFit(targetSum).map { it.toSet() }.toList()

        groups
            .sortedWith(compareBy<Set<Int>> { it.size }.then(compareBy { it.quantumEntanglement() }))
            .forEach { best ->
                val candidates = groups.filter { group -> best.none { it in group } }
                if (candidates.canPartitionBy(size - 1)) return best
            }
        return null
    }

    private fun Collection<Set<Int>>.canPartitionBy(parts: Int): Boolean {
        if (parts == 1) return isNotEmpty()

        return firstOrNull { group ->
            filter { complement -> complement.none { it in group } }
                .canPartitionBy(parts - 1)
        } != null
    }

    private fun Iterable<Int>.quantumEntanglement(): Long = fold(1L) { product, number -> product * number }
}