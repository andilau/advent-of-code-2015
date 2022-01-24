package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 14")
class Day14Test {
    val example = """
    Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
    Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `After the 1000th second, both reindeer are resting, and Comet is in the lead at 1120`() {
            assertThat(Day14(example).apply { duration = 1000 }.partOne()).isEqualTo(1120)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `After the 1000th second, Dancer has accumulated 689 points`() {
            assertThat(Day14(example).apply { duration = 1000 }.partTwo()).isEqualTo(689)
        }

    }
}