package days

@AdventOfCodePuzzle(
    name = "Corporate Policy",
    url = "https://adventofcode.com/2015/day/11",
    date = Date(day = 11, year = 2015)
)
class Day11(private val password: String) : Puzzle {

    override fun partOne(): String = validPasswordsOf(password).first()

    override fun partTwo(): String = validPasswordsOf(password).drop(1).first()

    private fun validPasswordsOf(password: String) =
        generateSequence(password.toCharArray()) { it.nextCandidate().nextCandidateSkippingInvalidLetters() }
            .drop(1)
            .filter { it.hasThreeLettersStraight() && it.hasTwoExclusivePairs() }
            .map { it.joinToString("") }

    private fun CharArray.hasThreeLettersStraight(): Boolean {
        for (i in 0..lastIndex - 2) {
            if (this[i] + 1 == this[i + 1] && this[i] + 2 == this[i + 2]) return true
        }
        return false
    }

    private fun CharArray.hasNoLetterIOL() =
        !any { it in "iol" }

    private fun CharArray.hasTwoExclusivePairs(): Boolean {
        var index = lastIndex
        while (this[index] != this[--index]) if (index <= 2) return false
        index--
        while (this[index] != this[--index]) if (index <= 0) return false
        return true
    }

    fun nextCandidate(): String = generateSequence(password.toCharArray()) { it.nextCandidate() }
        .drop(1)
        .first()
        .joinToString("")

    internal fun hasThreeLettersStraight(): Boolean = password.toCharArray().hasThreeLettersStraight()
    internal fun hasNoLetterIOL(): Boolean = password.toCharArray().hasNoLetterIOL()
    internal fun hasTwoExclusivePairs(): Boolean = password.toCharArray().hasTwoExclusivePairs()

    companion object {
        val ALPHABET = 'a'..'z'
    }

    private fun CharArray.nextCandidate(): CharArray {
        for (index in this.lastIndex downTo 0) {
            when (this[index]) {
                ALPHABET.last -> this[index] = ALPHABET.first
                else -> {
                    this[index]++
                    break
                }
            }
        }
        return this
    }

    private fun CharArray.nextCandidateSkippingInvalidLetters(): CharArray {
        var index = 0
        while (index < lastIndex) {
            when (this[index]) {
                in "iol" -> {
                    this[index++]++
                    while (index <= lastIndex) this[index++] = ALPHABET.first
                    break
                }
            }
            index++
        }
        return this
    }
}
