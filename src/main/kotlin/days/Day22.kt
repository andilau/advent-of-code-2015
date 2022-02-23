package days

import java.util.*
import kotlin.math.max

@AdventOfCodePuzzle(
    name = "Wizard Simulator 20XX",
    url = "https://adventofcode.com/2015/day/22",
    date = Date(day = 22, year = 2015)
)
class Day22(val input: List<String>) : Puzzle {
    private val wizard = Wizard(50, 500)
    private val boss = Boss.from(input)

    override fun partOne() =
        GameState(wizard, boss).findBest()?.manaSpent
            ?: error("No best game found.")

    override fun partTwo() =
        GameState(wizard, boss).findBest(true)?.manaSpent
            ?: error("No Best game found.")

    data class GameState(
        val wizard: Wizard,
        val boss: Boss,
        val spells: MutableList<Spell> = mutableListOf(),
        val history: MutableList<Spell> = mutableListOf()
    ) {
        var manaSpent = 0

        fun findBest(hard: Boolean = false): GameState? {
            var best: GameState? = null

            val queue: Queue<GameState> = LinkedList()
            queue.offer(this)
            while (queue.isNotEmpty()) {
                val state = queue.poll()
                // already to expensive
                if (best != null && state.manaSpent > best.manaSpent) continue

                for (spell in SPELLS_AVAILABLE) {
                    // spell is not applicable
                    val next = state.copy().next(spell, hard) ?: continue

                    if (best != null && next.manaSpent > best.manaSpent) continue
                    when {
                        next.wizardWon() -> best = next
                        next.bossWon() -> continue
                        else -> queue.offer(next)
                    }
                }
            }
            return best
        }

        fun next(spell: Spell, hard: Boolean): GameState? {
            fun applyEffects() {
                spells.forEach {
                    if (it.duration > 0) it.onEffect(wizard, boss)
                    if (it.duration == 1) it.onFade(wizard, boss)
                }
                spells.forEachIndexed { ix, sp -> spells[ix] = sp.copy(duration = sp.duration - 1) }
                spells.removeIf { it.duration <= 0 }
            }

            // wizard move
            if (hard) wizard.health--
            if (wizard.dead()) return this

            applyEffects()
            if (boss.dead()) return this

            if (spell.cost > wizard.mana) return null           // not in budget
            if (spells.any { it.name == spell.name }) return null  // effect already running

            // cast spell
            with(spell) {
                onCast(wizard, boss)
                wizard.mana -= cost
                manaSpent += cost
                history += spell
                if (duration > 0) spells += spell
            }

            // boss move
            applyEffects()
            if (boss.dead()) return this
            wizard.attackedBy(boss)
            if (wizard.dead()) return this

            return this
        }

        private fun copy(): GameState {
            return GameState(
                wizard.copy(),
                boss.copy(),
                spells.toMutableList(),
                history.toMutableList()
            ).also {
                it.manaSpent = manaSpent
            }
        }

        private fun wizardWon() = boss.dead() && !wizard.dead()
        private fun bossWon() = wizard.dead() && !boss.dead()

        data class Spell(
            val name: String,
            val cost: Int,
            val duration: Int = 0,
            val onCast: (Wizard, Boss) -> Unit = { _, _ -> },
            val onEffect: (Wizard, Boss) -> Unit = { _, _ -> },
            val onFade: (Wizard, Boss) -> Unit = { _, _ -> }
        )

        companion object {
            val SPELLS_AVAILABLE = setOf(
                Spell("Magic Missile", 53, onCast = { _, b -> b.health -= 4 }),
                Spell("Drain", 73, onCast = { w, b -> w.health += 2; b.health -= 2 }),
                Spell("Shield", 113, 6, onCast = { w, _ -> w.armor += 7 }, onFade = { w, _ -> w.armor -= 7 }),
                Spell("Poison", 173, 6, onEffect = { _, b -> b.health -= 3 }),
                Spell("Recharge", 229, 5, onEffect = { w, _ -> w.mana += 101 }),
            )
        }
    }

    abstract class Player(open var health: Int) {
        fun dead() = health <= 0
    }

    data class Wizard(override var health: Int, var mana: Int, var armor: Int = 0) : Player(health) {
        fun attackedBy(boss: Boss) {
            health -= max(boss.damage - armor, 1)
        }
    }

    data class Boss(override var health: Int, val damage: Int) : Player(health) {
        companion object {
            fun from(lines: List<String>) = lines
                .map { it.replace(Regex("""[^\d]+"""), "") }
                .map(String::toInt)
                .let { Boss(it[0], it[1]) }
        }
    }
}