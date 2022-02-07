package days

import days.Day21.Item.*
import java.lang.Integer.max

@AdventOfCodePuzzle(
    name = "RPG Simulator 20XX",
    url = "https://adventofcode.com/2015/day/21",
    date = Date(day = 21, year = 2015)
)
class Day21(val input: List<String>) : Puzzle {
    private val items = parseItems()
    private val boss = input.map { it.replace(Regex("""[^\d]+"""), "") }
        .map(String::toInt)
        .let { Boss(it[0], it[1], it[2]) }

    override fun partOne(): Int = items
        .equipPlayer()
        .filter { it winsAgainst boss.copy() }
        .minOf { it.cost() }

    override fun partTwo(): Int = items
        .equipPlayer()
        .filter { it loosesAgainst boss.copy() }
        .maxOf { it.cost() }

    private fun Collection<Item>.equipPlayer() = sequence {
        for (weapon in filterIsInstance<Weapon>())
            for (armor in filterIsInstance<Armor>() + null)
                for (ring1 in filterIsInstance<Ring>() + null)
                    for (ring2 in filterIsInstance<Ring>() - ring1 + null) {
                        yield(Player(100, weapon, armor, ring1, ring2))
                    }
    }

    private infix fun Player.winsAgainst(other: Boss): Boolean {
        while (true) {
            if (this.attacks(other)) return true
            if (other.attacks(this)) return false
        }
    }

    private infix fun Player.loosesAgainst(boss: Boss) = !winsAgainst(boss)

    sealed interface Playable {
        var health: Int
        fun attackPoints(): Int
        fun defensePoints(): Int

        infix fun attacks(other: Playable): Boolean {
            other.health -= max(attackPoints() - other.defensePoints(), 1)
            return other.health <= 0
        }
    }

    data class Boss(override var health: Int, val attack: Int, val defense: Int) : Playable {
        override fun attackPoints(): Int = attack
        override fun defensePoints(): Int = defense
    }

    data class Player(
        override var health: Int, val weapon: Weapon, val armor: Armor?, val ring1: Ring?, val ring2: Ring?
    ) : Playable {
        private val items = arrayOf(weapon, armor, ring1, ring2)

        override fun attackPoints() = items.filterNotNull().sumOf { it.damage }
        override fun defensePoints() = items.filterNotNull().sumOf { it.armor }

        fun cost() = items.filterNotNull().sumOf { it.cost }
    }

    sealed interface Item {
        val name: String
        val cost: Int
        val damage: Int
        val armor: Int

        data class Weapon(
            override val name: String,
            override val cost: Int,
            override val damage: Int,
            override val armor: Int
        ) : Item

        data class Armor(
            override val name: String,
            override val cost: Int,
            override val damage: Int,
            override val armor: Int
        ) : Item

        data class Ring(
            override val name: String,
            override val cost: Int,
            override val damage: Int,
            override val armor: Int
        ) : Item

        companion object {
            private val REGEX = Regex("""(\w+|\w+ \+\d]?) +(\d+) +(\d+) +(\d+)""")

            fun weapon(line: String) = REGEX.matchEntire(line)?.destructured?.let { (name, cost, damage, armor) ->
                Weapon(name, cost.toInt(), damage.toInt(), armor.toInt())
            }

            fun armor(line: String) = REGEX.matchEntire(line)?.destructured?.let { (name, cost, damage, armor) ->
                Armor(name, cost.toInt(), damage.toInt(), armor.toInt())
            }

            fun ring(line: String) = REGEX.matchEntire(line)?.destructured?.let { (name, cost, damage, armor) ->
                Ring(name, cost.toInt(), damage.toInt(), armor.toInt())
            }
        }
    }

    enum class ItemType { WEAPON, ARMOR, RING }

    private fun parseItems(): Set<Item> {
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
            """.trimIndent().lines().mapNotNull {
            if (it.isEmpty()) {
                type = when (type) {
                    ItemType.WEAPON -> ItemType.ARMOR
                    ItemType.ARMOR -> ItemType.RING
                    ItemType.RING -> error("Puh ")
                }
                null
            }
            else
                when (type) {
                    ItemType.WEAPON -> Item.weapon(it)
                    ItemType.ARMOR -> Item.armor(it)
                    ItemType.RING -> Item.ring(it)
                }
        }.toSet()
    }
}