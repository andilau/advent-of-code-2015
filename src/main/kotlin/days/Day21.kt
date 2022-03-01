package days

import days.Day21.Item.ItemType
import kotlin.math.max

@AdventOfCodePuzzle(
    name = "RPG Simulator 20XX",
    url = "https://adventofcode.com/2015/day/21",
    date = Date(day = 21, year = 2015)
)
class Day21(val input: List<String>) : Puzzle {
    private val items = parseItems()
    private val boss = Boss.from(input)

    override fun partOne(): Int = items
        .equipPlayer()
        .filter { it isWinner boss.copy() }
        .minOf { it.cost() }

    override fun partTwo(): Int = items
        .equipPlayer()
        .filter { it isLooser boss.copy() }
        .maxOf { it.cost() }

    private fun Collection<Item>.equipPlayer() = sequence {
        for (weapon in filter { it.type == ItemType.WEAPON })
            for (armor in filter { it.type == ItemType.ARMOR } + null)
                with(filter { it.type == ItemType.RING } + null) {
                    for (ring1 in this.withIndex())
                        for (ring2 in this.drop(ring1.index + 1))
                            yield(Player(100, weapon, armor, ring1.value, ring2))
                }
    }

    fun testCollection(): Int = items.equipPlayer().count()

    sealed interface Playable {
        var health: Int
        val attack: Int
        val defense: Int
        infix fun attacks(other: Playable): Boolean {
            other.health -= max(attack - other.defense, 1)
            return other.health <= 0
        }
    }

    data class Boss(override var health: Int, override val attack: Int, override val defense: Int) : Playable {
        companion object {
            fun from(lines: List<String>) = lines
                .map { it.replace(Regex("""[^\d]+"""), "") }
                .map(String::toInt)
                .let { Boss(it[0], it[1], it[2]) }
        }
    }

    data class Player(
        override var health: Int, val weapon: Item, val armor: Item?, val ring1: Item?, val ring2: Item?
    ) : Playable {
        private val items = arrayOf(weapon, armor, ring1, ring2)
        override val attack by lazy { items.filterNotNull().sumOf { it.damage } }

        override val defense by lazy { items.filterNotNull().sumOf { it.armor } }
        fun cost() = items.filterNotNull().sumOf { it.cost }

        infix fun isWinner(other: Boss): Boolean {
            while (true) {
                if (this.attacks(other)) return true
                if (other.attacks(this)) return false
            }
        }

        infix fun isLooser(boss: Boss) = !isWinner(boss)
    }

    data class Item(val type: ItemType, val name: String, val cost: Int, val damage: Int, val armor: Int) {

        companion object {
            private val REGEX = Regex("""(\w+|\w+ \+\d]?) +(\d+) +(\d+) +(\d+)""")
            fun from(line: String, type: ItemType) =
                REGEX.matchEntire(line)?.destructured?.let { (name, cost, damage, armor) ->
                    Item(type, name, cost.toInt(), damage.toInt(), armor.toInt())
                }
        }

        enum class ItemType { WEAPON, ARMOR, RING }
    }

    private fun parseItems(): List<Item> {
        var type: ItemType = ItemType.WEAPON
        return """
            Weapons:    Cost  Damage  Armor
            Dagger        8     4       0
            Shortsword   10     5       0
            Warhammer    25     6       0
            Longsword    40     7       0
            Greataxe     74     8       0
            
            Armor:      Cost  Damage  Armor
            Leather      13     0       1
            Chainmail    31     0       2
            Splintmail   53     0       3
            Bandedmail   75     0       4
            Platemail   102     0       5
            
            Rings:      Cost  Damage  Armor
            Damage +1    25     1       0
            Damage +2    50     2       0
            Damage +3   100     3       0
            Defense +1   20     0       1
            Defense +2   40     0       2
            Defense +3   80     0       3
            """
            .trimIndent().lines()
            .filter { it.isNotEmpty() }
            .mapNotNull { line ->
                if (line.any { it == ':' }) {
                    val section = line.substringBefore(':')
                    type = when (section) {
                        "Weapons" -> ItemType.WEAPON
                        "Armor" -> ItemType.ARMOR
                        "Rings" -> ItemType.RING
                        else -> error("Unknown section: $section")
                    }
                    null
                }
                else Item.from(line, type)
            }
    }
}