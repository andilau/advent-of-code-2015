package days

@AdventOfCodePuzzle(
    name = "Aunt Sue",
    url = "https://adventofcode.com/2015/day/16",
    date = Date(day = 16, year = 2015)
)
class Day16(input: List<String>) : Puzzle {
    private val aunts = input.map(AuntSue::from).toSet()
    private val signature = measurements()

    override fun partOne(): Int {
        return with(aunts.toMutableSet()) {
            signature.forEach { (key, value) ->
                this.removeIf {
                    it.properties.containsKey(key) &&
                            it.properties[key] != value
                }
            }
            this
        }.single().id
    }

    override fun partTwo(): Int {
        return with(aunts.toMutableSet()) {
            signature.forEach { (key, value) ->
                this.removeIf {
                    it.properties.containsKey(key) &&
                            !when (key) {
                                "cats", "trees" -> it.properties[key]!! > value
                                "pomeranians", "goldfish" -> it.properties[key]!! < value
                                else ->
                                    it.properties[key] == value
                            }
                }
            }
            this
        }.single().id
    }

    data class AuntSue(val id: Int, val properties: Map<String, Int>) {
        companion object {
            fun from(line: String): AuntSue {
                val id = line.substringBefore(':').substringAfter("Sue ").toInt()
                val properties = line.substringAfter(": ")
                    .split(", ")
                    .associate { it.split(": ", limit = 2).let { it.first() to it.last().toInt() } }
                return AuntSue(id, properties)
            }
        }
    }

    private fun measurements() = """
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
}
