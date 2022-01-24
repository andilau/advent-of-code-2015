package days

@AdventOfCodePuzzle(
    name = "Aunt Sue",
    url = "https://adventofcode.com/2015/day/16",
    date = Date(day = 16, year = 2015)
)
class Day16(input: List<String>) : Puzzle {
    private val ants = input.map(AntSue::from).toSet().toMutableSet()

    override fun partOne(): Int {
        val props = """
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
     """.trimIndent().lines().map { it.substringBefore(": ") to it.substringAfter(": ").toInt() }.toMap()

        props.forEach { (key, value) ->
            ants.removeIf {
                it.properties.containsKey(key) &&
                it.properties[key] != value}
            println("ants.size = ${ants.size}")
        }


        return ants.single().id
    }

    override fun partTwo(): Int = 0

    data class AntSue(val id: Int, val properties: Map<String, Int>) {
        companion object {
            fun from(line: String): AntSue {
                /*Sue 1: cars: 9, akitas: 3, goldfish: 0
                Sue 2: akitas: 9, children: 3, samoyeds: 9
                Sue 3: trees: 6, cars: 6, children: 4
                Sue 4: trees: 4, vizslas: 4, goldfish: 9
                Sue 5: akitas: 9, vizslas: 7, cars: 5
                */
                val no = line.substringBefore(':').substringAfter("Sue ").toInt()
                val properties = line.substringAfter(": ")
                    .split(", ").associate { it.split(": ", limit = 2).let { it.first() to it.last().toInt() } }

                return AntSue(no, properties)
            }
        }
    }
}