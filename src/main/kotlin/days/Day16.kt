package days

@AdventOfCodePuzzle(
    name = "Aunt Sue",
    url = "https://adventofcode.com/2015/day/16",
    date = Date(day = 16, year = 2015)
)
class Day16(input: List<String>) : Puzzle {
    private val aunts = input.map(AuntSueDescription::from).toSet()
    private val signature = AuntSueSignature

    override fun partOne(): Int =
        aunts.toMutableSet()
            .apply { removeIf { !signature.matchesButOutdated(it) } }
            .single().id

    override fun partTwo(): Int =
        aunts.toMutableSet()
            .apply { removeIf { !signature.matchesWithRanges(it) } }
            .single().id

    data class AuntSueDescription(val id: Int, val properties: Map<String, Int>) {
        companion object {
            fun from(line: String): AuntSueDescription {
                val id = line.substringBefore(':').substringAfter("Sue ").toInt()
                val properties = line.substringAfter(": ")
                    .split(", ")
                    .associate { property ->
                        property
                            .split(": ", limit = 2)
                            .let { it.first() to it.last().toInt() }
                    }
                return AuntSueDescription(id, properties)
            }
        }
    }


    object AuntSueSignature {
        private val properties: Map<String, Int> = """
             children: 3
             cats: 7
             samoyeds: 2
             pomeranians: 3
             akitas: 0
             vizslas: 0
             goldfish: 5
             trees: 3
             cars: 2
             perfumes: 1
             """.trimIndent().lines()
            .associate { it.substringBefore(": ") to it.substringAfter(": ").toInt() }

        fun matchesButOutdated(description: AuntSueDescription): Boolean =
            description.properties.all { (key, value) ->
                properties.containsKey(key) && properties[key] == value
            }

        fun matchesWithRanges(description: AuntSueDescription) =
            description.properties.all { (key, value) ->
                properties.containsKey(key) &&
                        when (key) {
                            "cats", "trees" -> properties[key]!! < value
                            "pomeranians", "goldfish" -> properties[key]!! > value
                            else -> properties[key] == value
                        }
            }
    }
}
