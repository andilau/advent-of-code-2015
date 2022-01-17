package days

@AdventOfCodePuzzle(
    name = "JSAbacusFramework.io",
    url = "https://adventofcode.com/2015/day/12",
    date = Date(day = 12, year = 2015)
)
class Day12(private val json: List<String>) : Puzzle {

    override fun partOne(): Int =
        json.sumOf { line -> line.sumOfNumbers() }

    override fun partTwo(): Int =
        generateSequence(json) { json ->
            json.map { it.replaceJsonArray() }
                .map { it.replaceJsonObject() }
        }
            .zipWithNext()
            .first { it.first == it.second }
            .first
            .sumOf { it.sumOfNumbers() }

    private fun String.sumOfNumbers() =
        NUMBER_PATTERN.findAll(this).sumOf { it.value.toInt() }

    private fun String.replaceJsonObject() =
        JSON_OBJECT_PATTERN
            .replace(this) { result ->
                if (result.groups[1]!!.value.contains("\"red\"")) "0"
                else result.groups[1]!!.value.split(',', ':').sumOf { it.toIntOrNull() ?: 0 }.toString()
            }

    private fun String.replaceJsonArray() =
        JSON_ARRAY_PATTERN
            .replace(this) { result ->
                result.groups[1]?.value?.split(',')?.sumOf { it.toIntOrNull() ?: 0 }.toString()
            }

    companion object {
        private val NUMBER_PATTERN = Regex("""(-?\d+)""")
        private val JSON_ARRAY_PATTERN = Regex("""\[([A-Za-z0-9-,"]+)]""")
        private val JSON_OBJECT_PATTERN = Regex("""\{([A-Za-z0-9-,":]+)}""")
    }
}