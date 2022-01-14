package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 9")
class Day9Test {
    private val input = """
            London to Dublin = 464
            London to Belfast = 518
            Dublin to Belfast = 141
            """.trimIndent().lines()

    private val possibleRoutes: Map<String, Int> = """
        Dublin -> London -> Belfast = 982
        London -> Dublin -> Belfast = 605
        London -> Belfast -> Dublin = 659
        Dublin -> Belfast -> London = 659
        Belfast -> Dublin -> London = 605
        Belfast -> London -> Dublin = 982
    """.trimIndent()
        .lines()
        .associate { line -> line.split(" = ").let { it.first() to it.last().toInt() } }

    @Test
    internal fun `Should contain all possible distances`() {
        assertThat(Day9(input).totals)
            .containsAll(possibleRoutes.values)
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        internal fun `Shortest distance should equal to 605`() {
            assertThat(Day9(input).partOne()).isEqualTo(605)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        internal fun `Longest distance should equal to 982`() {
            assertThat(Day9(input).partTwo()).isEqualTo(982)
        }

    }
}