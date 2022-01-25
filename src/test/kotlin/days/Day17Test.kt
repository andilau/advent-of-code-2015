package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 17")
class Day17Test {
    val input = "20, 15, 10, 5, 5".split(", ")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun test() {
            assertThat(Day17(input).apply { storage = 25 }.partOne()).isEqualTo(4)
        }

        @Test
        fun testFill() {
            val toList = input.map(String::toInt).combinationsSum(25).toList()
            toList.forEach { println(it) }
            assertThat(toList)
                .hasSize(4)
                .containsExactlyInAnyOrder(listOf(20, 5), listOf(20, 5), listOf(15, 10), listOf(15, 5, 5))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `How many different ways to fill with  minimum number of containers`() {
            assertThat(Day17(input).apply { storage = 25 }.partTwo()).isEqualTo(3)
        }

    }
}