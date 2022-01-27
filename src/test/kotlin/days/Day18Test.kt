package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 18")
class Day18Test {
    val input = """
        .#.#.#
        ...##.
        #....#
        ..#...
        #.#..#
        ####..
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Four lights should be on after 4 steps`() {
            assertThat(Day18(input).apply { steps = 4 }.partOne()).isEqualTo(4)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `17 lights should be on after 5 steps`() {
            assertThat(Day18(input).apply { steps = 5 }.partTwo()).isEqualTo(17)
        }
    }
}