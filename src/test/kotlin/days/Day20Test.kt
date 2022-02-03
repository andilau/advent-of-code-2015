package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 20")
class Day20Test {
    val minPresents = listOf(33_100_000)

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        private val expected = listOf(10, 30, 40, 70, 60, 120, 80, 150, 130)

        @Test
        fun `Presents delivered to first 9 houses`() {
            val presents = Day20(minPresents).presentsDeliveredToInfiniteHouses().take(expected.size).toList()
            assertThat(presents).isEqualTo(expected)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        private val expected = listOf(11, 33, 44, 77, 66, 132, 88, 165, 143)

        @Test
        fun `Presents delivered to first 9 houses`() {
            val presents = Day20(minPresents).presentsDeliveredTo50AndExtra().take(expected.size).toList()
            assertThat(presents).isEqualTo(expected)
        }
    }

    /*
    1   -> 1 + (1)          = 1
    2   -> 1 + 2            = 3
    3   -> 1 + 3            = 4
    4   -> 1 + 2 + 4        = 7 (2 + (4/2))
    5   -> 1 + 5            = 6
    6   -> 1 + 2 + 3 + 6    = 12 (2 + 6/2) (3 + 6/3)
    7   -> 1 + 7            = 8
    8   -> 1 + 2 + 4 + 8    = 15 (2 + 8/2)
    9   -> 1 + 3.+ 9        = 13 (3 + 9/3)
    ...
    16  -> 1 + 16           = 31   (2 + 16/2 + 4 + 16/4) -> 2+8 + 4+4 - 1*4
    */
}