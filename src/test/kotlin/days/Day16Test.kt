package days

import org.junit.jupiter.api.*

@DisplayName("Day 16")
class Day16Test {
    val example = """
        Sue 1: cars: 9, akitas: 3, goldfish: 0
        Sue 2: akitas: 9, children: 3, samoyeds: 9
        Sue 3: trees: 6, cars: 6, children: 4
        Sue 4: trees: 4, vizslas: 4, goldfish: 9
        Sue 5: akitas: 9, vizslas: 7, cars: 5
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `should not throw`() {
            assertDoesNotThrow { Day16(example) }
        }

        @Test
        fun `should  throw`() {
            assertThrows<IllegalArgumentException> { Day16(example).partOne() }
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `should  throw`() {
            assertThrows<IllegalArgumentException> { Day16(example).partTwo() }
        }
    }
}