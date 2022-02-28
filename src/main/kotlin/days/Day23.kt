package days

@AdventOfCodePuzzle(
    name = "Opening the Turing Lock",
    url = "https://adventofcode.com/2015/day/23",
    date = Date(day = 23, year = 2015)
)
class Day23(private val program: List<String>) : Puzzle {

    override fun partOne(): Int = Computer(program).run().b

    override fun partTwo(): Int = Computer(program).apply { a = 1 }.run().b

    class Computer(private val program: List<String>) {
        private val register = mutableMapOf('a' to 0, 'b' to 0)
        var a: Int
            get() = register.getOrDefault('a', 0)
            set(value) = register.set('a', value)
        var b: Int
            get() = register.getOrDefault('b', 0)
            set(value) = register.set('b', value)

        fun run(): Computer {
            var ip = 0
            while (ip in program.indices) {
                val instruction = program[ip]
                if (instruction.length < 5) break
                val command = instruction.substring(0, 3)
                val symbol = instruction[4]

                ip += when (command) {
                    "hlf" -> register.computeIfPresent(symbol) { _, i -> i / 2 }.let { 1 }
                    "tpl" -> register.computeIfPresent(symbol) { _, i -> i * 3 }.let { 1 }
                    "inc" -> register.computeIfPresent(symbol) { _, i -> i + 1 }.let { 1 }
                    "jmp" -> instruction.jump()
                    "jie" -> if (register.getOrDefault(symbol, -1) % 2 == 0) instruction.jump() else 1
                    "jio" -> if (register.getOrDefault(symbol, 0) == 1) instruction.jump() else 1
                    else -> error("Unknown instruction: $instruction")
                }
            }
            return this
        }

        private fun String.jump() = substringAfterLast(' ').toInt()
    }

    internal fun solveForRegisterA() = Computer(program).run().a

    internal fun solveForRegisterB() = Computer(program).run().b
}