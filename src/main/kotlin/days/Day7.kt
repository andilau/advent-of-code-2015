package days

import days.Day7.Instruction.*

@AdventOfCodePuzzle(
    name = "Some Assembly Required",
    url = "https://adventofcode.com/2015/day/7",
    date = Date(day = 7, year = 2015)
)
class Day7(instructions: List<String>) : Puzzle {
    private val wires: Map<String, Instruction> = instructions.parseInstructions()

    override fun partOne(): Int = wires.solveSignalFor("a")

    override fun partTwo(): Int =
        wires
            .toMutableMap()
            .apply { this["b"] = Literal(partOne()) }
            .solveSignalFor("a")

    internal fun solveSignalFor(variable: String) =
        wires.solveSignalFor(variable)

    private fun List<String>.parseInstructions() =
        associate {
            it.split(" -> ", limit = 2)
                .let { (instruction, identifier) ->
                    identifier to Instruction.from(instruction)
                }
        }

    private fun Map<String, Instruction>.solveSignalFor(wire: String): Int {
        val memo: MutableMap<String, Int> = mutableMapOf()

        fun Map<String, Instruction>.solve(wire: String): Int {
            return memo[wire]
                ?: wire.toIntOrNull()
                ?: when (val expression = this[wire]) {
                    is Literal -> expression.value
                    is Equals -> solve(expression.op)
                    is And -> solve(expression.op1) and solve(expression.op2)
                    is Or -> solve(expression.op1) or solve(expression.op2)
                    is Lshift -> solve(expression.op) shl expression.amount
                    is Rshift -> solve(expression.op) shr expression.amount
                    is Not -> solve(expression.op).inv() and 0xFFFF
                    else -> error("Unknown expression ($expression) for signal to wire $wire ")
                }
                    .also { memo[wire] = it }
        }
        return solve(wire)
    }

    sealed interface Instruction {
        data class Literal(val value: Int) : Instruction
        data class Equals(val op: String) : Instruction
        data class And(val op1: String, val op2: String) : Instruction
        data class Or(val op1: String, val op2: String) : Instruction
        data class Lshift(val op: String, val amount: Int) : Instruction
        data class Rshift(val op: String, val amount: Int) : Instruction
        data class Not(val op: String) : Instruction

        companion object {
            private val number = """\d+""".toRegex()
            private val identifier = """[a-z]+""".toRegex()

            fun from(line: String): Instruction =
                with(line) {
                    when {
                        matches(number) -> Literal(toInt())
                        matches(identifier) -> Equals(this)
                        contains(" AND ") -> split(" AND ").let { And(it.first(), it.last()) }
                        contains(" OR ") -> split(" OR ").let { Or(it.first(), it.last()) }
                        contains(" LSHIFT ") -> split(" LSHIFT ").let { Lshift(it.first(), it.last().toInt()) }
                        contains(" RSHIFT ") -> split(" RSHIFT ").let { Rshift(it.first(), it.last().toInt()) }
                        startsWith("NOT ") -> Not(substringAfter("NOT "))
                        else -> error("Unknown configuration: $line")
                    }
                }
        }
    }
}