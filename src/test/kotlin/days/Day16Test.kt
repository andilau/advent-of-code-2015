package days

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.*
import util.InputReader

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
        fun `Example data is valid but shortend so should parse`() {
            assertDoesNotThrow { Day16(example) }
        }

        @Test
        fun `Example data is not sufficient to match signature and should throw`() {
            assertThrows<NoSuchElementException> { Day16(example).partOne() }
        }

        @Test
        fun `Full data is sufficient to match exactly one description and should equal id 373`() {
            Assertions.assertThat(Day16(InputReader.getInputAsList(16)).partOne()).isEqualTo(373)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Example data is not sufficient to match signature and should throw`() {
            assertThrows<NoSuchElementException> { Day16(example).partTwo() }
        }

        @Test
        fun `Full data is sufficient to match exactly one description and should equal id 260`() {
            Assertions.assertThat(Day16(InputReader.getInputAsList(16)).partTwo()).isEqualTo(260)
        }
    }
}