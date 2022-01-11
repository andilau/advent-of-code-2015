package `2015`

import days.Day7
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.*

@DisplayName("Day 7")
class Day7Test {
    private val example = """
            123 -> x
            456 -> y
            x AND y -> d
            x OR y -> e
            x LSHIFT 2 -> f
            y RSHIFT 2 -> g
            NOT x -> h
            NOT y -> i
            """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        internal fun `Invalid wire configuration should throw`() {
            val invalid = "NIL x -> k"
            val exampleWithInvalid = example.toMutableList().apply { add(invalid) }
            assertThatThrownBy { Day7(exampleWithInvalid).solveSignalFor("x") }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessageContaining("Unknown configuration: NIL x")
        }

        @TestFactory
        fun `Wires should evaluate to a signal of`() =
            listOf(
                "x" to 123,
                "y" to 456,
                "d" to 72,
                "e" to 507,
                "f" to 492,
                "g" to 114,
                "h" to 65412,
                "i" to 65079
            )
                .map { (wire, signal) ->
                    DynamicTest.dynamicTest("Wire $wire should be evaluated to a signal of $signal") {
                        assertThat(Day7(example).solveSignalFor(wire)).isEqualTo(signal)

                    }

                }

        @Test
        internal fun `Should validate direct wire connection to Equals`() {
            val element = "x -> p"
            val inputWithP = example.toMutableList().apply { add(element) }
            assertThat(Day7(inputWithP).solveSignalFor("p")).isEqualTo(123)
        }

        @Test
        internal fun `Should validate bitwise right shift configuration`() {
            val element = "x RSHIFT 2 -> p"
            val inputWithP = example.toMutableList().apply { add(element) }
            assertThat(Day7(inputWithP).solveSignalFor("p")).isEqualTo(123 / 4)
        }

        @Test
        internal fun `Unknown wire configuration should throw `() {
            assertThatThrownBy { Day7(example).partOne() }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessageContaining("Unknown expression (null) for signal to wire a")
        }

        @Test
        internal fun `Wire a should simply evaluate to signal of 123`() {
            val input = "123 -> a".lines()
            assertThat(Day7(input).partOne()).isEqualTo(123)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        internal fun `Unknown wire`() {
            assertThatThrownBy { Day7(example).partTwo() }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessageContaining("Unknown expression (null) for signal to wire a")
        }

        @Test
        internal fun `Wire b should simply evaluate to signal of 123`() {
            val input = listOf("123 -> a", "a -> b")
            assertThat(Day7(input).partTwo()).isEqualTo(123)
        }
    }
}