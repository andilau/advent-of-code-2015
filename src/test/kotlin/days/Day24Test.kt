package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 24")
class Day24Test {
    val input = (1..5).toList() + (7..11).toList()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `99 is the quantum entanglement of the optimal group of packages if partitioned by 3`() {
            assertThat(Day24(input).partOne()).isEqualTo(99)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `44 is the quantum entanglement of the optimal group of packages if partitioned by 4`() {
            assertThat(Day24(input).partTwo()).isEqualTo(44)
        }
    }
}