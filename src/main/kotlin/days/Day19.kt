package days

import java.util.*

@AdventOfCodePuzzle(
    name = "Medicine for Rudolph",
    url = "https://adventofcode.com/2015/day/19",
    date = Date(day = 19, year = 2015)
)
class Day19(input: List<String>) : Puzzle {
    private val replacements = parseReplacements(input)
    private val medicine = parseMedicin(input)

    override fun partOne(): Int = medicine.moleculesFusion().distinct().count()

    override fun partTwo(): Int {
        var minimumSteps = Int.MAX_VALUE

        val queue = PriorityQueue(compareBy<IndexedValue<Molecule>> { it.value.value.length })
        queue.add(IndexedValue(0, medicine))
        val seen = mutableSetOf<Molecule>()
        while (queue.isNotEmpty()) {
            val poll = queue.poll()
            val (steps, molecule) = poll

            if (molecule in seen) continue
            seen += molecule

            if (steps > minimumSteps) continue

            if (molecule == Molecule.SINGLE_ELECTRON) {
                minimumSteps = steps
                break
            }

            molecule.moleculesFission().forEach {
                queue += IndexedValue(steps + 1, it)
            }
        }
        return minimumSteps
    }

    private fun parseReplacements(input: List<String>) = input
        .takeWhile(String::isNotEmpty)
        .map { line -> line.split(" => ").let { it.first() to it.last() } }
        .asSequence()

    private fun parseMedicin(input: List<String>) = input
        .dropWhile(String::isNotEmpty)
        .drop(1)
        .first()
        .let { Molecule(it) }

    private fun Molecule.moleculesFusion(): Sequence<Molecule> = replacements
        .flatMap { (from, to) -> this.replace(from, to) }

    private fun Molecule.moleculesFission(): Sequence<Molecule> = replacements
        .flatMap { (from, to) -> this.replace(to, from) }

    @JvmInline
    value class Molecule(val value: String) {
        companion object {
            val SINGLE_ELECTRON = Molecule("e")
        }
    }

    private fun Molecule.replace(from: String, to: String) = sequence {
        var index = 0
        val molecule = this@replace
        while (true) {
            index = molecule.value.indexOf(from, index)
            if (index == -1) return@sequence
            yield(
                Molecule(
                    molecule.value.substring(0, index) + to + molecule.value.substring(
                        index + from.length,
                        value.length
                    )
                )
            )
            index++
        }
    }
}