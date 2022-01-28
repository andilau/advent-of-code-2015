package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 19")
class Day19Test {
    val input = """
        H => HO
        H => OH
        O => HH
        
        HOH
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Starting with HOH exactly four distinct molecules should be generated`() {
            assertThat(Day19(input).partOne()).isEqualTo(4)
        }

        @Test
        fun `Starting with HOHOHO exactly seven distinct molecules should be generated`() {
            assertThat(Day19(input.dropLast(2) + "" + "HOHOHO").partOne()).isEqualTo(7)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        private val input = listOf("e => H", "e => O") + this@Day19Test.input

        @Test
        fun `Molecule HOH should be generated in minimum of three steps`() {
            assertThat(Day19(input).partTwo()).isEqualTo(3)
        }

        @Test
        fun `Molecule HOHOHO should be generated in minimum of six steps`() {
            assertThat(Day19(input.dropLast(1) + "HOHOHO").partTwo()).isEqualTo(6)
        }

    }
}