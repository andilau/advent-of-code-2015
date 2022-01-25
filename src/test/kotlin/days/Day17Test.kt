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
            assertThat(Day17(input).apply { storage=25 }.partOne()).isEqualTo(4)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

    }
}